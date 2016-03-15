package com.arrabal.koth.proxy;

import com.arrabal.koth.handler.WorldEventHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Arrabal on 2/20/2016.
 */
public abstract class CommonProxy implements IProxy{

    public void registerEventHandlers(){
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldEventHandler());
    }
}
