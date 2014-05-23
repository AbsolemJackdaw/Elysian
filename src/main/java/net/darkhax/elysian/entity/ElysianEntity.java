package net.darkhax.elysian.entity;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ElysianEntity {

    public ElysianEntity() {

        register();
    }

    private void register() {

    	EntityRegistry.registerGlobalEntityID(EntityGreenDragon.class, "ElysianGreenDragon", EntityRegistry.findGlobalUniqueEntityId(), 0x00ff00, 0xff00ff);
        EntityRegistry.registerGlobalEntityID(EntityElysianDragonfly.class, "ElysianDragonfly", EntityRegistry.findGlobalUniqueEntityId(), 0x00ffff, 0x0000ff);
        EntityRegistry.registerGlobalEntityID(EntityRuneGolem.class, "ElysianRuneGolem", EntityRegistry.findGlobalUniqueEntityId(), 0xf0ffff, 0x0f00ff);
        EntityRegistry.registerGlobalEntityID(EntityEnvironementCreature.class, "ElysianEnvironementEntity", EntityRegistry.findGlobalUniqueEntityId(), 0xf0f0ff, 0x0ff0f0);

    }
}
