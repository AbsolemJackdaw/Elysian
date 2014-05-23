package net.darkhax.elysian.items.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKeyStone extends ModelBase {

    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;

    public ModelKeyStone() {

        textureWidth = 32;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-2F, 0F, -2F, 4, 1, 4);
        Shape1.setRotationPoint(0F, 10.8F, 0F);
        Shape1.setTextureSize(32, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 0, 18);
        Shape2.addBox(0F, -2F, -2F, 8, 1, 4);
        Shape2.setRotationPoint(0F, 11F, 0F);
        Shape2.setTextureSize(32, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 1.3F);
        Shape3 = new ModelRenderer(this, 0, 23);
        Shape3.addBox(-2F, -2F, -8F, 4, 1, 8);
        Shape3.setRotationPoint(0F, 11F, 0F);
        Shape3.setTextureSize(32, 32);
        Shape3.mirror = true;
        setRotation(Shape3, 1.3F, 0F, 0F);
        Shape4 = new ModelRenderer(this, 0, 23);
        Shape4.addBox(-2F, -2F, 0F, 4, 1, 8);
        Shape4.setRotationPoint(0F, 11F, 0F);
        Shape4.setTextureSize(32, 32);
        Shape4.mirror = true;
        setRotation(Shape4, -1.3F, 0F, 0F);
        Shape5 = new ModelRenderer(this, 0, 18);
        Shape5.addBox(-8F, -2F, -2F, 8, 1, 4);
        Shape5.setRotationPoint(0F, 11F, 0F);
        Shape5.setTextureSize(32, 32);
        Shape5.mirror = true;
        setRotation(Shape5, 0F, 0F, -1.3F);
        Shape6 = new ModelRenderer(this, 0, 9);
        Shape6.addBox(-3F, 0F, -3F, 6, 2, 6);
        Shape6.setRotationPoint(0F, 16.4F, 0F);
        Shape6.setTextureSize(32, 32);
        Shape6.mirror = true;
        setRotation(Shape6, 0F, 0F, 0F);
        Shape7 = new ModelRenderer(this, 2, 10);
        Shape7.addBox(-2.5F, 0F, -2.5F, 5, 3, 5);
        Shape7.setRotationPoint(0F, 13.4F, 0F);
        Shape7.setTextureSize(32, 32);
        Shape7.mirror = true;
        setRotation(Shape7, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        super.render(entity, f, f1, f2, f3, f4, f5);
        render(f5);
    }

    public void render(float f5) {

        Shape1.render(f5);
        Shape2.render(f5);
        Shape3.render(f5);
        Shape4.render(f5);
        Shape5.render(f5);
        Shape6.render(f5);
        Shape7.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {

        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
