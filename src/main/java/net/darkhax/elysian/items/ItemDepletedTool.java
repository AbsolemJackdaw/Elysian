package net.darkhax.elysian.items;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.util.IChargeable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemDepletedTool extends Item  implements IChargeable{
	
	public ItemDepletedTool() {
        this.setCreativeTab(Elysian.tabElysian);
	}
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		String s = getUnlocalizedName().replace("item.", "").replace("Depleted", "");
		itemIcon = par1IconRegister.registerIcon("elysian:tools/"+ s);
//		glow = par1IconRegister.registerIcon("elysian:tools/"+ s + "_glow");
	}

	@Override
	public boolean isFull3D() {
		return true;
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		return itemIcon;
	}
	
	@Override
	public int getChargeTime() {
		return 20;
	}

	@Override
	public ItemStack getChargedItemResult() {
		return getUnlocalizedName().equals("item.runeAxeDepleted") ? new ItemStack(ElysianItems.runeAxe) :
					getUnlocalizedName().equals("item.runePickaxeDepleted") ? new ItemStack(ElysianItems.runePickaxe) :
							new ItemStack(ElysianItems.runeSpade);		
	}
}
