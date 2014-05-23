package net.darkhax.elysian.proxy;

import java.util.HashMap;
import java.util.Map;

import net.darkhax.elysian.packet.ServerPacket;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.FMLEventChannel;

public class CommonProxy {

    /**
     * Used to store IExtendedEntityProperties data temporarily between player
     * death and respawn
     */
    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

    /**
     * Removes the compound from the map and returns the NBT tag stored for name
     * or null if none exists
     */
    public static NBTTagCompound getEntityData(String name) {

        return extendedEntityData.remove(name);
    }

    /**
     * Adds an entity's custom data to the map for temporary storage
     *
     * @param compound
     *            An NBT Tag Compound that stores the IExtendedEntityProperties
     *            data only
     */
    public static void storeEntityData(String name, NBTTagCompound compound) {

        extendedEntityData.put(name, compound);
    }

    public void registerPacketHandlers(FMLEventChannel channel) {

        channel.register(new ServerPacket());
    }

    public void registerSidedEvents() {

    }

    public void registerRenders() {

    }
}