package net.darkhax.elysian.entity.renderer;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.entity.EntityElysianDragonfly;
import net.darkhax.elysian.entity.model.ModelElysianDragonfly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderElysianDragonfly extends RenderLiving {

	private static final ResourceLocation dragonFlyTexture = new ResourceLocation("elysian:textures/mobs/dragonfly_base.png");
	private static final ResourceLocation glow = new ResourceLocation("elysian:/textures/mobs/dragonfly_glow.png");
	private static final ResourceLocation glow_eyes = new ResourceLocation("elysian:/textures/mobs/dragonfly_glow_eyes.png");
	float theSin;

	public RenderElysianDragonfly(ModelBase par1ModelBase, float par2) {

		super(par1ModelBase, par2);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase pet, float par2) {

		GL11.glTranslatef(0f, 0.85f, 0f);
	}

	public void renderDragonFly(EntityElysianDragonfly entity, double par2, double par4, double par6, float par8, float par9) {

		super.doRender(entity, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {

		this.renderDragonFly((EntityElysianDragonfly) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {

		this.renderDragonFly((EntityElysianDragonfly) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {

		return dragonFlyTexture;
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {

		return renderGlow((EntityElysianDragonfly) par1EntityLivingBase, par2, par3);
	}

	ModelElysianDragonfly model = new ModelElysianDragonfly();
	ModelElysianDragonfly model2 = new ModelElysianDragonfly();

	private int renderGlow(EntityElysianDragonfly entity, int par2, float par3) {

		float time = (float) Math.sin(Elysian.getSysTimeF() / 10);
		Math.sin(Elysian.getSysTimeF() / 20f);

		if (par2 == 0) {

			this.setRenderPassModel(model);
			renderManager.renderEngine.bindTexture(glow);

			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (entity.isInvisible())
				GL11.glDepthMask(false);
			else
				GL11.glDepthMask(true);

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			int color = entity.getColor();
			float red = ((color & 0xFF0000) >> 16) / 255f;
			float green = ((color & 0x00FF00) >> 8) / 255f;
			float blue = (color & 0x0000FF) / 255f;

			float sin = 0f;
			theSin += 0.5f;
			sin = MathHelper.sin(theSin/10);

			GL11.glColor3f(red <= sin ? sin : red,
					green <= sin ? sin : green,
							blue <= sin ? sin : blue);
			return 1;
		}
		if (par2 == 1) {

			this.setRenderPassModel(model2);
			renderManager.renderEngine.bindTexture(glow_eyes);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (entity.isInvisible())
				GL11.glDepthMask(false);
			else
				GL11.glDepthMask(true);

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glColor4f(1f, time, 0, 1f);

			return 1;
		}
		return -1;
	}
}
