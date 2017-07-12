package com.github.arrabal.koth.world.biome;

import com.github.arrabal.koth.reference.enums.BiomeTypes;
import net.minecraft.world.biome.Biome;

/**
 * Created by Arrabal on 3/21/2016.
 */
public class BiomeGenSoKForest extends Biome {

    private BiomeTypes.Forests forestType;

    public BiomeGenSoKForest(BiomeTypes.Forests forestType, BiomeProperties properties) {
        super(properties);
        this.forestType = forestType;
    }
}
