package net.darkhax.elysian.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class DragonAgressive extends ElysianDragon implements IMob {

	/** Cooldown time between target loss and new target aquirement. */
	private int aggroCooldown = 0;
	public int prevAttackCounter = 0;
	public static int attackCounter = 0;
	private Entity targetedEntity = null;

	/** The explosion radius of spawned fireballs. */
	private int explosionStrength = 1;

	public DragonAgressive(World par1World) {
		super(par1World);
	}


	@Override
	protected void updateEntityActionState()
	{
		if (!this.worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			this.setDead();
		}

		this.despawnEntity();
		this.prevAttackCounter = DragonAgressive.attackCounter;
		double d0 = this.waypointX - this.posX;
		double d1 = this.waypointY - this.posY;
		double d2 = this.waypointZ - this.posZ;
		double d3 = d0 * d0 + d1 * d1 + d2 * d2;

		if (d3 < 1.0D || d3 > 3600.0D)
		{
			this.waypointX = this.posX + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.waypointY = this.posY + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
			this.waypointZ = this.posZ + (this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F;
		}

		if (this.courseChangeCooldown-- <= 0)
		{
			this.courseChangeCooldown += this.rand.nextInt(5) + 2;
			d3 = MathHelper.sqrt_double(d3);

			if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3))
			{
				this.motionX += d0 / d3 * 0.1D;
				this.motionY += d1 / d3 * 0.1D;
				this.motionZ += d2 / d3 * 0.1D;
			}
			else
			{
				this.waypointX = this.posX;
				this.waypointY = this.posY;
				this.waypointZ = this.posZ;
			}
		}

		if (this.targetedEntity != null && this.targetedEntity.isDead)
		{
			this.targetedEntity = null;
		}

		if (this.targetedEntity == null || this.aggroCooldown-- <= 0)
		{
			this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 100.0D);

			if (this.targetedEntity != null)
			{
				this.aggroCooldown = 20;
			}
		}

		double d4 = 64.0D;

		if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < d4 * d4)
		{
			double d5 = this.targetedEntity.posX - this.posX;
			double d6 = this.targetedEntity.boundingBox.minY + this.targetedEntity.height / 2.0F - (this.posY + this.height / 2.0F);
			double d7 = this.targetedEntity.posZ - this.posZ;
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(d5, d7)) * 180.0F / (float)Math.PI;

			if (this.canEntityBeSeen(this.targetedEntity))
			{
				if (DragonAgressive.attackCounter == 10)
				{
					this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1007, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
				}

				++DragonAgressive.attackCounter;

				if (DragonAgressive.attackCounter == 20)
				{
					this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
					EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d5, d6, d7);
					entitylargefireball.field_92057_e = this.explosionStrength;
					double d8 = 4.0D;
					Vec3 vec3 = this.getLook(1.0F);
					entitylargefireball.posX = this.posX + vec3.xCoord * d8;
					entitylargefireball.posY = this.posY + this.height / 2.0F + 0.5D;
					entitylargefireball.posZ = this.posZ + vec3.zCoord * d8;
					this.worldObj.spawnEntityInWorld(entitylargefireball);
					DragonAgressive.attackCounter = -40;
				}
			}
			else if (DragonAgressive.attackCounter > 0)
			{
				--DragonAgressive.attackCounter;
			}
		}
		else
		{
			this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;

			if (DragonAgressive.attackCounter > 0)
			{
				--DragonAgressive.attackCounter;
			}
		}

		if (!this.worldObj.isRemote)
		{
			byte b0 = this.dataWatcher.getWatchableObjectByte(16);
			byte b1 = (byte)(DragonAgressive.attackCounter > 10 ? 1 : 0);

			if (b0 != b1)
			{
				this.dataWatcher.updateObject(16, Byte.valueOf(b1));
			}
		}
	}

}
