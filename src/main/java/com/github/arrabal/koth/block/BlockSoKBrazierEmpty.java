package com.github.arrabal.koth.block;

import com.github.arrabal.koth.creativetab.SoKCreativeTabs;
import com.github.arrabal.koth.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Arrabal on 5/11/2016.
 */
public class BlockSoKBrazierEmpty extends Block {

    //protected static final AxisAlignedBB baseBB = new AxisAlignedBB(-0.315625d, 0.0d, -0.315625d, 0.315625d, 0.0625d, 0.315625d);
    //protected static final AxisAlignedBB standBB = new AxisAlignedBB(-0.1687256d, 0.0625d, -0.1687256d, 0.1687256d, 0.3d, 0.1678256d);
    //protected static final AxisAlignedBB postBB = new AxisAlignedBB(-0.08125d, 0.3d, -0.08125d, 0.08125d, 1.28736d, 0.08125d);
    //protected static final AxisAlignedBB bowlBB = new AxisAlignedBB(-0.28125d, 1.28736d, -0.28125d, 0.28125d, 1.53125d, 0.28125d);
    //protected static final AxisAlignedBB selectionBox = new AxisAlignedBB(-0.08125d, 0.0d, -0.08125d, 0.08125d, 1.53125d, 0.08125d);
    protected static final AxisAlignedBB baseBB = new AxisAlignedBB(0.184375d, 0.0d, 0.184375, 0.815625d, 0.0625d, 0.815625d);
    protected static final AxisAlignedBB standBB = new AxisAlignedBB(0.331275d, 0.0625d, 0.331275d, 0.6687256d, 0.3d, 0.6687256d);
    protected static final AxisAlignedBB postBB = new AxisAlignedBB(0.41875d, 0.3d, 0.41875d, 0.58125d, 1.28736d, 0.58125d);
    protected static final AxisAlignedBB bowlBB = new AxisAlignedBB(0.21875d, 1.28736d, 0.21875d, 0.78125d, 1.53125d, 0.78125d);
    protected static final AxisAlignedBB selectionBox = new AxisAlignedBB(0.41875d, 0.0d, 0.41875d, 0.58125d, 1.0d, 0.58125d);

    public BlockSoKBrazierEmpty(){
        super(Material.ROCK);
        setUnlocalizedName("brazier_stone");
        setRegistryName(new ResourceLocation(Reference.MOD_ID, "brazier_stone"));
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), getRegistryName());
        initModel();
        setCreativeTab(SoKCreativeTabs.tabSoKBlocks);
        this.useNeighborBrightness = true;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side){
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState){
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState){
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState blockState, IBlockAccess world, BlockPos pos){
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState blockState){
        return false;
    }

    /*
    @Override
    public boolean isVisuallyOpaque(){
        return false;
    }*/

    @Override
    public EnumBlockRenderType getRenderType(IBlockState blockState){
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return selectionBox;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_){
        addCollisionBoxToList(pos, entityBox, collidingBoxes, baseBB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, standBB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, postBB);
        addCollisionBoxToList(pos, entityBox, collidingBoxes, bowlBB);
    }
}