package com.arrabal.koth.proxy;

import com.arrabal.koth.handler.BlockEventHandler;
import com.arrabal.koth.handler.WorldEventHandler;
import com.arrabal.koth.world.gen.structure.VillageAbandonedHouse;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

/**
 * Created by Arrabal on 2/20/2016.
 */
public abstract class CommonProxy implements IProxy{

    public void registerEventHandlers(){
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
    }

    public void registerVillage() {
        MapGenStructureIO.registerStructureComponent(VillageAbandonedHouse.class, "ViAbH");
        VillagerRegistry.instance().registerVillageCreationHandler(new VillageAbandonedHouse());
    }
}
