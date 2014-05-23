package net.darkhax.elysian.items;

import net.darkhax.elysian.util.ManaType;
import net.minecraft.item.ItemStack;

public interface ICastingItem {

    /**
     * Gets the ammount of mana this item uses. The order of mana types is Fire,
     * Water, Earth, Air, Light, Dark, Duality
     *
     * @return int[]: the mana usage.
     */
    int[] getManaUsage();

    ManaType getManaType(ItemStack stack);
}