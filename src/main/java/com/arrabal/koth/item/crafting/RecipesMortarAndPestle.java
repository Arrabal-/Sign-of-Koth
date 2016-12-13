package com.arrabal.koth.item.crafting;

import com.arrabal.koth.init.ModItems;
import com.arrabal.koth.reference.enums.Minerals;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

/**
 * Created by Arrabal on 3/29/2016.
 */
public class RecipesMortarAndPestle implements IRecipe {

    private ItemStack craftingResult = ItemStack.EMPTY;
    private ItemStack mortarPestle = ItemStack.EMPTY;

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {

        this.craftingResult = ItemStack.EMPTY;
        this.mortarPestle = ItemStack.EMPTY;
        int numMortar = 0;
        int numBricks = 0;
        int numHardClay = 0;
        int totalComponents = 0;

        for (int slot = 0; slot < inv.getSizeInventory(); slot++){
            ItemStack itemStack = inv.getStackInSlot(slot);
            if (!itemStack.isEmpty()){
                if (itemStack.getItem() == ModItems.mortar_pestle && this.mortarPestle == ItemStack.EMPTY) {
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
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> stacks = NonNullList.<ItemStack>withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        int slots;
        for (slots = 0; slots < stacks.size(); slots++){
            ItemStack stackInSlot = inv.getStackInSlot(slots);
            if (!stackInSlot.isEmpty() && stackInSlot.getItem() == ModItems.mortar_pestle) stacks.set(slots, this.mortarPestle.copy());
            else stacks.set(slots, net.minecraftforge.common.ForgeHooks.getContainerItem(stackInSlot));
        }
        return stacks;
    }
}
