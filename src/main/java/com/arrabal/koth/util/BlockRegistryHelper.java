package com.arrabal.koth.util;

import com.arrabal.koth.SignOfKoth;
import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.block.BlockSoKDoor;
import com.arrabal.koth.creativetab.SoKCreativeTabs;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Arrabal on 2/23/2016.
 */
public class BlockRegistryHelper {

    public static Block registerFluidBlock(Fluid fluid, BlockFluidBase fluidBlock, String name){
        Block block = GameRegistry.registerBlock(fluidBlock, null, name);
        SignOfKoth.proxy.registerFluidBlockRendering(block, name);
        return block;
    }

    public static Block registerDoor(BlockSoKDoor doorBlock, String name, Item doorItem){
        Block block = registerBlock(doorBlock, name, null);
        doorItem = ItemRegistryHelper.registerItem(new ItemDoor(block), name);
        doorBlock.setDoorItem(doorItem);
        return block;
    }

    public static void registerBlockVariant(Block block, String stateName, int stateMeta){
        Item item = Item.getItemFromBlock(block);
        SignOfKoth.proxy.registerItemVariantModel(item, stateName, stateMeta);
    }

    public static Block registerBlock(Block block, String blockName){
        return registerBlock(block, blockName, SoKCreativeTabs.tabSoKBlocks);
    }

    public static Block registerBlock(Block block, String blockName, CreativeTabs creativeTab){

        block.setUnlocalizedName(blockName);
        block.setCreativeTab(creativeTab);

        if (block instanceof ISoKBlock){
            ISoKBlock sokBlock = (ISoKBlock)block;
            GameRegistry.registerBlock(block, sokBlock.getItemClass(), blockName);
            SignOfKoth.proxy.registerNonRenderingProperties(block);
            IBlockState defaultState = block.getDefaultState();
            if (defaultState == null){
                defaultState = block.getBlockState().getBaseState();
                LogHelper.error("missing default state for " + block.getUnlocalizedName());
            }

            ImmutableSet<IBlockState> presets = BlockStateHelper.getBlockstatePresets(block);
            if (presets.isEmpty()){
                //no subtypes
                registerBlockVariant(block, blockName, 0);
            }
            else {
                //register all subtypes
                for (IBlockState state : presets){
                    String stateName = sokBlock.getBlockstateName(state);
                    int stateMeta = block.getMetaFromState(state);
                    registerBlockVariant(block, stateName, stateMeta);
                }
            }
        }
        else {
            //simple registration for vanilla-type blocks
            GameRegistry.registerBlock(block, ItemBlock.class, blockName);
            registerBlockVariant(block, blockName, 0);
        }
        return block;
    }
}
