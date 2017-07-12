package com.github.arrabal.koth;

import com.github.arrabal.koth.command.SoKCommand;
import com.github.arrabal.koth.handler.*;
import com.github.arrabal.koth.init.*;
import com.github.arrabal.koth.proxy.IProxy;
import com.github.arrabal.koth.reference.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

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
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
