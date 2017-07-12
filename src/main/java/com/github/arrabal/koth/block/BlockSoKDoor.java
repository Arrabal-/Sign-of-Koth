package com.github.arrabal.koth.block;

import com.github.arrabal.koth.api.block.ISoKBlock;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * Created by Arrabal on 2/23/2016.
 */
public class BlockSoKDoor extends BlockDoor implements ISoKBlock {

    private Item doorItem;
    private int securedLevel;

    public static final PropertyBool SECURED = PropertyBool.create("secured");

    public BlockSoKDoor(Material material){
        super(material);
    }

    public BlockSoKDoor(boolean secured){
        super(Material.WOOD);
        this.setHardness(3.0f); // change based on material
        this.setHarvestLevel("axe",0); //change based on material and state
        this.setSoundType(SoundType.WOOD); //change based on material
        this.disableStats();
        if (secured) {
            this.securedLevel = 2;
        }
        else {
            this.securedLevel = 0;
        }
        this.setDefaultState(this.getDefaultState().withProperty(SECURED, Boolean.valueOf(secured)));
    }

    private void increaseSecuredLevel(int change){
        int secLev = this.getSecuredLevel();
        if (secLev < 2) secLev += change;
        this.setSecuredLevel(secLev);
    }

    private void decreaseSecuredLevel(int change){
        int secLev = this.getSecuredLevel();
        if (secLev > 0) secLev -= change;
        this.setSecuredLevel(secLev);
    }

    public void changeSecuredLevel(int change){
        if (change > 0) increaseSecuredLevel(change);
        else if (change < 0) decreaseSecuredLevel(change);
    }

    private void setSecuredLevel(int level){
        if (level < 0) level = 0;
        if (level > 2) level = 2;
        this.securedLevel = level;
    }

    public int getSecuredLevel(){
        return this.securedLevel;
    }

    public boolean isSecured(){
        return this.securedLevel > 1;
    }


    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return null;
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

    @Override
    public IBlockColor getBlockColor() {
        return null;
    }

    @Override
    public IItemColor getItemColor() {
        return null;
    }

    public void setDoorItem(Item doorItem) {
        this.doorItem = doorItem;
    }

    public Item getDoorItem(){
        return this.doorItem == null ? Items.OAK_DOOR : this.doorItem;
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {HALF, FACING, OPEN, HINGE, POWERED, SECURED});
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getItem(World world, BlockPos blockPos, IBlockState state){
        return new ItemStack(this.getDoorItem());
    }

    @Override
    public Item getItemDropped(IBlockState blockState, Random rand, int fortune){
        return blockState.getValue(HALF) == EnumDoorHalf.UPPER ? null : this.getDoorItem();
    }

    //TODO:  Doors that are secured have higher harvest level and do not open.
    //TODO:  Doors that are secured break into boards instead of dropping the door item
    // 6 boards = unsecured door; unsecured door + 2 boards = secured door
}
