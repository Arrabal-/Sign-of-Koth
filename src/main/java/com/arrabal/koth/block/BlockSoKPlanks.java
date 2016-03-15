package com.arrabal.koth.block;

import com.arrabal.koth.reference.enums.SoKLogs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;

/**
 * Created by Arrabal on 3/12/2016.
 */
public class BlockSoKPlanks extends VariantSoKBlock {

    public static final PropertyEnum<SoKLogs> VARIANT = PropertyEnum.<SoKLogs>create("variant", SoKLogs.class);

    public BlockSoKPlanks(){
        super(Material.wood);
        this.setDefaultState(this.getDefaultState().withProperty(VARIANT, SoKLogs.BEECH));
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setStepSound(soundTypeWood);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    protected BlockState createBlockState(){
        return new BlockState(this, new IProperty[] {VARIANT});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, SoKLogs.byMetaData(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetaData();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    public IProperty[] getPresetProperties() {
        return new IProperty[] {VARIANT};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return null;
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {
        return blockState.getValue(VARIANT).getName() + "_planks";
    }
}
