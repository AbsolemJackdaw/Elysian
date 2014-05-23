package net.darkhax.elysian.entity.effects;

import net.darkhax.elysian.Elysian;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class EntityElysianPowerEffect extends EntityFX {

	public EntityElysianPowerEffect(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
		super(par1World, par2, par4, par6, par8, par10, par12);

		init();
	}

	public EntityElysianPowerEffect(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);

		init();

	}

	private void init(){
		setParticleIcon(Elysian.effect);
		setAlphaF(0.8f);
		particleScale = 0.5f + rand.nextFloat();
		particleMaxAge = 100;

		this.motionX = 0;
		this.motionY *= -0.5D;
		this.motionZ = 0;

		float f4 = (float)Math.random() * 0.4F + 0.6F;
		this.noClip = false;
	}

	@Override
	public int getFXLayer() {
		return 1;
	}
}
