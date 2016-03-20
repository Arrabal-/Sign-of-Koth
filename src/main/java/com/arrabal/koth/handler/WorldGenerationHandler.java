package com.arrabal.koth.handler;

import com.arrabal.koth.util.LogHelper;
import com.arrabal.koth.world.WorldGenerationContainer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Arrabal on 2/22/2016.
 */
public class WorldGenerationHandler implements IWorldGenerator {

    private ConcurrentLinkedQueue<int[]> currentlyGenerating;
    private static ConcurrentHashMap<Integer, WorldGenerationContainer> generatorMap;

    public WorldGenerationHandler(){
        this.currentlyGenerating = new ConcurrentLinkedQueue<int[]>();

    }

    public static File getWorldSaveDir(World world){
        ISaveHandler saveHandler = world.getSaveHandler();
        if (saveHandler.getChunkLoader(world.provider) instanceof AnvilChunkLoader){
            AnvilChunkLoader loader = (AnvilChunkLoader) saveHandler.getChunkLoader(world.provider);
            for (Field f : loader.getClass().getDeclaredFields()){
                if (f.getType().equals(File.class)){
                    try{
                        f.setAccessible((true));
                        File saveLocation = (File) f.get(loader);
                        LogHelper.debug("Found world save directory as: " + saveLocation);
                        return saveLocation;
                    }
                    catch (Exception e){
                        LogHelper.error("Failed trying to locate world save directory");
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!world.isRemote){
            int[] tuple = {chunkX, chunkZ};
            if (this.currentlyGenerating.contains(tuple)){
                LogHelper.debug("Caught recursive generator call at [" + chunkX + ", " + chunkZ + "]");
            }
            else{
                WorldGenerationContainer handler = getGenContainerFromWorld(world);
                if (!handler.catchChunkBug(chunkX, chunkZ)){
                    this.currentlyGenerating.add(tuple);
                    if (world.provider instanceof WorldProviderHell){
                        //do nether stuff
                    }
                    else if (world.provider instanceof WorldProviderEnd){
                        //do end stuff
                    }
                    else{
                        //do overworld && other dimension stuff
                    }
                    this.currentlyGenerating.remove(tuple);
                }
            }
        }
    }

    public static WorldGenerationContainer getGenContainerFromWorld(World world){
        WorldGenerationContainer handler = null;
        if (!world.isRemote){
            if (!generatorMap.containsKey(world.provider.getDimension())){
                handler = new WorldGenerationContainer(world);
                generatorMap.put(world.provider.getDimension(), handler);
            }
            else{
                handler = generatorMap.get(world.provider.getDimension());
            }
        }
        return handler;
    }
}
