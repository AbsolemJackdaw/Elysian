package net.darkhax.elysian.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.entity.effects.EntityElysianPowerEffect;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElysianLeaves extends BlockLeaves{

	public static final String[] names = new String[] {"elysian"};

	public BlockElysianLeaves() {
		super();
		setCreativeTab(Elysian.tabElysian);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_,
			int p_149720_3_, int p_149720_4_) {
		return 0x21f1f5;
	}
	
	@Override
	public IIcon getIcon(int var1, int var2) {
		return blockIcon;
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
		return null;
	}
	
	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune){
		return null;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		super.registerBlockIcons(reg);
		blockIcon = reg.registerIcon("leaves_oak");
	}

	@Override
	public String[] func_150125_e() {
		return names;
	}
	
	@Override
	public boolean isNormalCube() {
		return false;
	}
	
//	@Override
//	public int getRenderType() {
//		return RenderingRegistry.getNextAvailableRenderId();
//	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		super.randomDisplayTick(world, x, y, z, rand);

		for (int l = 0; l < 2; ++l)
		{
			double d1 = y + 1.0f;
			double d2 = 0.0D;
			double d3 = 0.0D;
			double d4 = 0.0D;
			int i1 = (rand.nextInt(2) * 2) - 1;
			int j1 = (rand.nextInt(2) * 2) - 1;
			d2 = (rand.nextFloat() - 0.5D) * 0.125D;
			d3 = (rand.nextFloat() - 0.5D) * 0.125D;
			d4 = (rand.nextFloat() - 0.5D) * 0.125D;
			d4 = rand.nextFloat() * 1.0F * j1;
			d2 = rand.nextFloat() * 1.0F * i1;
			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityElysianPowerEffect(world, x+rand.nextFloat(), d1, z+rand.nextFloat(), d2, d3, d4));
		}
	}

}
