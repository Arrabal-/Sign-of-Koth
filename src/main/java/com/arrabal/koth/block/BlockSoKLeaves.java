package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.init.ModBlocks;
import com.arrabal.koth.item.ItemSoKBlock;
import com.arrabal.koth.reference.enums.SoKTrees;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Created by Arrabal on 3/2/2016.
 */
public class BlockSoKLeaves extends BlockLeaves implements ISoKBlock {

    public static final PropertyEnum<SoKTrees> VARIANT = PropertyEnum.<SoKTrees>create("variant", SoKTrees.class);

    public BlockSoKLeaves(){
        super();
        this.setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(VARIANT, this.getTreeType(meta)).withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
                .withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
    }

    public SoKTrees getTreeType(int meta){
        return SoKTrees.byMetaData((meta & 3) % 4);
    }

    @Override
    public int getMetaFromState(IBlockState state){
        SoKTrees trees = (SoKTrees) state.getValue(VARIANT);
        int meta = trees.getMetaData();
        if (!((Boolean)state.getValue(DECAYABLE)).booleanValue()){
            meta |= 4;
        }
        if (((Boolean)state.getValue(CHECK_DECAY)).booleanValue()){
            meta |= 8;
        }
        return meta;
    }

    public enum ColoringType {PLAIN, TINTED}

    public static ColoringType getColoringType(SoKTrees tree){
        switch(tree){
            case CEDAR:
                return ColoringType.TINTED;
            default:
                return ColoringType.PLAIN;
        }
    }

    @Override
    protected BlockState createBlockState(){
        return new BlockState(this, new IProperty[] {CHECK_DECAY, DECAYABLE, VARIANT});
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBlockColor(){
        return 0xFFFFFF;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(IBlockState state){
        switch(getColoringType((SoKTrees)state.getValue(VARIANT))){
            case TINTED:
                return ColorizerFoliage.getFoliageColorPine();
            case PLAIN: default:
                return 0xFFFFFF;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess worldIn, BlockPos blockPos, int renderPass){
        switch(getColoringType((SoKTrees)worldIn.getBlockState(blockPos).getValue(VARIANT))){
            case TINTED:
                return ColorizerFoliage.getFoliageColorPine();
            case PLAIN:default:
                return 0xFFFFFF;
        }
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos blockPos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getStateFromMeta(meta).withProperty(CHECK_DECAY, Boolean.valueOf(false)).withProperty(DECAYABLE, Boolean.valueOf(false));
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos blockPos, EntityPlayer player){
        return new ItemStack(this, 1, this.getMetaFromState(this.getDefaultState().withProperty(VARIANT, world.getBlockState(blockPos).getValue(VARIANT))));
    }

    @Override
    protected int getSaplingDropChance(IBlockState state){
        return 20;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune){
        ItemStack saplingStack = new ItemStack(ModBlocks.sapling, 1, state.getValue(VARIANT).getMetaData());
        return saplingStack.getItem();
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state){
       return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(VARIANT).getMetaData());
    }

    @Override
    public int damageDropped(IBlockState state){
        return state.getValue(VARIANT).getMetaData();
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState blockState = world.getBlockState(pos);
        return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 1, ((SoKTrees)blockState.getValue(VARIANT)).getMetaData())));
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
        return new IProperty[]{CHECK_DECAY, DECAYABLE};
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {
        SoKTrees tree = ((SoKTrees) blockState.getValue(VARIANT));
        switch(tree){
            default:
                return tree.getName() + "_leaves";
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumWorldBlockLayer getBlockLayer(){
        return Blocks.leaves.getBlockLayer();
    }

    @Override
    public boolean isOpaqueCube(){
        return Blocks.leaves.isOpaqueCube();
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }
}
