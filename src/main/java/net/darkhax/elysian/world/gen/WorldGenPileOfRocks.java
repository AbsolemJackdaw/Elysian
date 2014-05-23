package net.darkhax.elysian.world.gen;

import java.util.Random;

import net.darkhax.elysian.blocks.ElysianBlocks;
import net.darkhax.elysian.blocks.containers.TileEntityPileOfRocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPileOfRocks extends WorldGenerator {

	public WorldGenPileOfRocks(boolean par1) {
		super(par1);
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4,
			int par5) {

		int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
		int j1 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
		int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
		if(par1World.isAirBlock(i1, j1, k1) && par1World.getBlock(i1, j1 - 1, k1).isNormalCube()){
			par1World.setBlock(i1, j1, k1, ElysianBlocks.pileOfRocks, 0, 2);
			par1World.setTileEntity(i1, j1, k1, new TileEntityPileOfRocks());

		}
		return false;
	}

}
