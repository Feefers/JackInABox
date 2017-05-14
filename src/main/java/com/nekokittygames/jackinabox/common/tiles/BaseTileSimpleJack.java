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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.core.config.plugins.ResolverUtil;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Created by fiona on 04/04/2017.
 */
public abstract class BaseTileSimpleJack extends TileEntity implements ITickable {

    public boolean booming = false;
    public int warm_up = 0;
    public int rangeX;
    public int rangeY;
    public int rangeZ;
    public int currentY = 0;
    public float chance = 0.25f;

    public abstract String getDigType();


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readExtraNBT(compound);

    }

    public BaseTileSimpleJack setRange(int rangeX, int rangeY, int rangeZ) {
        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.rangeZ = rangeZ;
        return this;
    }

    public BaseTileSimpleJack setChance(float chance) {
        this.chance = chance;
        return this;
    }

    public void readExtraNBT(NBTTagCompound compound) {
        booming = compound.getBoolean("BOOMING");
        warm_up = compound.getInteger("WARMUP");
        currentY = compound.getInteger("CURRENTY");
        rangeX = compound.getInteger("RANGEX");
        rangeY = compound.getInteger("RANGEY");
        rangeZ = compound.getInteger("RANGEZ");
        chance = compound.getFloat("CHANCE");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        return writeExtraNBT(compound);
    }

    public NBTTagCompound writeExtraNBT(NBTTagCompound compound) {
        compound.setBoolean("BOOMING", booming);
        compound.setInteger("WARMUP", warm_up);
        compound.setInteger("CURRENTY", currentY);
        compound.setInteger("RANGEX", rangeX);
        compound.setInteger("RANGEY", rangeY);
        compound.setInteger("RANGEZ", rangeZ);
        compound.setFloat("CHANCE", chance);
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
        if (booming) {
            if (warm_up > 0) {
                warm_up--;
                return;
            }
            if (!world.isRemote) {
                if (world.getWorldTime() % 20 == 0) {
                    JackInABox.log.info("Boom");
                    if (currentY == 0) {
                        currentY = pos.getY() - rangeY / 2;
                    }
                    Map<BlockPos, IBlockState> toRemove = new HashMap<>();
                    for (int x = pos.getX() - rangeX / 2; x < pos.getX() + rangeX / 2; x++) {
                        for (int z = pos.getZ() - rangeZ / 2; z < pos.getZ() + rangeZ / 2; z++) {
                            BlockPos checkpos = new BlockPos(x, currentY, z);
                            IBlockState state = world.getBlockState(checkpos);
                            if (state.getBlock().getBlockHardness(state, world, checkpos) != -1.0F) {
                                if (state.getBlock().getHarvestTool(state) != null && state.getBlock().getHarvestTool(state).equalsIgnoreCase(getDigType())) {
                                    if (IsAcceptible(state, checkpos)) {
                                        toRemove.put(checkpos, state);
                                    }
                                }
                            }
                        }
                    }
                    for (Map.Entry<BlockPos, IBlockState> entry : toRemove.entrySet()) {
                        List<ItemStack> items = entry.getValue().getBlock().getDrops(world, entry.getKey(), entry.getValue(), 0);
                        Random rand = new Random();
                        for (ItemStack stack : items) {
                            if (!stack.isEmpty() && world.getGameRules().getBoolean("doTileDrops") && rand.nextDouble() < chance) {
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
                    currentY++;
                    if (currentY > (pos.getY() + rangeY / 2))
                        world.setBlockToAir(pos);
                }
            }
        }
    }

    protected abstract boolean IsAcceptible(IBlockState stack, BlockPos checkpos);

    public void boom() {
        if (booming == false) {
            booming = true;
            warm_up = 200;
            this.markDirty();
        }
    }


}
