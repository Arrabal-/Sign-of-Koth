package com.arrabal.koth.init;

import com.arrabal.koth.block.BlockSoKLeaves;
import com.arrabal.koth.block.BlockSoKLog;
import com.arrabal.koth.block.BlockSoKSapling;
import com.arrabal.koth.handler.FurnaceFuelHandler;
import com.arrabal.koth.reference.enums.SoKLogs;
import com.arrabal.koth.reference.enums.SoKTrees;
import com.arrabal.koth.util.CraftingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Arrabal on 3/14/2016.
 */
public class ModCrafting {

    public static void init(){
        addOreDictionaryRegistration();
        addCraftingRecipes();
        addSmeltingRecipes();
    }

    private static void addCraftingRecipes(){

        // logs into corresponding planks
        for (SoKLogs wood : SoKLogs.values()){
            GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planks_0, 4, wood.getMetaData()), CraftingHelper.getItemStackFromProperty(ModBlocks.log_0, 1, BlockSoKLog.VARIANT, wood));
        }

    }

    private static void addOreDictionaryRegistration(){

        // support for wood types
        for (SoKLogs wood : SoKLogs.values()){
            OreDictionary.registerOre("logWood", CraftingHelper.getItemStackFromProperty(ModBlocks.log_0, 1, BlockSoKLog.VARIANT, wood));
            OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planks_0, 1, wood.getMetaData()));
        }

        // support for tree types
        for (SoKTrees tree : SoKTrees.values()){
            OreDictionary.registerOre("treeSapling", CraftingHelper.getItemStackFromProperty(ModBlocks.sapling, 1, BlockSoKSapling.VARIANT, tree));
            OreDictionary.registerOre("treeLeaves", CraftingHelper.getItemStackFromProperty(ModBlocks.leaf_0, 1, BlockSoKLeaves.VARIANT, tree));
        }
    }

    public static void addSmeltingRecipes(){

        //smelt SoK Logs into charcoal
        for (SoKLogs wood : SoKLogs.values()){
            GameRegistry.addSmelting(CraftingHelper.getItemStackFromProperty(ModBlocks.log_0, 1, BlockSoKLog.VARIANT, wood), new ItemStack(Items.coal, 1, 1), 0.15f);
        }

        // add new fuels
        FurnaceFuelHandler sokFuelHandler = new FurnaceFuelHandler();
        GameRegistry.registerFuelHandler(sokFuelHandler);
        sokFuelHandler.addFuel(ModBlocks.sapling, 100);

    }
}
