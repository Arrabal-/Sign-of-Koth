package com.arrabal.koth.handler;

import com.arrabal.koth.init.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * Created by Arrabal on 3/24/2016.
 */
public class BlockEventHandler {

    @SubscribeEvent
    public void onHarvestDropsEvent(BlockEvent.HarvestDropsEvent event){
        if (event.getState().getBlock() == Blocks.gold_ore && !event.isSilkTouching()){
            Random random = new Random(event.getWorld().getTotalWorldTime());
            if (random.nextInt(100) < 15){
                int numDropped = random.nextInt(3) + 1;
                if (event.getFortuneLevel() > 0){
                    int i = random.nextInt(event.getFortuneLevel() + 2) - 1;
                    if (i < 0) i = 0;
                    numDropped = numDropped * (i + 1);
                }
                ItemStack stack = new ItemStack(ModItems.minerals, numDropped, 0);
                event.getDrops().add(stack);
            }
        }
    }
}
