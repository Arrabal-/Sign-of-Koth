package com.github.arrabal.koth.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Arrabal on 2/20/2016.
 */
public interface IProxy {

    public abstract ClientProxy getClientProxy();

    public abstract void registerKeyBindings();

    public abstract void registerRendering();

    public abstract void registerColoring();

    public abstract void registerEventHandlers();

    public abstract void registerFluidBlockRendering(Block block, String name);

    public abstract void registerItemVariantModel(Item item,String name, int metaData);

    public abstract void registerBlockSided(Block block);

    public abstract void registerVillage();

    public abstract void processTextureOverrides();

    public abstract void registerOBJLoader();

    public abstract void preInit(FMLPreInitializationEvent event);

    public abstract void init(FMLInitializationEvent event);

    public abstract void postInit(FMLPostInitializationEvent event);
}
