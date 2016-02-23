package com.arrabal.koth.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by Arrabal on 2/20/2016.
 */
public interface IProxy {

    public abstract ClientProxy getClientProxy();

    public abstract void registerKeyBindings();

    public abstract void initRenderingAndTextures();

    public abstract void registerEventHandlers();

    public abstract void registerFluidBlockRendering(Block block, String name);

    public abstract void registerItemVariantModel(Item item,String name, int metaData);

    public abstract void registerNonRenderingProperties(Block block);


}
