package com.arrabal.koth.init;

import com.arrabal.koth.block.*;
import com.arrabal.koth.creativetab.SoKCreativeTabs;
import com.arrabal.koth.reference.Reference;
import com.arrabal.koth.reference.enums.SoKLogs;
import com.arrabal.koth.util.BlockRegistryHelper;
import com.arrabal.koth.util.ItemRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Arrabal on 2/22/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

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

    // Fences and Gates
    public static final Block fence_beech = new BlockSoKWoodFence(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.BEECH));
    public static final Block fence_cedar = new BlockSoKWoodFence(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.CEDAR));
    public static final Block fence_hemlock = new BlockSoKWoodFence(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.HEMLOCK));
    public static final Block fence_sugarmaple = new BlockSoKWoodFence(planks_0.getDefaultState().withProperty(BlockSoKPlanks.VARIANT, SoKLogs.SUGAR_MAPLE));

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

        // Fences and Gates
        BlockRegistryHelper.registerBlock(fence_beech, "beech_fence");
        BlockRegistryHelper.registerBlock(fence_cedar, "cedar_fence");
        BlockRegistryHelper.registerBlock(fence_hemlock, "hemlock_fence");
        BlockRegistryHelper.registerBlock(fence_sugarmaple, "sugarmaple_fence");

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
