package com.github.arrabal.koth.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

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
    public void registerRendering() {
        //do nothing
    }

    @Override
    public void registerColoring() {
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
    public void registerBlockSided(Block block) {
        //do nothing
    }

    @Override
    public void processTextureOverrides() {
        // do nothing
    }

    @Override
    public void registerOBJLoader() {
        // do nothing
    }
}
