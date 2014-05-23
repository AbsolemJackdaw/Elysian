package net.darkhax.elysian.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.darkhax.elysian.items.ElysianItems;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockEssence extends BlockElysian{

	public BlockEssence() {
		super(Material.rock, "essenceBlock", "pickaxe", 1);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister ireg) {
		super.registerBlockIcons(ireg);
		blockIcon = ireg.registerIcon("elysian:eEssence");
	}
	
//	@Override
//	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float f1, int par1) {
//	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z,
			int metadata, int fortune) {

		ArrayList<ItemStack> l = new ArrayList<ItemStack>();
		
		int rand = new Random().nextInt(5) + 1;
		ItemStack is = new ItemStack(ElysianItems.runicComponent, rand, 0);
		
		l.add(is);
		
		return l ;
	}
	
	
//	@Override
//	protected void dropBlockAsItem(World world, int x,
//			int y, int z, ItemStack stack) {
//		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")){
//			
//			int rand = new Random().nextInt(5) + 1;
//			ItemStack is = new ItemStack(ElysianItems.runicComponent, rand, 0);
//			ItemStack is = new ItemStack(ElysianItems.runicComponent, rand, 0);
//			EntityItem item = new EntityItem(world, x, y, z, is);
//        }
//	}
	
	
}
