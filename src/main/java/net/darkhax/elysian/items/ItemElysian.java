package net.darkhax.elysian.items;

import net.darkhax.elysian.Elysian;
import net.minecraft.item.Item;

public class ItemElysian extends Item {

    public ItemElysian(String itemName) {

        this.setCreativeTab(Elysian.tabElysian);
        this.setUnlocalizedName("elysian." + itemName);
    }
}