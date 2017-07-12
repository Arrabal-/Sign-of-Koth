package com.github.arrabal.koth.client.gui;

import com.github.arrabal.koth.handler.ConfigHandler;
import com.github.arrabal.koth.reference.Names;
import com.github.arrabal.koth.reference.Reference;
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
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new DummyConfigElement.DummyCategoryElement("Aesthetic Settings", Reference.MOD_ID,
                new ConfigElement(ConfigHandler.config.getCategory(Names.Configuration.CATEGORY_AESTHETICS)).getChildElements()));
        list.add(new DummyConfigElement.DummyCategoryElement("Mechanics Settings", Reference.MOD_ID,
                new ConfigElement(ConfigHandler.config.getCategory(Names.Configuration.CATEGORY_MECHANICS)).getChildElements()));

        return list;
    }

    public SoKGuiConfig(GuiScreen guiScreen){
        super(guiScreen, getConfigElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
