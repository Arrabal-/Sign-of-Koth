package com.arrabal.koth.init;

import com.arrabal.koth.reference.Reference;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Arrabal on 2/22/2016.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems {

    public static Item boarded_door;

    public static void init(){
        registerItems();
        setupModels();
    }

    public static void registerItems(){

    }

    private static void setupModels(){
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT){
            //register models
        }
    }
}
