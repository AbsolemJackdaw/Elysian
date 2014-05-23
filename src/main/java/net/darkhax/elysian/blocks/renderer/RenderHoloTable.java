package net.darkhax.elysian.blocks.renderer;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.darkhax.elysian.blocks.model.ModelCarverTable;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderHoloTable extends TileEntitySpecialRenderer {

	public RenderHoloTable() {

		blockrender = new RenderBlocks();
		rand = new Random();
	}

	private static final ResourceLocation texture = new ResourceLocation("elysian", "textures/blocks/engraving_table.png");
	ModelCarverTable table = new ModelCarverTable();
	private RenderBlocks blockrender;
	private Random rand;
	protected final ResourceLocation glint = new ResourceLocation("textures/misc/enchanted_item_glint.png");

	private float fadeIn = 2f;
	private float scaleFade;

	@Override
	public void renderTileEntityAt(TileEntity var1, double d, double d1, double d2, float var8) {

		TileEntityHoloTable te = (TileEntityHoloTable) var1;

		if (te.getReset()) {
			scaleFade = 0;
			fadeIn = 2.0f;
		}

		GL11.glPushMatrix();

		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 2.25F, (float) d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);

		GL11.glTranslatef(0f, 0.75f, 0f);

		bindTexture(texture);

		table.render(0.0625f);

		if (te.getStackInSlot(TileEntityHoloTable.PATTERN) != null) {
			renderStacks(te.getStackInSlot(TileEntityHoloTable.PATTERN), 0.3f, TileEntityHoloTable.PATTERN);
		}

		if (te.getStackInSlot(TileEntityHoloTable.STONE) != null) {
			renderStacks(te.getStackInSlot(TileEntityHoloTable.STONE), 0.2f, TileEntityHoloTable.STONE);
		}

		if (te.getStackInSlot(TileEntityHoloTable.RESULT) != null) {
			renderResult(te.getStackInSlot(TileEntityHoloTable.RESULT), 0.4f);
		}

		GL11.glPopMatrix();

	}

	private void renderStacks(ItemStack itemstack, float scale, int slot) {

		GL11.glPushMatrix();
		if ((itemstack != null) && (itemstack.getItem() != null)) {
			bindTextureMap(itemstack);

			/* =======BLOCKS======= */
			if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock &&
					RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())){
				GL11.glTranslatef(0.25f, 0.44f, -0.25f);

				float f2 = 0.25F;
				int i1 = Block.getBlockFromItem(itemstack.getItem()).getRenderType();

				if ((i1 == 1) || (i1 == 19) || (i1 == 12) || (i1 == 2)) {
					f2 = 0.5F;
				}

				if (scale > 0f) {
					f2 = 0.2f;
				}

				int b0 = 1;
				if (itemstack.stackSize > 1) {
					b0 = 2;
				}

				if (itemstack.stackSize > 20) {
					b0 = 3;
				}

				if (itemstack.stackSize > 40) {
					b0 = 4;
				}

				GL11.glScalef(f2, f2, f2);

				getSlotPosition(slot, f2, false);

				for (int i = 0; i < b0; i++) {
					GL11.glTranslatef(0.05f * i, 0.05f * i, 0.05f * i);
					blockrender.renderBlockAsItem(Block.getBlockFromItem(itemstack.getItem()), itemstack.getItemDamage(), 1.0f);
				}
			}
			/* =====ITEMS===== */
			else {
				getSlotPosition(slot, 0.3f, true);

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
			}
		}
		GL11.glPopMatrix();
	}

	private float turn;

	private void renderResult(ItemStack itemstack, float scale) {

		turn++;

		if (fadeIn > -0.2f)
			fadeIn -= 0.05;
		if (scaleFade < scale)
			scaleFade += 0.005;

		GL11.glPushMatrix();
		if ((itemstack != null) && (itemstack.getItem() != null)) {
			bindTextureMap(itemstack);

			/* =======BLOCKS======= */
			if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {

				float f2 = 0.25F;
				int i1 = Block.getBlockFromItem(itemstack.getItem()).getRenderType();

				if ((i1 == 1) || (i1 == 19) || (i1 == 12) || (i1 == 2)) {
					f2 = 0.5F;
				}

				if (scale > 0f) {
					f2 = scaleFade;
				}

				GL11.glScalef(f2, f2, f2);
				GL11.glTranslatef(0.0F, fadeIn, 0.0F);

				float f3 = 1.0F;
				GL11.glRotatef(180, 1, 0, 0);

				GL11.glRotatef(turn + 1, 0, 0, 1);
				GL11.glRotatef(turn, 0, 1, 0);
				GL11.glRotatef(turn + 5, 1, 0, 0);

				blockrender.renderBlockAsItem(Block.getBlockFromItem(itemstack.getItem()), itemstack.getItemDamage(), f3);

			}
			else {
				float f2 = scaleFade;
				GL11.glScalef(f2, f2, f2);
				GL11.glTranslatef(0.0F, fadeIn, 0.0F);

				GL11.glRotatef(180, 1, 0, 0);

				GL11.glRotatef(turn + 1, 0, 0, 1);
				GL11.glRotatef(turn, 0, 1, 0);
				GL11.glRotatef(turn + 5, 1, 0, 0);
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
			}
		}
		GL11.glPopMatrix();
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

			bindTextureMap(var18);

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

	private void bindTextureMap(ItemStack item) {

		Minecraft.getMinecraft().renderEngine.bindTexture(RenderManager.instance.renderEngine.getResourceLocation(item.getItemSpriteNumber()));
	}

	private void getSlotPosition(int i, float scale,boolean item){
		switch(i){
		case TileEntityHoloTable.PATTERN:
			if(item){
				GL11.glTranslatef(-0.145f, 0.585f, 0.05f);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glScalef(scale, scale, scale);
				break;
			}
			GL11.glTranslatef(-2.3F, 0.35F, 2.3F);
			GL11.glRotatef(180, 1, 0, 0);
			break;

		case TileEntityHoloTable.STONE:
			if(item){
				GL11.glTranslatef(0.175f, 0.585f, -0.32f);
				GL11.glRotatef(90, 1, 0, 0);
				GL11.glScalef(scale, scale, scale);
				break;
			}
			GL11.glTranslatef(-0.3F, 0.35F, 0.0F);
			GL11.glRotatef(180, 1, 0, 0);
			break;

		default:
			break;

		}
	}
}
