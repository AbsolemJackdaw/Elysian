package net.darkhax.elysian.items.renderer;

import net.darkhax.elysian.blocks.model.ModelCarverTable;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderItemCarverTable implements IItemRenderer{

	ResourceLocation table = new ResourceLocation("elysian", "textures/blocks/engraving_table.png");
	ModelCarverTable modelTable = new ModelCarverTable();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		float f = 1.0f;

		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(table);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glTranslatef(0, -1, 0);

		switch (type) {
		case EQUIPPED_FIRST_PERSON:
			GL11.glTranslatef(0, -1f, -0.6f);
			GL11.glScalef(f, f, f);
			break;

		case EQUIPPED:
			f = 1.2f;
			GL11.glRotatef(-55, 1, 0, 0);
			GL11.glRotatef(-40, 0, 0, 1);
			GL11.glTranslatef(0, -0.2f, 0);
			GL11.glScalef(f, f, f);
			break;

		case ENTITY:
			break;

		case INVENTORY:
			GL11.glScalef(f, f, f);
			break;

		default:
			break;
		}

		modelTable.render(0.0625f);

		GL11.glPopMatrix();


	}

}
