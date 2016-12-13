package com.arrabal.koth.block;

import com.arrabal.koth.reference.enums.SoKLogs;
import com.arrabal.koth.util.BlockStateHelper;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Arrabal on 3/15/2016.
 */
public class BlockSoKHalfWoodSlab extends BlockSoKWoodSlab {

    @Override
    public boolean isDouble() {
        return false;
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
        return ((SoKLogs) blockState.getValue(VARIANT)).getName() + "_slab";
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item itemIn, CreativeTabs creativeTabs, NonNullList<ItemStack> list){
        ImmutableSet<IBlockState> presets = BlockStateHelper.getBlockstatePresets(this);
        for (IBlockState state : presets){
            list.add(new ItemStack(itemIn, 1, this.getMetaFromState(state)));
        }
    }
}
