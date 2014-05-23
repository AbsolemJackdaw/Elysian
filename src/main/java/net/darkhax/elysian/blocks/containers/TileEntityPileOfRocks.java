package net.darkhax.elysian.blocks.containers;

import net.darkhax.elysian.entity.EntityRuneGolem;
import net.darkhax.elysian.items.ElysianItems;
import net.darkhax.elysian.util.ManaType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPileOfRocks extends TileEntity {

	private float yoffset;

	private ManaType mana;

	public void setMana(ManaType mana) {
		this.mana = mana;
	}

	public float getYOffset(){
		return yoffset;
	}

	public void triggerEvent(){
		yoffset = 0.0001f;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("mana", ManaType.manaTypeToByte(mana));
		nbt.setFloat("timer", yoffset);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		mana = ManaType.getManaFromDamage((int)nbt.getByte("mana"));
		yoffset = nbt.getFloat("timer");
	}

	@Override
	public void updateEntity() {

		if(yoffset > 0){
			yoffset += 0.001f;

			switch(mana){

			case WATER:
				if(yoffset >= 0.5f){
					if(!worldObj.isRemote){
						int random = worldObj.rand.nextInt(9);
						for(int i = 0; i < random +1; i++)
							worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord, zCoord, 
									new ItemStack(ElysianItems.runicComponent)));
					}
					worldObj.setBlockToAir(xCoord, yCoord, zCoord);
					worldObj.removeTileEntity(xCoord, yCoord, zCoord);
				}
				break;

			case LIFE:
				if(yoffset >= 0.75f){
					EntityRuneGolem golem = new EntityRuneGolem(worldObj);
					golem.setLocationAndAngles(xCoord+0.5f, yCoord, zCoord+0.5f, 0, 0);

					if(!worldObj.isRemote)
						worldObj.spawnEntityInWorld(golem);

					worldObj.setBlockToAir(xCoord, yCoord, zCoord);
					worldObj.removeTileEntity(xCoord, yCoord, zCoord);
				}

				break;

			default:
				break;
			}
		}
	}

	public ManaType getMana() {
		return mana;
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
		this.readFromNBT(pkt.func_148857_g()); 
	}
}
