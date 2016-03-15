package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.init.ModBlocks;
import com.arrabal.koth.item.ItemSoKBlock;
import com.arrabal.koth.reference.enums.SoKLogs;
import com.arrabal.koth.reference.enums.SoKTrees;
import com.arrabal.koth.world.gen.feature.WorldGenCedar;
import com.arrabal.koth.world.gen.feature.WorldGenSoKBigTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by Arrabal on 3/2/2016.
 */
public class BlockSoKSapling extends BlockBush implements IGrowable, ISoKBlock {

    public static final PropertyEnum<SoKTrees> VARIANT = PropertyEnum.<SoKTrees>create("variant", SoKTrees.class);
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public BlockSoKSapling(){
        super();
        this.setStepSound(Block.soundTypeGrass);
        this.setHardness(0.0f);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, SoKTrees.CEDAR).withProperty(STAGE, Integer.valueOf(0)));
        float f = 0.4f;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }

    @Override
    protected BlockState createBlockState(){
        return new BlockState(this, new IProperty[]{STAGE, VARIANT});
    }

    @Override
    public String getLocalizedName(){
        return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + SoKTrees.CEDAR.getName() + ".name");
    }

    @Override
    public void updateTick(World worldIn, BlockPos blockPos, IBlockState state, Random rand){
        if (!worldIn.isRemote)
        {
            super.updateTick(worldIn, blockPos, state, rand);

            if (worldIn.getLightFromNeighbors(blockPos.up()) >= 9 && rand.nextInt(7) == 0)
            {
                this.grow(worldIn, blockPos, state, rand);
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(worldIn, pos, state, rand);
        }
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.grow(worldIn, pos, state, rand);
    }

    public void generateTree(World worldIn, BlockPos blockPos, IBlockState state, Random random){
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, random, blockPos)) return;
        boolean extraHeight = random.nextInt(10) == 0;
        WorldGenerator worldGenerator = getSmallTreeGenerator(state.getValue(VARIANT), extraHeight);

        IBlockState clearState = Blocks.air.getDefaultState();

        worldIn.setBlockState(blockPos, clearState, 4);
        if (!worldGenerator.generate(worldIn, random, blockPos)){
            worldIn.setBlockState(blockPos, state, 4);
        }
    }

    private boolean isSaplingAt(World worldIn, BlockPos blockPos, SoKTrees tree){
        return this.isTypeAt(worldIn, blockPos, tree);
    }

    public boolean isTypeAt(World worldIn, BlockPos blockPos, SoKTrees type){
        IBlockState state = worldIn.getBlockState(blockPos);
        return state.getBlock() == this && state.getValue(VARIANT) == type;
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(STAGE, meta >> 3).withProperty(VARIANT, SoKTrees.byMetaData(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state){
        SoKTrees treeType = state.getValue(VARIANT);
        int meta = treeType.getMetaData();
        return ((Integer)state.getValue(STAGE)).intValue() * 8 + meta;
    }

    @Override
    public int damageDropped(IBlockState blockState){
        return ((SoKTrees)blockState.getValue(VARIANT)).getMetaData();
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double)worldIn.rand.nextFloat() < 0.45D;
    }

    protected WorldGenerator getSmallTreeGenerator(SoKTrees tree, boolean useBigTree){
        switch(tree){
            case BEECH:
                if (useBigTree){
                    return new WorldGenSoKBigTree(true, ModBlocks.leaf_0.getDefaultState().withProperty(BlockSoKLeaves.VARIANT, SoKTrees.BEECH).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)),
                            ModBlocks.log_0.getDefaultState().withProperty(BlockSoKLog.VARIANT, SoKLogs.BEECH));
                }
                return new WorldGenTrees(true, 4, ModBlocks.log_0.getDefaultState().withProperty(BlockSoKLog.VARIANT, SoKLogs.BEECH),
                        ModBlocks.leaf_0.getDefaultState().withProperty(BlockSoKLeaves.VARIANT, SoKTrees.BEECH).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)), false);
            case CEDAR:
                return new WorldGenCedar(true, useBigTree);
            case HEMLOCK:
            case SUGAR_MAPLE:
                if (useBigTree){
                    return new WorldGenSoKBigTree(true, ModBlocks.leaf_0.getDefaultState().withProperty(BlockSoKLeaves.VARIANT, SoKTrees.SUGAR_MAPLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)),
                            ModBlocks.log_0.getDefaultState().withProperty(BlockSoKLog.VARIANT, SoKLogs.SUGAR_MAPLE));
                }
                return new WorldGenTrees(true, 4, ModBlocks.log_0.getDefaultState().withProperty(BlockSoKLog.VARIANT, SoKLogs.SUGAR_MAPLE),
                        ModBlocks.leaf_0.getDefaultState().withProperty(BlockSoKLeaves.VARIANT, SoKTrees.SUGAR_MAPLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)), false);
            default: return new WorldGenForest(true, false);
        }
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
        return new IProperty[]{VARIANT};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[]{STAGE};
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {
        SoKTrees tree = blockState.getValue(VARIANT);
        switch(tree){
            default:
                return tree.getName() + "_sapling";
        }
    }
}