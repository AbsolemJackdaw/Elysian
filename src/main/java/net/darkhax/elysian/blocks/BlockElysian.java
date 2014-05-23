package net.darkhax.elysian.blocks;

import net.darkhax.elysian.Elysian;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockElysian extends Block {

	public BlockElysian(Material material, String name, String tool, int level) {

		super(material);
		this.setCreativeTab(Elysian.tabElysian);
		this.setBlockName("elysian." + name);
		setHarvestLevel(tool, level);
	}

	public BlockElysian(Material material, String name) {
		super(material);
		this.setCreativeTab(Elysian.tabElysian);
		this.setBlockName("elysian." + name);
	}
}