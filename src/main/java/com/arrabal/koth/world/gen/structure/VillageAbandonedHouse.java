package com.arrabal.koth.world.gen.structure;

import com.arrabal.koth.block.BlockSoKDoor;
import com.arrabal.koth.block.BlockSoKPlanks;
import com.arrabal.koth.block.BlockSoKSiding;
import com.arrabal.koth.init.ModBlocks;
import com.arrabal.koth.reference.enums.SoKLogs;
import com.arrabal.koth.world.storage.loot.ModLootTableList;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.List;
import java.util.Random;

/**
 * Created by Arrabal on 3/23/2016.
 */
public class VillageAbandonedHouse extends StructureVillagePieces.Village implements VillagerRegistry.IVillageCreationHandler{

    private static final IBlockState SIDING = ModBlocks.cedar_siding.getDefaultState().withProperty(BlockSoKSiding.WEATHERED, Boolean.valueOf(true));
    private static final IBlockState SHAKE = ModBlocks.cedar_siding.getDefaultState().withProperty(BlockSoKSiding.SHAKE, Boolean.valueOf(true)).withProperty(BlockSoKSiding.WEATHERED, Boolean.valueOf(true));
    private static final IBlockState FLOORING = ModBlocks.planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.HEMLOCK);
    private static IBlockState logBeams = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
    private static IBlockState roofStairs = Blocks.SPRUCE_STAIRS.getDefaultState();
    private static final IBlockState ROOFPLANKS = Blocks.PLANKS.getDefaultState().withProperty(BlockPlanks.VARIANT, BlockPlanks.EnumType.SPRUCE);
    private static final IBlockState CARPET = Blocks.CARPET.getDefaultState().withProperty(BlockCarpet.COLOR, EnumDyeColor.GRAY);

    private boolean hasMadeChest = false;

    public VillageAbandonedHouse(){}

    public VillageAbandonedHouse(StructureVillagePieces.Start start, int weight, Random random, StructureBoundingBox boundingBox, EnumFacing facing){
        super(start, weight);
        this.setCoordBaseMode(facing);
        this.boundingBox = boundingBox;
    }

    public static VillageAbandonedHouse buildPiece(StructureVillagePieces.Start start, List<StructureComponent> componentList, Random rand, int p1, int p2, int p3, EnumFacing facing, int weight)
    {
        StructureBoundingBox structureboundingbox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 9, 7, 12, facing);
        return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(componentList, structureboundingbox) == null ? new VillageAbandonedHouse(start, weight, rand, structureboundingbox, facing) : null;
    }

    @Override
    protected void func_189915_a(World worldIn, StructureBoundingBox boundingBoxIn, Random rand, int x, int y, int z, EnumFacing facing, BlockDoor doorBlock)
    {
        this.setBlockState(worldIn, ModBlocks.secured_door.getDefaultState().withProperty(BlockSoKDoor.FACING, facing), x, y, z, boundingBoxIn);
        this.setBlockState(worldIn, ModBlocks.secured_door.getDefaultState().withProperty(BlockSoKDoor.FACING, facing).withProperty(BlockSoKDoor.HALF, BlockSoKDoor.EnumDoorHalf.UPPER), x, y + 1, z, boundingBoxIn);
    }

    @Override
    public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
        if (this.averageGroundLvl < 0)
        {
            this.averageGroundLvl = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.averageGroundLvl < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLvl - this.boundingBox.maxY + 7 - 1, 0);
        }

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 7, 4, 4, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 8, 4, 10, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 9, 8, 0, 10, FLOORING, FLOORING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 8, 4, 0, 8, FLOORING, FLOORING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 8, 8, 0, 8, FLOORING, FLOORING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 5, 8, 0, 7, FLOORING, FLOORING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 5, 6, 1, 8, CARPET, CARPET, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 7, 0, 4, FLOORING, FLOORING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 2, 6, 1, 3, CARPET, CARPET, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 0, 5, SIDING, SIDING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 1, 0, 0, 3, 5, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 0, 0, 8, 0, 10, SIDING, SIDING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 8, 1, 0, 8, 3, 10, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 0, 7, 0, 0, SIDING, SIDING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 0, 7, 2, 0, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 5, 2, 0, 5, SIDING, SIDING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 5, 2, 1, 5, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 0, 6, 2, 0, 10, SIDING, SIDING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 1, 6, 2, 3, 10, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 0, 10, 7, 0, 10, SIDING, SIDING, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 1, 10, 7, 3, 10, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 0, 7, 3, 0, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 2, 5, 2, 3, 5, SHAKE, SHAKE, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 1, 8, 4, 1, ROOFPLANKS, ROOFPLANKS, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 4, 4, 3, 4, 4, ROOFPLANKS, ROOFPLANKS, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 5, 2, 8, 5, 3, ROOFPLANKS, ROOFPLANKS, false);
        this.setBlockState(worldIn, ROOFPLANKS, 0, 4, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, ROOFPLANKS, 0, 4, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, ROOFPLANKS, 8, 4, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, ROOFPLANKS, 8, 4, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, ROOFPLANKS, 8, 4, 4, structureBoundingBoxIn);
        IBlockState iblockstate = roofStairs.withProperty(BlockStairs.FACING, EnumFacing.NORTH);
        IBlockState iblockstate1 = roofStairs.withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
        IBlockState iblockstate2 = roofStairs.withProperty(BlockStairs.FACING, EnumFacing.WEST);
        IBlockState iblockstate3 = roofStairs.withProperty(BlockStairs.FACING, EnumFacing.EAST);

        for (int i = -1; i <= 2; ++i)
        {
            for (int j = 0; j <= 8; ++j)
            {
                this.setBlockState(worldIn, iblockstate, j, 4 + i, i, structureBoundingBoxIn);

                if ((i > -1 || j <= 1) && (i > 0 || j <= 3) && (i > 1 || j <= 4 || j >= 6))
                {
                    this.setBlockState(worldIn, iblockstate1, j, 4 + i, 5 - i, structureBoundingBoxIn);
                }
            }
        }

        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 3, 4, 5, 3, 4, 10, ROOFPLANKS, ROOFPLANKS, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 7, 4, 2, 7, 4, 10, ROOFPLANKS, ROOFPLANKS, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 5, 4, 4, 5, 10, ROOFPLANKS, ROOFPLANKS, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 5, 4, 6, 5, 10, ROOFPLANKS, ROOFPLANKS, false);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 6, 3, 5, 6, 10, ROOFPLANKS, ROOFPLANKS, false);

        for (int k = 4; k >= 1; --k)
        {
            this.setBlockState(worldIn, ROOFPLANKS, k, 2 + k, 7 - k, structureBoundingBoxIn);

            for (int k1 = 8 - k; k1 <= 10; ++k1)
            {
                this.setBlockState(worldIn, iblockstate3, k, 2 + k, k1, structureBoundingBoxIn);
            }
        }

        this.setBlockState(worldIn, ROOFPLANKS, 6, 6, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn,ROOFPLANKS, 7, 5, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, iblockstate2, 6, 6, 4, structureBoundingBoxIn);

        for (int l = 6; l <= 8; ++l)
        {
            for (int l1 = 5; l1 <= 10; ++l1)
            {
                this.setBlockState(worldIn, iblockstate2, l, 12 - l, l1, structureBoundingBoxIn);
            }
        }

        this.setBlockState(worldIn, logBeams, 0, 2, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 0, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 0, 2, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 4, 2, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 2, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 6, 2, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 8, 2, 1, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 2, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 3, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 8, 2, 4, structureBoundingBoxIn);
        this.setBlockState(worldIn, SHAKE, 8, 2, 5, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 8, 2, 6, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 7, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 8, 2, 8, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 8, 2, 9, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 2, 2, 6, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 7, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 2, 2, 8, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 2, 2, 9, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 4, 4, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.GLASS_PANE.getDefaultState(), 5, 4, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, logBeams, 6, 4, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, ROOFPLANKS, 5, 5, 10, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 1, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.AIR.getDefaultState(), 2, 2, 0, structureBoundingBoxIn);
        this.setBlockState(worldIn, Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH), 2, 3, 1, structureBoundingBoxIn);
        this.func_189915_a(worldIn, structureBoundingBoxIn, randomIn, 2, 1, 0, EnumFacing.NORTH, null);
        this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, -1, 3, 2, -1, Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), false);

        if (this.getBlockStateFromPos(worldIn, 2, 0, -1, structureBoundingBoxIn).getMaterial() == Material.AIR && this.getBlockStateFromPos(worldIn, 2, -1, -1, structureBoundingBoxIn).getMaterial() != Material.AIR)
        {
            this.setBlockState(worldIn, Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH), 2, 0, -1, structureBoundingBoxIn);
            this.setBlockState(worldIn, FLOORING, 2, 0, 0, structureBoundingBoxIn);
        }

        for (int i1 = 0; i1 < 5; ++i1)
        {
            for (int i2 = 0; i2 < 9; ++i2)
            {
                this.clearCurrentPositionBlocksUpwards(worldIn, i2, 7, i1, structureBoundingBoxIn);
                this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(), i2, -1, i1, structureBoundingBoxIn);
            }
        }

        for (int j1 = 5; j1 < 11; ++j1)
        {
            for (int j2 = 2; j2 < 9; ++j2)
            {
                this.clearCurrentPositionBlocksUpwards(worldIn, j2, 7, j1, structureBoundingBoxIn);
                this.replaceAirAndLiquidDownwards(worldIn, Blocks.COBBLESTONE.getDefaultState(), j2, -1, j1, structureBoundingBoxIn);
            }
        }

        Vec3i chestPos = new Vec3i(5, 0, 8);
        if (!this.hasMadeChest && this.boundingBox.isVecInside(new BlockPos(this.getXWithOffset(chestPos.getX(),chestPos.getZ()), this.getYWithOffset(chestPos.getY()), this.getZWithOffset(chestPos.getX(),chestPos.getZ())))){
            this.hasMadeChest = true;
            this.generateChest(worldIn, this.boundingBox, randomIn, chestPos.getX(), chestPos.getY(), chestPos.getZ(), ModLootTableList.CHESTS_VILLAGE_ABANDONED_HOUSE);
        }

        //this.spawnVillagers(worldIn, structureBoundingBoxIn, 4, 1, 2, 2);
        return true;
    }

    private Vec3i getHiddenChestLocation(Random random){
        int x, z;
        if (random.nextInt(2) != 0){
            x = random.nextInt(3) + 4;
            z = random.nextInt(4) + 5;
        }
        else {
            x = random.nextInt(5) + 2;
            z = random.nextInt(2) + 2;
        }
        return new Vec3i(x, 0, z);
    }

    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight(VillageAbandonedHouse.class, 8, 1);
    }

    @Override
    public Class<?> getComponentClass() {
        return VillageAbandonedHouse.class;
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
        return buildPiece(startPiece, pieces, random, p1, p2, p3, facing, p5);
    }

    protected void writeStructureToNBT(NBTTagCompound tagCompound)
    {
        super.writeStructureToNBT(tagCompound);
        tagCompound.setBoolean("Chest", this.hasMadeChest);
    }

    /**
     * (abstract) Helper method to read subclass data from NBT
     */
    protected void readStructureFromNBT(NBTTagCompound tagCompound)
    {
        super.readStructureFromNBT(tagCompound);
        this.hasMadeChest = tagCompound.getBoolean("Chest");
    }
}
