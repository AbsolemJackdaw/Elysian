package net.darkhax.elysian.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.darkhax.elysian.data.PlayerElysianProperties;
import net.darkhax.elysian.items.ItemTarotCard;
import net.darkhax.elysian.packet.ElysianPackets;
import net.darkhax.elysian.util.ManaType;
import net.darkhax.elysian.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiTarrotBook extends GuiScreen {

	EntityPlayer player;

	private int[] selectedCards = new int[Reference.SELECTABLECARDS];

	private int[] cardPos = new int[] { 20, 12, 5, 15, 7, 8, 22, 19, 4, 2, 1, 18, 2, 9, 21, 16, 17, 10, 6, 11, 13, 14 };

	private int[] manatypes = new int[Reference.SELECTABLECARDS];

	private int page = 0;
	private final int pageMax = 3;
	private final int pageMin = 0;

	PlayerElysianProperties prop;

	private List cardsToolTip = new ArrayList();

	private static final ResourceLocation bookGui = new ResourceLocation("elysian", "/textures/gui/book_tarot.png");

	public GuiTarrotBook(EntityPlayer player) {

		super();

		this.player = player;
		prop = PlayerElysianProperties.get(player);

		// debug line. all values should initially be -1 !
		for (int i = 0; i < Reference.SELECTABLECARDS; i++) {

			selectedCards[i] = prop.getCardsHighlightedInBook()[i];
		}
	}

	@Override
	public void initGui() {

		int posX = (this.width) / 2;
		int posY = (this.height) / 2;

		this.buttonList.clear();
		this.buttonList.add(new GuiButton(100, posX - 256 / 2 - 21, posY - 128 / 2 - 21, 20, 20, "X"));
		this.buttonList.add(new GuiFlipPageButton(101, posX + 256 / 2 - 32, posY + 40, 32, 16, true));
		this.buttonList.add(new GuiFlipPageButton(102, posX - 256 / 2, posY + 40, 32, 16, false));

		for (int i = 0; i < 6; i++) {

			if (!(page == 3 && i > 3)) {

				int cardID = getCardIDFromButtonID(i);

				if (prop.hasCollectedCard(cardID)) {

					int u = MathHelper.floor_float((float) cardID % 6) - 1;
					int v = MathHelper.floor_float((float) cardID / 6);
					int x = i <= 2 ? posX - 114 + 36 * i : posX - 98 + 36 * i;
					this.buttonList.add(new GuiCardButton(i, x, posY - 22, 32, 48, u, v));
				}
			}
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {

		int posX = (this.width) / 2;
		int posY = (this.height) / 2;

		Minecraft.getMinecraft().renderEngine.bindTexture(bookGui);
		/* x on screen, y on screen , x in texture, y in texture, x size, y size */
		drawTexturedModalRect(posX - 256 / 2, posY - 128 / 2, 0, 0, 256, 128);
		drawTexturedModalRect(posX - 70, posY - 40, 64 + (page * 32), 128, 16, 16);
		drawTexturedModalRect(posX + 52, posY - 40, 64 + 16 + (page * 32), 128, 16, 16);

		super.drawScreen(par1, par2, par3);
		int i = 0;

		for (int selectedCard : selectedCards) {
			if (selectedCard > 0) {
				i++;
				if (page == MathHelper.floor_double((float) selectedCard / (float) 6)) {

					int buttonOnPage = selectedCard % 6 - 1;
					int x = buttonOnPage <= 2 ? posX - 114 + 36 * buttonOnPage : posX - 98 + 36 * buttonOnPage;
					Minecraft.getMinecraft().renderEngine.bindTexture(bookGui);
					//draws yellow outline for cards. this means it is selected
					drawTexturedModalRect(x, posY - 22, 0, 160, 32, 48);

				}
			}
		}
		//draws the number of selected cards as an integer, next to its cap
		fontRendererObj.drawString(i + "/" + selectedCards.length, posX - 256/2 + 5, posY - 128/2 - 10, 0xffffff);

		fillTempManaBar();
		drawManaCalculator(posX, posY);

		for (int l = 0; l < this.buttonList.size(); ++l)
		{
			GuiButton guibutton = (GuiButton)this.buttonList.get(l);
			if(guibutton instanceof GuiCardButton){

				boolean b = par1 >= guibutton.xPosition &&
						par2 >= guibutton.yPosition &&
						par1 < guibutton.xPosition + /*guibutton.getButtonWidth()*/ 32 &&
						par2 < guibutton.yPosition + /*guibutton.getButtonWidth()*/ 48;

						cardsToolTip.clear();
						cardsToolTip.add(ItemTarotCard.getNameFromID(getCardIDFromButtonID(guibutton.id)));

						if (b)
							drawToolTip(cardsToolTip /*button tool tip list*/, par1, par2, fontRendererObj);
			}
		}
	}

	@Override
	public void drawBackground(int p_146278_1_) {

		super.drawBackground(p_146278_1_);
	}

	@Override
	public boolean doesGuiPauseGame() {

		// must be false to be able to send packets
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		super.actionPerformed(button);

		if (button.id >= 100) {

			if (button.id == 102) {

				if (page != pageMin) {

					page--;
					initGui();
				}
			}

			if (button.id == 101) {

				if (page != pageMax) {

					page++;
					initGui();
				}
			}

			if (button.id == 100) {

				player.closeScreen();
			}

		}

		else {

			boolean containsCard = false;

			System.out.println("card clicked");
			int buttonID = page * 6 + button.id + 1;

			for (int i = 0; i < selectedCards.length; i++) {

				if (selectedCards[i] == buttonID) {

					containsCard = true;
					selectedCards[i] = 0;
					break;
				}
			}

			if (!containsCard) {

				for (int i = 0; i < selectedCards.length; i++) {

					if (selectedCards[i] == 0) {

						selectedCards[i] = buttonID;
						break;
					}
				}
			}

			for (int selectedCard : selectedCards) {

				if (selectedCard > 0) {

					System.out.println(selectedCard);
				}
			}
		}
	}

	@Override
	public void onGuiClosed() {

		sendPacket();
	}

	private void sendPacket() {

		ElysianPackets.INSTANCE.syncGuiDataToServer(selectedCards);
	}

	private void drawToolTip(List list, int par1, int par2, FontRenderer font) {

		if (!list.isEmpty())
		{
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			int k = 0;
			Iterator iterator = list.iterator();

			while (iterator.hasNext())
			{
				String s = (String)iterator.next();
				int l = font.getStringWidth(s);

				if (l > k)
				{
					k = l;
				}
			}

			int j2 = par1 + 12;
			int k2 = par2 - 12;
			int i1 = 8;

			if (list.size() > 1)
			{
				i1 += 2 + (list.size() - 1) * 10;
			}

			if (j2 + k > this.width)
			{
				j2 -= 28 + k;
			}

			if (k2 + i1 + 6 > this.height)
			{
				k2 = this.height - i1 - 6;
			}

			this.zLevel = 300.0F;
			//itemRender.zLevel = 300.0F;
			int j1 = -267386864;
			this.drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1);
			this.drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1);
			this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1);
			this.drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1);
			this.drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1);
			int k1 = 1347420415;
			int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
			this.drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1);
			this.drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1);
			this.drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1);

			for (int i2 = 0; i2 < list.size(); ++i2)
			{
				String s1 = (String)list.get(i2);
				font.drawStringWithShadow(s1, j2, k2, -1);

				if (i2 == 0)
				{
					k2 += 2;
				}

				k2 += 10;
			}

			this.zLevel = 0.0F;
			//itemRender.zLevel = 0.0F;
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			RenderHelper.enableStandardItemLighting();
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}

	private int getCardIDFromButtonID(int buttonID){
		int dex = page * 6 + buttonID;
		return cardPos[dex];
	}


	/**draws the mana calculator*/
	private void drawManaCalculator(int posX, int posY){
		//draws the mana bonus on screen from selected cards
		fontRendererObj.drawString("Bonus Mana  : " +
				manatypes[0] + "/"+
				manatypes[1] + "/"+
				manatypes[2] + "/"+
				manatypes[3] + "/"+
				manatypes[4] + "/"+
				manatypes[5] + "/"+
				manatypes[6],
				posX-1, posY - 128/2 - 25, 0xffffff);
		//draws the current player mana total on screen
		fontRendererObj.drawString("Player Mana : " +
				prop.getMana(ManaType.FIRE) + "/" +
				prop.getMana(ManaType.WATER) + "/" +
				prop.getMana(ManaType.AIR) + "/" +
				prop.getMana(ManaType.EARTH) + "/" +
				prop.getMana(ManaType.LIGHT) + "/" +
				prop.getMana(ManaType.DARKNESS) + "/" +
				prop.getMana(ManaType.LIFE),
				posX, posY - 128/2 - 16, 0xffffff);

		//draws the total mana on screen (current + bonus)
		fontRendererObj.drawString("Mana Total   : " +
				(prop.getMana(ManaType.FIRE)    + manatypes[0]) + "/" +
				(prop.getMana(ManaType.WATER)   + manatypes[1])+ "/" +
				(prop.getMana(ManaType.AIR)     + manatypes[2])+ "/" +
				(prop.getMana(ManaType.EARTH)   + manatypes[3]) + "/" +
				(prop.getMana(ManaType.LIGHT)   + manatypes[4])+ "/" +
				(prop.getMana(ManaType.DARKNESS)+ manatypes[5]) + "/" +
				(prop.getMana(ManaType.LIFE)    + manatypes[6]),
				posX, posY - 128/2 - 7, 0xffffff);
	}


	/**fills the bonus mana calculator with the appropriate numbers*/
	private void fillTempManaBar(){
		int a,b,c,d,e,f,g;
		a=b=c=d=e=f=g=0;

		for (int selectedCard : selectedCards) {
			if(selectedCard > 0){
				if(selectedCard < 4){
					a++;
				}else if(selectedCard  < 7){
					b++;
				}else if(selectedCard  < 10){
					c++;
				}else if(selectedCard  < 13){
					d++;
				}else if(selectedCard  < 16){
					e++;
				}else if(selectedCard  < 19){
					f++;
				}else if(selectedCard  < 23){
					g++;
				}
			}
		}
		manatypes[0] = (a*10)*a;
		manatypes[1] = (b*10)*b;
		manatypes[2] = (c*10)*c;
		manatypes[3] = (d*10)*d;
		manatypes[4] = (e*10)*e;
		manatypes[5] = (f*10)*f;
		manatypes[6] = (g*10)*g;
	}
}