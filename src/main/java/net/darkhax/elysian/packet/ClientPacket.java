package net.darkhax.elysian.packet;

import io.netty.buffer.ByteBufInputStream;
import net.darkhax.elysian.data.PlayerElysianProperties;
import net.darkhax.elysian.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class ClientPacket extends ServerPacket {

    @SubscribeEvent
    public void onClientPacket(ClientCustomPacketEvent event) {

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        ByteBufInputStream dis = new ByteBufInputStream(event.packet.payload());

        PlayerElysianProperties prop = PlayerElysianProperties.get(player);

        try {

            int guiID = dis.readInt();

            switch (guiID) {

                case SYNC_CLIENT:

                    for (int i = 0; i < Reference.SELECTABLECARDS; i++) {

                        prop.setCardSelectedInBook(i, dis.readInt());
                    }

                    int size = dis.readInt();

                    for (int i = 0; i < size; i++) {

                        prop.addCardToCollection(dis.readInt());
                    }

                    break;

                case SYNC_BOOKCARDS_TO_CLIENT:

                    int index = dis.readInt();
                    int card = dis.readInt();

                    prop.setCardSelectedInBook(index, card);

                    break;
            }

            dis.close();
        }

        catch (Exception e) {

        }
    }
}
