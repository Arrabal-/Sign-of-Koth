package com.arrabal.koth.world.gen.feature;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;

import java.util.List;
import java.util.Random;

/**
 * Created by Arrabal on 3/10/2016.
 */
public class WorldGenSoKBigTree extends WorldGenBigTree {

    private Random random;
    private World world;
    private BlockPos basePos = BlockPos.ORIGIN;
    private int heightLimit;
    private int height;
    private double heightAttenuation = 0.618D;
    private double branchSlope = 0.381D;
    private double scaleWidth = 1.0D;
    private double leafDensity = 1.0D;
    private int trunkSize = 1;
    private int heightLimitLimit = 12;
    /** Sets the distance limit for how far away the generator will populate leaves from the base leaf node. */
    private int leafDistanceLimit = 4;
    private List<WorldGenSoKBigTree.FoliageCoordinates> coordinatesList;
    private IBlockState leafType;
    private IBlockState logType;
    private int minHeight;

    public WorldGenSoKBigTree(boolean notify, IBlockState leaves, IBlockState logs){
        super(notify);
        this.leafType = leaves;
        this.logType = logs;
        this.minHeight = 0;
    }

    public WorldGenSoKBigTree(boolean notify, IBlockState leaves, IBlockState logs, double heightAttenuation, double branchSlope, double scaleWidth,
                              double leafDensity, int trunkSize, int maxHeight, int minHeight, int leafDist){
        super(notify);
        this.leafType = leaves;
        this.logType = logs;
        this.heightAttenuation = heightAttenuation;
        this.branchSlope = branchSlope;
        this.scaleWidth = scaleWidth;
        this.leafDensity =leafDensity;
        this.trunkSize = trunkSize;
        this.heightLimitLimit = maxHeight;
        this.leafDistanceLimit = leafDist;
        this.minHeight = minHeight;
    }

    private void generateLeafNodeList()
    {
        this.height = (int)((double)this.heightLimit * this.heightAttenuation);

        if (this.height >= this.heightLimit)
        {
            this.height = this.heightLimit - 1;
        }

        int i = (int)(1.382D + Math.pow(this.leafDensity * (double)this.heightLimit / 13.0D, 2.0D));

        if (i < 1)
        {
            i = 1;
        }

        int j = this.basePos.getY() + this.height;
        int k = this.heightLimit - this.leafDistanceLimit;
        this.coordinatesList = Lists.<WorldGenSoKBigTree.FoliageCoordinates>newArrayList();
        this.coordinatesList.add(new WorldGenSoKBigTree.FoliageCoordinates(this.basePos.up(k), j));

        for (; k >= 0; --k)
        {
            float f = this.layerSize(k);

            if (f >= 0.0F)
            {
                for (int l = 0; l < i; ++l)
                {
                    double d0 = this.scaleWidth * (double)f * ((double)this.random.nextFloat() + 0.328D);
                    double d1 = (double)(this.random.nextFloat() * 2.0F) * Math.PI;
                    double d2 = d0 * Math.sin(d1) + 0.5D;
                    double d3 = d0 * Math.cos(d1) + 0.5D;
                    BlockPos blockpos = this.basePos.add(d2, (double)(k - 1), d3);
                    BlockPos blockpos1 = blockpos.up(this.leafDistanceLimit);

                    if (this.checkBlockLine(blockpos, blockpos1) == -1)
                    {
                        int i1 = this.basePos.getX() - blockpos.getX();
                        int j1 = this.basePos.getZ() - blockpos.getZ();
                        double d4 = (double)blockpos.getY() - Math.sqrt((double)(i1 * i1 + j1 * j1)) * this.branchSlope;
                        int k1 = d4 > (double)j ? j : (int)d4;
                        BlockPos blockpos2 = new BlockPos(this.basePos.getX(), k1, this.basePos.getZ());

                        if (this.checkBlockLine(blockpos2, blockpos) == -1)
                        {
                            this.coordinatesList.add(new WorldGenSoKBigTree.FoliageCoordinates(blockpos, blockpos2.getY()));
                        }
                    }
                }
            }
        }
    }

    private void generateLeaves(BlockPos blockPos, float leafSize, IBlockState blockState)
    {
        int i = (int)((double)leafSize + 0.618D);

        for (int j = -i; j <= i; ++j)
        {
            for (int k = -i; k <= i; ++k)
            {
                if (Math.pow((double)Math.abs(j) + 0.5D, 2.0D) + Math.pow((double)Math.abs(k) + 0.5D, 2.0D) <= (double)(leafSize * leafSize))
                {
                    BlockPos blockpos = blockPos.add(j, 0, k);
                    net.minecraft.block.state.IBlockState state = this.world.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, this.world, blockpos) || state.getBlock().isLeaves(state, this.world, blockpos))
                    {
                        this.setBlockAndNotifyAdequately(this.world, blockpos, blockState);
                    }
                }
            }
        }
    }

    private float layerSize(int layerHeight)
    {
        if ((float)layerHeight < (float)this.heightLimit * 0.3F)
        {
            return -1.0F;
        }
        else
        {
            float f = (float)this.heightLimit / 2.0F;
            float f1 = f - (float)layerHeight;
            float f2 = MathHelper.sqrt(f * f - f1 * f1);

            if (f1 == 0.0F)
            {
                f2 = f;
            }
            else if (Math.abs(f1) >= f)
            {
                return 0.0F;
            }

            return f2 * 0.5F;
        }
    }

    private float leafSize(int nodeDistance)
    {
        return nodeDistance >= 0 && nodeDistance < this.leafDistanceLimit ? (nodeDistance != 0 && nodeDistance != this.leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
    }


    private void generateLeafNode(BlockPos pos, IBlockState leaves){
        for (int i = 0; i < this.leafDistanceLimit; ++i)
        {
            this.generateLeaves(pos.up(i), this.leafSize(i), leaves);
        }
    }

    private void placeLogs(BlockPos pos1, BlockPos pos2, IBlockState state)
    {
        BlockPos blockpos = pos2.add(-pos1.getX(), -pos1.getY(), -pos1.getZ());
        int i = this.getGreatestDistance(blockpos);
        float f = (float)blockpos.getX() / (float)i;
        float f1 = (float)blockpos.getY() / (float)i;
        float f2 = (float)blockpos.getZ() / (float)i;

        for (int j = 0; j <= i; ++j)
        {
            BlockPos blockpos1 = pos1.add((double)(0.5F + (float)j * f), (double)(0.5F + (float)j * f1), (double)(0.5F + (float)j * f2));
            BlockLog.EnumAxis blocklog$enumaxis = this.getLogAxis(pos1, blockpos1);
            this.setBlockAndNotifyAdequately(this.world, blockpos1, state.withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
        }
    }

    private int getGreatestDistance(BlockPos posIn)
    {
        int i = MathHelper.abs(posIn.getX());
        int j = MathHelper.abs(posIn.getY());
        int k = MathHelper.abs(posIn.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    private BlockLog.EnumAxis getLogAxis(BlockPos blockPos1, BlockPos blockPos2)
    {
        BlockLog.EnumAxis blocklog$enumaxis = BlockLog.EnumAxis.Y;
        int i = Math.abs(blockPos2.getX() - blockPos1.getX());
        int j = Math.abs(blockPos2.getZ() - blockPos1.getZ());
        int k = Math.max(i, j);

        if (k > 0)
        {
            if (i == k)
            {
                blocklog$enumaxis = BlockLog.EnumAxis.X;
            }
            else if (j == k)
            {
                blocklog$enumaxis = BlockLog.EnumAxis.Z;
            }
        }

        return blocklog$enumaxis;
    }

    private void generateLeaves()
    {
        for (WorldGenSoKBigTree.FoliageCoordinates worldgenbigtree$foliagecoordinates : this.coordinatesList)
        {
            this.generateLeafNode(worldgenbigtree$foliagecoordinates, this.leafType);
        }
    }

    private boolean leafNodeNeedsBase(int distToBase)
    {
        return (double)distToBase >= (double)this.heightLimit * 0.2D;
    }

    private void generateTrunk()
    {
        BlockPos blockpos = this.basePos;
        BlockPos blockpos1 = this.basePos.up(this.height);
        this.placeLogs(blockpos, blockpos1, this.logType);

        if (this.trunkSize == 2)
        {
            this.placeLogs(blockpos.east(), blockpos1.east(), this.logType);
            this.placeLogs(blockpos.east().south(), blockpos1.east().south(), this.logType);
            this.placeLogs(blockpos.south(), blockpos1.south(), this.logType);
        }
    }

    private void generateLeafNodeBases()
    {
        for (WorldGenSoKBigTree.FoliageCoordinates worldgenbigtree$foliagecoordinates : this.coordinatesList)
        {
            int i = worldgenbigtree$foliagecoordinates.getNodeBaseY();
            BlockPos blockpos = new BlockPos(this.basePos.getX(), i, this.basePos.getZ());

            if (!blockpos.equals(worldgenbigtree$foliagecoordinates) && this.leafNodeNeedsBase(i - this.basePos.getY()))
            {
                this.placeLogs(blockpos, worldgenbigtree$foliagecoordinates, this.logType);
            }
        }
    }

    private int checkBlockLine(BlockPos posOne, BlockPos posTwo)
    {
        BlockPos blockpos = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
        int i = this.getGreatestDistance(blockpos);
        float f = (float)blockpos.getX() / (float)i;
        float f1 = (float)blockpos.getY() / (float)i;
        float f2 = (float)blockpos.getZ() / (float)i;

        if (i == 0)
        {
            return -1;
        }
        else
        {
            for (int j = 0; j <= i; ++j)
            {
                BlockPos blockpos1 = posOne.add((double)(0.5F + (float)j * f), (double)(0.5F + (float)j * f1), (double)(0.5F + (float)j * f2));

                if (!this.isReplaceable(world, blockpos1))
                {
                    return j;
                }
            }

            return -1;
        }
    }

    @Override
    // sets leaf distance limit
    public void setDecorationDefaults()
    {
        super.setDecorationDefaults();
        this.leafDistanceLimit = 5;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        this.world = worldIn;
        this.basePos = position;
        this.random = new Random(rand.nextLong());

        if (this.heightLimit == 0)
        {
            //this.heightLimit = 5 + this.random.nextInt(this.heightLimitLimit);
            this.heightLimit = (this.minHeight > 0) ? this.minHeight + this.random.nextInt(this.heightLimitLimit) : 5 + this.random.nextInt(this.heightLimitLimit);
        }

        if (!this.validTreeLocation())
        {
            this.world = null; //Fix vanilla Mem leak, holds latest world
            return false;
        }
        else
        {
            this.generateLeafNodeList();
            this.generateLeaves();
            this.generateTrunk();
            this.generateLeafNodeBases();
            this.world = null; //Fix vanilla Mem leak, holds latest world
            return true;
        }
    }

    private boolean validTreeLocation()
    {
        BlockPos down = this.basePos.down();
        net.minecraft.block.state.IBlockState state = this.world.getBlockState(down);
        boolean isSoil = state.getBlock().canSustainPlant(state, this.world, down, net.minecraft.util.EnumFacing.UP, ((net.minecraft.block.BlockSapling) Blocks.SAPLING));

        if (!isSoil)
        {
            return false;
        }
        else
        {
            int i = this.checkBlockLine(this.basePos, this.basePos.up(this.heightLimit - 1));

            if (i == -1)
            {
                return true;
            }
            else if (i < 6)
            {
                return false;
            }
            else
            {
                this.heightLimit = i;
                return true;
            }
        }
    }


    static class FoliageCoordinates extends BlockPos
    {
        private final int nodeBaseY;

        public FoliageCoordinates(BlockPos blockPos, int nodeBase)
        {
            super(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            this.nodeBaseY = nodeBase;
        }

        public int getNodeBaseY()
        {
            return this.nodeBaseY;
        }
    }

}
