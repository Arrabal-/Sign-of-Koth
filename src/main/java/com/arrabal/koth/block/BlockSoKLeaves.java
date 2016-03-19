package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.init.ModBlocks;
import com.arrabal.koth.item.ItemSoKBlock;
import com.arrabal.koth.reference.enums.SoKTrees;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
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
        return SoKTrees.byMetaData(meta & 3);
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
            case BEECH:
                return ColoringType.PLAIN;
            case CEDAR:
                return ColoringType.TINTED;
            case HEMLOCK:
                return ColoringType.PLAIN;
            case SUGAR_MAPLE:
                return ColoringType.PLAIN;
            default:
                return ColoringType.PLAIN;
        }
    }

    @Override
    public IBlockColor getBlockColor(){
        return new IBlockColor() {
            @Override
            public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
                boolean inWorld = world != null && pos != null;
                switch (getColoringType((SoKTrees) state.getValue(BlockSoKLeaves.VARIANT))){
                    case TINTED:
                        return inWorld ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
                    case PLAIN: default: return 0xFFFFFF;
                }
            }
        };
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {CHECK_DECAY, DECAYABLE, VARIANT});
    }


    @Override
    public IItemColor getItemColor() { return ModBlocks.BLOCK_ITEM_COLOR; }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos blockPos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getStateFromMeta(meta).withProperty(CHECK_DECAY, Boolean.valueOf(false)).withProperty(DECAYABLE, Boolean.valueOf(false));
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos blockPos, EntityPlayer player){
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
       return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(state));
    }

    @Override
    public int damageDropped(IBlockState state){
        return state.getValue(VARIANT).getMetaData();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState blockState = world.getBlockState(pos);
        return new java.util.ArrayList(java.util.Arrays.asList(new ItemStack(this, 1,
                this.getMetaFromState(this.getDefaultState().withProperty(VARIANT, world.getBlockState(pos).getValue(VARIANT))))));
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemSoKBlock.class;
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
    public BlockRenderLayer getBlockLayer(){
        return Blocks.leaves.getBlockLayer();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return Blocks.leaves.isOpaqueCube(state);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }
}
