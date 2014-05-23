package net.darkhax.elysian.blocks;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.darkhax.elysian.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockElysianHoloTable extends BlockElysian {

	public BlockElysianHoloTable(Material material) {

		super(material, "chiselBlock", "pickaxe", 1);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int d, float f1, float f2, float f3) {

		TileEntityHoloTable te = (TileEntityHoloTable) world.getTileEntity(x, y, z);

		if(player.isSneaking()){
			player.openGui(Elysian.instance, Reference.OPENGUI_CHISELBLOCK, world, x, y, z);
			return true;
		}
		
		//TODO needs to come back
		//TODO needs crashes fixed
//
//		if (player.getCurrentEquippedItem() != null){
//
//			ItemStack stack = player.getCurrentEquippedItem().copy();
//
//			if (te.getStackInSlot(0) == null){
//				te.addInventorySlotContents(0, stack);
//				stack = null;
//				player.setCurrentItemOrArmor(0, stack);
//				return true;
//
//			}else if (te.getStackInSlot(0) != null &&
//					stack.getItem().equals(te.getStackInSlot(0).getItem())){
//
//				if (te.getStackInSlot(0).stackSize + stack.stackSize <= stack.getMaxStackSize()) {
//					te.addInventorySlotContents(0, stack);
//					stack = null;
//				}
//				else {
//					te.addInventorySlotContents(0, stack);
//					int rest = te.getStackInSlot(0).stackSize + stack.stackSize - stack.getMaxStackSize();
//					stack.stackSize = rest;
//				}
//				return true;
//			}
//
//			else if(te.getStackInSlot(1) == null){
//				te.addInventorySlotContents(1, stack);
//				stack = null;
//				player.setCurrentItemOrArmor(0, stack);
//				return true;
//			}
//
//			if (stack.getItem().equals(te.getStackInSlot(1).getItem())){
//				System.out.println(te.getStackInSlot(0).stackSize + stack.stackSize);
//				if (te.getStackInSlot(1).stackSize + stack.stackSize <= stack.getMaxStackSize()) {
//					te.addInventorySlotContents(1, stack);
//					stack = null;
//				}
//				else {
//					te.addInventorySlotContents(1, stack);
//					int rest = te.getStackInSlot(1).stackSize + stack.stackSize - stack.getMaxStackSize();
//					stack.stackSize = rest;
//				}
//				return true;
//			}
//			player.setCurrentItemOrArmor(0, stack);
//			return true;
//		}
//
//		if (te.getStackInSlot(2) != null && !player.isSneaking()) {
//
//			if (!world.isRemote)
//				world.spawnEntityInWorld(new EntityItem(world, x, y, z, te.getStackInSlot(2).copy()));
//
//			int amt = te.getStackInSlot(2).stackSize;
//
//			ItemStack stone = te.getStackInSlot(1);
//			ItemStack patt = te.getStackInSlot(0);
//
//			stone.stackSize -= amt;
//			patt.stackSize -= amt;
//
//			if(stone.stackSize == 0)stone = null;
//			if(patt.stackSize == 0)patt = null;
//
//			te.setInventorySlotContents(2, null);
//			te.setInventorySlotContents(1, stone);
//			te.setInventorySlotContents(0, patt);
//
//			return true;
//		}
//
//		if (te.getStackInSlot(2) == null){
//			if( te.getStackInSlot(1) != null ||  te.getStackInSlot(0) != null){
//				if( te.getStackInSlot(1) != null) {
//					if (!world.isRemote)
//						world.spawnEntityInWorld(new EntityItem(world, x, y, z, te.getStackInSlot(1).copy()));
//					te.setInventorySlotContents(1, null);
//				}
//				if (te.getStackInSlot(0) != null) {
//					if (!world.isRemote)
//						world.spawnEntityInWorld(new EntityItem(world, x, y, z, te.getStackInSlot(0).copy()));
//					te.setInventorySlotContents(0, null);
//				}
//				return true;
//			}
//		}
//
//		player.openGui(Elysian.instance, Reference.OPENGUI_CHISELBLOCK, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y,int z, Block block, int meta) {

		TileEntityHoloTable te = (TileEntityHoloTable) world.getTileEntity(x, y, z);

		if(te.getStackInSlot(0) != null)
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, te.getStackInSlot(0).copy()));
		if(te.getStackInSlot(1) != null)
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, te.getStackInSlot(1).copy()));
		if(te.getStackInSlot(2) != null)
			world.spawnEntityInWorld(new EntityItem(world, x, y, z, te.getStackInSlot(2).copy()));

		world.removeTileEntity(x, y, z);

	}

	@Override
	public boolean hasTileEntity(int metadata) {

		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {

		return new TileEntityHoloTable();
	}

	@Override
	public int getRenderType() {

		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public boolean isOpaqueCube() {

		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {

		return false;
	}
}