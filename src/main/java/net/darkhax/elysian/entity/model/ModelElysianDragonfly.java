package net.darkhax.elysian.entity.model;

import net.darkhax.elysian.Elysian;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelElysianDragonfly extends ModelBase {

	public ModelRenderer[] tail = new ModelRenderer[18];

	private float progress;

	ModelRenderer Body;
	ModelRenderer Head;
	ModelRenderer BodySmall;
	ModelRenderer Tail;
	ModelRenderer LegUpLeft1;
	ModelRenderer LegUpRight1;
	ModelRenderer LegUpLeft3;
	ModelRenderer LegUpRight2;
	ModelRenderer LegUpRight3;
	ModelRenderer LegUpLeft2;
	ModelRenderer LeftWingFront;
	ModelRenderer RightWingFront;
	ModelRenderer LeftWingDown;
	ModelRenderer RightWingDown;
	ModelRenderer LeftEye;
	ModelRenderer RightEye;
	ModelRenderer LegDownLeft1;
	ModelRenderer LegDownLeft2;
	ModelRenderer LegDownLeft3;
	ModelRenderer LegDownRight1;
	ModelRenderer LegDownRight2;
	ModelRenderer LegDownRight3;
	ModelRenderer Antenna1;
	ModelRenderer Antenna2;

	public ModelElysianDragonfly() {

		textureWidth = 38;
		textureHeight = 34;

		Body = new ModelRenderer(this, 0, 9);
		Body.addBox(-3F, 0F, 0F, 6, 6, 6);
		Body.setRotationPoint(0F, 0F, 0F);
		Body.setTextureSize(38, 34);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-2.5F, -1F, -5F, 5, 4, 5);
		Head.setRotationPoint(0F, 0F, 0F);
		Head.setTextureSize(38, 34);
		Head.mirror = true;
		setRotation(Head, 0.3F, 0F, 0F);
		BodySmall = new ModelRenderer(this, 24, 14);
		BodySmall.addBox(-2.5F, 0F, 6F, 5, 5, 2);
		BodySmall.setRotationPoint(0F, 0F, 0F);
		BodySmall.setTextureSize(38, 34);
		BodySmall.mirror = true;
		setRotation(BodySmall, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 0, 21);
		Tail.addBox(-2F, 0F, 8F, 4, 3, 2);
		Tail.setRotationPoint(0F, 0F, 0F);
		Tail.setTextureSize(38, 34);
		Tail.mirror = true;
		setRotation(Tail, 0F, 0F, 0F);
		LegUpLeft1 = new ModelRenderer(this, 20, 0);
		LegUpLeft1.addBox(0F, 0F, 0F, 1, 4, 1);
		LegUpLeft1.setRotationPoint(2F, 6F, 0F);
		LegUpLeft1.setTextureSize(38, 34);
		LegUpLeft1.mirror = true;
		setRotation(LegUpLeft1, 0F, 0F, -1.4F);
		LegUpRight1 = new ModelRenderer(this, 20, 0);
		LegUpRight1.addBox(-1F, 0F, 0F, 1, 4, 1);
		LegUpRight1.setRotationPoint(-2F, 6F, 0F);
		LegUpRight1.setTextureSize(38, 34);
		LegUpRight1.mirror = true;
		setRotation(LegUpRight1, 0F, 0F, 1.4F);
		LegUpLeft3 = new ModelRenderer(this, 20, 0);
		LegUpLeft3.addBox(0F, 0F, 0F, 1, 4, 1);
		LegUpLeft3.setRotationPoint(2F, 6F, 4F);
		LegUpLeft3.setTextureSize(38, 34);
		LegUpLeft3.mirror = true;
		setRotation(LegUpLeft3, 0F, -0.7F, -1.4F);
		LegUpRight2 = new ModelRenderer(this, 20, 0);
		LegUpRight2.addBox(-1F, 0F, 0F, 1, 4, 1);
		LegUpRight2.setRotationPoint(-2F, 6F, 2F);
		LegUpRight2.setTextureSize(38, 34);
		LegUpRight2.mirror = true;
		setRotation(LegUpRight2, 0F, 0.3F, 1.4F);
		LegUpRight3 = new ModelRenderer(this, 20, 0);
		LegUpRight3.addBox(-1F, 0F, 0F, 1, 4, 1);
		LegUpRight3.setRotationPoint(-2F, 6F, 4F);
		LegUpRight3.setTextureSize(38, 34);
		LegUpRight3.mirror = true;
		setRotation(LegUpRight3, 0F, 0.7F, 1.4F);
		LegUpLeft2 = new ModelRenderer(this, 20, 0);
		LegUpLeft2.addBox(0F, 0F, 0F, 1, 4, 1);
		LegUpLeft2.setRotationPoint(2F, 6F, 2F);
		LegUpLeft2.setTextureSize(38, 34);
		LegUpLeft2.mirror = true;
		setRotation(LegUpLeft2, 0F, -0.3F, -1.4F);
		LeftWingFront = new ModelRenderer(this, 0, 26);
		LeftWingFront.addBox(0F, 0F, 0F, 14, 1, 3);
		LeftWingFront.setRotationPoint(0F, 0F, 0F);
		LeftWingFront.setTextureSize(38, 34);
		LeftWingFront.mirror = true;
		setRotation(LeftWingFront, 0F, 0F, -0.4F);
		RightWingFront = new ModelRenderer(this, 0, 26);
		RightWingFront.addBox(-14F, 0F, 0F, 14, 1, 3);
		RightWingFront.setRotationPoint(0F, 0F, 0F);
		RightWingFront.setTextureSize(38, 34);
		RightWingFront.mirror = true;
		setRotation(RightWingFront, 0F, 0F, 0.4F);
		LeftWingDown = new ModelRenderer(this, 0, 30);
		LeftWingDown.addBox(0F, 0F, 0F, 12, 1, 3);
		LeftWingDown.setRotationPoint(0F, 0F, 0F);
		LeftWingDown.setTextureSize(38, 34);
		LeftWingDown.mirror = true;
		setRotation(LeftWingDown, 0F, -0.5F, -0.3F);
		RightWingDown = new ModelRenderer(this, 0, 30);
		RightWingDown.addBox(-12F, 0F, 0F, 12, 1, 3);
		RightWingDown.setRotationPoint(0F, 0F, 0F);
		RightWingDown.setTextureSize(38, 34);
		RightWingDown.mirror = true;
		setRotation(RightWingDown, 0F, 0.5F, 0.3F);
		LeftEye = new ModelRenderer(this, 0, 9);
		LeftEye.addBox(3F, 0F, -3F, 1, 2, 2);
		LeftEye.setRotationPoint(0F, 0F, 0F);
		LeftEye.setTextureSize(38, 34);
		setRotation(LeftEye, 0.2F, 0.3F, 0F);
		RightEye = new ModelRenderer(this, 0, 9);
		RightEye.addBox(-4F, 0F, -3F, 1, 2, 2);
		RightEye.setRotationPoint(0F, 0F, 0F);
		RightEye.setTextureSize(38, 34);
		RightEye.mirror = true;
		setRotation(RightEye, 0.2F, -0.3F, 0F);
		Antenna1 = new ModelRenderer(this, 0, 0);
		Antenna1.addBox(1F, -1F, -2F, 1, 1, 1);
		Antenna1.setRotationPoint(0F, 0F, 0F);
		Antenna1.setTextureSize(38, 34);
		Antenna1.mirror = true;
		setRotation(Antenna1, 0F, 0F, 0F);
		Antenna2 = new ModelRenderer(this, 0, 0);
		Antenna2.addBox(-2F, -1F, -2F, 1, 1, 1);
		Antenna2.setRotationPoint(0F, 0F, 0F);
		Antenna2.setTextureSize(38, 34);
		Antenna2.mirror = true;
		setRotation(Antenna2, 0F, 0F, 0F);

		LegDownLeft1 = new ModelRenderer(this, 24, 0);
		LegDownLeft1.addBox(3F, 1.5F, 0F, 1, 5, 1);
		LegDownLeft1.setRotationPoint(2F, 6F, 0F);
		LegDownLeft1.setTextureSize(38, 34);
		LegDownLeft1.mirror = true;

		LegDownLeft2 = new ModelRenderer(this, 24, 0);
		LegDownLeft2.addBox(3F, 1.5F, 0F, 1, 5, 1);
		LegDownLeft2.setRotationPoint(2F, 6F, 2F);
		LegDownLeft2.setTextureSize(38, 34);
		LegDownLeft2.mirror = true;

		LegDownLeft3 = new ModelRenderer(this, 24, 0);
		LegDownLeft3.addBox(3F, 1.5F, 0F, 1, 5, 1);
		LegDownLeft3.setRotationPoint(2F, 6F, 4F);
		LegDownLeft3.setTextureSize(38, 34);
		LegDownLeft3.mirror = true;

		LegDownRight1 = new ModelRenderer(this, 24, 0);
		LegDownRight1.addBox(-4F, 1.5F, 0F, 1, 5, 1);
		LegDownRight1.setRotationPoint(-2F, 6F, 0F);
		LegDownRight1.setTextureSize(38, 34);
		LegDownRight1.mirror = true;

		LegDownRight2 = new ModelRenderer(this, 24, 0);
		LegDownRight2.addBox(-4F, 1.5F, 0F, 1, 5, 1);
		LegDownRight2.setRotationPoint(-2F, 6F, 2F);
		LegDownRight2.setTextureSize(38, 34);
		LegDownRight2.mirror = true;

		LegDownRight3 = new ModelRenderer(this, 24, 0);
		LegDownRight3.addBox(-4F, 1.5F, 0F, 1, 5, 1);
		LegDownRight3.setRotationPoint(-2F, 6F, 4F);
		LegDownRight3.setTextureSize(38, 34);
		LegDownRight3.mirror = true;

		for (int i = 0; i < tail.length; i++) {

			this.tail[i] = new ModelRenderer(this, 0, 21);
			this.tail[i].addBox(-2f, 0f, 8f, 4, 3, 2);
			this.tail[i].setRotationPoint(0F, 0F, 0f);
			this.tail[i].setTextureSize(38, 34);
			this.tail[i].mirror = true;
			setRotation(this.tail[i], 0.0F, 0f, 0F);
		}

		addAllChildModels();
	}

	private void addAllChildModels() {

		onGroundFeet();

		Head.addChild(Antenna1);
		Head.addChild(Antenna2);
		Head.addChild(LeftEye);
		Head.addChild(RightEye);

		Body.addChild(BodySmall);
		Body.addChild(Head);

		LegUpLeft1.addChild(LegDownLeft1);
		LegUpLeft2.addChild(LegDownLeft2);
		LegUpLeft3.addChild(LegDownLeft3);

		LegUpRight1.addChild(LegDownRight1);
		LegUpRight2.addChild(LegDownRight2);
		LegUpRight3.addChild(LegDownRight3);

		for (int var3 = 0; var3 < tail.length - 1; var3++) {
			this.tail[var3].addChild(this.tail[var3 + 1]);
		}

		Tail.addChild(tail[tail.length - 1]);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

		super.render(entity, f, f1, f2, f3, f4, f5);
		this.progress = Elysian.getSysTimeF() / 3.978873F;
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);

		for (ModelRenderer element : this.tail) {
			element.setRotationPoint(0, 0, 1);
			tail[0].setRotationPoint(0, 0, 0);
			tail[0].render(f5);
		}

		render(f5);
	}

	public void render(float f5) {

		Body.render(f5);
		Head.render(f5);
		BodySmall.render(f5);
		LegUpLeft1.render(f5);
		LegUpRight1.render(f5);
		LegUpLeft3.render(f5);
		LegUpRight2.render(f5);
		LegUpRight3.render(f5);
		LegUpLeft2.render(f5);
		LeftWingFront.render(f5);
		RightWingFront.render(f5);
		LeftWingDown.render(f5);
		RightWingDown.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {

		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {

		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);

		float var4 = 0.5F * 0.75F;
		float var6 = f * 0.6662F + this.progress * 0.6662f;

		float speed = 55f;
		float width = 2f;

		for (int j = 0; j < this.tail.length; j++) {
			this.tail[j].rotateAngleY = this.tail[j].rotateAngleZ = 0;

			this.tail[j].rotateAngleX = MathHelper.cos((var6 * 3f - (j + 1) * 1F) * var4) / 30f;
		}

		// move paws
		LegUpLeft1.rotateAngleZ = -0.5f + MathHelper.cos((var6 * 2 - (0 + 1) * 1F) * var4) / 10f;
		LegUpLeft2.rotateAngleZ = -0.5f + MathHelper.cos((var6 * 2 - (0 + 1) * 1F) * var4) / 10f;
		LegUpLeft3.rotateAngleZ = -0.5f + MathHelper.cos((var6 * 2 - (0 + 1) * 1F) * var4) / 10f;
		// move paws
		LegUpRight1.rotateAngleZ = 0.5f - MathHelper.cos((var6 * 2 - (0 + 1) * 1F) * var4) / 10f;
		LegUpRight2.rotateAngleZ = 0.5f - MathHelper.cos((var6 * 2 - (0 + 1) * 1F) * var4) / 10f;
		LegUpRight3.rotateAngleZ = 0.5f - MathHelper.cos((var6 * 2 - (0 + 1) * 1F) * var4) / 10f;

		// move wings
		LeftWingFront.rotateAngleZ = MathHelper.cos((var6 * speed - (0 + 1) * 1F) * var4) / width;
		LeftWingDown.rotateAngleZ = MathHelper.cos((var6 * speed - (0 + 1) * 1F) * var4) / width;
		RightWingFront.rotateAngleZ = -MathHelper.cos((var6 * speed - (0 + 1) * 1F) * var4) / width;
		RightWingDown.rotateAngleZ = -MathHelper.cos((var6 * speed - (0 + 1) * 1F) * var4) / width;
	}

	private void onGroundFeet() {

		LegDownRight1.setRotationPoint(0.4f, 0, 0);
		LegDownRight2.setRotationPoint(0.4f, 0, 0);
		LegDownRight3.setRotationPoint(0.4f, 0, 0);

		LegDownLeft1.setRotationPoint(-0.4f, 0, 0);
		LegDownLeft2.setRotationPoint(-0.4f, 0, 0);
		LegDownLeft3.setRotationPoint(-0.4f, 0, 0);

		setRotation(LegDownRight1, 0, 0, -0.9f);
		setRotation(LegDownRight2, 0, 0, -0.9f);
		setRotation(LegDownRight3, 0, 0, -0.9f);

		setRotation(LegUpRight1, -0.1f, 0, 1.4f);
		setRotation(LegUpRight2, 0.4f, 0, 1.4f);
		setRotation(LegUpRight3, 0.7f, 0, 1.4f);

		setRotation(LegDownLeft1, 0, 0, 0.9f);
		setRotation(LegDownLeft2, 0, 0, 0.9f);
		setRotation(LegDownLeft3, 0, 0, 0.9f);

		setRotation(LegUpLeft1, -0.1f, 0, -1.4f);
		setRotation(LegUpLeft2, 0.4f, 0, -1.4f);
		setRotation(LegUpLeft3, 0.7f, 0, -1.4f);

	}

}
