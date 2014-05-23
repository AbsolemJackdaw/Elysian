package net.darkhax.elysian.blocks.containers.slot;

import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotChiselResult extends Slot {

	EntityPlayer player;
    public SlotChiselResult(EntityPlayer p, TileEntityHoloTable inv, int i, int b, int c) {

        super(inv, i, b, c);
        player = p;
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {

        return false;
    }
    
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        this.onCrafting(par2ItemStack);
        super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
    }
    
    @Override
    protected void onCrafting(ItemStack par1ItemStack) {
    	super.onCrafting(par1ItemStack);
    	 par1ItemStack.onCrafting(player.worldObj, player, 0);
    }
}
