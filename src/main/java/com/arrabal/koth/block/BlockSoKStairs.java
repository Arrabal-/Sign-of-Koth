package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.item.ItemSoKBlock;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;

/**
 * Created by Arrabal on 3/15/2016.
 */
public class BlockSoKStairs extends BlockStairs implements ISoKBlock{

    public BlockSoKStairs(IBlockState parentState){
        super(parentState);
        this.useNeighborBrightness = true;
    }


    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemSoKBlock.class;
    }

    @Override
    public IProperty[] getPresetProperties() {
        return new IProperty[] {};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[] {};
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {
        return super.getUnlocalizedName();
    }

    @Override
    public IBlockColor getBlockColor() {
        return null;
    }

    @Override
    public IItemColor getItemColor() {
        return null;
    }
}
