package com.github.arrabal.koth.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;

/**
 * Created by Arrabal on 3/16/2016.
 */
public class BlockSoKSiding extends VariantSoKBlock {

    public static final PropertyBool SHAKE = PropertyBool.create("is_shake");
    public static final PropertyBool WEATHERED = PropertyBool.create("weathered");

    public BlockSoKSiding(){
        super(Material.WOOD);
        this.setDefaultState(this.getDefaultState().withProperty(SHAKE, Boolean.FALSE).withProperty(WEATHERED, Boolean.FALSE));
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setSoundType(SoundType.WOOD);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {SHAKE, WEATHERED});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        if ((meta & 1) != 0) state = state.withProperty(WEATHERED, Boolean.TRUE);
        if ((meta & 2) != 0) state = state.withProperty(SHAKE, Boolean.TRUE);
        return state;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = 0;
        if (state.getValue(WEATHERED)) meta |= 1;
        if (state.getValue(SHAKE)) meta |= 2;
        return meta;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    public IProperty[] getPresetProperties() {
        return new IProperty[] {SHAKE, WEATHERED};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return null;
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {
        String name = "_siding";
        boolean isWeathered = blockState.getValue(WEATHERED);
        if (blockState.getValue(SHAKE)) name = "_shake" + name;
        if (isWeathered) name = "weathered_cedar" + name;
        else name = "cedar" + name;
        return name;
    }
}
