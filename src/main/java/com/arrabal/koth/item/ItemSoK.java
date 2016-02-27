package com.arrabal.koth.item;

import com.arrabal.koth.creativetab.SoKCreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by Arrabal on 2/24/2016.
 */
public class ItemSoK extends Item {

    public ItemSoK(){
        super();
        setMaxStackSize(1);
        setCreativeTab(SoKCreativeTabs.tabSoKItems);
        setNoRepair();
    }

    public ItemSoK(String itemName){
        this();
        setRegistryName(itemName);
        setUnlocalizedName(itemName);
    }

    public void setCanRepair(boolean canRepair){
        this.canRepair = canRepair;
    }
}
