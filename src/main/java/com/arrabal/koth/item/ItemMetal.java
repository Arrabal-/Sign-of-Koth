package com.arrabal.koth.item;

import com.arrabal.koth.reference.enums.Metals;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Arrabal on 4/6/2016.
 */
public class ItemMetal extends ItemSoK {

    public ItemMetal(){
        super(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        return "item." + Metals.byMetaData(stack.getMetadata()).getName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems){
        for (Metals metal : Metals.values()){
            subItems.add(new ItemStack(itemIn, 1, metal.getMetaData()));
        }
    }
}
