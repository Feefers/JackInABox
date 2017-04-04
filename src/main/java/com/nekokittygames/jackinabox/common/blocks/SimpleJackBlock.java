package com.nekokittygames.jackinabox.common.blocks;

import com.nekokittygames.jackinabox.common.libs.LibBlocks;
import com.nekokittygames.jackinabox.common.tiles.TileSimpleJack;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by fiona on 04/04/2017.
 */
public class SimpleJackBlock extends Block implements ITileEntityProvider {
    public SimpleJackBlock() {
        super(Material.WOOD);
        setUnlocalizedName(LibBlocks.SIMPLE_JACK);
        setRegistryName(LibBlocks.SIMPLE_JACK);
        setCreativeTab(CreativeTabs.TOOLS);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileSimpleJack();
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
        if (!worldIn.isRemote && worldIn.isBlockPowered(pos)) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te != null) {
                ((TileSimpleJack) te).boom();
            }
        }
    }


}
