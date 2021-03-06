package com.github.arrabal.koth.handler;

import com.github.arrabal.koth.reference.Names;
import com.github.arrabal.koth.reference.Reference;
import com.github.arrabal.koth.settings.ConfigurationSettings;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by Arrabal on 2/20/2016.
 */
public class ConfigHandler {

    public static File worldFile;
    public static Configuration config;


    public static void init(String configPath){
        if (config==null){
            worldFile = new File(configPath + "SignOfKoth.cfg");
            config = new Configuration(worldFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration(){

        try{
            //mod options
            ConfigurationSettings.USE_VANILLA_TEXTURES = config.get(Names.Configuration.CATEGORY_AESTHETICS, "bVanillaTextures", false,
                    "Set to true to disable overridden vanilla textures.").setRequiresMcRestart(true).getBoolean();


        } catch (Exception e){

        } finally {
            if (config.hasChanged()){
                config.save();
            }
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.getModID().equalsIgnoreCase(Reference.MOD_ID)){
            //re-sync configs
            loadConfiguration();
        }
    }
}
