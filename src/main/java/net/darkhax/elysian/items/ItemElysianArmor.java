package net.darkhax.elysian.items;

import net.darkhax.elysian.Elysian;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemElysianArmor extends ItemArmor {

	public ItemElysianArmor(ArmorMaterial mat, int renderindex, int armortype) {
		super(mat, renderindex, armortype);
		setCreativeTab(Elysian.tabElysian);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {

		if ((stack.getItem() == ElysianItems.armorChest)
				|| (stack.getItem() == ElysianItems.armorHelmet)
				|| (stack.getItem() == ElysianItems.armorBoots))
			return "elysian:/armor/stone_1.png";
		if (stack.getItem() == ElysianItems.armorLegs)
			return "elysian:/armor/stone_2.png";
		return null;
	
	}
}
