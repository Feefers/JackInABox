package com.nekokittygames.jackinabox.common.compat;

import com.nekokittygames.jackinabox.common.blocks.SimpleJackBlockBase;
import com.nekokittygames.jackinabox.common.tiles.BaseTileSimpleJack;
import com.nekokittygames.jackinabox.common.tiles.TileSimpleJack;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by fiona on 31/05/2017.
 */
public class JackInABoxHUDBlock implements IWailaDataProvider {
    @Nonnull
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    @Nonnull
    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    @Nonnull
    @Override
    @SideOnly(Side.CLIENT)
    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        if (iWailaDataAccessor.getBlock() instanceof SimpleJackBlockBase) {
            BaseTileSimpleJack te = (BaseTileSimpleJack) iWailaDataAccessor.getTileEntity();
            int warm_up = te.warm_up / 20;
            int layer = te.currentY;
            boolean boomin = te.booming;
            if (!boomin) {
                list.add(I18n.format("jackinabox.waila.inactive"));
            } else if (boomin && warm_up > 0) {
                list.add(I18n.format("jackinabox.waila.counting", warm_up));
            } else {
                list.add(I18n.format("jackinabox.waila.booming", layer));
            }

        }
        return list;
    }

    @Nonnull
    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    @Nonnull
    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound nbtTagCompound, World world, BlockPos blockPos) {
        return nbtTagCompound;
    }
}
