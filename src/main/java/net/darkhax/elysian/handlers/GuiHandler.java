package net.darkhax.elysian.handlers;

import net.darkhax.elysian.blocks.containers.ContainerHoloTable;
import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.darkhax.elysian.gui.GuiHoloTable;
import net.darkhax.elysian.gui.GuiTarrotBook;
import net.darkhax.elysian.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case Reference.OPENGUI_CHISELBLOCK:
			return new ContainerHoloTable(player.inventory, (TileEntityHoloTable) world.getTileEntity(x, y, z));
		default:
			break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch (ID) {
		case Reference.OPENGUI_TARROTBOOK:
			return new GuiTarrotBook(player);

		case Reference.OPENGUI_CHISELBLOCK:
			return new GuiHoloTable(player, (TileEntityHoloTable) world.getTileEntity(x, y, z));

		}

		return null;
	}
}