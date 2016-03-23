package com.arrabal.koth;

import com.arrabal.koth.command.SoKCommand;
import com.arrabal.koth.handler.*;
import com.arrabal.koth.init.*;
import com.arrabal.koth.network.Network;
import com.arrabal.koth.proxy.IProxy;
import com.arrabal.koth.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Arrabal on 2/19/2016.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY_CLASS)
public class SignOfKoth {

    @Mod.Instance(Reference.MOD_ID)
    public static SignOfKoth instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event){

        event.registerServerCommand(new SoKCommand());
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(event.getModConfigurationDirectory() + Reference.CONFIG_FOLDER);
        Network.init();
        proxy.registerKeyBindings();
        //ModEvents.init();
        ModItems.init();
        ModBlocks.init();

        ModVanillaCompatability.init();
        proxy.registerEventHandlers();

        ModCrafting.init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.registerColoring();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        TileEntities.init();
        proxy.registerRendering();
        proxy.registerEventHandlers();
        proxy.registerVillage();

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
