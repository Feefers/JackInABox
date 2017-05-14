package com.nekokittygames.jackinabox.common.blocks;

import com.nekokittygames.jackinabox.common.tiles.BaseTileSimpleJack;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by fiona on 04/04/2017.
 */
public abstract class SimpleJackBlockBase extends Block implements ITileEntityProvider {

    public int rangeX;
    public int rangeY;
    public int rangeZ;
    public float chance;

    public SimpleJackBlockBase(int rangeX, int rangeY, int rangeZ, float chance) {
        super(Material.WOOD);
        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.rangeZ = rangeZ;
        this.chance = chance;
        setCreativeTab(CreativeTabs.TOOLS);
    }


    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
        worldIn.removeTileEntity(pos);
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable EnumFacing side) {
        return true;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (worldIn.isBlockPowered(pos)) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te != null) {
                ((BaseTileSimpleJack) te).boom();
            }
        }
    }


}
