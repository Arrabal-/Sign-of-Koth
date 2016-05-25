package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.item.ItemSoKBlock;
import net.minecraft.block.BlockFence;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;

/**
 * Created by Arrabal on 3/30/2016.
 */
public class BlockSoKWoodFence extends BlockFence implements ISoKBlock {

    public BlockSoKWoodFence(IBlockState parentState){
        super(Material.WOOD, parentState.getMapColor());
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setSoundType(SoundType.WOOD);
    }


    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemSoKBlock.class;
    }

    @Override
    public IProperty[] getPresetProperties() {
        return new IProperty[]{};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[]{};
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
