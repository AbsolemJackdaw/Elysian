package net.darkhax.elysian.entity;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGreenDragon extends DragonFriendly {

    public EntityGreenDragon(World par1World) {
    	super(par1World);
	}

	/**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {

        return super.attackEntityFrom(par1DamageSource, par2);
    }

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity
     * has recently been hit by a player. @param par2 - Level of Looting used to
     * kill this mob.
     */
    @Override
    protected void dropFewItems(boolean par1, int par2) {
    }

    public int getColor(){
    	return 0x029a18;
    }
}
