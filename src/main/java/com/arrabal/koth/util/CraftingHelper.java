package com.arrabal.koth.util;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

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
}
