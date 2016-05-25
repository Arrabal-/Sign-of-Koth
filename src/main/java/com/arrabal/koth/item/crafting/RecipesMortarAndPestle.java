package com.arrabal.koth.item.crafting;

import com.arrabal.koth.init.ModItems;
import com.arrabal.koth.reference.enums.Minerals;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

/**
 * Created by Arrabal on 3/29/2016.
 */
public class RecipesMortarAndPestle implements IRecipe {

    private ItemStack craftingResult;
    private ItemStack mortarPestle;

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {

        this.craftingResult = null;
        this.mortarPestle = null;
        int numMortar = 0;
        int numBricks = 0;
        int numHardClay = 0;
        int totalComponents = 0;

        for (int slot = 0; slot < inv.getSizeInventory(); slot++){
            ItemStack itemStack = inv.getStackInSlot(slot);
            if (itemStack != null){
                if (itemStack.getItem() == ModItems.mortar_pestle && this.mortarPestle == null) {
                    numMortar++;
                    this.mortarPestle = itemStack;
                }
                else if (itemStack.getItem() == Items.BRICK) numBricks++;
                else if (itemStack.getItem() instanceof ItemBlock){
                    if ( ((ItemBlock) itemStack.getItem()).getBlock() == Blocks.HARDENED_CLAY) numHardClay++;
                }
            }
        }
        totalComponents = totalComponents + numMortar + numBricks + numHardClay;
        if (numMortar != 1) return false;
        switch (totalComponents){
            case 2:{
                if (numBricks == 1){
                    craftingResult = new ItemStack(ModItems.minerals, 1, Minerals.BRICK_DUST.getMetaData());
                    return true;
                }
                else if (numHardClay == 1){
                    craftingResult = new ItemStack(ModItems.minerals, 4, Minerals.BRICK_DUST.getMetaData());
                    return true;
                }
                break;
            }
            default: return false;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return this.craftingResult.copy();
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.craftingResult;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inv) {
        ItemStack[] stacks = new ItemStack[inv.getSizeInventory()];
        int slots;
        for (slots = 0; slots < stacks.length; slots++){
            ItemStack stackInSlot = inv.getStackInSlot(slots);
            if (stackInSlot != null && stackInSlot.getItem() == ModItems.mortar_pestle) stacks[slots] = this.mortarPestle.copy();
            else stacks[slots] = net.minecraftforge.common.ForgeHooks.getContainerItem(stackInSlot);
        }
        return stacks;
    }
}
