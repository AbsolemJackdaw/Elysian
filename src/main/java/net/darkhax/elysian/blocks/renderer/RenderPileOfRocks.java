package net.darkhax.elysian.blocks.renderer;

import net.darkhax.elysian.blocks.containers.TileEntityPileOfRocks;
import net.darkhax.elysian.entity.model.ModelElysianGolem;
import net.darkhax.elysian.util.ManaType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderPileOfRocks extends TileEntitySpecialRenderer{

	private static final ItemStack rock = new ItemStack(Blocks.cobblestone);
	private static final ItemStack brick = new ItemStack(Blocks.bedrock);
	private static RenderBlocks blockrender = new RenderBlocks();
	private static final ModelElysianGolem golem = new ModelElysianGolem();
	private static final ResourceLocation golemTexture = new ResourceLocation("elysian","textures/blocks/rune_golem.png");

	@Override
	public void renderTileEntityAt(TileEntity var1, double d, double d1,
			double d2, float var8) {

		TileEntityPileOfRocks pile = (TileEntityPileOfRocks)var1;

		GL11.glPushMatrix();

		GL11.glTranslatef((float) d + 0.5F, (float) d1 + 2.25F, (float) d2 + 0.5F);
		GL11.glScalef(1.0F, -1F, -1F);

		if(pile.getYOffset() > 0){
			
			if(pile.getMana().equals(ManaType.LIFE)){
				GL11.glPushMatrix();

				bindTexture(golemTexture);
				GL11.glTranslatef(0, -pile.getYOffset() + 1.5f, 0);
				golem.renderForRocks();
				GL11.glPopMatrix();

			}
			
			GL11.glTranslatef(0, pile.getYOffset(), 0);
		}


		bindTextureMap(brick);
		renderPileOfRocks();

		GL11.glPopMatrix();

	}

	public static void renderPileOfRocks(){
		renderBlock(brick, 0.3f, 0.25f, 0.1f, 0.27f);
		renderBlock(brick, 0.4f, -0.25f, 0.1f, 0.15f);
		renderBlock(brick, 0.5f, 0.2f, 0.1f, -0.15f);
		renderBlock(brick, 0.2f, 0.1f, -0.27f, 0.19f);

		renderBlock(rock, 0.25f, 0.25f, -0.15f, 0.27f);

		renderBlock(rock, 0.5f, 0f, 0f, 0f);
		renderBlock(rock, 0.3f, -0.1f, -0.4f, -0.1f);
		renderBlock(rock, 0.6f, -0.152f, -0.05f, -0.17f);
	}

	private static void renderBlock(ItemStack itemstack, float scale, float dx, float dy, float dz){
		GL11.glPushMatrix();

		if ((itemstack != null) && (itemstack.getItem() != null)) {

			if (itemstack.getItemSpriteNumber() == 0 && itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {

				float f2 = 0.25F;
				int i1 = Block.getBlockFromItem(itemstack.getItem()).getRenderType();

				if ((i1 == 1) || (i1 == 19) || (i1 == 12) || (i1 == 2)) {
					f2 = 0.5F;
				}

				if (scale > 0f) {
					f2 = scale;
				}

				f2 = scale;
				GL11.glTranslatef(dx, 2f + dy, dz);
				GL11.glScalef(f2, f2, f2);

				float f3 = 1.0F;
				GL11.glRotatef(180, 1, 0, 0);

				blockrender.renderBlockAsItem(Block.getBlockFromItem(itemstack.getItem()), itemstack.getItemDamage(), f3);
			}
		}
		GL11.glPopMatrix();
	}

	private static void bindTextureMap(ItemStack item) {
		Minecraft.getMinecraft().
		renderEngine.
		bindTexture(
				RenderManager.
				instance.
				renderEngine.
				getResourceLocation(
						item.
						getItemSpriteNumber()));
	}
}
