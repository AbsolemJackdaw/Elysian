package net.darkhax.elysian;

import net.darkhax.elysian.blocks.ElysianBlocks;
import net.darkhax.elysian.blocks.containers.RecipesHoloTable;
import net.darkhax.elysian.items.ElysianItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class ElysianRecipes {

	public ElysianRecipes() {

		RecipesHoloTable.addRecipe(new ItemStack[]{new ItemStack(Items.gold_ingot), new ItemStack(Blocks.dirt)},
				new ItemStack(Blocks.command_block));

		RecipesHoloTable.addRecipe(new ItemStack[]{new ItemStack(Blocks.dirt), new ItemStack(Blocks.dirt)},
				new ItemStack(Items.nether_wart));

		for(int i = 0; i < 7; i++)
			RecipesHoloTable.addRecipe(new ItemStack[]{new ItemStack(ElysianBlocks.log, 1, i), new ItemStack(ElysianItems.runicComponent)},
					new ItemStack(ElysianItems.keyStone, 1, i));

		GameRegistry.addRecipe(new ItemStack(ElysianItems.runePickaxeDepleted),
				new Object[]{"xxx","0a0","0a0", 'a', ElysianBlocks.cobble, 'x', ElysianItems.runicComponent});

		GameRegistry.addRecipe(new ItemStack(ElysianItems.runeAxeDepleted),
				new Object[]{"xx0","xa0","0a0", 'a', ElysianBlocks.cobble, 'x', ElysianItems.runicComponent});

		GameRegistry.addRecipe(new ItemStack(ElysianItems.runeAxeDepleted),
				new Object[]{"0xx","0ax","0a0", 'a', ElysianBlocks.cobble, 'x', ElysianItems.runicComponent});

		GameRegistry.addRecipe(new ItemStack(ElysianItems.runeSpadeDepleted),
				new Object[]{"0x0","0a0","0a0", 'a', ElysianBlocks.cobble, 'x', ElysianItems.runicComponent});
		
		GameRegistry.addRecipe(new ItemStack(ElysianBlocks.plank, 4, 0),
				new Object[]{"x", 'x', ElysianBlocks.log});

		GameRegistry.addRecipe(new ItemStack(Blocks.crafting_table),
				new Object[]{"xx","xx", 'x', ElysianBlocks.plank});
		
		GameRegistry.addRecipe(new ItemStack(Blocks.furnace),
				new Object[]{"xxx","x x","xxx", 'x', ElysianBlocks.cobble});
	}

}
