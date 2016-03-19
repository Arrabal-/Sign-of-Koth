package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.creativetab.SoKCreativeTabs;
import com.arrabal.koth.reference.enums.SoKLogs;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arrabal on 3/15/2016.
 */
public abstract class BlockSoKWoodSlab extends BlockSlab implements ISoKBlock {

    public static final PropertyEnum<SoKLogs> VARIANT = PropertyEnum.<SoKLogs>create("variant", SoKLogs.class);

    public BlockSoKWoodSlab(){
        super(Material.wood);
        IBlockState iblockstate = this.blockState.getBaseState();
        if (!this.isDouble()){
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
            this.setCreativeTab(SoKCreativeTabs.tabSoKBlocks);
        }
        this.setDefaultState(iblockstate.withProperty(VARIANT, SoKLogs.BEECH));
        this.useNeighborBrightness = !this.isDouble();
        this.setHardness(2.0f);
        this.setResistance(5.0f);
        this.setStepSound(SoundType.WOOD);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {HALF, VARIANT});
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {return null;}

    @Override
    public IBlockColor getBlockColor() {return null;}

    @Override
    public IItemColor getItemColor() {return null;}

    @Override
    public String getUnlocalizedName(int meta){
        return "tile." + this.getBlockstateName(this.getStateFromMeta(meta));
    }

    @Override
    public IProperty<?> getVariantProperty(){
        return VARIANT;
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack){
        return SoKLogs.byMetaData(stack.getMetadata() & 7);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, SoKLogs.byMetaData(meta & 7));
        if (!this.isDouble()){
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
        }
        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state){
        SoKLogs wood = state.getValue(VARIANT);
        int meta = 0;
        meta |= wood.getMetaData();
        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP){
            meta |= 8;
        }
        return meta;
    }

    @Override
    public int damageDropped(IBlockState state){
        // always drop a bottom slab
        return this.getMetaFromState(state.withProperty(HALF, EnumBlockHalf.BOTTOM));
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune){
        List<ItemStack> drops = new ArrayList<ItemStack>();
        SoKLogs wood = state.getValue(VARIANT);
        IBlockState halfState = this.getStateFromMeta(this.damageDropped(state));
        drops.add(new ItemStack(halfState.getBlock(), 1, halfState.getBlock().getMetaFromState(halfState)));
        return drops;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face){
        if (!this.isDouble()) return Blocks.wooden_slab.getFlammability(world, pos, face);
        return Blocks.double_wooden_slab.getFlammability(world, pos, face);
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face){
        if (!this.isDouble()) return Blocks.wooden_slab.getFireSpreadSpeed(world, pos, face);
        return Blocks.double_wooden_slab.getFireSpreadSpeed(world, pos, face);
    }
}
