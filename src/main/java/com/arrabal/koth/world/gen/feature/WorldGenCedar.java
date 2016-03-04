package com.arrabal.koth.world.gen.feature;

import com.arrabal.koth.block.BlockSoKLeaves;
import com.arrabal.koth.block.BlockSoKLog;
import com.arrabal.koth.block.BlockSoKSapling;
import com.arrabal.koth.init.ModBlocks;
import com.arrabal.koth.reference.enums.SoKLogs;
import com.arrabal.koth.reference.enums.SoKTrees;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

/**
 * Created by Arrabal on 3/1/2016.
 */
public class WorldGenCedar extends WorldGenAbstractTree {

    public static final IBlockState logType = ModBlocks.log_0.getDefaultState().withProperty(BlockSoKLog.VARIANT, SoKLogs.CEDAR);
    public static final IBlockState leafType = ModBlocks.leaf_0.getDefaultState().withProperty(BlockSoKLeaves.VARIANT, SoKTrees.CEDAR).withProperty(BlockSoKLeaves.CHECK_DECAY, Boolean.valueOf(false));

    private boolean useExtraHeight;
    private int minHeight = 9;
    private int maxHeight = 13;
    private int foliageBands = 9;


    public WorldGenCedar(boolean notify, boolean extraHeight){
        super(notify);
        this.useExtraHeight = extraHeight;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {

        int height = this.minHeight + rand.nextInt(this.maxHeight - this.minHeight - 1) + (this.useExtraHeight ? rand.nextInt(2) + 1 : 0);
        height = (height > this.maxHeight ? this.maxHeight : height);
        int denseFoliageStart = height - 7;
        boolean hasSpace = true;
        boolean sapling = worldIn.getBlockState(position).getBlock() == ModBlocks.sapling;
        if (position.getY() >= 1 && position.getY() + height + 1 <= 256){
            int radius;

            for (int y = position.getY(); y <= position.getY() + 1 + height; y++){
                radius = 1;

                if (y == position.getY()){
                    radius = 0;
                }

                if ((y >= position.getY() + denseFoliageStart) && (y < position.getY() + denseFoliageStart + 1)){
                    radius = 2;
                }

                BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
                for (int x = position.getX() - radius; x <= position.getX() + radius && hasSpace; x++){
                    for (int z = position.getZ() - radius; z <= position.getZ() + radius && hasSpace; z++){
                        if (y >= 0 && y < 256){
                            if (!this.isReplaceable(worldIn, mutableBlockPos.set(x, y, z))){
                                hasSpace = false;
                            }
                        }
                        else hasSpace = false;
                    }
                }
            }

            if (!hasSpace) return false;
            else{
                BlockPos down = position.down();
                Block baseBlock = worldIn.getBlockState(down).getBlock();
                boolean isSoil = baseBlock.canSustainPlant(worldIn, down, net.minecraft.util.EnumFacing.UP, ((BlockSoKSapling) ModBlocks.sapling));

                if(isSoil && position.getY() < 256 - height - 1){
                    baseBlock.onPlantGrow(worldIn, down, position);
                    int startFoliage = height - this.foliageBands;
                    int foliageTopper = (height < 12) ? 2 : height - 9;
                    this.generateLeaves(worldIn, height, position, startFoliage);
                    this.generateTrunk(worldIn, height, position, foliageTopper);
                    return true;
                }
                return false;
            }
        }
        else {
            return false;
        }
    }

    private void generateLeaves(World worldIn, int treeHeight, BlockPos treeStart, int foliageStart){
        int radius;
        boolean cross;
        int foliageMin = treeStart.getY() + foliageStart;
        int treeTopPosition = treeStart.getY() + treeHeight;
        int wideBandPosition = treeStart.getY() + treeHeight - 7;
        for (int h = foliageMin; h < treeTopPosition - 1; h++) {
            radius = 1;
            cross = true;
            if ((h >= wideBandPosition - 1) && (h <= wideBandPosition + 2)) {
                radius = 2;
                if ((h == wideBandPosition) || (h == wideBandPosition + 1)) {
                    cross = false;
                }
            } else if (h == wideBandPosition + 3) {
                cross = false;
            }
            for (int x = treeStart.getX() - radius; x <= treeStart.getX() + radius; x++) {
                for (int z = treeStart.getZ() - radius; z <= treeStart.getZ() + radius; z++) {
                    BlockPos buildPos = new BlockPos(x, h, z);
                    Block buildBlock;
                    if (!cross && (Math.abs(x - treeStart.getX()) + Math.abs(z - treeStart.getZ()) <= radius + 1)) {
                        buildBlock = worldIn.getBlockState(buildPos).getBlock();
                        if (buildBlock.isAir(worldIn, buildPos) || buildBlock.isLeaves(worldIn, buildPos)) {
                            this.setBlockAndNotifyAdequately(worldIn, buildPos, leafType);
                        }

                    } else if (Math.abs(x - treeStart.getX()) + Math.abs(z - treeStart.getZ()) <= radius) {
                        buildBlock = worldIn.getBlockState(buildPos).getBlock();
                        if (buildBlock.isAir(worldIn, buildPos) || buildBlock.isLeaves(worldIn, buildPos)) {
                            this.setBlockAndNotifyAdequately(worldIn, buildPos, leafType);
                        }
                    }
                }
            }
        }
        //set the final column of leaves in the center
        BlockPos buildPos = new BlockPos(treeStart.getX(), treeTopPosition - 1, treeStart.getZ());
        Block buildBlock = worldIn.getBlockState(buildPos).getBlock();
        if (buildBlock.isAir(worldIn, buildPos) || buildBlock.isLeaves(worldIn, buildPos)) {
            this.setBlockAndNotifyAdequately(worldIn, buildPos, leafType);
        }
    }

    private void generateTrunk(World worldIn, int treeHeight, BlockPos treeStart, int foliageCap){
        BlockPos.MutableBlockPos buildPos = new BlockPos.MutableBlockPos();
        for (int h = 0; h < treeHeight - foliageCap; h++){
            int y = treeStart.getY() + h;
            buildPos.set(treeStart.getX(), y, treeStart.getZ());
            Block buildBlock = worldIn.getBlockState(buildPos).getBlock();
            if (buildBlock.isAir(worldIn,buildPos) || buildBlock.isLeaves(worldIn, buildPos)){
                this.setBlockAndNotifyAdequately(worldIn, buildPos, logType);
            }
        }
    }
}
