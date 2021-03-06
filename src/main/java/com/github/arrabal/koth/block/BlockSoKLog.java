package com.github.arrabal.koth.block;

import com.github.arrabal.koth.api.block.ISoKBlock;
import com.github.arrabal.koth.item.ItemSoKBlock;
import com.github.arrabal.koth.reference.enums.SoKLogs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by Arrabal on 3/1/2016.
 */
public class BlockSoKLog extends BlockLog implements ISoKBlock {

    public static final PropertyEnum<SoKLogs> VARIANT = PropertyEnum.<SoKLogs>create("variant", SoKLogs.class);

    public BlockSoKLog(){
        super();
        this.setDefaultState(this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
        this.setBlockstateHarvestLevels();
    }

    @Override
    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {LOG_AXIS, VARIANT});
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.values()[meta >> 2]).withProperty(VARIANT, SoKLogs.byMetaData(meta & 3));
    }

    @Override
    public int getMetaFromState(IBlockState state){
        SoKLogs wood = (SoKLogs) state.getValue(VARIANT);
        return ((BlockLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal() * 4 + wood.getMetaData();
    }

    @Override
    public int damageDropped(IBlockState state){
        return this.getMetaFromState(state.withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public float getBlockHardness(IBlockState state, World worldIn, BlockPos pos){
        float hardness = this.blockHardness;
            switch (state.getValue(VARIANT)){
                case BEECH:
                    hardness += 0.5f;
                    break;
                case SUGAR_MAPLE:
                    hardness += 1.0f;
                    break;
                default:
                    break;
            }
        return hardness;
    }


    private boolean canHeldItemHarvest(EntityPlayer harvester, IBlockState blockToHarvest){
        ItemStack itemStack = harvester.getHeldItem(EnumHand.MAIN_HAND);
        return !itemStack.isEmpty() ? itemStack.canHarvestBlock(blockToHarvest) : false;
    }

    private boolean canHarvestBlock(Block block, EntityPlayer player, IBlockAccess world, BlockPos pos){
        IBlockState state = world.getBlockState(pos);
        if (state.getValue(VARIANT) != SoKLogs.BEECH &&
                state.getValue(VARIANT) != SoKLogs.SUGAR_MAPLE && block.getMaterial(state).isToolNotRequired())
        {
            return true;
        }

        ItemStack stack = player.inventory.getCurrentItem();
        state = state.getBlock().getActualState(state, world, pos);
        String tool = block.getHarvestTool(state);
        if (stack.isEmpty() || tool == null)
        {
            return net.minecraftforge.event.ForgeEventFactory.doPlayerHarvestCheck(player, state, this.canHeldItemHarvest(player, state));

        }

        int toolLevel = stack.getItem().getHarvestLevel(stack, tool, player, state);
        if (toolLevel < 0)
        {
            return net.minecraftforge.event.ForgeEventFactory.doPlayerHarvestCheck(player, state, this.canHeldItemHarvest(player, state));
        }

        return toolLevel >= block.getHarvestLevel(state);
    }

    @Override
    public float getPlayerRelativeBlockHardness(IBlockState state, EntityPlayer playerIn, World worldIn, BlockPos pos){
        float hardness = state.getBlockHardness(worldIn, pos);
        if (hardness < 0.0F)
        {
            return 0.0F;
        }

        if (!canHarvestBlock(state.getBlock(), playerIn, worldIn, pos))
        {
            return playerIn.getDigSpeed(state, pos) / hardness / 100F;
        }
        else
        {
            return playerIn.getDigSpeed(state, pos) / hardness / 30F;
        }
    }

    @Override
    public boolean isToolEffective(String type, IBlockState state){
        if (state.getValue(VARIANT) != SoKLogs.BEECH &&
                state.getValue(VARIANT) != SoKLogs.SUGAR_MAPLE) return super.isToolEffective(type, state);
        if ("axe".equals(type)) return false;
        return type != null && type.equals(getHarvestTool(state));
    }

    @Override
    public Class<? extends ItemBlock> getItemClass() {
        return ItemSoKBlock.class;
    }

    @Override
    public IProperty[] getPresetProperties() {
        return new IProperty[] {VARIANT};
    }

    @Override
    public IProperty[] getNonRenderingProperties() {
        return null;
    }

    @Override
    public String getBlockstateName(IBlockState blockState) {

        SoKLogs wood = blockState.getValue(VARIANT);
        switch(wood){
            default:
                return wood.getName() + "_log";
        }
    }

    @Override
    public IBlockColor getBlockColor() {
        return null;
    }

    @Override
    public IItemColor getItemColor() {
        return null;
    }

    private void setBlockstateHarvestLevels(){
        java.util.Iterator<IBlockState> iterator = getBlockState().getValidStates().iterator();
        while (iterator.hasNext()){
            IBlockState state = iterator.next();
            switch (state.getValue(VARIANT)){
                case BEECH: case SUGAR_MAPLE:
                    this.setHarvestLevel("axe", 3, state);
                    break;
                default:
                    this.setHarvestLevel("axe", 0, state);
            }
        }
    }
}
