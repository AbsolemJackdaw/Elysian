package net.darkhax.elysian.items;

import java.util.List;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.util.ManaType;
import net.darkhax.elysian.util.StackUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemKeyStone extends Item implements ICastingItem {

	public ItemKeyStone() {

		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Elysian.tabElysian);
		this.setUnlocalizedName("elysian.keyStone");
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 7; ++i) {
			ItemStack stack = new ItemStack(ElysianItems.keyStone,1,i);
			StackUtils.prepareStackNBT(stack, "charge", 6000);
			list.add(stack);
		}
	}

	private boolean shouldUpdate;
	
	public void startCharging(){
		shouldUpdate = true;
	}
	
	public boolean shouldUpdate(){
		return shouldUpdate;
	}
	
	public void finishCharging(ItemStack stack, int charge){
		shouldUpdate = false;
	}


	@Override
	public int[] getManaUsage() {

		return null;
	}

	@Override
	public ManaType getManaType(ItemStack stack) {

		int i = stack.getItemDamage();
		return ManaType.getManaFromDamage(i);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean bool) {

		StackUtils.prepareStackTag(stack);
		NBTTagCompound stackTag = stack.stackTagCompound;

		if (stackTag.hasKey("charge")) {
			info.add("Charge : " + stackTag.getInteger("charge"));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {

		switch(par1ItemStack.getItemDamage()){
		case 0:
			return getUnlocalizedName() + ".fire";
		case 1:
			return getUnlocalizedName() + ".water";
		case 2:
			return getUnlocalizedName() + ".earth";
		case 3:
			return getUnlocalizedName() + ".wind";
		case 4:
			return getUnlocalizedName() + ".light";
		case 5:
			return getUnlocalizedName() + ".darkness";
		case 6:
			return getUnlocalizedName() + ".life";

		}

		return super.getUnlocalizedName(par1ItemStack);
	}
	
	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World,	EntityPlayer par3EntityPlayer) {
		super.onCreated(par1ItemStack, par2World, par3EntityPlayer);
		StackUtils.prepareStackNBT(par1ItemStack, "charge", 6000);
	}
}