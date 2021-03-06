package com.github.arrabal.koth.proxy;

import com.github.arrabal.koth.api.block.ISoKBlock;
import com.github.arrabal.koth.client.texture.RestoredVanillaResourcePack;
import com.github.arrabal.koth.reference.Reference;
import com.github.arrabal.koth.settings.ConfigurationSettings;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by Arrabal on 2/20/2016.
 */
public class ClientProxy extends CommonProxy {

    private static List<Block> blocksToColor = Lists.newArrayList();

    @Override
    public ClientProxy getClientProxy() {
        return this;
    }

    @Override
    public void registerKeyBindings() {
        //do stuff
    }

    @Override
    public void registerRendering() {
        //((BlockSoKLeaves)ModBlocks.leaf_0).setGraphicsLevel(minecraft.gameSettings.fancyGraphics);
    }

    @Override
    public void registerColoring() {
        for (Block block : blocksToColor){
            ISoKBlock sokBlock = (ISoKBlock) block;

            if (sokBlock.getBlockColor() != null)
                Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(sokBlock.getBlockColor(), block);
            if (sokBlock.getItemColor() != null)
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler(sokBlock.getItemColor(), block);
        }
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
        if (item != Items.AIR){
            ModelLoader.registerItemVariants(item, new ResourceLocation(Reference.MOD_PREFIX + name));
            ModelLoader.setCustomModelResourceLocation(item, metaData, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
        }
    }

    @Override
    public void registerBlockSided(Block block) {
        if (block instanceof ISoKBlock){
            ISoKBlock sokBlock = (ISoKBlock) block;
            IProperty[] nonRenderingProps = sokBlock.getNonRenderingProperties();

            if (nonRenderingProps != null){
                IStateMapper custom_mapper = (new StateMap.Builder()).ignore(nonRenderingProps).build();
                ModelLoader.setCustomStateMapper(block, custom_mapper);
            }

            if (sokBlock.getBlockColor() != null || sokBlock.getItemColor() != null){
                blocksToColor.add(block);
            }
        }
    }

    @Override
    public void registerVillage() {
        super.registerVillage();
    }

    @Override
    public void processTextureOverrides() {
        if (ConfigurationSettings.USE_VANILLA_TEXTURES){
            FMLClientHandler clientHandler = FMLClientHandler.instance();

            List<IResourcePack> resourcePackList = ReflectionHelper.getPrivateValue(FMLClientHandler.class, clientHandler, "resourcePackList");
            Map<String, IResourcePack> resourcePackMap = ReflectionHelper.getPrivateValue(FMLClientHandler.class, clientHandler, "resourcePackMap");
            AbstractResourcePack resourcePack = (AbstractResourcePack) clientHandler.getResourcePackFor(Reference.MOD_ID);

            resourcePackList.remove(resourcePack);
            resourcePackMap.remove(Reference.MOD_ID);

            RestoredVanillaResourcePack restoredVanillaResourcePack = new RestoredVanillaResourcePack(FMLCommonHandler.instance().findContainerFor(Reference.MOD_ID));

            resourcePackList.add(restoredVanillaResourcePack);
            resourcePackMap.put(Reference.MOD_ID, restoredVanillaResourcePack);
        }
    }

    @Override
    public void registerOBJLoader() {
        OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        this.registerOBJLoader();
        this.registerKeyBindings();
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        this.registerColoring();
        this.registerRendering();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }
}
