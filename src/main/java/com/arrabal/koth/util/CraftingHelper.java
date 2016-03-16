package com.arrabal.koth.util;

import com.arrabal.koth.api.block.ISoKBlock;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arrabal on 3/15/2016.
 */
public class CraftingHelper {

    private static <T extends Enum<T> & IStringSerializable> int getMetaFromProperty(Block block, IProperty property, T value){
        IBlockState state = block.getDefaultState().withProperty(property, value);
        return block.getMetaFromState(state);
    }

    public static <T extends Enum<T> & IStringSerializable> ItemStack getItemStackFromProperty(Block block, int howMany, IProperty property, T value){
        return new ItemStack(block, howMany, getMetaFromProperty(block, property, value));
    }

    public static List<ItemStack> getItemStackForAllBlockstates(Block block, int howMany){
        List<ItemStack> list = new ArrayList<ItemStack>();
        if (block instanceof ISoKBlock){
            ImmutableSet<IBlockState> presets = BlockStateHelper.getBlockstatePresets(block);
            if (presets.isEmpty()){
                // no variants
                list.add(new ItemStack(block, howMany, 0));
            }
            else{
                for (IBlockState state : presets){
                    list.add(new ItemStack(block, howMany, block.getMetaFromState(state)));
                }
            }
        }
        return list;
    }
}
