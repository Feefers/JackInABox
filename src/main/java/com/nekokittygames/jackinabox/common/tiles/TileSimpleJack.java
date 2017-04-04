package com.nekokittygames.jackinabox.common.tiles;

import com.nekokittygames.jackinabox.common.JackInABox;
import com.nekokittygames.jackinabox.common.libs.References;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fiona on 04/04/2017.
 */
public class TileSimpleJack extends TileEntity implements ITickable {

    public boolean booming = false;
    public int warm_up = 0;


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readExtraNBT(compound);

    }

    public void readExtraNBT(NBTTagCompound compound) {
        booming = compound.getBoolean("BOOMING");
        warm_up = compound.getInteger("WARMUP");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        return writeExtraNBT(compound);
    }

    public NBTTagCompound writeExtraNBT(NBTTagCompound compound) {
        compound.setBoolean("BOOMING", booming);
        compound.setInteger("WARMUP", warm_up);
        return compound;
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound cmp = new NBTTagCompound();
        writeExtraNBT(cmp);
        return new SPacketUpdateTileEntity(pos, 1, cmp);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        readExtraNBT(pkt.getNbtCompound());
    }

    @Override
    public void update() {
        if (booming && !world.isRemote) {
            //if(warm_up>0)
            //{
            //    warm_up--;
            //    return;
            //}
            JackInABox.log.info("Boom");
            Map<BlockPos, IBlockState> toRemove = new HashMap<>();
            for (int x = pos.getX() - References.SIMPLE_RANGE_X / 2; x < pos.getX() + References.SIMPLE_RANGE_X / 2; x++) {
                for (int y = pos.getY() - References.SIMPLE_RANGE_Y / 2; y < pos.getY() + References.SIMPLE_RANGE_Y / 2; y++) {
                    for (int z = pos.getZ() - References.SIMPLE_RANGE_Z / 2; z < pos.getZ() + References.SIMPLE_RANGE_Z / 2; z++) {
                        BlockPos checkpos = new BlockPos(x, y, z);

                        IBlockState state = world.getBlockState(checkpos);
                        if (state.getBlock().getHarvestTool(state) != null && (state.getBlock().getHarvestTool(state).equalsIgnoreCase("axe") || state.getBlock().isLeaves(state, world, checkpos))) {
                            toRemove.put(checkpos, state);
                        }

                    }
                }
            }
            for (Map.Entry<BlockPos, IBlockState> entry : toRemove.entrySet()) {
                List<ItemStack> items = entry.getValue().getBlock().getDrops(world, entry.getKey(), entry.getValue(), 0);
                for (ItemStack stack : items) {
                    if (!stack.isEmpty() && world.getGameRules().getBoolean("doTileDrops")) {
                        float f = 0.5F;
                        double d0 = (double) (world.rand.nextFloat() * 0.5F) + 0.25D;
                        double d1 = (double) (world.rand.nextFloat() * 0.5F) + 0.25D;
                        double d2 = (double) (world.rand.nextFloat() * 0.5F) + 0.25D;
                        EntityItem entityitem = new EntityItem(world, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, stack);
                        entityitem.setDefaultPickupDelay();
                        world.spawnEntity(entityitem);
                    }
                }
                world.setBlockToAir(entry.getKey());

            }
            world.setBlockToAir(pos);
        }
    }

    public void boom() {
        if (booming == false) {
            booming = true;
            warm_up = 200;
            this.markDirty();
        }
    }


}
