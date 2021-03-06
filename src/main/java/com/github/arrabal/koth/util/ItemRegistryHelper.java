package com.github.arrabal.koth.util;

import com.github.arrabal.koth.creativetab.SoKCreativeTabs;
import com.github.arrabal.koth.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by Arrabal on 2/23/2016.
 */
public class ItemRegistryHelper {


    public static Item registerItem(Item item, String name) {
        return registerItem(item, name, SoKCreativeTabs.tabSoKItems);
    }

    public static Item registerItem(ItemSlab item, String name){
        return GameRegistry.register(item, new ResourceLocation(Reference.MOD_PREFIX + name));
    }

    public static Item registerItem(Item item, String name, CreativeTabs creativeTab){
        item.setUnlocalizedName(name);
        if (creativeTab != null) {
            item.setCreativeTab(creativeTab);
        }
        String itemRegName = Reference.MOD_PREFIX + name + "_item" ;
        item.setRegistryName(new ResourceLocation(itemRegName));
        GameRegistry.register(item);


        if (FMLCommonHandler.instance().getSide() == Side.CLIENT){
            if (item.getHasSubtypes()){
                NonNullList<ItemStack> subItems = NonNullList.<ItemStack>create();
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
