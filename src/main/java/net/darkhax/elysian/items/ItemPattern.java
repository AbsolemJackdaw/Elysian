package net.darkhax.elysian.items;

import java.util.List;

import net.darkhax.elysian.util.IPattern;
import net.darkhax.elysian.util.Reference;
import net.darkhax.elysian.util.StackUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPattern extends ItemElysian implements IPattern {

    public static IIcon[] iconArray;

    public ItemPattern(String itemName) {

        super(itemName);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {

        iconArray = new IIcon[17];

        for (int i = 0; i < iconArray.length; ++i) {

            iconArray[i] = register.registerIcon("elysian:" + "pattern/pattern_" + i);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconIndex(ItemStack stack) {

        if (stack != null) {

            StackUtils.prepareStackTag(stack);
            NBTTagCompound stackTag = stack.stackTagCompound;

            if (stackTag.hasKey(Reference.NBT_PATTERN)) {

                this.itemIcon = iconArray[stackTag.getInteger(Reference.NBT_PATTERN)];
                return iconArray[stackTag.getInteger(Reference.NBT_PATTERN)];
            }
        }

        return iconArray[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean bool) {

        StackUtils.prepareStackTag(stack);
        NBTTagCompound stackTag = stack.stackTagCompound;

        if (stackTag.hasKey(Reference.NBT_PATTERN)) {

            info.add("Value: " + stackTag.getInteger(Reference.NBT_PATTERN));
        }

        else
            info.add("Value: Nothing");
    }

    /**
     * Allows access to the item icons.
     *
     * @param patternID
     * @return
     */
    public IIcon getIconFromID(int patternID) {

        return iconArray[patternID];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {

        for (int i = 0; i < 17; i++) {

            ItemStack stack = new ItemStack(ElysianItems.pattern);
            StackUtils.prepareStackNBT(stack, Reference.NBT_PATTERN, i);
            list.add(stack);
        }
    }
}