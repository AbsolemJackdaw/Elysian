package net.darkhax.elysian.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

//TODO make more biomes
public class ElysianBiomes {

    public static BiomeGenBase biomeElysian;

    public ElysianBiomes() {

        addBiomes();
    }

    private void addBiomes() {

        biomeElysian = new BiomeGenElysianPlains(41);
    }
}