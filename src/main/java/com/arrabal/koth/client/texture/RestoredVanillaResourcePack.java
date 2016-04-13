package com.arrabal.koth.client.texture;

import com.arrabal.koth.reference.Reference;
import com.arrabal.koth.settings.ConfigurationSettings;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.FMLFileResourcePack;
import net.minecraftforge.fml.common.ModContainer;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Arrabal on 4/1/2016.
 */
public class RestoredVanillaResourcePack extends FMLFileResourcePack {

    private IResourcePack sokResourcePack;

    public RestoredVanillaResourcePack(ModContainer container){
        super(container);
        this.sokResourcePack = (IResourcePack) FMLClientHandler.instance().getResourcePackFor(Reference.MOD_ID);
    }

    @Override
    protected InputStream getInputStreamByName(String resourceName) throws IOException {
        if (resourceName.equals("assets/minecraft/textures/blocks/stone_diorite.png")){
            return sokResourcePack.getInputStream(new ResourceLocation("sok:/textures/replacements/blocks/stone_diorite.png"));
        }
        return sokResourcePack.getInputStream(new ResourceLocation(resourceName));
    }
}
