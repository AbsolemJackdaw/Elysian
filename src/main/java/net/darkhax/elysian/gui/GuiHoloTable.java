package net.darkhax.elysian.gui;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

import net.darkhax.elysian.blocks.containers.ContainerHoloTable;
import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

public class GuiHoloTable extends GuiContainer {

	EntityPlayer player;
	TileEntityHoloTable te;
	private ResourceLocation BG = new ResourceLocation("elysian:textures/gui/chiselTable.png");
	protected final ResourceLocation glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	public GuiHoloTable(EntityPlayer player, TileEntityHoloTable te) {

		super(new ContainerHoloTable(player.inventory, te));

		this.player = player;
		this.te = te;
	}

	private float anim;

	private float theSin;
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

		theSin+=0.1f;

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;

		mc.renderEngine.bindTexture(BG);
		drawTexturedModalRect(j, k, 0, 0, 176, 168);

		anim += 0.5f;
		if (te.craftSlots[TileEntityHoloTable.RESULT] != null) {
			renderBlock(te.craftSlots[TileEntityHoloTable.RESULT], anim, anim, anim);
		}

		GL11.glEnable(GL11.GL_BLEND);
		if(te.isCharging()){
			float f= MathHelper.sin(theSin);
			int alpha = (int) (155 + f*100);

			fontRendererObj.drawStringWithShadow("RECHARGING", j+5 , k+5, fadeColor(alpha)); //alpha is first two digits
			fontRendererObj.drawStringWithShadow(te.manaChargeCountDown + "", j+5 , k+15, fadeColor(alpha)); //alpha is first two digits

		}
		
		GL11.glDisable(GL11.GL_BLEND);

	}
	/**Thanks Ri5ux, for this fine piece of code*/
	private int fadeColor(int alpha){
		Color color = new Color(255, 255, 255, alpha);

		ByteBuffer dest = ByteBuffer.allocate(4);
		color.writeABGR(dest);
		dest.rewind();

		return dest.getInt();
	}
	private RenderBlocks blockrender = new RenderBlocks();

	private void renderBlock(ItemStack itemstack, float x, float y, float z) {

		float scale = 40.0f;
		if ((itemstack != null) && (itemstack.getItem() != null)) {
			// bindTextureMap(itemstack);
			Minecraft.getMinecraft().renderEngine.bindTexture(RenderManager.instance.renderEngine.getResourceLocation(itemstack.getItemSpriteNumber()));

			/* =======BLOCKS======= */
			if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {
				float f2 = scale;

				GL11.glPushMatrix();

				GL11.glTranslatef(this.width / 2, this.height / 2, 60);
				GL11.glTranslatef(40 - 5f, -40f, 0f);

				GL11.glRotatef(210 + x, 1, 0, 0);
				GL11.glRotatef(45 + y, 0, 1, 0);
				GL11.glRotatef(0 + z, 0, 0, 1);

				GL11.glScalef(f2, f2, f2);

				float f3 = 1.0F;

				blockrender.renderBlockAsItem(Block.getBlockFromItem(itemstack.getItem()), itemstack.getItemDamage(), f3);
				GL11.glDisable(GL11.GL_BLEND);

				GL11.glPopMatrix();
			}
			else {
				GL11.glPushMatrix();

				GL11.glTranslatef(this.width / 2, this.height / 2, 60);
				GL11.glTranslatef(40 - 5f, -40f, 0f);

				GL11.glRotatef(210 + x, 1, 0, 0);
				GL11.glRotatef(45 + y, 0, 1, 0);
				GL11.glRotatef(0 + z, 0, 0, 1);

				GL11.glScalef(scale, scale, scale);
				try {

					if (itemstack.getItem().requiresMultipleRenderPasses()) {
						for (int k = 0; k <= 1; k++)
							drawItem(itemstack, k);
					}
					else {
						drawItem(itemstack, 0);
					}
				}
				catch (Throwable throwable) {
					throw new RuntimeException(throwable);
				}
				GL11.glPopMatrix();
			}
		}
	}

	private void drawItem(ItemStack var18, int par2) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		IIcon var4 = var18.getIconIndex();

		if (!(var4 instanceof TextureAtlasSprite)) {

			return;
		}

		TextureAtlasSprite icon = (TextureAtlasSprite) var4;

		GL11.glPushMatrix();
		Tessellator var8 = Tessellator.instance;

		float c1 = var4.getMinU();
		float c2 = var4.getMaxU();
		float c3 = var4.getMinV();
		float c4 = var4.getMaxV();

		float var14 = 0.5F;
		float var15 = 0.25F;

		float var16 = 0.0625F;
		float var17 = 0.021875F;
		int var19 = var18.stackSize;
		byte var24;
		if (var19 < 2) {
			var24 = 1;
		}
		else {
			if (var19 < 16) {
				var24 = 2;
			}
			else {
				if (var19 < 32) {
					var24 = 3;
				}
				else {
					var24 = 4;
				}
			}
		}
		GL11.glTranslatef(-var14, -var15, -((var16 + var17) * var24 / 2.0F));

		for (int var20 = 0; var20 < var24; var20++) {
			GL11.glTranslatef(0.0F, 0.0F, var16 + var17);

			Minecraft.getMinecraft().renderEngine.bindTexture(RenderManager.instance.renderEngine.getResourceLocation(var18.getItemSpriteNumber()));

			int k1 = var18.getItem().getColorFromItemStack(var18, par2);
			float f4 = (k1 >> 16 & 0xFF) / 255.0F;
			float f6 = (k1 >> 8 & 0xFF) / 255.0F;
			float f8 = (k1 & 0xFF) / 255.0F;
			GL11.glColor4f(f4, f6, f8, 1.0F);
			ItemRenderer.renderItemIn2D(var8, c2, c3, c1, c4, icon.getIconHeight(), icon.getIconWidth(), 0.0625F);

			if ((var18 != null) && (var18.hasEffect(par2)) && (par2 == 0)) {
				GL11.glDepthFunc(514);
				GL11.glDisable(2896);
				Minecraft.getMinecraft().renderEngine.bindTexture(glint);
				GL11.glEnable(3042);
				GL11.glBlendFunc(768, 1);
				float colorMult = 0.76F;
				GL11.glColor4f(0.5F * colorMult, 0.25F * colorMult, 0.8F * colorMult, 1.0F);
				GL11.glMatrixMode(5890);
				GL11.glPushMatrix();
				float scale = 0.125F;
				GL11.glScalef(scale, scale, scale);
				float time = Minecraft.getSystemTime() % 3000L / 3000.0F * 8.0F;
				GL11.glTranslatef(time, 0.0F, 0.0F);
				GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(scale, scale, scale);
				time = Minecraft.getSystemTime() % 4873L / 4873.0F * 8.0F;
				GL11.glTranslatef(-time, 0.0F, 0.0F);
				GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(var8, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
				GL11.glPopMatrix();
				GL11.glMatrixMode(5888);
				GL11.glDisable(3042);
				GL11.glEnable(2896);
				GL11.glDepthFunc(515);
			}
		}
		GL11.glPopMatrix();
	}
}
