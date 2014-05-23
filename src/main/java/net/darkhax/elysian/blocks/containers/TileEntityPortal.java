package net.darkhax.elysian.blocks.containers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityPortal extends TileEntity {

    @Override
    public double getMaxRenderDistanceSquared() {

        return 65536.0D;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {

        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public boolean canUpdate() {

        return true;
    }

    @Override
    public Packet getDescriptionPacket() {

        NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

        this.readFromNBT(pkt.func_148857_g()); // packet.data
    }
}