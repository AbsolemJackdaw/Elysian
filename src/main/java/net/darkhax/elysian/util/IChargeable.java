package net.darkhax.elysian.util;

import net.minecraft.item.ItemStack;

public interface IChargeable {

	/**returns the time on how long this item needs to be in a holotable with a
	 * keystone to be recharged. Calculated in seconds.*/
	public int getChargeTime();
	
	/**returns what item the charged item needs to be turned in to*/
	public ItemStack getChargedItemResult();
 
}
