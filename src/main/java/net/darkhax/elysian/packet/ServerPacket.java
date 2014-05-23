package net.darkhax.elysian.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.data.PlayerElysianProperties;
import net.darkhax.elysian.util.Reference;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class ServerPacket {

    public static final int SYNC_PLAYERDATA_TO_SERVER = 0;
    public static final int SYNC_CLIENT = 1;
    public static final int SYNC_BOOKCARDS_TO_CLIENT = 0;

    @SubscribeEvent
    public void onServerPacket(ServerCustomPacketEvent event) {

        if (!event.packet.channel().equals(Elysian.packetChannel)) {

            return;
        }

        EntityPlayerMP player = ((NetHandlerPlayServer) event.handler).playerEntity;
        ByteBuf buf = event.packet.payload();
        ByteBufInputStream dis = new ByteBufInputStream(buf);

        System.out.println("Server packet recieved");

        try {

            int identifier = dis.readInt();

            switch (identifier) {
                case SYNC_PLAYERDATA_TO_SERVER:

                    PlayerElysianProperties prop = PlayerElysianProperties.get(player);

                    for (int i = 0; i < Reference.SELECTABLECARDS; i++) {

                        int card = dis.readInt();
                        prop.setCardSelectedInBook(i, card);

                        if (card > 0) {

                            System.out.println(card);
                        }
                    }

                    break;

                default:

                    break;
            }

            dis.close();
        }

        catch (Exception e) {

        }
    }
}