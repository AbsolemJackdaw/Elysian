package net.darkhax.elysian.blocks.containers;

import net.darkhax.elysian.items.ItemKeyStone;
import net.darkhax.elysian.util.IChargeable;
import net.darkhax.elysian.util.IChargedItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHoloTable extends TileEntity implements IInventory {

	private static final int inventorySize = 3;
	/** 0 pattern, 1 stone, 2 result */
	public ItemStack[] craftSlots;

	public static final int PATTERN = 0;
	public static final int STONE = 1;
	public static final int RESULT = 2;

	public TileEntityHoloTable() {

		craftSlots = new ItemStack[inventorySize];
	}

	@Override
	public int getSizeInventory() {

		return inventorySize;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {

		return craftSlots[var1];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt /* amount */) {

		ItemStack stack = getStackInSlot(slot);
		ItemStack stone = getStackInSlot(STONE);
		ItemStack patt = getStackInSlot(PATTERN);

		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			}
			else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}

			if (slot == RESULT) {
				if(stack.getItem() instanceof IChargedItem)
					return stack;

				stone.stackSize -= amt;
				patt.stackSize -= amt;

				if (stone.stackSize == 0)
					setInventorySlotContents(STONE, null);

				if (patt.stackSize == 0)
					setInventorySlotContents(PATTERN, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {

		this.craftSlots[2] = null;
		if (this.craftSlots[slot] != null && slot < 2) {
			ItemStack itemstack = craftSlots[slot];
			this.craftSlots[slot] = null;

			return itemstack;
		}
		else {
			return null;
		}
	}

	public void addInventorySlotContents(int slot, ItemStack stack) {

		if (craftSlots[slot] == null) {
			setInventorySlotContents(slot, stack);
			return;
		}

		if (craftSlots[slot].stackSize + stack.stackSize <= 64) {
			craftSlots[slot].stackSize += stack.stackSize;
			return;
		}
		else {
			int rest = craftSlots[slot].stackSize + stack.stackSize - 64;
			stack.stackSize = rest;
			craftSlots[slot].stackSize = 64;
			return;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {

		craftSlots[slot] = stack;

		if ((stack != null) && (stack.stackSize > getInventoryStackLimit()))
			stack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() {

		return "chisel table";
	}

	@Override
	public boolean hasCustomInventoryName() {

		return false;
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public void markDirty() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {

		return true;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {

		return false;
	}

	public void craft() {

		if (craftSlots[PATTERN] == null || craftSlots[STONE] == null) {
			rechargeCountDown = -1;
			manaChargeCountDown = -1;

			if(craftSlots[RESULT] != null && craftSlots[RESULT].getItem() instanceof IChargedItem)
				return;

			craftSlots[RESULT] = null;
			return;
		}

		ItemStack result = null;

		ItemStack pattern = null;
		ItemStack stone = null;

		if (craftSlots[PATTERN] != null && craftSlots[STONE] != null) {

			pattern = craftSlots[PATTERN].copy();
			stone = craftSlots[STONE].copy();

			if(pattern.getItem() instanceof IChargeable && stone.getItem() instanceof ItemKeyStone && craftSlots[RESULT] == null){
				if(pattern.stackSize == 1){
					rechargeItem(pattern, stone, PATTERN);
				}
				return;
			}

			if(stone.getItem() instanceof IChargeable && pattern.getItem() instanceof ItemKeyStone && craftSlots[RESULT] == null){
				if(stone.stackSize == 1){
					rechargeItem(stone, pattern,  STONE);
				}
				return;
			}

			if(pattern != null && stone != null)
			result = RecipesHoloTable.getCraftingResult(new ItemStack[]{pattern, stone});

			if(result != null){
				result.stackSize = stone.stackSize < pattern.stackSize ? stone.stackSize : pattern.stackSize;
				craftSlots[RESULT] = result.copy();
			}
		}
		return;
	}

	private int rechargeCountDown = -1;
	public int manaChargeCountDown = -1;

	private void rechargeItem(ItemStack pattern, ItemStack keystone, int slot) {
		IChargeable item = (IChargeable) pattern.getItem();
		ItemKeyStone key = ((ItemKeyStone)keystone.getItem());

		if(rechargeCountDown == -1)
			rechargeCountDown = item.getChargeTime()*20;


		if (manaChargeCountDown == -1)
			manaChargeCountDown = keystone.stackTagCompound.getInteger("charge");

		manaChargeCountDown--;
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("charge", manaChargeCountDown);
		
		System.out.println("1" + tag);
		
		System.out.println("2" + keystone.stackTagCompound);

		keystone.stackTagCompound = tag;
		setInventorySlotContents(slot == 0 ? 1 : 0, keystone);
		
		rechargeCountDown --;

		if(rechargeCountDown <= 0){
			setInventorySlotContents(RESULT, item.getChargedItemResult());
			setInventorySlotContents(slot, null);
			rechargeCountDown = -1;
			manaChargeCountDown = -1;

			key.finishCharging(keystone, manaChargeCountDown);
		}
	}

	public boolean isCharging(){
		return rechargeCountDown > 0;
	}

	@Override
	public void updateEntity() {

		craft();

		if (craftSlots[PATTERN] == null || craftSlots[STONE] == null) {
			if (!reset)
				reset = true;
		}
		else if (reset)
			reset = false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);

		rechargeCountDown = nbt.getInteger("rcharge");

		NBTTagList tagList = nbt.getTagList("Inventory", 10);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if ((slot >= 0) && (slot < craftSlots.length))
				craftSlots[slot] = ItemStack.loadItemStackFromNBT(tag);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);

		nbt.setInteger("rcharge", rechargeCountDown);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < craftSlots.length; i++) {
			ItemStack stack = craftSlots[i];
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		nbt.setTag("Inventory", itemList);
	}

	private boolean reset;

	public boolean getReset() {

		return reset;
	}

	@Override
	public boolean canUpdate() {

		return true;
	}

	@Override
	public Packet getDescriptionPacket() {

		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

		this.readFromNBT(pkt.func_148857_g()); // packet.data

	}
}
