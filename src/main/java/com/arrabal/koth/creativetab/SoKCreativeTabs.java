package com.arrabal.koth.creativetab;

import com.arrabal.koth.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Arrabal on 2/21/2016.
 */
public class SoKCreativeTabs {

    public static final CreativeTabs tabSoKBlocks = new CreativeTabs(CreativeTabs.getNextID(), Reference.LOWERCASE_MOD_ID + "blocks"){
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem(){
            return Item.getItemFromBlock(Blocks.brick_block); //TODO:  replace with mod block
        }
    };

    public static final CreativeTabs tabSoKItems = new CreativeTabs(CreativeTabs.getNextID(), Reference.LOWERCASE_MOD_ID + "items") {
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem() {
            return Items.item_frame; //TODO: replace with mod item
        }
    };

}
