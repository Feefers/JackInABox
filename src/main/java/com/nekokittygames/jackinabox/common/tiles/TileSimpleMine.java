package com.nekokittygames.jackinabox.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;

/**
 * Created by fiona on 04/05/2017.
 */
public class TileSimpleMine extends BaseTileSimpleJack {


    @Override
    protected boolean IsAcceptible(IBlockState stack, BlockPos checkpos) {
        return true;
    }

    @Override
    public String getDigType() {
        return "pickaxe";
    }
}
