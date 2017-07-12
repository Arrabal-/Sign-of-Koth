package com.github.arrabal.koth.proxy;

import com.github.arrabal.koth.SignOfKoth;
import com.github.arrabal.koth.handler.BlockEventHandler;
import com.github.arrabal.koth.handler.ConfigHandler;
import com.github.arrabal.koth.handler.GuiHandler;
import com.github.arrabal.koth.handler.WorldEventHandler;
import com.github.arrabal.koth.init.*;
import com.github.arrabal.koth.network.Network;
import com.github.arrabal.koth.reference.Reference;
import com.github.arrabal.koth.world.gen.structure.VillageAbandonedHouse;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

/**
 * Created by Arrabal on 2/20/2016.
 */
public abstract class CommonProxy implements IProxy{

    public void registerEventHandlers(){
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldEventHandler());
        MinecraftForge.EVENT_BUS.register(new BlockEventHandler());
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
    }

    public void registerVillage() {
        MapGenStructureIO.registerStructureComponent(VillageAbandonedHouse.class, "ViAbH");
        VillagerRegistry.instance().registerVillageCreationHandler(new VillageAbandonedHouse());
    }

    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(event.getModConfigurationDirectory() + Reference.CONFIG_FOLDER);
        Network.init();
        ModItems.init();
        ModBlocks.init();
        ModCrafting.init();
        ModVanillaCompatability.init();
        this.registerEventHandlers();

    }

    public void init(FMLInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(SignOfKoth.instance, new GuiHandler());
        TileEntities.init();
        this.registerVillage();
    }

    public void postInit(FMLPostInitializationEvent event){

    }
}
