package mcjty.restrictions.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

import javax.annotation.Nullable;

import mcjty.restrictions.items.ModItems;

public class GenericTileEntity extends TileEntity implements ITickable {

    protected AxisAlignedBB aabb = null;
    protected int power = 0;
    protected final double speed;

    public GenericTileEntity(double speed) {
        this.speed = speed;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power != this.power) {
            this.power = power;
            aabb = null;
            markDirtyClient();
        }
    }

    @Override
    public void onLoad() {
        setPower(world.isBlockIndirectlyGettingPowered(pos));
    }

    protected AxisAlignedBB getBox() {
        if (aabb == null) {
            EnumFacing direction = getWorld().getBlockState(getPos()).getValue(GenericBlock.FACING);
            aabb = new AxisAlignedBB(getPos().offset(direction));
            if (power > 1) {
                aabb = aabb.union(new AxisAlignedBB(getPos().offset(direction, power)));
            }

        }
        return aabb;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        int oldpower = power;
        this.readFromNBT(packet.getNbtCompound());
        if (oldpower != power) {
            aabb = null;
        }
    }

    @Override
    public void update() {
        EnumFacing direction = getWorld().getBlockState(getPos()).getValue(GenericBlock.FACING);
        if (!world.isRemote) {
            if (power > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, getBox());
                for (Entity entity : entities) {
                    entity.addVelocity(direction.getFrontOffsetX() * speed, direction.getFrontOffsetY() * speed, direction.getFrontOffsetZ() * speed);
                }
            }
        } else {
            if (power > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(EntityPlayer.class, getBox());
                for (Entity entity : entities) {
                    ItemStack boots = ((EntityPlayer) entity).getItemStackFromSlot(EntityEquipmentSlot.FEET);
                    if (boots.isEmpty() || boots.getItem() != ModItems.glassBoots) {
                        entity.addVelocity(direction.getFrontOffsetX() * speed, direction.getFrontOffsetY() * speed, direction.getFrontOffsetZ() * speed);
                    }
                }
            }
        }
    }

    public void markDirtyClient() {
        markDirty();
        if (getWorld() != null) {
            IBlockState state = getWorld().getBlockState(getPos());
            getWorld().notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    public void markDirtyQuick() {
        if (getWorld() != null) {
            getWorld().markChunkDirty(this.pos, this);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        power = compound.getInteger("power");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("power", power);
        return super.writeToNBT(compound);
    }
}
