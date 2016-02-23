package com.arrabal.koth.util;

import com.arrabal.koth.creativetab.SoKCreativeTabs;
import com.arrabal.koth.reference.Reference;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arrabal on 2/23/2016.
 */
public class ItemRegistryHelper {


    public static Item registerItem(Item item, String name) {
        return registerItem(item, name, SoKCreativeTabs.tabSoKItems);
    }

    public static Item registerItem(Item item, String name, CreativeTabs creativeTab){
        item.setUnlocalizedName(name);
        if (creativeTab != null) {
            item.setCreativeTab(creativeTab);
        }
        GameRegistry.registerItem(item, name);


        if (FMLCommonHandler.instance().getSide() == Side.CLIENT){
            if (item.getHasSubtypes()){
                List<ItemStack> subItems = new ArrayList<ItemStack>();
                item.getSubItems(item, creativeTab, subItems);
                for (ItemStack subItem : subItems){
                    String subItemName = item.getUnlocalizedName(subItem);
                    subItemName = subItemName.substring(subItemName.indexOf(".") + 1);

                    ModelBakery.registerItemVariants(item, new ResourceLocation(Reference.MOD_ID,subItemName));
                    ModelLoader.setCustomModelResourceLocation(item, subItem.getMetadata(), new ModelResourceLocation(Reference.MOD_ID + ":" + subItemName, "inventory"));

                }
            }
            else {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
            }
        }
        return item;
    }
}
