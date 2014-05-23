package net.darkhax.elysian.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int dimensionID;

    public static void createConfig(File configFile) {

        Configuration config = new Configuration(configFile);
        config.load();

        dimensionID = config.get(Configuration.CATEGORY_GENERAL, "The Dimension ID", 7).getInt();
        config.save();
    }
}
