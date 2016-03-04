package com.arrabal.koth.api.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

/**
 * Created by Arrabal on 2/22/2016.
 */
public interface ISoKBlock {

    public Class<? extends ItemBlock> getItemClass();
    public int getItemRenderColor(IBlockState blockState, int tintIndex);
    public IProperty[] getPresetProperties();
    public IProperty[] getNonRenderingProperties();
    public String getBlockstateName(IBlockState blockState);
}
