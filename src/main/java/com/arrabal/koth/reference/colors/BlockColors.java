package com.arrabal.koth.reference.colors;

import com.arrabal.koth.api.block.ISoKBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;

/**
 * Created by Arrabal on 3/19/2016.
 */
public class BlockColors {

    public static final IBlockColor FOLIAGE_COLOR = new IBlockColor() {
        @Override
        public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
            return world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic();
        }
    };

    public static final IBlockColor GRASS_COLOR = new IBlockColor() {
        @Override
        public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
            return world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
        }
    };

    public static final IItemColor BLOCK_ITEM_COLOR = new IItemColor() {
        @Override
        public int getColorFromItemstack(ItemStack stack, int tintIndex) {
            IBlockState state = ((ItemBlock)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
            IBlockColor blockColor = ((ISoKBlock)state.getBlock()).getBlockColor();
            return blockColor == null ? 0xFFFFFF : blockColor.colorMultiplier(state, null, null, tintIndex);

        }
    };
}
