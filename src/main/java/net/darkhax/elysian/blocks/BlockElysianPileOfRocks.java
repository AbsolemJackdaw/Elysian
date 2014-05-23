package net.darkhax.elysian.blocks;

import net.darkhax.elysian.blocks.containers.TileEntityPileOfRocks;
import net.darkhax.elysian.items.ItemKeyStone;
import net.darkhax.elysian.util.ManaType;
import net.darkhax.elysian.util.StackUtils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockElysianPileOfRocks extends BlockElysian {

	public BlockElysianPileOfRocks(Material material, String name, String tool,
			int level) {
		super(material, name, tool, level);
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityPileOfRocks();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float f1, float f2, float f3) {

		ItemStack equipedStack = player.getCurrentEquippedItem();

		if(equipedStack != null){
			if(equipedStack.getItem() instanceof ItemKeyStone){

				TileEntityPileOfRocks pile = ((TileEntityPileOfRocks)world.getTileEntity(x, y, z));

				if(pile.getYOffset() <= 0f)
					switch(((ItemKeyStone)equipedStack.getItem()).getManaType(equipedStack))
					{
					case LIFE:
						if(StackUtils.hasKey(equipedStack, "charge")){
							if(equipedStack.stackTagCompound.getInteger("charge") > 1200){

								equipedStack.stackTagCompound.setInteger("charge",
										equipedStack.stackTagCompound.getInteger("charge") - 1200);
								
								pile.setMana(ManaType.LIFE);
								pile.triggerEvent();
							}
						}

						break;

					default:
						break;
					}
			}
		}

		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}


}
