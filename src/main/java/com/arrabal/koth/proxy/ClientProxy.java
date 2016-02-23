package com.arrabal.koth.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by Arrabal on 2/20/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public ClientProxy getClientProxy() {
        return this;
    }

    @Override
    public void registerKeyBindings() {
        //do stuff
    }

    @Override
    public void initRenderingAndTextures() {
        //do stuff
    }

    @Override
    public void registerEventHandlers(){
        //do stuff
    }

    @Override
    public void registerFluidBlockRendering(Block block, String name) {
        //do stuff
    }

    @Override
    public void registerItemVariantModel(Item item, String name, int metaData) {
        //do stuff
    }

    @Override
    public void registerNonRenderingProperties(Block block) {
        //do stuff
    }
}
