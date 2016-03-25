package com.arrabal.koth.item;

import com.arrabal.koth.reference.enums.Minerals;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Arrabal on 3/24/2016.
 */
public class ItemMineral extends ItemSoK {

    public ItemMineral(){
        super(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        return Minerals.byMetaData(stack.getMetadata()).getName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems){
        subItems.add(new ItemStack(itemIn, 1, Minerals.ELECTRUM_FRAGMENT.getMetaData()));
        subItems.add(new ItemStack(itemIn, 1, Minerals.GREEN_VITRIOL.getMetaData()));
        subItems.add((new ItemStack(itemIn, 1, Minerals.SILVER_CHLORIDE.getMetaData())));
    }
}
