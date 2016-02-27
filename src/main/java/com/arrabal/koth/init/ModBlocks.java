package com.arrabal.koth.init;

import com.arrabal.koth.block.BlockSoKDoor;
import com.arrabal.koth.block.BlockSoKTextureTest2;
import com.arrabal.koth.block.BlockSokTextureTest;
import com.arrabal.koth.block.SimpleSoKBlock;
import com.arrabal.koth.reference.Reference;
import com.arrabal.koth.util.BlockRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Arrabal on 2/22/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {

    // Simple Blocks
    public static final SimpleSoKBlock textureTestBlock = new BlockSokTextureTest(Material.wood); //temporary block for testing out new textures
    public static final SimpleSoKBlock textureTest2Block = new BlockSoKTextureTest2(Material.wood); //temporary block for testing out new textures

    // Door Blocks
    public static final Block boarded_door = new BlockSoKDoor();

    public static void init(){
        //Simple Blocks
        BlockRegistryHelper.registerBlock(textureTestBlock, "textureTest");
        BlockRegistryHelper.registerBlock(textureTest2Block, "textureTest2");

        //Door Blocks
        BlockRegistryHelper.registerDoor((BlockSoKDoor) boarded_door, "boarded_door", ModItems.boarded_door);
    }
}
