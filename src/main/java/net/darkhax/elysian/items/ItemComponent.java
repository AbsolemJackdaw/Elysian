package net.darkhax.elysian.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemComponent extends ItemElysian {

	public ItemComponent(String itemName) {
		super(itemName);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		itemIcon = par1IconRegister.registerIcon("elysian:runicComponent");
	}
}
