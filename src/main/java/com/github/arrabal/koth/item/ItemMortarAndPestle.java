package com.github.arrabal.koth.item;

import com.github.arrabal.koth.reference.enums.BowlType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Arrabal on 3/28/2016.
 */
public class ItemMortarAndPestle extends ItemSoK {

    public ItemMortarAndPestle(){
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        return "item." + BowlType.byMetaData(stack.getMetadata()).getName() + "_mortar_and_pestle";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems){
        for (BowlType bowlType : BowlType.values()){
            subItems.add(new ItemStack(itemIn, 1, bowlType.getMetaData()));
        }
    }
}
