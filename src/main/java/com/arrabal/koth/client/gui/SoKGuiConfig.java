package com.arrabal.koth.client.gui;

import com.arrabal.koth.handler.ConfigHandler;
import com.arrabal.koth.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arrabal on 2/20/2016.
 */
public class SoKGuiConfig extends GuiConfig {

    @SuppressWarnings("unchecked")
    private static List<IConfigElement> getConfigElements(){
        List<IConfigElement> list = new ArrayList<>();
        list.add(new DummyConfigElement.DummyCategoryElement("Mod Mechanics Settings", Reference.MOD_ID,
                new ConfigElement(ConfigHandler.config.getCategory(Reference.CONFIG_CATEGORY_MECHANICS)).getChildElements()));

        return list;
    }

    public SoKGuiConfig(GuiScreen guiScreen){
        super(guiScreen, getConfigElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
