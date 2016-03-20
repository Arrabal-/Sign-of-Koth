package com.arrabal.koth.block;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;

/**
 * Created by Arrabal on 3/15/2016.
 */
public class BlockSoKStairs extends BlockStairs {

    public BlockSoKStairs(IBlockState parentState){
        super(parentState);
        this.useNeighborBrightness = true;
    }
}
