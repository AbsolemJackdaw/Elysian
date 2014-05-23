package net.darkhax.elysian.blocks.containers;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class RecipesHoloTable {

	private static Map<ItemStack[], ItemStack> recipes = new HashMap<ItemStack[], ItemStack>();

	public RecipesHoloTable() {
		
	}

	public static void addRecipe(ItemStack[] stack, ItemStack result) {
		recipes.put(stack, result);
	}

	public static ItemStack getCraftingResult(ItemStack[] stack) {

		for (ItemStack element : stack) {
			if(element == null){
				return null;
			}
		}

		int ind = 0;

		for(ItemStack[] iss : recipes.keySet()){
			
			for(ItemStack i : iss){
				if(ind < 2)
					if(i.getItem().equals(stack[ind].getItem())){
						if(i.getItemDamage() == stack[ind].getItemDamage())
							ind++;
					}
			}
			if(ind == stack.length){
				return recipes.get(iss);
			}
		}
		return null;
	}
}