package com.arrabal.koth.item;

import com.arrabal.koth.api.block.ISoKBlock;
import com.arrabal.koth.util.BlockStateHelper;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Arrabal on 3/1/2016.
 */
public class ItemSoKBlock extends ItemBlock {

    public ISoKBlock sokBlock;

    public ItemSoKBlock(Block block){
        super(block);
        if (block instanceof ISoKBlock){
            this.sokBlock = (ISoKBlock) block;
        }
        else{
            throw new IllegalArgumentException("ItemSoKBlock must be created with a block implementing ISoKBlock");
        }
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems){
        ImmutableSet<IBlockState> presets = BlockStateHelper.getBlockstatePresets(this.block);
        if (presets.isEmpty()){
            subItems.add(new ItemStack(this.block, 1, 0));
        }
        else{
            for (IBlockState blockState : presets){
                subItems.add(new ItemStack(this.block, 1, this.block.getMetaFromState(blockState)));
            }
        }
    }

    @Override
    public int getMetadata(int meta){
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack){
        ImmutableSet<IBlockState> presets = BlockStateHelper.getBlockstatePresets(this.block);
        if (presets.isEmpty()){
            return super.getUnlocalizedName();
        }
        else{
            int meta = stack.getMetadata();
            IBlockState oldState = block.getStateFromMeta(meta);
            IBlockState newState = BlockStateHelper.getPresetBlockstate(oldState);
            return super.getUnlocalizedName() + "." + sokBlock.getBlockstateName(newState);
        }
    }
}
