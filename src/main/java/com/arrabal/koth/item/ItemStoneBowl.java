package com.arrabal.koth.item;

import com.arrabal.koth.reference.enums.BowlType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Arrabal on 3/28/2016.
 */
public class ItemStoneBowl extends ItemSoK {

    public ItemStoneBowl(){
        super(64);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        return "item." + BowlType.byMetaData(stack.getMetadata()).getName() + "_bowl";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems){
        for (BowlType bowlType : BowlType.values()){
            subItems.add(new ItemStack(itemIn, 1, bowlType.getMetaData()));
        }
    }
}
