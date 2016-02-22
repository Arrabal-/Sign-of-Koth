package com.arrabal.koth.world;

import net.minecraft.world.World;

/**
 * Created by Arrabal on 2/22/2016.
 */
public class WorldGenerationContainer {

    private ChunkGenerationLogger chunkLogger;

    public WorldGenerationContainer(){}

    public WorldGenerationContainer(World world){
        this.chunkLogger = (ChunkGenerationLogger) world.getPerWorldStorage().loadData(ChunkGenerationLogger.class, "SoKChunkLogger");
        if (this.chunkLogger == null){
            this.chunkLogger = new ChunkGenerationLogger("SoKChunkLogger");
            world.getPerWorldStorage().setData("SoKChunkLogger", this.chunkLogger);
        }
    }

    public boolean catchChunkBug(int chunkX, int chunkZ){
        return chunkLogger.catchChunkBug(chunkX, chunkZ);
    }
}
