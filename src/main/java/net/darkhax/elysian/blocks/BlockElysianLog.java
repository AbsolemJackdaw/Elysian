package net.darkhax.elysian.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockElysianLog extends BlockElysian {

	public static final String[] treeTypes = new String[] { "fire", "water", "earth", "wind", "light", "darkness", "duality" };

	@SideOnly(Side.CLIENT)
	private IIcon[] treeIcon;

	@SideOnly(Side.CLIENT)
	private IIcon treeTop;

	public BlockElysianLog(Material material, String name) {

		super(material, name, "axe", 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {

		if (side <= 1) {

			return this.treeTop;
		}

		if (meta < 0 || meta >= this.treeIcon.length) {

			meta = 0;
		}

		return this.treeIcon[meta];
	}

	@Override
	public int damageDropped(int dropMeta) {

		return dropMeta;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {

		for(int i = 0; i < 7; i ++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir) {

		this.treeTop = ir.registerIcon("elysian:tree/tree_top");
		this.treeIcon = new IIcon[BlockElysianLog.treeTypes.length];

		for (int i = 0; i < this.treeIcon.length; ++i) {

			this.treeIcon[i] = ir.registerIcon("elysian:tree/tree_side_" + BlockElysianLog.treeTypes[i]);
		}
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}
}