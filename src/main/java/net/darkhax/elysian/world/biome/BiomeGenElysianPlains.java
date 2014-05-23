package net.darkhax.elysian.world.biome;

import java.util.List;

import net.darkhax.elysian.blocks.ElysianBlocks;
import net.darkhax.elysian.entity.EntityElysianDragonfly;
import net.darkhax.elysian.entity.EntityEnvironementCreature;
import net.darkhax.elysian.entity.EntityGreenDragon;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenElysianPlains extends BiomeGenBase {

	public BiomeGenElysianPlains(int par1) {

		super(par1);

		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();

		this.topBlock = ElysianBlocks.grass;
		this.fillerBlock = ElysianBlocks.dirt;
		this.setBiomeName("Elysian Plains");
		this.setDisableRain();
		this.waterColorMultiplier = 0xE42D17;
		setColor(0xE42D17);
		setEnableSnow();
		
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityGreenDragon.class, 1, 1, 1));
		this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityElysianDragonfly.class, 1, 1, 1));
		this.spawnableCaveCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityEnvironementCreature.class, 5, 5, 10));

	}
	
	
	@Override
	public List getSpawnableList(EnumCreatureType par1EnumCreatureType)
    {
        return par1EnumCreatureType == EnumCreatureType.monster ? this.spawnableMonsterList : (par1EnumCreatureType == EnumCreatureType.creature ? this.spawnableCreatureList : (par1EnumCreatureType == EnumCreatureType.waterCreature ? this.spawnableWaterCreatureList : (par1EnumCreatureType == EnumCreatureType.ambient ? this.spawnableCaveCreatureList : null)));
    }
}