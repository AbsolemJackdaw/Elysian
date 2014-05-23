package net.darkhax.elysian.blocks.containers.slot;

import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotStone extends Slot {

    public SlotStone(TileEntityHoloTable par1iInventory, int par2, int par3, int par4) {

        super(par1iInventory, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack) {

        if (par1ItemStack == null) {

            return false;
        }

        return  true;
    }
}