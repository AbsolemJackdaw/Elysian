package net.darkhax.elysian.blocks;

import java.util.Random;

import net.darkhax.elysian.blocks.containers.TileEntityPortal;
import net.darkhax.elysian.world.TeleporterElysian;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class BlockElysianPortal extends BlockElysian {

	public BlockElysianPortal() {

		super(Material.portal, "portal");
		this.setLightLevel(0.75F);
		this.setHardness(-1.0F);
		this.setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 0.0625F, 0.6F);
	}

	@Override
	public void registerBlockIcons(IIconRegister ireg) {
		blockIcon = ireg.registerIcon("elysian:portal");
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	private int timeUntillTeleport = 300;
	private static final int TIMER = 300;

	@Override
	public void onEntityCollidedWithBlock(World parWorld, int par2, int par3, int par4, Entity entity) {

		if(!(entity instanceof EntityLivingBase))
			return;

		if(!parWorld.isRemote)
		{
			System.out.println(timeUntillTeleport);
			EntityLivingBase e = (EntityLivingBase)entity;

			if (e.ridingEntity == null && e.riddenByEntity == null) {

				e.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 100));

				timeUntillTeleport--;

				if(timeUntillTeleport < 0){
					if (e.dimension != 2) {
						FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entity, 2, new TeleporterElysian(MinecraftServer.getServer().worldServerForDimension(2)));
					}
					else {
						FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) entity, 0, new TeleporterElysian(MinecraftServer.getServer().worldServerForDimension(0)));
					}
					timeUntillTeleport = TIMER;
				}
			}
		}
	}

	public boolean onBlockActivated (World parWorld, int x, int y, int z, EntityPlayer parEntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {

		if (parEntityPlayer.ridingEntity == null && parEntityPlayer.riddenByEntity == null && !parWorld.isRemote) {

			if (parEntityPlayer.dimension != 2) {

				FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) parEntityPlayer, 2, new TeleporterElysian(MinecraftServer.getServer().worldServerForDimension(2)));
			}

			else {

				FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) parEntityPlayer, 0, new TeleporterElysian(MinecraftServer.getServer().worldServerForDimension(0)));
			}
		}

		return super.onBlockActivated(parWorld, x, y, z, parEntityPlayer, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityPortal();
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		super.randomDisplayTick(world, x, y, z, rand);

		for (int l = 0; l < 50; ++l)
		{
			double d1 = y + (rand.nextFloat()*2.75f);
			double d2 = 0.0D;
			double d3 = 0.0D;
			double d4 = 0.0D;
			int i1 = (rand.nextInt(2) * 2) - 1;
			int j1 = (rand.nextInt(2) * 2) - 1;
			d2 = (rand.nextFloat() - 0.5D) * 0.125D;
			d3 = (rand.nextFloat() - 0.5D) * 0.125D;
			d4 = (rand.nextFloat() - 0.5D) * 0.125D;
			d4 = rand.nextFloat() * 1.0F * j1;
			d2 = rand.nextFloat() * 1.0F * i1;
			world.spawnParticle("portal", x+0.5, d1, z+0.5, d2, d3, d4);
		}
	}
}
