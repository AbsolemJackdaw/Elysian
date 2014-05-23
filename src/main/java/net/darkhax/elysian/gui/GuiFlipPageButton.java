package net.darkhax.elysian.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiFlipPageButton extends GuiButton {

    protected static final ResourceLocation button = new ResourceLocation("elysian", "textures/gui/book_tarot.png");

    /** true left, false right */
    private boolean left;

    public GuiFlipPageButton(int par1, int par2, int par3, int par4, int par5, boolean b) {

        super(par1, par2, par3, par4, par5, "");
        left = b;
    }

    @Override
    public void drawButton(Minecraft mc, int var1, int var2) {

        if (this.visible) {

            boolean flag = var1 >= this.xPosition && var2 >= this.yPosition && var1 < this.xPosition + this.width && var2 < this.yPosition + this.height;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            int u = 0;
            int v = 128;

            if (left) {

                u += 32;
            }

            if (flag) {

                v += 16;
            }

            // mc.getTextureManager().bindTexture(buttonTextures);
            // this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 67,
            // width, height);

            mc.getTextureManager().bindTexture(button);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, width, height);
        }
    }
}