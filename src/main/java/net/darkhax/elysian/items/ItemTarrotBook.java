package net.darkhax.elysian.items;

import net.darkhax.elysian.Elysian;
import net.darkhax.elysian.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTarrotBook extends ItemElysian {

    public ItemTarrotBook(String itemName) {

        super(itemName);
        this.setCreativeTab(Elysian.tabElysian);
        this.setTextureName("elysian:book");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        if (world.isRemote) {

            player.openGui(Elysian.instance, Reference.OPENGUI_TARROTBOOK, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        }

        return super.onItemRightClick(stack, world, player);
    }
}