package net.darkhax.elysian.blocks.containers;

import net.darkhax.elysian.blocks.containers.slot.SlotChiselResult;
import net.darkhax.elysian.blocks.containers.slot.SlotPattern;
import net.darkhax.elysian.blocks.containers.slot.SlotStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHoloTable extends Container {

	private TileEntityHoloTable inventory;

	public ContainerHoloTable(InventoryPlayer player, TileEntityHoloTable inv) {

		inventory = inv;

		this.addSlotToContainer(new SlotPattern(inv, 0, 16, 38));
		this.addSlotToContainer(new SlotStone(inv, 1, 16, 64));
		this.addSlotToContainer(new SlotChiselResult(player.player, inv, 2, 48, 51));

		// add player inventory
		for (int i = 0; i < 3; i++) {

			for (int k = 0; k < 9; k++) {

				addSlotToContainer(new Slot(player, k + (i * 9) + 9, 8 + (k * 18), 68 + (i * 18) + 18));
			}
		}

		for (int j = 0; j < 9; j++) {

			addSlotToContainer(new Slot(player, j, 8 + (j * 18), 126 + 18));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {

		ItemStack itemstack = null;
		Slot invSlot = (Slot) this.inventorySlots.get(slot);

		getSlot(TileEntityHoloTable.STONE).getStack();
		getSlot(TileEntityHoloTable.PATTERN).getStack();

		if (invSlot != null && invSlot.getHasStack()) {

			ItemStack itemstack1 = invSlot.getStack();
			itemstack = itemstack1.copy();

			if (slot == 2) {

				if(getSlot(TileEntityHoloTable.STONE).getStack() != null) 
					inventory.decrStackSize(slot, getSlot(TileEntityHoloTable.RESULT).getStack().stackSize);
				
				if(getSlot(TileEntityHoloTable.PATTERN).getStack() != null) 
					inventory.decrStackSize(slot, getSlot(TileEntityHoloTable.RESULT).getStack().stackSize);
				
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {

					return null;
				}

				invSlot.onSlotChange(itemstack1, itemstack);
			}

			else if (slot != 1 && slot != 0) {

				if (slot < 3) {

					if (!this.mergeItemStack(itemstack1, 0, 2, false)) {

						return null;
					}
				}
				else if (slot >= 3 && slot < 30) {

					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {

						return null;
					}
				}

				else if (slot >= 30 && slot < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {

					return null;
				}
			}

			else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {

				return null;
			}

			if (itemstack1.stackSize == 0) {

				invSlot.putStack((ItemStack) null);
			}
			else {

				invSlot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {

				return null;
			}

			invSlot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public void onCraftMatrixChanged(IInventory par1iInventory) {

		super.onCraftMatrixChanged(par1iInventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {

		return true;
	}

	@Override
	public void detectAndSendChanges() {

		super.detectAndSendChanges();
	}
}