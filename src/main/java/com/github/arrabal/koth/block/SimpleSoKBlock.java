package com.github.arrabal.koth.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Arrabal on 2/24/2016.
 */
public class SimpleSoKBlock extends Block {

    public SimpleSoKBlock(){
        this(Material.ROCK);
    }

    public SimpleSoKBlock(Material material){
        super(material);
    }


    @Override
    public void breakBlock(World world, BlockPos blockPos, IBlockState blockState){
        this.dropInventory(world, blockPos, blockState);
        super.breakBlock(world, blockPos, blockState);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase placer, ItemStack itemStack){
        super.onBlockPlacedBy(world, blockPos, blockState, placer, itemStack);
        if (this.hasTileEntity(blockState)){
            //used to set facing for mod TE
            EnumFacing facing = placer.getHorizontalFacing().getOpposite();
            if (itemStack.hasDisplayName()){
                //if the tile entity needs to be set to a custom name
            }
            //if the facing of the tile entity needs to be set
        }
    }

    public static EnumFacing getFacingFromEntitiy(World world, BlockPos clickedBlock, EntityLivingBase clicker){
        return clicker.getHorizontalFacing().getOpposite();
    }

    protected void dropInventory(World world, BlockPos blockPos, IBlockState blockState){
        if (!this.hasTileEntity(blockState)) return;
        TileEntity tileEntity = world.getTileEntity(blockPos);
        if (!(tileEntity instanceof IInventory)) return;
        IInventory inventory = (IInventory) tileEntity;
        for (int i = 0; i < inventory.getSizeInventory(); i++){
            ItemStack itemStack = inventory.getStackInSlot(i);
            if (!itemStack.isEmpty() && itemStack.getCount() > 0) {
                Random rand = new Random();
                float dx = rand.nextFloat() * 0.8f + 0.1f;
                float dy = rand.nextFloat() * 0.8f + 0.1f;
                float dz = rand.nextFloat() * 0.8f + 0.1f;

                EntityItem entityItem = new EntityItem(world, blockPos.getX() + dx, blockPos.getY() + dy, blockPos.getZ() + dz, itemStack.copy());

                if (itemStack.hasTagCompound()){
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float motionFactor = 0.05f;
                entityItem.motionX = rand.nextGaussian() * motionFactor;
                entityItem.motionY = rand.nextGaussian() * motionFactor + 0.2f;
                entityItem.motionZ = rand.nextGaussian() * motionFactor;
                world.spawnEntity(entityItem);
                itemStack.setCount(0);
            }
        }
    }
}
