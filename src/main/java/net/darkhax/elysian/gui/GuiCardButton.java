package net.darkhax.elysian.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiCardButton extends GuiButton {

    protected static final ResourceLocation button = new ResourceLocation("elysian", "textures/gui/cards.png");

    private int id;
    private int page;

    public GuiCardButton(int par1, int par2, int par3, int par4, int par5, int cardID, int page) {

        super(par1, par2, par3, par4, par5, "");
        id = cardID;
        this.page = page;
    }

    @Override
    public void drawButton(Minecraft mc, int var1, int var2) {

        if (this.visible) {

            boolean flag = var1 >= this.xPosition && var2 >= this.yPosition && var1 < this.xPosition + this.width && var2 < this.yPosition + this.height;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            int u = id * 32;
            int v = page * 48;

            mc.getTextureManager().bindTexture(button);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, u, v, width, height);

            if (flag) {

                this.drawTexturedModalRect(this.xPosition, this.yPosition, 224, 48, width, height);
            }
        }
    }
}