package com.arrabal.koth.init;

import com.arrabal.koth.block.*;
import com.arrabal.koth.creativetab.SoKCreativeTabs;
import com.arrabal.koth.reference.Reference;
import com.arrabal.koth.util.BlockRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Arrabal on 2/22/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    // Simple Blocks
    public static final SimpleSoKBlock textureTestBlock = new BlockSokTextureTest(Material.wood); //temporary block for testing out new textures
    public static final SimpleSoKBlock textureTest2Block = new BlockSoKTextureTest2(Material.wood); //temporary block for testing out new textures

    // Tree Blocks
    public static final Block log_0 = new BlockSoKLog();
    public static final Block leaf_0 = new BlockSoKLeaves();
    public static final Block sapling = new BlockSoKSapling();

    // Door Blocks
    public static final Block boarded_door = new BlockSoKDoor();

    public static void init(){
        //Simple Blocks
        BlockRegistryHelper.registerBlock(textureTestBlock, "textureTest");
        BlockRegistryHelper.registerBlock(textureTest2Block, "textureTest2");

        // Tree Parts
        BlockRegistryHelper.registerBlock(log_0, "log_0", SoKCreativeTabs.tabSoKWorldGen);
        BlockRegistryHelper.registerBlock(leaf_0, "leaf_0", SoKCreativeTabs.tabSoKWorldGen);
        BlockRegistryHelper.registerBlock(sapling, "sapling", SoKCreativeTabs.tabSoKWorldGen);

        //Door Blocks
        BlockRegistryHelper.registerDoor((BlockSoKDoor) boarded_door, "boarded_door", ModItems.boarded_door);

        //Set Fire Info
        ModBlocks.initFireValues();
    }

    private static void initFireValues(){
        Blocks.fire.setFireInfo(log_0,5, 5);
        Blocks.fire.setFireInfo(leaf_0, 30, 60);
    }
}
