package net.darkhax.elysian.blocks.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class RenderPortal extends TileEntitySpecialRenderer {

	IIcon portal = null;
	float max = -4.6f;
	float min = -0.6f;
	float mid = -2.6f;
	float c = 0;

	@Override
	public void renderTileEntityAt(TileEntity var1, double d, double d1,
			double d2, float var8) {

		c += 0.1f;

		Block b = var1.getWorldObj().getBlock(var1.xCoord, var1.yCoord, var1.zCoord);
		if(b != null){
			portal = b.getIcon(0, 0);
		}

		float f = 0.04f;

		if(portal != null){

			GL11.glPushMatrix();
			bindTexture(TextureMap.locationBlocksTexture);

			GL11.glColor4f(0f, 1f, 0.7f, 1f);

			GL11.glTranslatef((float)d + 0.5F, (float)d1 + 2.25F, (float)d2 + 0.5F);
			GL11.glScaled(1f, -1f, -1f);
			
			//thank you Corosus for aiding me find how to turn the portal with the players view !
			GL11.glRotatef(RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);

			GL11.glTranslatef(-2.6f, -2.8f +MathHelper.sin(c/4)/4 + 1.5f, 0f);
			GL11.glScalef(f, f, f);
			
			drawPortalQuad(0, 0, portal, 128, 128);
			
			GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);

			GL11.glPopMatrix();

		}
	}

	public void drawPortalQuad(int par1, int par2, IIcon par3Icon, int par4, int par5){

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(par1 + 0, par2 + par5, 0, par3Icon.getMinU(), par3Icon.getMaxV());
		tessellator.addVertexWithUV(par1 + par4, par2 + par5, 0, par3Icon.getMaxU(), par3Icon.getMaxV());
		tessellator.addVertexWithUV(par1 + par4, par2 + 0, 0, par3Icon.getMaxU(), par3Icon.getMinV());
		tessellator.addVertexWithUV(par1 + 0, par2 + 0, 0, par3Icon.getMinU(), par3Icon.getMinV());

		tessellator.addVertexWithUV(par1 + 0, par2 + 0, 0, par3Icon.getMinU(), par3Icon.getMinV());
		tessellator.addVertexWithUV(par1 + par4, par2 + 0, 0, par3Icon.getMaxU(), par3Icon.getMinV());
		tessellator.addVertexWithUV(par1 + par4, par2 + par5, 0, par3Icon.getMaxU(), par3Icon.getMaxV());
		tessellator.addVertexWithUV(par1 + 0, par2 + par5, 0, par3Icon.getMinU(), par3Icon.getMaxV());
		tessellator.draw();

	}
}
