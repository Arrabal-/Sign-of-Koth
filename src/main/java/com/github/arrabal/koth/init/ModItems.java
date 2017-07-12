package com.github.arrabal.koth.init;

import com.github.arrabal.koth.item.*;
import com.github.arrabal.koth.util.ItemRegistryHelper;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Arrabal on 2/22/2016.
 */
//@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static Item boarded_door;
    public static Item secured_door;
    public static Item wooden_slab;
    public static final Item wooden_board = new ItemWoodenBoard();
    public static final Item minerals = new ItemMineral();
    public static final Item stone_bowl = new ItemStoneBowl();
    public static final Item mortar_pestle = new ItemMortarAndPestle();
    public static final Item metals = new ItemMetal();
    public static final Item dossier = new ItemGuide();
    public static final Item scrawled_notes = new ItemFoundNotes();

    public static void init(){
        registerItems();
        setupModels();
    }

    public static void registerItems(){

        // crafting components
        ItemRegistryHelper.registerItem(wooden_board, "wooden_board");
        ItemRegistryHelper.registerItem(minerals, "mineral");
        ItemRegistryHelper.registerItem(stone_bowl, "stone_bowl");
        ItemRegistryHelper.registerItem(mortar_pestle, "mortar_and_pestle");
        ItemRegistryHelper.registerItem(metals, "metal");
        ItemRegistryHelper.registerItem(dossier, "sok_guide");
        ItemRegistryHelper.registerItem(scrawled_notes, "scrawled_notes");

    }

    private static void setupModels(){
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT){
            //register models
        }
    }
}
