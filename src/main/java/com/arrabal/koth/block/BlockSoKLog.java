package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.item.ItemSoKBlock;
import com.arrabal.koth.reference.enums.SoKLogs;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;

/**
 * Created by Arrabal on 3/1/2016.
 */
public class BlockSoKLog extends BlockLog implements ISoKBlock {

    public static final PropertyEnum<SoKLogs> VARIANT = PropertyEnum.<SoKLogs>create("variant", SoKLogs.class);

    public BlockSoKLog(){
        super();
        this.setDefaultState(this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        this.setHarvestLevel("axe", 0);
    }

    @Override
    protected BlockState createBlockState(){
        return new BlockState(this, new IProperty[] {LOG_AXIS, VARIANT});
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta >> 2]).withProperty(VARIANT, SoKLogs.byMetaData(meta & 3));
    }

    @Override
    public int getMetaFromState(IBlockState state){
        SoKLogs wood = (SoKLogs) state.getValue(VARIANT);
        return ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal() * 4 + wood.getMetaData();
    }

    @Override
    public int damageDropped(IBlockState state){
        return this.getMetaFromState(state.withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemSoKBlock.class;
    }

    @Override
    public int getItemRenderColor(IBlockState blockState, int tintIndex) {
        return this.getRenderColor(blockState);
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

        SoKLogs wood = blockState.getValue(VARIANT);
        switch(wood){
            default:
                return wood.getName() + "_log";
        }
    }
}
