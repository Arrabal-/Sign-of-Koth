package com.arrabal.koth.handler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arrabal on 3/14/2016.
 */
public class FurnaceFuelHandler implements IFuelHandler {

    private Map<Pair<Item, Integer>, Integer> itemMetaFuelList = new HashMap<Pair<Item, Integer>, Integer>();
    private Map<Item, Integer> itemFuelList = new HashMap<Item, Integer>();

    @Override
    public int getBurnTime(ItemStack fuel) {
        return getFuelValue(fuel);
    }

    public void addFuel(Item item, int meta, int value){
        itemMetaFuelList.put(Pair.of(item, meta), value);
    }

    public void addFuel(Item item, int value){
        itemFuelList.put(item, value);
    }

    public void addFuel(Block block, int meta, int value){
        addFuel(Item.getItemFromBlock(block), meta, value);
    }

    public void addFuel(Block block, int value){
        addFuel(Item.getItemFromBlock(block), value);
    }

    public int getFuelValue(ItemStack stack){
        if (stack.isEmpty()) return 0;
        Item item = stack.getItem();
        if (item == null) return 0;
        Pair<Item, Integer> itemMetaPair = Pair.of(item, stack.getItemDamage());

        if (itemMetaFuelList.containsKey(itemMetaPair)){
            return itemMetaFuelList.get(itemMetaPair);
        }

        if (itemFuelList.containsKey(item)){
            return itemFuelList.get(item);
        }

        return 0;
    }
}
