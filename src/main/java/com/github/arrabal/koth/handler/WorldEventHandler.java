package com.github.arrabal.koth.handler;

import com.github.arrabal.koth.util.WorldGenerationHelper;
import com.github.arrabal.koth.world.gen.feature.WorldGenCedar;
import net.minecraft.world.biome.Biome;
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
        if (event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE){
            Biome eventBiome = event.getWorld().getBiome(event.getPos());
            if (BiomeDictionary.hasType(eventBiome, BiomeDictionary.Type.PLAINS) && !BiomeDictionary.hasType(eventBiome, BiomeDictionary.Type.HOT)){
                // insert generation of cedar trees into plains type biomes
                WorldGenerationHelper.insertSparseTreeGenerator(event.getWorld(), event.getRand(), event.getPos(), this.cedarTreeGenerator);
                event.setResult(Event.Result.ALLOW);
                return;
            }
        }
    }
}
