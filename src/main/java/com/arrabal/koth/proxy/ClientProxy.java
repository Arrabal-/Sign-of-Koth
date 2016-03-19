package com.arrabal.koth.proxy;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

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
        super.registerEventHandlers();
    }

    @Override
    public void registerFluidBlockRendering(Block block, String name) {
        //do stuff
    }

    @Override
    public void registerItemVariantModel(Item item, String name, int metaData) {
        if (item != null){
            ModelBakery.registerItemVariants(item, new ResourceLocation("sok:" + name));
            ModelLoader.setCustomModelResourceLocation(item, metaData, new ModelResourceLocation(Reference.LOWERCASE_MOD_ID + ":" + name, "inventory"));
        }
    }

    @Override
    public void registerNonRenderingProperties(Block block) {
        if (block instanceof ISoKBlock){
            ISoKBlock sokBlock = (ISoKBlock) block;
            IProperty[] nonRenderingProps = sokBlock.getNonRenderingProperties();

            if (nonRenderingProps != null){
                IStateMapper custom_mapper = (new StateMap.Builder()).ignore(nonRenderingProps).build();
                ModelLoader.setCustomStateMapper(block, custom_mapper);
            }
        }
    }
}
