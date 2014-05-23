package net.darkhax.elysian.items;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.blocks.ElysianBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class ItemLifeWaterBucket extends ItemBucket {

	public ItemLifeWaterBucket() {
		super(ElysianBlocks.water);
		setUnlocalizedName("elysian.waterBucket");
		setCreativeTab(Elysian.tabElysian);
	}
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		itemIcon = Items.water_bucket.getIconFromDamage(0);
	}
}
