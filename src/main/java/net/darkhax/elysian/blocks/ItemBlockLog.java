package net.darkhax.elysian.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockLog extends ItemBlock {

    public ItemBlockLog(Block block) {

        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {

        return this.field_150939_a.func_149735_b(2, BlockColored.func_150032_b(meta));
    }

    @Override
    public int getMetadata(int meta) {

        return meta;
    }
}