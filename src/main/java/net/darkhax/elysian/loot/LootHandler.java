package net.darkhax.elysian.loot;

import java.util.Random;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class LootHandler {

    private static ChestGenHooks elysianChest;

    public LootHandler() {

        initChestGroups();
    }

    public void initChestGroups() {

        elysianChest = ChestGenHooks.getInfo("elysianDungeonChest");
    }

    /**
     * method for adding items to the custom chest.
     *
     * @param stack: ItemStack being added.
     * @param min: What is the minimum of these that should appear.
     * @param max: What is the maximum of these that should appear.
     * @param weight: How often should these show up.
     */
    public static void addItemToChest(ItemStack stack, int min, int max, int weight) {

        elysianChest.addItem(new WeightedRandomChestContent(stack, min, max, weight));
    }

    /**
     * Sets the minimum and maximum amount of items that can show up in the
     * chest.
     *
     * @param min: The minimum amount of items.
     * @param max: The maximum amount of items.
     */
    public static void alterChestSize(int min, int max) {

        elysianChest.setMin(min);
        elysianChest.setMax(max);
    }

    /**
     * Not yet implemented: generates a chest with loot.
     *
     * @param te: the chest te.
     * @param category: the type of chest.
     * @param random: random.
     */
    public static void generateLoot(TileEntity te, String category, Random random) {

        if (te instanceof IInventory) {

            ChestGenHooks.getInfo("Category");
        }
    }
}