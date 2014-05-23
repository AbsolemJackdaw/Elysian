package net.darkhax.elysian.items.renderer;

import net.darkhax.elysian.items.ICastingItem;
import net.darkhax.elysian.items.models.ModelKeyStone;
import net.darkhax.elysian.util.ManaType;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderKeyStone implements IItemRenderer {

	ResourceLocation keystone = new ResourceLocation("elysian", "textures/items/keystone/keystone.png");
	ResourceLocation overlay = new ResourceLocation("elysian", "textures/items/keystone/keystone_overlay.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	ModelKeyStone stone = new ModelKeyStone();
	float theSin;

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		float f = 2.0f;
		GL11.glPushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(keystone);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glTranslatef(0, -1, 0);

		switch (type) {
		case EQUIPPED_FIRST_PERSON:
			f = 1.5f;
			GL11.glTranslatef(0, -1.5f, -0.6f);
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
			GL11.glTranslatef(0, -0.8f, 0);
			GL11.glScalef(f, f, f);
			break;

		default:
			break;
		}

		stone.render(0.0625f);

		Minecraft.getMinecraft().renderEngine.bindTexture(overlay);
		int color = ManaType.manaTypeToColor(((ICastingItem) item.getItem()).getManaType(item));

		float red = (color & 0xFF0000) >> 16;
		float green = (color & 0x00FF00) >> 8;
		float blue = (color & 0x0000FF);

		float sin = 0f;
		if (type != ItemRenderType.INVENTORY) {
			theSin += 0.5f;
			sin = MathHelper.sin(theSin/40);
		}

		GL11.glColor3f(red / 255f <= sin ? sin : red / 255f,
				green / 255f <= sin ? sin : green / 255f,
						blue / 255f <= sin ? sin : blue / 255f);

		stone.render(0.0625f);

		GL11.glPopMatrix();
	}

}
