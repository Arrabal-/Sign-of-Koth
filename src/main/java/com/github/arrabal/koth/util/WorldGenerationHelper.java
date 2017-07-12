package com.github.arrabal.koth.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by Arrabal on 3/4/2016.
 */
public class WorldGenerationHelper {

    public static void insertSparseTreeGenerator(World worldIn, Random randomGenerator, BlockPos blockPos, WorldGenerator generator){
        int treesPerChunk = 0;
        if (randomGenerator.nextInt(10) == 0) {
            treesPerChunk += (randomGenerator.nextInt(2) + 1);
        }
        for (int attempts = 0; attempts < treesPerChunk; attempts++){
            int relativeX = randomGenerator.nextInt(16) + 8;
            int relativeZ = randomGenerator.nextInt(16) + 8;
            BlockPos genPosition = worldIn.getHeight(blockPos.add(relativeX, 0, relativeZ));
            if (generator.generate(worldIn, randomGenerator, genPosition)){
                LogHelper.trace("inserted sparse tree generation into biome decoration");
            }
        }
    }
}
