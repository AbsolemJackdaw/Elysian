package net.darkhax.elysian.handlers;

import net.darkhax.elysian.data.PlayerElysianProperties;
import net.darkhax.elysian.packet.ElysianPackets;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class ElysianEventHandler {

	public ElysianEventHandler() {

		FMLCommonHandler.instance().bus().register(this);
	}


	/* PlayerCardProperties.get(e.player);
	 * syncClient(e.player);
	 *
	 * Gets the player data,
	 * then syncs that data to the client
	 *
	 * Data gets reset on death/respawn/changing dimensions
	 *
	 * */
	
	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerChangedDimensionEvent e) {

		PlayerElysianProperties.get(e.player);
		syncClient(e.player);
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e) {

		PlayerElysianProperties.get(e.player);
		syncClient(e.player);
	}

	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent e) {

		PlayerElysianProperties.get(e.player);
		syncClient(e.player);
	}

	private void syncClient(EntityPlayer p){

		ElysianPackets.INSTANCE.syncAllDataToClient(p);
	}
}