package com.arrabal.koth.init;

import com.arrabal.koth.handler.WorldEventHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Arrabal on 3/4/2016.
 */
public class ModEvents {

    public static void init(){
        MinecraftForge.TERRAIN_GEN_BUS.register(new WorldEventHandler());
    }
}
