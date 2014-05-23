package net.darkhax.elysian.items;

import java.util.List;

import net.darkhax.elysian.data.PlayerElysianProperties;
import net.darkhax.elysian.util.StackUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTarotCard extends ItemElysian {

    private static String[] cardNames = { "Blank", "Fool", "Magician", "High Priestess", "Empress", "Emperor", "Heirophant", "Lovers", "Chariot", "Justic", "Hermit", "Wheel of Fortune", "Strength", "Hanged Mane", "Dead", "Temperance", "Devil", "Tower", "Star", "Moon", "Sun", "Judgement", "World" };

    public static IIcon[] iconArray;

    public ItemTarotCard(String itemName) {

        super(itemName);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean bool) {

        StackUtils.prepareStackTag(stack);
        NBTTagCompound stackTag = stack.stackTagCompound;

        if (stackTag.hasKey("cardID")) {

            info.add(cardNames[stackTag.getInteger("cardID")]);
        }

        else
            info.add(cardNames[0]);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        int cardID = getCardID(stack);

        PlayerElysianProperties prop = PlayerElysianProperties.get(player);

        if (!prop.hasCollectedCard(cardID)) {

            prop.unlockCard(cardID);
            // System.out.println("The card is " + getNameFromID(cardID));

            if (!prop.getCollectedCardList().isEmpty()) {

                for (int i = 0; i > prop.getCollectedCardList().size(); i++) {

                    System.out.println(getNameFromID(prop.getCollectedCardList().get(i)));
                }
            }
        }

        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        iconArray = new IIcon[cardNames.length];

        for (int i = 0; i < iconArray.length; ++i) {

            iconArray[i] = register.registerIcon("elysian:" + "tarot/tarot_" + i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack) {

        if (stack != null) {

            StackUtils.prepareStackTag(stack);
            NBTTagCompound stackTag = stack.stackTagCompound;

            if (stackTag.hasKey("cardID")) {

                this.itemIcon = iconArray[stackTag.getInteger("cardID")];
                return iconArray[stackTag.getInteger("cardID")];
            }
        }

        return iconArray[0];
    }

    /**
     * Grabs an instance of the name list.
     *
     * @return String[]: The name list.
     */
    public static String[] getNameList() {

        return cardNames;
    }

    /**
     * Gives a card name based on an id number. See cardNames for reference.
     * @param cardNumber: The card number ID.
     * @return String: The card name based on id number.
     */
    public static String getNameFromID(int cardNumber) {

        return cardNames[cardNumber];
    }

    /**
     * Retrieves a cardID from a card name.
     *
     * @param name: The name of the card.
     * @return int: The id of the card. 0 if name is not right.
     */
    public static int getIDFromName(String name) {

        for (int i = 0; i > cardNames.length; i++) {

            if (cardNames[i].equalsIgnoreCase(name)) {

                return i;
            }
        }

        return 0;
    }

    /**
     * Grabs the card id for the card.
     * @param stack: The stack of the card being checked.
     * @return int: The card ID.
     */
    public static int getCardID(ItemStack stack) {

        StackUtils.prepareStackTag(stack);
        return stack.stackTagCompound.getInteger("cardID");
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {

        for (int i = 0; i < ItemTarotCard.getNameList().length; i++) {

            ItemStack stack = new ItemStack(ElysianItems.tarotCard);
            StackUtils.prepareStackNBT(stack, "cardID", i);
            list.add(stack);
        }
    }
}