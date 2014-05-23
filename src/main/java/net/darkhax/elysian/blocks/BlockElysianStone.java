package net.darkhax.elysian.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockElysianStone extends BlockElysian {

    public BlockElysianStone() {

        super(Material.rock, "stone");
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par3) {

        return Item.getItemFromBlock(ElysianBlocks.cobble);
    }
}