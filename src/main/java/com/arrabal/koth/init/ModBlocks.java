package com.arrabal.koth.init;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.block.*;
import com.arrabal.koth.creativetab.SoKCreativeTabs;
import com.arrabal.koth.reference.Reference;
import com.arrabal.koth.reference.enums.SoKLogs;
import com.arrabal.koth.util.BlockRegistryHelper;
import com.arrabal.koth.util.ItemRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Arrabal on 2/22/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    // Block Coloring
    public static final IBlockColor FOLIAGE_COLOR = new IBlockColor() {
        @Override
        public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
            return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
        }
    };

    public static final IBlockColor GRASS_COLOR = new IBlockColor() {
        @Override
        public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
            return world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
        }
    };

    public static final IItemColor BLOCK_ITEM_COLOR = new IItemColor() {
        @Override
        public int getColorFromItemstack(ItemStack stack, int tintIndex) {
            IBlockState state = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
            IBlockColor blockColor = ((ISoKBlock)state.getBlock()).getBlockColor();
            return blockColor == null ? 0xFFFFFF : blockColor.colorMultiplier(state, null, null, tintIndex);

        }
    };

    // Simple Blocks
    public static final SimpleSoKBlock textureTestBlock = new BlockSokTextureTest(Material.wood); //temporary block for testing out new textures
    public static final SimpleSoKBlock textureTest2Block = new BlockSoKTextureTest2(Material.wood); //temporary block for testing out new textures

    // Variant Blocks
    public static final Block planks_0 = new BlockSoKPlanks();
    public static  Block wooden_slab;
    public static  Block double_wooden_slab;
    public static final Block cedar_siding = new BlockSoKSiding();

    // Stair Blocks
    public static final Block stairs_beech = new BlockSoKStairs(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.BEECH));
    public static final Block stairs_cedar = new BlockSoKStairs(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.CEDAR));
    public static final Block stairs_hemlock = new BlockSoKStairs(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.HEMLOCK));
    public static final Block stairs_maple = new BlockSoKStairs(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.SUGAR_MAPLE));

    // Tree Blocks
    public static final Block log_0 = new BlockSoKLog();
    public static final Block leaf_0 = new BlockSoKLeaves();
    public static final Block sapling = new BlockSoKSapling();

    // Door Blocks
    public static final Block boarded_door = new BlockSoKDoor(false);
    public static final Block secured_door = new BlockSoKDoor(true);

    public static void init(){
        // Simple Blocks
        //BlockRegistryHelper.registerBlock(textureTestBlock, "textureTest");
        //BlockRegistryHelper.registerBlock(textureTest2Block, "textureTest2");

        // Variant Blocks
        BlockRegistryHelper.registerBlock(planks_0, "planks_0");
        BlockRegistryHelper.registerBlock(cedar_siding, "cedar_siding");
        wooden_slab = BlockRegistryHelper.registerBlock(new BlockSoKHalfWoodSlab(), "wood_slab_0", null);
        double_wooden_slab = BlockRegistryHelper.registerBlock(new BlockSoKDoubleWoodSlab(), "double_wood_slab_0", null);
        ModItems.wooden_slab = ItemRegistryHelper.registerItem(new ItemSlab(wooden_slab, (BlockSlab) wooden_slab, (BlockSlab) double_wooden_slab), "wood_slab_0", null);


        // Stair Blocks
        BlockRegistryHelper.registerBlock(stairs_beech, "beech_stairs");
        BlockRegistryHelper.registerBlock(stairs_cedar, "cedar_stairs");
        BlockRegistryHelper.registerBlock(stairs_hemlock, "hemlock_stairs");
        BlockRegistryHelper.registerBlock(stairs_maple, "sugarmaple_stairs");

        // Tree Parts
        BlockRegistryHelper.registerBlock(log_0, "log_0", SoKCreativeTabs.tabSoKWorldGen);
        BlockRegistryHelper.registerBlock(leaf_0, "leaf_0", SoKCreativeTabs.tabSoKWorldGen);
        BlockRegistryHelper.registerBlock(sapling, "sapling", SoKCreativeTabs.tabSoKWorldGen);

        // Door Blocks
        BlockRegistryHelper.registerDoor((BlockSoKDoor) boarded_door, "boarded_door", ModItems.boarded_door);
        BlockRegistryHelper.registerDoor((BlockSoKDoor) secured_door, "secured_door", ModItems.secured_door);

        // Set Fire Info
        ModBlocks.initFireValues();
    }

    private static void initFireValues(){
        Blocks.fire.setFireInfo(log_0, 5, 5);
        Blocks.fire.setFireInfo(leaf_0, 30, 60);
        Blocks.fire.setFireInfo(planks_0, 5, 20);
        Blocks.fire.setFireInfo(stairs_beech, 5, 20);
        Blocks.fire.setFireInfo(stairs_cedar, 5, 20);
        Blocks.fire.setFireInfo(stairs_hemlock, 5, 20);
        Blocks.fire.setFireInfo(stairs_maple, 5, 20);
        Blocks.fire.setFireInfo(cedar_siding, 5, 20);
    }
}
