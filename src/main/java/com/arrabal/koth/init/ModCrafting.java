package com.arrabal.koth.init;

import com.arrabal.koth.block.*;
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
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.List;

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

        //===========================================Shapeless Recipes================================================//
        // logs into corresponding planks
        for (SoKLogs wood : SoKLogs.values()){
            GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planks_0, 4, wood.getMetaData()), CraftingHelper.getItemStackFromProperty(ModBlocks.log_0, 1, BlockSoKLog.VARIANT, wood));
        }


        //============================================Shaped Recipes==================================================//
        // planks into slabs and back into planks
        for (SoKLogs wood : SoKLogs.values()) {
            GameRegistry.addShapedRecipe(CraftingHelper.getItemStackFromProperty(ModBlocks.wooden_slab, 6, BlockSoKWoodSlab.VARIANT, wood), new Object[] {"PPP", 'P',CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, wood)});
            GameRegistry.addShapedRecipe(CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, wood), new Object[] {"S", "S", 'S', CraftingHelper.getItemStackFromProperty(ModBlocks.wooden_slab, 1, BlockSoKWoodSlab.VARIANT, wood)});
        }
        // wooden stairs
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.stairs_beech, 4), new Object[]
                {"P  ",
                 "PP ",
                 "PPP", 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.BEECH)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.stairs_cedar, 4), new Object[]
                {"P  ",
                 "PP ",
                 "PPP", 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.CEDAR)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.stairs_hemlock, 4), new Object[]
                {"P  ",
                 "PP ",
                 "PPP", 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.HEMLOCK)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.stairs_maple, 4), new Object[]
                {"P  ",
                 "PP ",
                 "PPP", 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.SUGAR_MAPLE)});
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.wooden_board, 6), new Object[] {"SSS", 'S', "slabWood"}));
        GameRegistry.addShapedRecipe(new ItemStack(((BlockSoKDoor)ModBlocks.boarded_door).getDoorItem(), 1), new Object[] {"BB ", "BB ", "BB ", 'B', new ItemStack(ModItems.wooden_board, 1)});
        GameRegistry.addShapedRecipe(new ItemStack(((BlockSoKDoor)ModBlocks.secured_door).getDoorItem(), 1), new Object[]
                {"B  ",
                 " D ",
                 "B  ", 'B', new ItemStack(ModItems.wooden_board, 1), 'D', new ItemStack(((BlockSoKDoor)ModBlocks.boarded_door).getDoorItem(), 1)});
    }

    private static void addOreDictionaryRegistration(){

        // support for wood types
        for (SoKLogs wood : SoKLogs.values()){
            OreDictionary.registerOre("logWood", CraftingHelper.getItemStackFromProperty(ModBlocks.log_0, 1, BlockSoKLog.VARIANT, wood));
            OreDictionary.registerOre("plankWood", new ItemStack(ModBlocks.planks_0, 1, wood.getMetaData()));
            OreDictionary.registerOre("slabWood", CraftingHelper.getItemStackFromProperty(ModBlocks.wooden_slab, 1, BlockSoKPlanks.VARIANT, wood));
        }
        OreDictionary.registerOre("stairWood", new ItemStack(ModBlocks.stairs_beech));
        OreDictionary.registerOre("stairWood", new ItemStack(ModBlocks.stairs_cedar));
        OreDictionary.registerOre("stairWood", new ItemStack(ModBlocks.stairs_hemlock));
        OreDictionary.registerOre("stairWood", new ItemStack(ModBlocks.stairs_maple));
        // support for cedar siding
        List<ItemStack> stackList = CraftingHelper.getItemStackForAllBlockstates(ModBlocks.cedar_siding, 1);
        for (int i = 0; i < stackList.size(); i++){
            OreDictionary.registerOre("plankWood", stackList.get(i));
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
        sokFuelHandler.addFuel(ModBlocks.wooden_slab, 150);
        sokFuelHandler.addFuel(ModItems.wooden_board, 75);

    }
}
