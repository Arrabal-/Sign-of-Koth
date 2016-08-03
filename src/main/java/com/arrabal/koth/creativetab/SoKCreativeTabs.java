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

    public static final CreativeTabs tabSoKBlocks = new CreativeTabs(CreativeTabs.getNextID(), Reference.MOD_ID + "blocks"){
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem(){
            return Item.getItemFromBlock(Blocks.BRICK_BLOCK); //TODO:  replace with mod block
        }
    };

    public static final CreativeTabs tabSoKItems = new CreativeTabs(CreativeTabs.getNextID(), Reference.MOD_ID + "items") {
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem() {
            return Items.ITEM_FRAME; //TODO: replace with mod item
        }
    };

    public static final CreativeTabs tabSoKWorldGen = new CreativeTabs(CreativeTabs.getNextID(), Reference.MOD_ID + "world_gen") {
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.PUMPKIN); //TODO:  replae with mod block
        }
    };

}
