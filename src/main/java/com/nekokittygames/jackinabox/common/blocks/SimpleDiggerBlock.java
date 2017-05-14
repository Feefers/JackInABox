package com.nekokittygames.jackinabox.common.blocks;

import com.nekokittygames.jackinabox.common.tiles.TileSimpleDigger;
import com.nekokittygames.jackinabox.common.tiles.TileSimpleJack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by fiona on 05/04/2017.
 */
public class SimpleDiggerBlock extends SimpleJackBlockBase {
    public SimpleDiggerBlock(int rangeX, int rangeY, int rangeZ, float chance, String name) {
        super(rangeX, rangeY, rangeZ, chance);
        setUnlocalizedName(name);
        setRegistryName(name);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSimpleDigger().setRange(rangeX, rangeY, rangeZ).setChance(chance);
    }
}
