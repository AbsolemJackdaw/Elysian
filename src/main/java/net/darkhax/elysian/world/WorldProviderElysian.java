package net.darkhax.elysian.world;

import net.darkhax.elysian.util.Reference;
import net.darkhax.elysian.world.biome.WorldChunkManagerElysian;
import net.darkhax.elysian.world.gen.ChunkProviderElysian;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

public class WorldProviderElysian extends WorldProvider {

    public void registerWorldChunkManager() {

        this.worldChunkMgr = new WorldChunkManagerElysian(worldObj);
        this.hasNoSky = true;
    }

    @Override
    public float getCloudHeight() {
    	return 80F;
    }
    
    @Override
    public String getDimensionName() {
        return "Elysian";
    }

    //@Override
    public static WorldProvider getProviderForDimension(int id) {

        return DimensionManager.createProviderFor(Reference.DIMID);
    }

    @Override
    public String getWelcomeMessage() {

        return "Pass through the gate!";
    }

    @Override
    public IChunkProvider createChunkGenerator() {

        return new ChunkProviderElysian(worldObj, worldObj.getSeed());
    }
    
    @Override
    public boolean isSurfaceWorld() {
    	return false;
    }

    @Override
    public boolean canRespawnHere() {

        return true;
    }
}