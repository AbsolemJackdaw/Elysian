package net.darkhax.elysian.blocks;

import net.darkhax.elysian.blocks.containers.TileEntityHoloTable;
import net.darkhax.elysian.blocks.containers.TileEntityPileOfRocks;
import net.darkhax.elysian.blocks.containers.TileEntityPortal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ElysianBlocks {

	public static final int renderCarvedBlocks = RenderingRegistry.getNextAvailableRenderId();

	public static Block stone;
	public static ElysianGrass grass;
	public static Block dirt;
	public static Block cobble;
	public static Block log;
	public static Block plank;
	public static Block moss;
	public static Block frame;
	public static Block portal;
	public static Block carverTable;
	public static Block pileOfRocks;
	public static Block water;
	public static Block essence;
	public static Block leaves;
	
	public static final String FLUIDNAME = "elysianFluid";
	public static Fluid LIFEWATER = new Fluid(FLUIDNAME).setViscosity(2000).setLuminosity(15).setDensity(1100).setTemperature(295);
	
	public ElysianBlocks() {

		addBlocks();
		registerBlocks();
		registerTiles();
	}

	private void addBlocks() {

		stone = new BlockElysianStone().setBlockTextureName("elysian:stone").setHardness(1.5f).setResistance(10.0f);
		grass = (ElysianGrass) new ElysianGrass().setBlockTextureName("elysian:grass").setHardness(0.5f).setLightLevel(0.5f);
		dirt = new BlockElysian(Material.ground, "dirt", "shovel", 0).setBlockTextureName("elysian:dirt").setHardness(0.5f).setLightLevel(0.5f);
		cobble = new BlockElysian(Material.rock, "cobble", "pickaxe", 0).setBlockTextureName("elysian:cobble").setHardness(1.5f);
		log = new BlockElysianLog(Material.wood, "log").setHardness(2f).setLightLevel(0.3f);
		plank = new BlockElysian(Material.wood, "plan").setBlockTextureName("elysian:wood_plank").setHardness(2f);
		moss = new BlockElysian(Material.rock, "moss", "pickaxe", 0).setBlockTextureName("elysian:mossy_stone").setHardness(1.5f);
		frame = new BlockElysian(Material.rock, "frame", "pickaxe", 1).setBlockTextureName("elysian:frame").setHardness(5.0f).setLightLevel(0.3f);
		portal = new BlockElysianPortal().setBlockTextureName("elysian:portal");
		carverTable = new BlockElysianHoloTable(Material.wood).setBlockTextureName("stone").setHardness(5f);
		pileOfRocks = new BlockElysianPileOfRocks(Material.rock, "pileOfRocks", "pickaxe", 4).setHardness(25f).setResistance(40f);
		essence = new BlockEssence().setHardness(5.0f).setResistance(5.0f);
		leaves = new BlockElysianLeaves().setHardness(0.5f).setResistance(0.5f).setLightLevel(0.3f).setBlockName("elysian:leaves");
		
		FluidRegistry.registerFluid(LIFEWATER);
		water = new BlockElysianWater(LIFEWATER, Material.water).setBlockName("elysian.elysianWater").setLightLevel(1.0f);
		LIFEWATER.setUnlocalizedName(water.getUnlocalizedName());
	}

	private void registerBlocks() {

		GameRegistry.registerBlock(stone, "stone");
		GameRegistry.registerBlock(grass, "grass");
		GameRegistry.registerBlock(dirt, "dirt");
		GameRegistry.registerBlock(cobble, "cobble");
		GameRegistry.registerBlock(log, ItemBlockLog.class, "log");
		GameRegistry.registerBlock(plank, "plank");
		GameRegistry.registerBlock(moss, "moss");
		GameRegistry.registerBlock(frame, "frame");
		GameRegistry.registerBlock(portal, "portal");
		GameRegistry.registerBlock(carverTable, "chiselBlock");
		GameRegistry.registerBlock(pileOfRocks, "pileOfRocks");
		GameRegistry.registerBlock(water, "elysianFluid");
		GameRegistry.registerBlock(essence, "elysianEssence");
		GameRegistry.registerBlock(leaves, "elysianLeaves");
	}

	private void registerTiles() {

	       GameRegistry.registerTileEntity(TileEntityHoloTable.class, "chiselTable");
	       GameRegistry.registerTileEntity(TileEntityPortal.class, "portal");
	       GameRegistry.registerTileEntity(TileEntityPileOfRocks.class, "pileOfRocks");
	}
}