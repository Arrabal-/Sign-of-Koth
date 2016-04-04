package com.arrabal.koth.proxy;

import com.arrabal.koth.world.gen.structure.VillageAbandonedHouse;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

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
}
