package net.darkhax.elysian.proxy;

import net.darkhax.elysian.blocks.ElysianBlocks;
import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.darkhax.elysian.blocks.containers.TileEntityPileOfRocks;
import net.darkhax.elysian.blocks.containers.TileEntityPortal;
import net.darkhax.elysian.blocks.renderer.RenderHoloTable;
import net.darkhax.elysian.blocks.renderer.RenderPileOfRocks;
import net.darkhax.elysian.blocks.renderer.RenderPortal;
import net.darkhax.elysian.entity.EntityElysianDragonfly;
import net.darkhax.elysian.entity.EntityEnvironementCreature;
import net.darkhax.elysian.entity.EntityGreenDragon;
import net.darkhax.elysian.entity.EntityRuneGolem;
import net.darkhax.elysian.entity.model.ModelElysianDragon;
import net.darkhax.elysian.entity.model.ModelElysianDragonfly;
import net.darkhax.elysian.entity.model.ModelElysianGolem;
import net.darkhax.elysian.entity.renderer.RenderElysianDragon;
import net.darkhax.elysian.entity.renderer.RenderElysianDragonfly;
import net.darkhax.elysian.entity.renderer.RenderElysianEnvCreature;
import net.darkhax.elysian.entity.renderer.RenderElysianGolem;
import net.darkhax.elysian.items.ElysianItems;
import net.darkhax.elysian.items.renderer.RenderItemCarverTable;
import net.darkhax.elysian.items.renderer.RenderItemPileOfRocks;
import net.darkhax.elysian.items.renderer.RenderKeyStone;
import net.darkhax.elysian.packet.ClientPacket;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.FMLEventChannel;

public class ClientProxy extends CommonProxy {

	public static final ModelElysianGolem model = new ModelElysianGolem();
	
    @Override
    public void registerSidedEvents() {

    }

    @Override
    public void registerRenders() {

        RenderingRegistry.registerEntityRenderingHandler(EntityGreenDragon.class, new RenderElysianDragon(new ModelElysianDragon(), 0.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntityElysianDragonfly.class, new RenderElysianDragonfly(new ModelElysianDragonfly(), 0.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntityRuneGolem.class, new RenderElysianGolem(new ModelElysianGolem(), 0.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntityEnvironementCreature.class, new RenderElysianEnvCreature());

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityHoloTable.class, new RenderHoloTable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPortal.class, new RenderPortal());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPileOfRocks.class, new RenderPileOfRocks());

        MinecraftForgeClient.registerItemRenderer(ElysianItems.keyStone, new RenderKeyStone());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ElysianBlocks.carverTable), new RenderItemCarverTable());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ElysianBlocks.pileOfRocks), new RenderItemPileOfRocks());

    }

    @Override
    public void registerPacketHandlers(FMLEventChannel channel) {

        channel.register(new ClientPacket());
    }
}