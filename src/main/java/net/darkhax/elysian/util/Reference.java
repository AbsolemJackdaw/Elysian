package net.darkhax.elysian.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reference {

    public static final String MOD_ID = "Elysian";
    public static final String MOD_NAME = "Elysian";
    public static final String VERSION_NUMBER = "0.0.0";
    public static final String CLIENT_PROXY_CLASS = "net.darkhax.elysian.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "net.darkhax.elysian.proxy.CommonProxy";
    public static final String TEXTURE_DOMAIN = "elysian:";

    public static final int SELECTABLECARDS = 7;

    public static final int OPENGUI_CHISELBLOCK = 0;
    public static final int OPENGUI_TARROTBOOK = 1;

    public static final Logger LOGGER = LogManager.getLogger("Elysian");

    public static final String NBT_CARD = "cardID";
    public static final String NBT_PATTERN = "patternID";
    public static final String NBT_CARVEDBLOCK_BLOCK = "carvedCameBlock";

    public static final int FIRE = 0xff4e00;
    public static final int AIR = 0x586c67;
    public static final int DARKNESS = 0x521177;
    public static final int EARTH = 0x5c3716;
    public static final int LIFE = 0x45a42c;
    public static final int LIGHT = 0xfffc00;
    public static final int WATER = 0x0a6acc;

    //TODO move to config file rather than reference
    public static final int DIMID = 2;
}