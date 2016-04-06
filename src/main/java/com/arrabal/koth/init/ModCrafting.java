package com.arrabal.koth.init;

import com.arrabal.koth.block.*;
import com.arrabal.koth.handler.FurnaceFuelHandler;
import com.arrabal.koth.item.ItemStoneBowl;
import com.arrabal.koth.item.crafting.RecipesMortarAndPestle;
import com.arrabal.koth.reference.enums.*;
import com.arrabal.koth.util.CraftingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHardenedClay;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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

    @SuppressWarnings("RedundantArrayCreation")
    private static void addCraftingRecipes(){

        //===========================================Shapeless Recipes================================================//
        // logs into corresponding planks
        for (SoKLogs wood : SoKLogs.values()){
            GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.planks_0, 4, wood.getMetaData()), CraftingHelper.getItemStackFromProperty(ModBlocks.log_0, 1, BlockSoKLog.VARIANT, wood));
        }
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.cedar_siding, 1, SidingType.CEDAR_SIDING.getMetaData()),
                new Object[] {new ItemStack(ModBlocks.planks_0, 1, SoKLogs.CEDAR.getMetaData()), new ItemStack(ModItems.wooden_board, 1)});
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.cedar_siding, 1, SidingType.CEDAR_SHAKE.getMetaData()),
                new Object[] {new ItemStack(ModBlocks.planks_0, 1, SoKLogs.CEDAR.getMetaData()), new ItemStack(ModItems.wooden_board, 1),
                        new ItemStack(ModItems.wooden_board, 1)});
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.cedar_siding, 1, SidingType.WEATHERED_SIDING.getMetaData()),
                new Object[] {new ItemStack(ModBlocks.cedar_siding, 1, SidingType.CEDAR_SIDING.getMetaData()),
                new ItemStack(Blocks.dirt), new ItemStack(Items.water_bucket)});
        GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.cedar_siding, 1, SidingType.WEATHERED_SHAKE.getMetaData()),
                new Object[] {new ItemStack(ModBlocks.cedar_siding, 1, SidingType.CEDAR_SHAKE.getMetaData()),
                        new ItemStack(Blocks.dirt), new ItemStack(Items.water_bucket)});
        GameRegistry.addRecipe(new RecipesMortarAndPestle());
        RecipeSorter.register("sok:mortar_pestle", RecipesMortarAndPestle.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");


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
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.stone_bowl, 4, BowlType.ANDESITE.getMetaData()), new Object[]
                {"S S",
                 " S ", 'S', new ItemStack(Blocks.stone, 1, BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata())});
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.stone_bowl, 4, BowlType.GRANITE.getMetaData()), new Object[]
                {"S S",
                        " S ", 'S', new ItemStack(Blocks.stone, 1, BlockStone.EnumType.GRANITE_SMOOTH.getMetadata())});
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.stone_bowl, 4, BowlType.DIORITE.getMetaData()), new Object[]
                {"S S",
                        " S ", 'S', new ItemStack(Blocks.stone, 1, BlockStone.EnumType.DIORITE_SMOOTH.getMetadata())});
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mortar_pestle, 1, BowlType.ANDESITE.getMetaData()), new Object[]
                {"  S",
                 " O ",
                 "B  ", 'S', "stickWood", 'O', "stone", 'B', new ItemStack(ModItems.stone_bowl, 1, BowlType.ANDESITE.getMetaData())}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mortar_pestle, 1, BowlType.GRANITE.getMetaData()), new Object[]
                {"  S",
                 " O ",
                 "B  ", 'S', "stickWood", 'O', "stone", 'B', new ItemStack(ModItems.stone_bowl, 1, BowlType.GRANITE.getMetaData())}));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.mortar_pestle, 1, BowlType.DIORITE.getMetaData()), new Object[]
                {"  S",
                 " O ",
                 "B  ", 'S', "stickWood", 'O', "stone", 'B', new ItemStack(ModItems.stone_bowl, 1, BowlType.DIORITE.getMetaData())}));
        // Fences and Fence Gates
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gate_beech, 2), new Object[]
                {"SPS",
                 "S S", 'S', new ItemStack(ModBlocks.post_beech, 1), 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.BEECH)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gate_cedar, 2), new Object[]
                {"SPS",
                 "S S", 'S', new ItemStack(ModBlocks.post_cedar, 1), 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.CEDAR)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gate_hemlock, 2), new Object[]
                {"SPS",
                 "S S", 'S', new ItemStack(ModBlocks.post_hemlock, 1), 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.HEMLOCK)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.gate_sugarmaple, 2), new Object[]
                {"SPS",
                 "S S", 'S', new ItemStack(ModBlocks.post_sugarmaple, 1), 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.SUGAR_MAPLE)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.post_beech, 1), new Object[]
                {"S", "P", 'S', CraftingHelper.getItemStackFromProperty(ModBlocks.wooden_slab, 1, BlockSoKWoodSlab.VARIANT, SoKLogs.BEECH),
                 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.BEECH)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.post_cedar, 1), new Object[]
                {"S", "P", 'S', CraftingHelper.getItemStackFromProperty(ModBlocks.wooden_slab, 1, BlockSoKWoodSlab.VARIANT, SoKLogs.CEDAR),
                 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.CEDAR)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.post_hemlock, 1), new Object[]
                {"S", "P", 'S', CraftingHelper.getItemStackFromProperty(ModBlocks.wooden_slab, 1, BlockSoKWoodSlab.VARIANT, SoKLogs.HEMLOCK),
                 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.HEMLOCK)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.post_sugarmaple, 1), new Object[]
                {"S", "P", 'S', CraftingHelper.getItemStackFromProperty(ModBlocks.wooden_slab, 1, BlockSoKWoodSlab.VARIANT, SoKLogs.SUGAR_MAPLE),
                 'P', CraftingHelper.getItemStackFromProperty(ModBlocks.planks_0, 1, BlockSoKPlanks.VARIANT, SoKLogs.SUGAR_MAPLE)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.fence_beech, 2), new Object[]
                {"PBP",
                 "PBP", 'P', new ItemStack(ModBlocks.post_beech, 1), 'B', new ItemStack(ModItems.wooden_board, 1)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.fence_cedar, 2), new Object[]
                {"PBP",
                 "PBP", 'P', new ItemStack(ModBlocks.post_cedar, 1), 'B', new ItemStack(ModItems.wooden_board, 1)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.fence_hemlock, 2), new Object[]
                {"PBP",
                 "PBP", 'P', new ItemStack(ModBlocks.post_hemlock, 1), 'B', new ItemStack(ModItems.wooden_board, 1)});
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.fence_sugarmaple, 2), new Object[]
                {"PBP",
                 "PBP", 'P', new ItemStack(ModBlocks.post_sugarmaple, 1), 'B', new ItemStack(ModItems.wooden_board, 1)});
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.metals, 1, Metals.PURE_SILVER_INGOT.getMetaData()), new Object[]
                {"NNN",
                 "NNN",
                 "NNN", 'N', new ItemStack(ModItems.metals, 1, Metals.PURE_SILVER_NUGGET.getMetaData())});
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
        // stone bowls and mortar and pestles
        for (BowlType bowlType : BowlType.values()) {
            OreDictionary.registerOre("bowlStone", new ItemStack(ModItems.stone_bowl, 1, bowlType.getMetaData()));
            OreDictionary.registerOre("mortarPestle", new ItemStack(ModItems.mortar_pestle, 1, bowlType.getMetaData()));
        }
    }

    public static void addSmeltingRecipes(){

        //smelt SoK Logs into charcoal
        for (SoKLogs wood : SoKLogs.values()){
            GameRegistry.addSmelting(CraftingHelper.getItemStackFromProperty(ModBlocks.log_0, 1, BlockSoKLog.VARIANT, wood), new ItemStack(Items.coal, 1, 1), 0.15f);
        }
        GameRegistry.addSmelting(new ItemStack(ModItems.minerals, 1, Minerals.SILVER_CHLORIDE.getMetaData()), new ItemStack(ModItems.metals, 1, Metals.PURE_SILVER_NUGGET.getMetaData()), 0.1f);

        // add new fuels
        FurnaceFuelHandler sokFuelHandler = new FurnaceFuelHandler();
        GameRegistry.registerFuelHandler(sokFuelHandler);
        sokFuelHandler.addFuel(ModBlocks.sapling, 100);
        sokFuelHandler.addFuel(ModBlocks.wooden_slab, 150);
        sokFuelHandler.addFuel(ModItems.wooden_board, 75);
        sokFuelHandler.addFuel(ModItems.boarded_door, 450);
        sokFuelHandler.addFuel(ModItems.secured_door, 600);

    }
}
