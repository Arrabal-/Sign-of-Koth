package com.github.arrabal.koth.item;

import net.minecraft.item.Item;

/**
 * Created by Arrabal on 2/24/2016.
 */
public abstract class ItemSoK extends Item {

    public ItemSoK(){
        super();
        this.setMaxStackSize(1);
        this.setNoRepair();
    }

    public ItemSoK(String itemName){
        this();
        this.setRegistryName(itemName);
    }

    public ItemSoK(int maxStackSize){
        super();
        this.setMaxStackSize(maxStackSize);
        this.setNoRepair();
    }

    public void setCanRepair(boolean canRepair){
        this.canRepair = canRepair;
    }
}
