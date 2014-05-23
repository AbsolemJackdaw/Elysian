package net.darkhax.elysian.packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.data.PlayerElysianProperties;
import net.darkhax.elysian.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class ElysianPackets {

    public static final ElysianPackets INSTANCE = new ElysianPackets();

    ByteBuf buf = Unpooled.buffer();
    ByteBufOutputStream out = new ByteBufOutputStream(buf);

    public void syncAllDataToClient(EntityPlayer p) {

        ByteBuf buf = Unpooled.buffer();
        ByteBufOutputStream out = new ByteBufOutputStream(buf);

        PlayerElysianProperties prop = PlayerElysianProperties.get(p);

        try {

            out.writeInt(ServerPacket.SYNC_CLIENT);

            for (int i = 0; i < Reference.SELECTABLECARDS; i++) {

                out.writeInt(prop.getCardsHighlightedInBook()[i]);
            }

            int size = prop.getCollectedCardList().size();
            out.writeInt(size);

            for (int i = 0; i < size; i++) {

                out.writeInt(prop.getCollectedCardList().get(i));
            }

            if (!p.worldObj.isRemote) {

                Elysian.Channel.sendTo(new FMLProxyPacket(buf, Elysian.packetChannel), (EntityPlayerMP) p);
            }

            out.close();
        }

        catch (Exception e) {

        }
    }

    public void syncGuiDataToServer(int[] selectedCards) {

        ByteBuf buf = Unpooled.buffer();
        ByteBufOutputStream out = new ByteBufOutputStream(buf);

        try {

            out.writeInt(ServerPacket.SYNC_PLAYERDATA_TO_SERVER);

            for (int selectedCard : selectedCards) {

                out.writeInt(selectedCard);
            }

            out.close();
        }

        catch (Exception e) {

        }

        Elysian.Channel.sendToServer(new FMLProxyPacket(buf, Elysian.packetChannel));
    }

    public void syncGuiDataToClient(int index, int cardid, EntityPlayerMP p) {

        ByteBuf buf = Unpooled.buffer();
        ByteBufOutputStream out = new ByteBufOutputStream(buf);

        try {

            out.writeInt(ServerPacket.SYNC_BOOKCARDS_TO_CLIENT);
            out.writeInt(index);
            out.writeInt(cardid);
            out.close();
        }

        catch (Exception e) {

        }

        if (!p.worldObj.isRemote) {

            Elysian.Channel.sendTo(new FMLProxyPacket(buf, Elysian.packetChannel), p);
        }
    }
}