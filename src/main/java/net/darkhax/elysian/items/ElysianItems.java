package net.darkhax.elysian.items;

import net.darkhax.elysian.blocks.ElysianBlocks;
import net.darkhax.elysian.util.Reference;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ElysianItems {

	public static final ToolMaterial runeAxeMaterial = EnumHelper.addToolMaterial("elysianAxeMaterial", 3, 450, 3.0f, 2f, 0);
	public static final ToolMaterial runePickaxeMaterial = EnumHelper.addToolMaterial("elysianPickaxeMaterial", 3, 450, 10.0f, 4f, 0);
	public static final ToolMaterial runeSpadeMaterial = EnumHelper.addToolMaterial("elysianSpadeMaterial", 3, 450, 3.0f, 2f, 0);


	public static Item tarotCard = new ItemTarotCard("tarotCards");
	public static Item tarotBook = new ItemTarrotBook("tarrotBook");
	public static Item pattern = new ItemPattern("pattern");
	public static Item keyStone = new ItemKeyStone();
	public static Item runicComponent = new ItemComponent("runicComponent");

	public static Item runeAxeDepleted = new ItemDepletedTool().setUnlocalizedName("runeAxeDepleted");
	public static Item runeAxe = new ItemChargedTool(5.0f, runeAxeMaterial, "axe").setUnlocalizedName("runeAxe");

	public static Item runePickaxeDepleted = new ItemDepletedTool().setUnlocalizedName("runePickaxeDepleted");
	public static Item runePickaxe = new ItemChargedTool(5.0f, runePickaxeMaterial, "pickaxe").setUnlocalizedName("runePickaxe");

	public static Item runeSpadeDepleted = new ItemDepletedTool().setUnlocalizedName("runeSpadeDepleted");
	public static Item runeSpade = new ItemChargedTool(5.0f, runeSpadeMaterial, "spade").setUnlocalizedName("runeSpade");

	public static Item waterBucket = new ItemLifeWaterBucket();

	public static final ArmorMaterial rockArmor = EnumHelper.addArmorMaterial("rockArmor", 1500, new int[]{3,7,5,2}, 50);

	public static Item armorHelmet = new ItemElysianArmor(rockArmor, 0, 0).setUnlocalizedName("elysian:rockArmorHelmet");
	public static Item armorChest =  new ItemElysianArmor(rockArmor, 0, 1).setUnlocalizedName("elysian:rockArmorChest");
	public static Item armorLegs =   new ItemElysianArmor(rockArmor, 0, 2).setUnlocalizedName("elysian:rockArmorLegs");
	public static Item armorBoots =  new ItemElysianArmor(rockArmor, 0, 3).setUnlocalizedName("elysian:rockArmorBoots");

	public ElysianItems() {

		GameRegistry.registerItem(tarotCard, "test", Reference.MOD_ID);
		GameRegistry.registerItem(tarotBook, "tarotBook", Reference.MOD_ID);
		GameRegistry.registerItem(pattern, "pattern", Reference.MOD_ID);
		GameRegistry.registerItem(keyStone, "keyStone", Reference.MOD_ID);
		GameRegistry.registerItem(runicComponent, "runicComponent", Reference.MOD_ID);
		GameRegistry.registerItem(waterBucket, "waterBucket", Reference.MOD_ID);

		GameRegistry.registerItem(runeAxe, "runeAxe", Reference.MOD_ID);
		GameRegistry.registerItem(runeAxeDepleted, "runeAxeDepleted", Reference.MOD_ID);
		GameRegistry.registerItem(runePickaxe, "runePickaxe", Reference.MOD_ID);
		GameRegistry.registerItem(runePickaxeDepleted, "runePickaxeDepleted", Reference.MOD_ID);
		GameRegistry.registerItem(runeSpade, "runeSpade", Reference.MOD_ID);
		GameRegistry.registerItem(runeSpadeDepleted, "runeSpadeDepleted", Reference.MOD_ID);

		GameRegistry.registerItem(armorHelmet, "rockHelm", Reference.MOD_ID);
		GameRegistry.registerItem(armorChest, "rockChest", Reference.MOD_ID);
		GameRegistry.registerItem(armorLegs, "rockLegs", Reference.MOD_ID);
		GameRegistry.registerItem(armorBoots, "rockBoots", Reference.MOD_ID);

		FluidContainerRegistry.registerFluidContainer(
				new FluidStack(ElysianBlocks.LIFEWATER, FluidContainerRegistry.BUCKET_VOLUME),
				new ItemStack(waterBucket), new ItemStack(Items.bucket));
	}
}