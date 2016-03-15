package com.arrabal.koth.proxy;

import com.arrabal.koth.handler.WorldEventHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Arrabal on 2/20/2016.
 */
public class ServerProxy extends CommonProxy {

    @Override
    public ClientProxy getClientProxy() {
        return null;
    }

    @Override
    public void registerKeyBindings() {
        // do nothing
    }

    @Override
    public void initRenderingAndTextures() {
        //do nothing
    }

    @Override
    public void registerFluidBlockRendering(Block block, String name) {
        //do nothing
    }

    @Override
    public void registerItemVariantModel(Item item, String name, int metaData) {
        //do nothing
    }

    @Override
    public void registerNonRenderingProperties(Block block) {
        //do nothing
    }
}
