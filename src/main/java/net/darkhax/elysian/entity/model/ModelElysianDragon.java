package net.darkhax.elysian.entity.model;

import java.util.Random;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.entity.DragonAgressive;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class ModelElysianDragon extends ModelBase {

	ModelRenderer jaw;
	ModelRenderer head;
	ModelRenderer teeth;
	ModelRenderer bodyBase;

	public ModelRenderer[] tail = new ModelRenderer[16];

	private float progress;
	private float openMouthTime;

	Random rand = new Random();

	public ModelElysianDragon() {

		textureWidth = 64;
		textureHeight = 32;

		jaw = new ModelRenderer(this, 32, 0);
		jaw.addBox(-4F, 2F, -8F, 8, 3, 8);
		jaw.setRotationPoint(0F, 0F, 0F);
		jaw.setTextureSize(64, 32);
		jaw.mirror = true;
		setRotation(jaw, 0F, 0F, 0F);

		bodyBase = new ModelRenderer(this, 24, 18);
		bodyBase.addBox(-0.5f, -6.5f, 0, 4, 13, 1);
		bodyBase.setRotationPoint(0F, 0F, 0);
		bodyBase.setTextureSize(64, 32);
		setRotation(bodyBase, 0f, -1.570796F, 0f);

		for (int var3 = 0; var3 < this.tail.length; var3++) {
			this.tail[var3] = new ModelRenderer(this, 0, 16);
			this.tail[var3].addBox(-3F, -3F, -3F, 6, 6, 6, 1.0f);
			this.tail[var3].setRotationPoint(0F, 0F, 0f);
			this.tail[var3].setTextureSize(64, 32);
			this.tail[var3].mirror = true;
			setRotation(this.tail[var3], 0.25F, 0f, 0F);
		}

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -4F, -8F, 8, 6, 8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		teeth = new ModelRenderer(this, 34, 11);
		teeth.addBox(-3.5F, 0.5F, -7.5F, 7, 2, 7);
		teeth.setRotationPoint(0F, 0F, 0F);
		teeth.setTextureSize(64, 32);
		teeth.mirror = true;
		setRotation(teeth, 0F, 0F, 0F);

		// body parts
		for (int var3 = 0; var3 < tail.length - 1; var3++) {
			this.tail[var3].addChild(this.tail[var3 + 1]);
		}

		bodyBase.addChild(tail[tail.length - 1]);

		jaw.addChild(teeth);

	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

		super.render(entity, f, f1, f2, f3, f4, f5);
		this.progress = Elysian.getSysTimeF() / 3.978873F;
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		for (ModelRenderer element : this.tail) {
			element.setRotationPoint(0, 0, 4);
			tail[0].setRotationPoint(0, 0, 3);
			tail[0].render(0.0625f);
		}
		head.setRotationPoint(0, 0, -1f);
		jaw.setRotationPoint(0, 0, -1f);
		head.render(f5);
		jaw.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {

		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {

		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		float var4 = 0.5F * 0.75F;
		float var6 = f * 0.6662F + this.progress * 0.6662f;

		for (int j = 0; j < this.tail.length; j++) {
			this.tail[j].rotateAngleZ = this.tail[j].rotateAngleX = 0;
			this.tail[j].rotateAngleY = MathHelper.sin((var6 * 2 - (j + 1) * 1F) * var4) / 10f;

			this.head.rotateAngleY = MathHelper.sin((var6 * 2 - (j + 1) * 1F) * var4) / 10f;
			this.jaw.rotateAngleY = MathHelper.sin((var6 * 2 - (j + 1) * 1F) * var4) / 10f;
		}

		if (entity != null && (entity instanceof EntityLiving)) {
			EntityLiving entityliving = (EntityLiving) entity;

			if (entityliving.hurtTime > 0) {
				setanimationTime(openMouthTime -= 0.05);
				jaw.rotateAngleX = openMouthTime / 2;
				if (openMouthTime <= 0)
					setanimationTime(openMouthTime += 0.05);
			}
			else {
				jaw.rotateAngleX = 0;
				openMouthTime = 2.0f;
			}
		}

		if (entity != null && (entity instanceof DragonAgressive))
		{
			DragonAgressive dragon = (DragonAgressive)entity;
			if(DragonAgressive.attackCounter > 0)
			{
				setanimationTime(openMouthTime -=0.05);
				jaw.rotateAngleX = openMouthTime/4;
				if(openMouthTime <=0)
					setanimationTime(openMouthTime +=0.05);
			}else{
				jaw.rotateAngleX = 0;
				openMouthTime = 2.0f;
			}
		}
	}

	// only returns 1.95 while it should return way less.
			private void setanimationTime(float f) {

		openMouthTime = f;
	}
}