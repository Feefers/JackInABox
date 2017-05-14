package com.nekokittygames.jackinabox.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

/**
 * Created by fiona on 04/05/2017.
 */
public class TileSimpleDigger extends BaseTileSimpleJack {

    @Override
    protected boolean IsAcceptible(IBlockState state, BlockPos checkpos) {
        return true;
    }

    @Override
    public String getDigType() {
        return "shovel";
    }
}
