package com.github.arrabal.koth.world.gen.feature;

import com.github.arrabal.koth.block.BlockSoKLeaves;
import com.github.arrabal.koth.block.BlockSoKLog;
import com.github.arrabal.koth.init.ModBlocks;
import com.github.arrabal.koth.reference.enums.SoKLogs;
import com.github.arrabal.koth.reference.enums.SoKTrees;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

/**
 * Created by Arrabal on 3/21/2016.
 */
public class WorldGenHemlockFromSapling extends WorldGenTaiga2 {

    private static final IBlockState logType = ModBlocks.log_0.getDefaultState().withProperty(BlockSoKLog.VARIANT, SoKLogs.HEMLOCK);
    private static final IBlockState leafType = ModBlocks.leaf_0.getDefaultState().withProperty(BlockSoKLeaves.VARIANT, SoKTrees.HEMLOCK).withProperty(BlockSoKLeaves.CHECK_DECAY, Boolean.valueOf(false));

    public WorldGenHemlockFromSapling(){
        super(true);
    }

    @Override
    protected void setBlockAndNotifyAdequately(World worldIn, BlockPos pos, IBlockState state){
        if (!state.getBlock().isLeaves(state, worldIn, pos)){
            super.setBlockAndNotifyAdequately(worldIn, pos, logType);
        }
        else super.setBlockAndNotifyAdequately(worldIn, pos, leafType);
    }
}
