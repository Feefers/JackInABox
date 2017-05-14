package com.nekokittygames.jackinabox.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by fiona on 04/05/2017.
 */
public class TileSimpleJack extends BaseTileSimpleJack {


    @Override
    protected boolean IsAcceptible(IBlockState state, BlockPos checkpos) {

        List<ItemStack> items = state.getBlock().getDrops(world, checkpos, state, 0);
        for (ItemStack stack : items) {
            if (!stack.isEmpty() && world.getGameRules().getBoolean("doTileDrops")) {
                int[] ids = OreDictionary.getOreIDs(stack);
                for (int id : ids) {
                    if (OreDictionary.getOreName(id).equalsIgnoreCase("logWood"))
                        return true;
                    }

                }

            }
        if (state.getBlock().isLeaves(state, world, checkpos)) {
            return true;
        }
        return false;
    }

    @Override
    public String getDigType() {
        return "axe";
    }
}
