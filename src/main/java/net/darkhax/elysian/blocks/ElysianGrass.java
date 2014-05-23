package net.darkhax.elysian.blocks;

import java.util.Random;

import net.darkhax.elysian.Elysian;
import net.minecraft.block.BlockGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Lots of methods in this class are not needed. Block should be changed to only include important
 * code.
 */
public class ElysianGrass extends BlockGrass {

    @SideOnly(Side.CLIENT)
    private IIcon top;

    @SideOnly(Side.CLIENT)
    private static int renderPass;

    public ElysianGrass() {

        super();
        this.setCreativeTab(Elysian.tabElysian);
        this.setBlockName("grass");
        setHarvestLevel("shovel", 0);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {

        return ElysianBlocks.dirt.getItemDropped(0, p_149650_2_, p_149650_3_);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {

        return side == 1 ? this.top : (side == 0 ? ElysianBlocks.dirt.getBlockTextureFromSide(side) : blockIcon);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess acces, int x, int y, int z, int side) {

        if (side == 1)
            return this.top;
        if (side == 0)
            return ElysianBlocks.dirt.getBlockTextureFromSide(side);
        
        return blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {

        return 0xffffff;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {

        return 16777215;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iRegister) {
        this.blockIcon = iRegister.registerIcon("elysian:grass_side");
        this.top = iRegister.registerIcon(this.getTextureName() + "_top");
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {

        if (!world.isRemote) {

            if (world.getBlockLightValue(x, y + 1, z) < 4 && world.getBlockLightOpacity(x, y + 1, z) > 2) {

                world.setBlock(x, y, z, ElysianBlocks.dirt);
            }

            else if (world.getBlockLightValue(x, y + 1, z) >= 9) {

                for (int l = 0; l < 4; ++l) {

                    int i1 = x + rand.nextInt(3) - 1;
                    int j1 = y + rand.nextInt(5) - 3;
                    int k1 = z + rand.nextInt(3) - 1;
                    world.getBlock(i1, j1 + 1, k1);

                    if (world.getBlock(i1, j1, k1) == ElysianBlocks.dirt && world.getBlockMetadata(i1, j1, k1) == 0 && world.getBlockLightValue(i1, j1 + 1, k1) >= 4 && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2) {

                        world.setBlock(i1, j1, k1, ElysianBlocks.grass);
                    }
                }
            }
        }
    }
}
