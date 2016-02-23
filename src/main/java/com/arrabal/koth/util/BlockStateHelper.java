package com.arrabal.koth.util;

import com.arrabal.koth.api.block.ISoKBlock;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Arrabal on 2/22/2016.
 *
 * Contains set of utility methods for working with blocks with subtypes based on BlockStates
 */
public class BlockStateHelper {

    //returns a set of states, representing all possible combinations of provided properties
    public static ImmutableSet<IBlockState> getBlockStateSet(IBlockState baseState, IProperty... properties){
        Stack<IProperty> propertyStack = new Stack<IProperty>();
        List<IBlockState> blockStates = new ArrayList<IBlockState>();
        for (IProperty property : properties) {
            propertyStack.push(property);
        }
        if (!propertyStack.isEmpty()){
            addBlockStatesToList(baseState, blockStates, propertyStack);
        }
        ImmutableSet<IBlockState> returnSet = ImmutableSet.copyOf(blockStates);
        return returnSet;
    }

    // uses recursion to add blockstates to a list
    private static void addBlockStatesToList(IBlockState blockState, List<IBlockState> list, Stack<IProperty> propertyStack){
        if (propertyStack.isEmpty()){
            list.add(blockState);
            return;
        }
        else{
            IProperty property = propertyStack.pop();
            for (Object value : property.getAllowedValues()){
                addBlockStatesToList(blockState.withProperty(property, (Comparable)value), list, propertyStack);
            }
            propertyStack.push(property);
        }
    }

    //return all possible preset block variations
    //only works on blocks implementing ISoKBlock, otherwise returns empty set
    public static ImmutableSet<IBlockState> getBlockstatePresets(Block block){
        if (!(block instanceof ISoKBlock)) return ImmutableSet.<IBlockState>of();
        IBlockState defaultState = block.getDefaultState();
        if (defaultState == null) defaultState = block.getBlockState().getBaseState();
        return getBlockStateSet(defaultState, ((ISoKBlock)block).getPresetProperties());
    }

    public static IProperty getPropertyByName(IBlockState blockState, String propertyName){
        for (IProperty property : (ImmutableSet<IProperty>) blockState.getProperties().keySet()){
            if (property.getName().equals(propertyName)) return property;
        }
        return null;
    }

    public static boolean isValidPropertyName(IBlockState blockState, String propertyName){
        return getPropertyByName(blockState, propertyName) != null;
    }

    public static Comparable getPropertyValueByName(IBlockState blockState, IProperty property, String valueName){
        for (Comparable value : (ImmutableSet<Comparable>) property.getAllowedValues()){
            if (value.toString().equals(valueName)) return value;
        }
        return null;
    }
}
