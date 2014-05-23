package net.darkhax.elysian.items;

import java.util.Set;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.util.IChargedItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.google.common.collect.Sets;

public class ItemChargedTool extends ItemTool implements IChargedItem{

	private static final Set axeSet = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});
	private static final Set spadeSet = Sets.newHashSet(new Block[] {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});
	private static final Set pickaxeSet = Sets.newHashSet(new Block[] {Blocks.obsidian, Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

	private IIcon glow;

	protected ItemChargedTool(float i, ToolMaterial mat, String s) {
		super(i, mat,
				s.equals("axe") ? axeSet : 
					s.equals("pickaxe") ? pickaxeSet : 
						s.equals("spade") ? spadeSet : null);

		this.setCreativeTab(Elysian.tabElysian);
		setMaxDamage(mat.getMaxUses());
		setMaxStackSize(1);
		setHarvestLevel(s, mat.getHarvestLevel());
	}

	@Override
	public boolean isFull3D() {
		return true;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return 16777215;
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		
		return toolMaterial.getEfficiencyOnProperMaterial();
	}
	
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		String s = getUnlocalizedName().replace("item.", "");
		itemIcon = par1IconRegister.registerIcon("elysian:tools/"+ s);
		glow = par1IconRegister.registerIcon("elysian:tools/"+ s + "_glow");
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return pass > 0 ? itemIcon : glow;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {

		if(par1ItemStack.getItemDamageForDisplay() >= getMaxDamage() - 2){
			if(par3Entity instanceof EntityPlayer){

				ItemStack depletedTool = null;

				if(par1ItemStack.getUnlocalizedName().equals("item.runeAxeCharged"))
					depletedTool = new ItemStack(ElysianItems.runeAxeDepleted);
				if(par1ItemStack.getUnlocalizedName().equals("runePickaxeCharged"))
					depletedTool = new ItemStack(ElysianItems.runeAxeDepleted);
				if(par1ItemStack.getUnlocalizedName().equals("runeSpadeCharged"))
					depletedTool = new ItemStack(ElysianItems.runeAxeDepleted);

				par3Entity.setCurrentItemOrArmor(0, depletedTool);
			}
		}
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 == 0 ? this.glow : super.getIconFromDamageForRenderPass(par1, par2);
	}

	@Override
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}
}
