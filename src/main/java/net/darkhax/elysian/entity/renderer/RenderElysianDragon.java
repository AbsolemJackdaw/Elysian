package net.darkhax.elysian.entity.renderer;

import net.darkhax.elysian.entity.ElysianDragon;
import net.darkhax.elysian.entity.model.ModelElysianDragon;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderElysianDragon extends RenderLiving {

	private static final ResourceLocation dragonTexture = new ResourceLocation("elysian:textures/mobs/rune_dragon_base.png");
	private static final ResourceLocation dragonGlow = new ResourceLocation("elysian:textures/mobs/rune_dragon_glow.png");
	ModelElysianDragon modelPass = new ModelElysianDragon();

	public RenderElysianDragon(ModelElysianDragon model, float par2) {
		super(model, par2);
	}

	float theSin;

	@Override
	protected void preRenderCallback(EntityLivingBase dragon, float par2) {
		GL11.glTranslatef(0f, 1f, -1f);
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase dragon, int par2, float par3)
	{
		if(par2 == 0){


			this.setRenderPassModel(modelPass);
			bindTexture(dragonGlow);         
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

			if (dragon.isInvisible())
			{
				GL11.glDepthMask(false);
			}
			else
			{
				GL11.glDepthMask(true);
			}

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			int color = ((ElysianDragon)dragon).getColor();
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
		return -1;
	}

	public void renderDragon(ElysianDragon dragon, double par2, double par4, double par6, float par8, float par9) {

		super.doRender(dragon, par2, par4, par6, par8, par9);
	}

	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {

		this.renderDragon((ElysianDragon) par1EntityLiving, par2, par4, par6, par8, par9);
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {

		this.renderDragon((ElysianDragon) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {

		return dragonTexture;
	}
}
