package com.arrabal.koth.handler;

import com.arrabal.koth.util.WorldGenerationHelper;
import com.arrabal.koth.world.gen.feature.WorldGenCedar;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Arrabal on 3/4/2016.
 */
public class WorldEventHandler {

    private WorldGenerator cedarTreeGenerator = new WorldGenCedar();

    @SubscribeEvent
    public void onDecorateBiomeEvent(DecorateBiomeEvent.Decorate event){
        if (event.type == DecorateBiomeEvent.Decorate.EventType.TREE){
            BiomeGenBase eventBiome = event.world.getBiomeGenForCoords(event.pos);
            if (BiomeDictionary.isBiomeOfType(eventBiome, BiomeDictionary.Type.PLAINS) && !BiomeDictionary.isBiomeOfType(eventBiome, BiomeDictionary.Type.HOT)){
                // insert generation of cedar trees into plains type biomes
                WorldGenerationHelper.insertSparseTreeGenerator(event.world, event.rand, event.pos, this.cedarTreeGenerator);
                event.setResult(Event.Result.ALLOW);
                return;
            }
        }
    }
}
