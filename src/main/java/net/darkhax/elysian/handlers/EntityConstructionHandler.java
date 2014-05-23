package net.darkhax.elysian.handlers;

import java.util.HashMap;
import java.util.Map;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.blocks.ElysianBlocks;
import net.darkhax.elysian.data.PlayerElysianProperties;
import net.darkhax.elysian.entity.EntityElysianDragonfly;
import net.darkhax.elysian.items.ElysianItems;
import net.darkhax.elysian.items.ItemElysianArmor;
import net.darkhax.elysian.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityConstructionHandler {

	private static final ResourceLocation glow= new ResourceLocation("elysian:/textures/mobs/rune_golem_glow.png");
	private static final ResourceLocation golem= new ResourceLocation("elysian:/textures/mobs/rune_golem.png");

	boolean [] stacks = new boolean[4];

	boolean [] stacks2 = new boolean[4];

	public EntityConstructionHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		buckets.put(ElysianBlocks.water, ElysianItems.waterBucket);
	}

	@SubscribeEvent
	public void playerUpdate(LivingUpdateEvent evt){

		if(evt.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)evt.entityLiving;
			if(hasArmor(player)){
				
			}
		}
	}

	@SubscribeEvent
	public void playerAttack(LivingHurtEvent evt){

		/**if this entity is attacked by a player*/
		if(evt.source.getSourceOfDamage() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)evt.source.getSourceOfDamage();
			if(hasArmor(player)){
				evt.ammount += 4;
			}
		}

		/**if the player(you) is being attacked*/
		if(evt.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)evt.entityLiving;
			if(hasArmor(player)){
				evt.ammount -= MathHelper.floor_float((evt.ammount) * 0.5f);
			}
		}
	}

	@SubscribeEvent
	public void playerJumping(LivingJumpEvent evt){

		if(evt.entityLiving instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)evt.entityLiving;
			if(hasArmor(player)){
				evt.entityLiving.motionY = 0;
			}
		}
	}

	private boolean hasArmor(EntityPlayer player){

		for(int i = 0; i < 4; i++){
			if(player.inventory.armorInventory[i] != null)
				stacks2[i] = (player.inventory.armorInventory[i].getItem() instanceof ItemElysianArmor);
			else
				stacks2[i] = false;
		}

		if(stacks2[0] && stacks2[1] && stacks2[2] && stacks2[3] ){
			return true;
		}

		return false;
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void renderplayer(RenderPlayerEvent.SetArmorModel evt){

		if(evt.stack != null)
			if(evt.stack.getItem() instanceof ItemElysianArmor)
				stacks[evt.slot] = true;
			else
				stacks[evt.slot] = false;
		else
			stacks[evt.slot] = false;


		if(stacks[0] == true && stacks[1] == true && stacks[2] == true && stacks[3] == true){

			evt.entityPlayer.setInvisible(true);

			ClientProxy.model.isChild = false;

			ClientProxy.model.Head.rotateAngleX = evt.renderer.modelBipedMain.bipedHead.rotateAngleX;
			ClientProxy.model.Head.rotateAngleY = evt.renderer.modelBipedMain.bipedHead.rotateAngleY;
			ClientProxy.model.Head.rotateAngleZ = evt.renderer.modelBipedMain.bipedHead.rotateAngleZ;

			ClientProxy.model.ArmLeft.rotateAngleX = evt.renderer.modelBipedMain.bipedLeftArm.rotateAngleX;
			ClientProxy.model.ArmLeft.rotateAngleY = evt.renderer.modelBipedMain.bipedLeftArm.rotateAngleY;
			ClientProxy.model.ArmLeft.rotateAngleZ = evt.renderer.modelBipedMain.bipedLeftArm.rotateAngleZ;

			ClientProxy.model.ArmRight.rotateAngleX = evt.renderer.modelBipedMain.bipedRightArm.rotateAngleX;
			ClientProxy.model.ArmRight.rotateAngleY = evt.renderer.modelBipedMain.bipedRightArm.rotateAngleY;
			ClientProxy.model.ArmRight.rotateAngleZ = evt.renderer.modelBipedMain.bipedRightArm.rotateAngleZ;

			ClientProxy.model.LegLeft.rotateAngleX = evt.renderer.modelBipedMain.bipedLeftLeg.rotateAngleX;
			ClientProxy.model.LegLeft.rotateAngleY = evt.renderer.modelBipedMain.bipedLeftLeg.rotateAngleY;
			ClientProxy.model.LegLeft.rotateAngleZ = evt.renderer.modelBipedMain.bipedLeftLeg.rotateAngleZ;

			ClientProxy.model.LegRight.rotateAngleX = evt.renderer.modelBipedMain.bipedRightLeg.rotateAngleX;
			ClientProxy.model.LegRight.rotateAngleY = evt.renderer.modelBipedMain.bipedRightLeg.rotateAngleY;
			ClientProxy.model.LegRight.rotateAngleZ = evt.renderer.modelBipedMain.bipedRightLeg.rotateAngleZ;

			GL11.glPushMatrix();
			GL11.glScalef(1.4f, 1.4f, 1.4f);
			GL11.glTranslatef(0, -0.4f, 0);
			for(int i = 0; i < 2; i ++){
				Minecraft.getMinecraft().renderEngine.bindTexture(i == 0 ? golem : glow);

				if(i == 1){
					GL11.glColor3f(0f, 0.4f, 0.2f);

				}
				else{
					GL11.glColor3f(1.0f, 1.0f, 1.0f);
				}

				ClientProxy.model.render(0.0625f);
				GL11.glColor3f(1.0f, 1.0f, 1.0f);
			}

			GL11.glPopMatrix();
			GL11.glColor3f(1.0f, 1.0f, 1.0f);

			evt.result = 0;
		}else{
			evt.entityPlayer.setInvisible(false);
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void icons(TextureStitchEvent.Pre evt){

		if(evt.map.getTextureType() == 0){ //0 is blocks, 1 items
			Elysian.effect =  evt.map.registerIcon("elysian:particles/powerParticleEffect");
			Elysian.entity =  evt.map.registerIcon("elysian:particles/particleEnvironement");
		}
	}

	@SubscribeEvent
	public void onEntityConstruction(EntityConstructing event) {

		if (event.entity instanceof EntityPlayer && PlayerElysianProperties.get((EntityPlayer) event.entity) == null) {

			PlayerElysianProperties.register((EntityPlayer) event.entity);
		}

		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties(PlayerElysianProperties.CARD_PROPS) == null) {

			event.entity.registerExtendedProperties(PlayerElysianProperties.CARD_PROPS, new PlayerElysianProperties((EntityPlayer) event.entity));
		}

		//Kindly proposing this for compacter code. works very nice too ! :D ~Subaraki
		//		if ((event.entity instanceof EntityPlayer)&& (PlayerCardProperties.get((EntityPlayer) event.entity) == null))
		//			PlayerCardProperties.register((EntityPlayer) event.entity);
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {

		if (!event.entity.worldObj.isRemote && (event.entity instanceof EntityPlayer)) {

			PlayerElysianProperties.loadProxyData((EntityPlayer) event.entity);
		}

		if(event.entity instanceof EntityElysianDragonfly){
			EntityElysianDragonfly eedf = (EntityElysianDragonfly) event.entity;

			BiomeGenBase biome = event.world.getBiomeGenForCoords((int)eedf.posX, (int)eedf.posZ);
			eedf.setColor(biome.color);
		}
	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {

		// we only want to save data for players (most likely, anyway)
		if (!event.entity.worldObj.isRemote && (event.entity instanceof EntityPlayer)) {

			PlayerElysianProperties.saveProxyData((EntityPlayer) event.entity);
		}
	}



	@SubscribeEvent
	public void onBucketFill(FillBucketEvent evt){
		System.out.println("bucket 2");

		ItemStack result = fillCustomBucket(evt.world, evt.target);

		if (result == null)
			return;
		evt.result = result;
		evt.setResult(Result.ALLOW);
	}

	public static Map<Block, Item> buckets = new HashMap<Block, Item>();

	private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) {

		Block block = world.getBlock(pos.blockX, pos.blockY, pos.blockZ);

		Item bucket = buckets.get(block);

		if (bucket != null && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) {
			world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
			return new ItemStack(bucket);
		} else
			return null;
	}
}