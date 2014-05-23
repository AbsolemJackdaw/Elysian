package net.darkhax.elysian.data;

import java.util.ArrayList;

import net.darkhax.elysian.packet.ElysianPackets;
import net.darkhax.elysian.proxy.CommonProxy;
import net.darkhax.elysian.util.DataUtils;
import net.darkhax.elysian.util.ManaType;
import net.darkhax.elysian.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerElysianProperties implements IExtendedEntityProperties {

    public static final String CARD_PROPS = "CardData";

    private final EntityPlayer player;
    private ArrayList<Integer> cardsCollectedByPlayer;
    private int[] cardsSelectedInBook;

    /** array that keeps amount of mana with indexes :  0:fire; 1:water, 2:earth, 3:air, 4:light, 5:darkness, 6:life */
    private int[] playerMana = new int[7];
    /** array that keeps cap of mana with indexes : 0:fire; 1:water, 2:earth, 3:air, 4:light, 5:darkness, 6:life*/
    private int[] playerManaCaps = new int[7];

    public PlayerElysianProperties(EntityPlayer player) {

        this.player = player;
        this.cardsCollectedByPlayer = new ArrayList();
        cardsSelectedInBook = new int[Reference.SELECTABLECARDS];
    }
    @Deprecated /**there already existed a method for this*/
    public ArrayList<Integer> getCollectedCards() {
        return this.getCollectedCardList();
    }

    @Deprecated /**there already existed a method for this*/
    public void addCardToCollection(int i) {
    	this.unlockCard(i);
    }

    public int[] getCardsHighlightedInBook() {

        return cardsSelectedInBook;
    }

    /*
     * method gets called first via server packet. then send a client packet to
     * be called client side data gets set in GuiTarrotBook.
     */
    public void setCardSelectedInBook(int index, int card) {

        cardsSelectedInBook[index] = card;

        if (!player.worldObj.isRemote) {
            ElysianPackets.INSTANCE.syncGuiDataToClient(index, card, (EntityPlayerMP) player);
        }
    }

    /**
     * Makes code more compact. gets the savekey for the player for saving to
     * proxy data
     */
    private static String getSaveKey(EntityPlayer player) {

        return player.getCommandSenderName() + ":" + CARD_PROPS;
    }

    /**
     * Loading and saving data to server proxy. This prevents the player losing
     * all data on death
     */
    public static void loadProxyData(EntityPlayer player) {

        PlayerElysianProperties playerData = PlayerElysianProperties.get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));

        if (savedData != null) {
            playerData.loadNBTData(savedData);
        }
    }

    public static void saveProxyData(EntityPlayer player) {

        PlayerElysianProperties playerData = PlayerElysianProperties.get(player);
        NBTTagCompound savedData = new NBTTagCompound();
        playerData.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSaveKey(player), savedData);
    }

    public static final void register(EntityPlayer player) {

        player.registerExtendedProperties(CARD_PROPS, new PlayerElysianProperties(player));
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

        NBTTagCompound properties = new NBTTagCompound();
        Integer[] cards = this.cardsCollectedByPlayer.toArray(new Integer[21]);
        // Null pointer issue
        int[] cardss = DataUtils.integerToInt(cards);
        properties.setIntArray("Cards", cardss);
        properties.setIntArray("BookCards", cardsSelectedInBook);
        properties.setIntArray("ElysianMana", playerMana);
        compound.setTag(CARD_PROPS, properties);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

        NBTTagCompound properties = (NBTTagCompound) compound.getTag(CARD_PROPS);
        Integer[] cards = DataUtils.intToInteger(properties.getIntArray("Cards"));
        this.cardsCollectedByPlayer = DataUtils.intToArrayList(cards);
        this.cardsSelectedInBook = properties.getIntArray("BookCards");
        this.playerMana = properties.getIntArray("ElysianMana");
    }

    public static final PlayerElysianProperties get(EntityPlayer p) {

        return (PlayerElysianProperties) p.getExtendedProperties(CARD_PROPS);
    }

    @Override
    public void init(Entity entity, World world) {

    }

    /**
     * Grabs the list of cards.
     *
     * @return ArrayList<Integer>: The card list.
     */
    public ArrayList<Integer> getCollectedCardList() {

        return this.cardsCollectedByPlayer;
    }

    /**
     * Checks to see if the player already has this card unlocked.
     *
     * @param cardID
     *            : The id of the card being checked.
     * @return boolean: Does the player have this card unlocked?
     */
    public boolean hasCollectedCard(int cardID) {

        if (this.cardsCollectedByPlayer.contains(cardID)) {

            return true;
        }

        return false;
    }

    /**
     * Unlocks a new card for the player.
     *
     * @param cardID
     *            : ID number for the card being unlocked.
     */
    public void unlockCard(int cardID) {

        this.cardsCollectedByPlayer.add(cardID);
    }

    /**
     * Removes a card from the players list. Not tested.
     *
     * @param cardID
     *            : ID number for the card being removed.
     */
    public void removeCard(int cardID) {

        this.cardsCollectedByPlayer.remove(cardID);
    }

    /* ================================================= */
    /* ====================MANA====================== */
    /* ================================================= */

    public void addMana(ManaType num, int amount) {

        switch (num) {
            case FIRE:
                playerMana[0] += amount;
                break;
            case WATER:
                playerMana[1] += amount;
                break;
            case AIR:
                playerMana[2] += amount;
                break;
            case EARTH:
                playerMana[3] += amount;
                break;
            case LIGHT:
                playerMana[4] += amount;
                break;
            case DARKNESS:
                playerMana[5] += amount;
                break;
            case LIFE:
                playerMana[6] += amount;
                break;
            default:
                System.out.println("This mana type is not supported in net.darkhax.elysian.data.PlayerElysianProperties.addMana");
                break;
        }

    }

    public void decreaseMana(ManaType num, int amount) {

        switch (num) {
            case FIRE:
                playerMana[0] -= amount;
                break;
            case WATER:
                playerMana[1] -= amount;
                break;
            case AIR:
                playerMana[2] -= amount;
                break;
            case EARTH:
                playerMana[3] -= amount;
                break;
            case LIGHT:
                playerMana[4] -= amount;
                break;
            case DARKNESS:
                playerMana[5] -= amount;
                break;
            case LIFE:
                playerMana[6] -= amount;
                break;
            default:
                System.out.println("This mana type is not supported in net.darkhax.elysian.data.PlayerElysianProperties.decreaseMana");
                break;
        }
    }

    public void setMana(ManaType num, int amount) {

        switch (num) {
            case FIRE:
                playerMana[0] = amount;
                break;
            case WATER:
                playerMana[1] = amount;
                break;
            case AIR:
                playerMana[2] = amount;
                break;
            case EARTH:
                playerMana[3] = amount;
                break;
            case LIGHT:
                playerMana[4] = amount;
                break;
            case DARKNESS:
                playerMana[5] = amount;
                break;
            case LIFE:
                playerMana[6] = amount;
                break;
            default:
                System.out.println("This mana type is not supported in net.darkhax.elysian.data.PlayerElysianProperties.setMana");
                break;
        }
    }

    public int getMana(ManaType num) {

        switch (num) {
            case FIRE:
                return playerMana[0];
            case WATER:
                return playerMana[1];
            case AIR:
                return playerMana[2];
            case EARTH:
                return playerMana[3];
            case LIGHT:
                return playerMana[4];
            case DARKNESS:
                return playerMana[5];
            case LIFE:
                return playerMana[6];
            default:
                System.out.println("This mana type is not supported in net.darkhax.elysian.data.PlayerElysianProperties.getMana");
                break;
        }
        return -1;
    }

    /* ================================================= */
    /* ====================MANACAP====================== */
    /* ================================================= */

    public void increaseCapForMana(ManaType num, int amount) {

        switch (num) {
            case FIRE:
                playerManaCaps[0] += amount;
                break;
            case WATER:
                playerManaCaps[1] += amount;
                break;
            case AIR:
                playerManaCaps[2] += amount;
                break;
            case EARTH:
                playerManaCaps[3] += amount;
                break;
            case LIGHT:
                playerManaCaps[4] += amount;
                break;
            case DARKNESS:
                playerManaCaps[5] += amount;
                break;
            case LIFE:
                playerManaCaps[6] += amount;
                break;
            default:
                System.out.println("This mana type is not supported in net.darkhax.elysian.data.PlayerElysianProperties.increaseCapForMana");
                break;
        }
    }

    public void decreaseCapForMana(ManaType num, int amount) {

        switch (num) {
            case FIRE:
                playerManaCaps[0] -= amount;
                break;
            case WATER:
                playerManaCaps[1] -= amount;
                break;
            case AIR:
                playerManaCaps[2] -= amount;
                break;
            case EARTH:
                playerManaCaps[3] -= amount;
                break;
            case LIGHT:
                playerManaCaps[4] -= amount;
                break;
            case DARKNESS:
                playerManaCaps[5] -= amount;
                break;
            case LIFE:
                playerManaCaps[6] -= amount;
                break;
            default:
                System.out.println("This mana type is not supported in net.darkhax.elysian.data.PlayerElysianProperties.decreaseCapForMana");
                break;
        }
    }

    public int getCapForMana(ManaType num) {

        switch (num) {
            case FIRE:
                return playerManaCaps[0];
            case WATER:
                return playerManaCaps[1];
            case AIR:
                return playerManaCaps[2];
            case EARTH:
                return playerManaCaps[3];
            case LIGHT:
                return playerManaCaps[4];
            case DARKNESS:
                return playerManaCaps[5];
            case LIFE:
                return playerManaCaps[6];
            default:
                System.out.println("This mana type is not supported in net.darkhax.elysian.data.PlayerElysianProperties.getCapForMana");
                break;
        }
        return -1;
    }
}
