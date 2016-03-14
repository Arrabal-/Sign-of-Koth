package com.arrabal.koth.block;

import com.arrabal.koth.api.block.ISoKBlock;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by Arrabal on 2/23/2016.
 */
public class BlockSoKDoor extends BlockDoor implements ISoKBlock {

    private Item doorItem;

    public static final PropertyBool SECURED = PropertyBool.create("secured");

    public BlockSoKDoor(Material material){
        super(material);
    }

    public BlockSoKDoor(){
        super(Material.wood);
        this.setHardness(3.0f); // change based on material
        this.setHarvestLevel("axe",0); //change based on material and state
        this.setStepSound(soundTypeWood); //change based on material
        this.disableStats();
        this.setDefaultState(this.getDefaultState().withProperty(SECURED, Boolean.valueOf(false)));
    }



    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return null;
    }

    @Override
    public int getItemRenderColor(IBlockState blockState, int tintIndex) {
        return this.getRenderColor(blockState);
    }

    @Override
    public IProperty[] getPresetProperties() {
        return new IProperty[]{};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return new IProperty[]{POWERED, SECURED};
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {
        return "";
    }

    public void setDoorItem(Item doorItem) {
        this.doorItem = doorItem;
    }

    public Item getDoorItem(){
        return this.doorItem == null ? Items.oak_door : this.doorItem;
    }

    @Override
    protected BlockState createBlockState(){
        return new BlockState(this, new IProperty[] {HALF, FACING, OPEN, HINGE, POWERED, SECURED});
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, BlockPos blockPos){
        return this.getDoorItem();
    }

    @Override
    public Item getItemDropped(IBlockState blockState, Random rand, int fortune){
        return blockState.getValue(HALF) == EnumDoorHalf.UPPER ? null : this.getDoorItem();
    }

    //TODO:  Doors that are secured have higher harvest level and do not open.
    //TODO:  Doors that are secured break into boards instead of dropping the door item
}
