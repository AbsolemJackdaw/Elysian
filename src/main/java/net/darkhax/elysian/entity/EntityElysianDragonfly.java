package net.darkhax.elysian.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityElysianDragonfly extends EntityLiving implements IAnimals {

	private ChunkCoordinates spawnPosition;
	private int color;

	public EntityElysianDragonfly(World par1World) {

		super(par1World);
		setSize(1.5f, 0.7f);
	}

	@Override
	protected void applyEntityAttributes() {

		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onUpdate() {

		super.onUpdate();
		this.motionY *= 0.2000000238418579D;
	}

	@Override
	protected void updateAITasks() {

		super.updateAITasks();

		if (this.spawnPosition != null && (!this.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ) || this.spawnPosition.posY < 1)) {
			this.spawnPosition = null;
		}

		if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.getDistanceSquared((int) this.posX, (int) this.posY, (int) this.posZ) < 4.0F) {
			this.spawnPosition = new ChunkCoordinates((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
		}

		double d0 = this.spawnPosition.posX + 0.5D - this.posX;
		double d1 = this.spawnPosition.posY + 0.1D - this.posY;
		double d2 = this.spawnPosition.posZ + 0.5D - this.posZ;
		this.motionX += (Math.signum(d0) * 0.2D - this.motionX) * 0.10000000149011612D;
		this.motionY += (Math.signum(d1) * 0.299999988079071D - this.motionY) * 0.10000000149011612D;
		this.motionZ += (Math.signum(d2) * 0.2D - this.motionZ) * 0.10000000149011612D;
		float f = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
		this.moveForward = 0.5F;
		this.rotationYaw += f1;

	}

	@Override
	// keep empty so it can fly
	protected void fall(float par1) {

	}

	@Override
	// keep empty so it can fly
	protected void updateFallState(double par1, boolean par3) {

	}


	public void setColor( int i ){
		color = i;
	}
	public int getColor(){
		return color;
	}


	@Override
	public boolean allowLeashing() {
		return true;
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return 2;
	}

	@Override
	public boolean getCanSpawnHere() {
		return this.rand.nextInt(1000) == 0;
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setInteger("entityColor", color);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);
		color = par1nbtTagCompound.getInteger("entityColor");
	}
}
