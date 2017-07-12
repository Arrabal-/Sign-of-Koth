package com.github.arrabal.koth.block;

import com.github.arrabal.koth.init.ModBlocks;
import com.github.arrabal.koth.reference.enums.SoKLogs;
import com.github.arrabal.koth.util.BlockStateHelper;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arrabal on 3/15/2016.
 */
public class BlockSoKDoubleWoodSlab extends BlockSoKWoodSlab {
    @Override
    public boolean isDouble() {
        return true;
    }

    @Override
    public IProperty[] getPresetProperties() {
        return new IProperty[] {VARIANT};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[] {HALF};
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {
        return ((SoKLogs) blockState.getValue(VARIANT)).getName() + "_double_slab";
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
        List<ItemStack> drops = new ArrayList<ItemStack>();
        SoKLogs wood = state.getValue(VARIANT);
        IBlockState halfState = this.getDefaultState().withProperty(VARIANT, wood).withProperty(HALF, EnumBlockHalf.BOTTOM);
        drops.add(new ItemStack(ModBlocks.wooden_slab, 2, ModBlocks.wooden_slab.getMetaFromState(halfState)));
        return drops;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs creativeTabs, NonNullList<ItemStack> list){
        ImmutableSet<IBlockState> presets = BlockStateHelper.getBlockstatePresets(this);
        for (IBlockState state : presets){
            list.add(new ItemStack(itemIn, 1, this.getMetaFromState(state)));
        }
    }
}
