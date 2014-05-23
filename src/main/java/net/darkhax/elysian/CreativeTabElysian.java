package net.darkhax.elysian;

import java.util.List;

import net.darkhax.elysian.items.ElysianItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabElysian extends CreativeTabs {

    CreativeTabElysian(int id, String name) {

        super(id, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void displayAllReleventItems(List itemList) {

        super.displayAllReleventItems(itemList);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {

        return ElysianItems.tarotCard;
    }
}