package net.darkhax.elysian.entity;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ElysianDragon extends EntityFlying  {

	public int courseChangeCooldown = 0;
	public double waypointX;
	public double waypointY;
	public double waypointZ;

	public ElysianDragon(World par1World) {
		super(par1World);
		this.setSize(2.0F, 1.0F);
		this.isImmuneToFire = true;
		this.experienceValue = 5;
		noClip = false;
	}

	public int getColor(){
		return 0x000000;
	}

	@Override
	protected void applyEntityAttributes() {

		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.1D);
	}

	@Override
	protected void updateEntityActionState() {

		super.updateEntityActionState();

		double d0 = this.waypointX - this.posX;
		double d1 = this.waypointY - this.posY;
		double d2 = this.waypointZ - this.posZ;
		double d3 = d0 * d0 + d1 * d1 + d2 * d2;

		if (d3 < 1.0D || d3 > 3600.0D) {
			this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
		}

		if (this.courseChangeCooldown-- <= 0) {
			this.courseChangeCooldown += this.rand.nextInt(5) + 2;
			d3 = MathHelper.sqrt_double(d3);

			if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3)) {
				this.motionX += d0 / d3 * 0.1D;
				this.motionY += d1 / d3 * 0.1D;
				this.motionZ += d2 / d3 * 0.1D;
			}
			else {
				this.waypointX = this.posX;
				this.waypointY = this.posY;
				this.waypointZ = this.posZ;
			}
		}
	}

	@Override
	protected String getLivingSound() {

		return null; // rand.nextInt(10) == 0 ? "elysian:growl" : "";
	}

	@Override
	protected String getHurtSound() {

		return null; // "elysian:roar";
	}

	@Override
	protected String getDeathSound() {

		return null; // "elysian:death";
	}

	@Override
	protected Item getDropItem() {

		return super.getDropItem();
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume() {

		return 1.0F;
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this
	 * entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		return this.rand.nextInt(1000) == 0 && super.getCanSpawnHere();
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}

	protected boolean isCourseTraversable(double par1, double par3, double par5, double par7) {

		double d4 = (this.waypointX - this.posX) / par7;
		double d5 = (this.waypointY - this.posY) / par7;
		double d6 = (this.waypointZ - this.posZ) / par7;
		AxisAlignedBB axisalignedbb = this.boundingBox.copy();

		for (int i = 1; i < par7; ++i) {
			axisalignedbb.offset(d4, d5, d6);

			if (!this.worldObj.getCollidingBoundingBoxes(this, axisalignedbb).isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(16, Byte.valueOf((byte) 0));
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.dataWatcher.getWatchableObjectByte(16);
	}

}
