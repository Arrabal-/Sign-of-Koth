package com.arrabal.koth.world.biome;

import com.arrabal.koth.reference.enums.BiomeTypes;
import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Arrabal on 3/21/2016.
 */
public class BiomeGenSoKForest extends BiomeGenBase {

    private BiomeTypes.Forests forestType;

    public BiomeGenSoKForest(BiomeTypes.Forests forestType, BiomeProperties properties) {
        super(properties);
        this.forestType = forestType;
    }
}
