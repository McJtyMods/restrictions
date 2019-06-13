package mcjty.restrictions.blocks;

import mcjty.restrictions.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;
import java.util.List;

public class GenericTileEntity extends TileEntity implements ITickable {

    protected AxisAlignedBB aabb = null;
    protected int power = 0;
    protected final double speed;

    public GenericTileEntity(TileEntityType<?> type, double speed) {
        super(type);
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
        setPower(world.getRedstonePowerFromNeighbors(pos));
    }

    protected AxisAlignedBB getBox() {
        if (aabb == null) {
            Direction direction = getWorld().getBlockState(getPos()).get(GenericBlock.FACING);
            aabb = new AxisAlignedBB(getPos().offset(direction));
            if (power > 1) {
                aabb = aabb.union(new AxisAlignedBB(getPos().offset(direction, power)));
            }

        }
        return aabb;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        this.write(nbtTag);
        return new SUpdateTileEntityPacket(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        int oldpower = power;
        this.read(packet.getNbtCompound());
        if (oldpower != power) {
            aabb = null;
        }
    }


    @Override
    public void tick() {
        Direction direction = getWorld().getBlockState(getPos()).get(GenericBlock.FACING);
        if (!world.isRemote) {
            if (power > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, getBox());
                for (Entity entity : entities) {
                    entity.addVelocity(direction.getXOffset() * speed, direction.getYOffset() * speed, direction.getZOffset() * speed);
                    if (direction == Direction.UP && entity.getMotion().y > -0.5D) {
                        entity.fallDistance = 1.0F;
                    }
                }
            }
        } else {
            if (power > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, getBox());
                for (Entity entity : entities) {
                    ItemStack boots = ((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET);
                    if (boots.isEmpty() || boots.getItem() != ModItems.glassBoots) {
                        entity.addVelocity(direction.getXOffset() * speed, direction.getYOffset() * speed, direction.getZOffset() * speed);
                        if (direction == Direction.UP && entity.getMotion().y > -0.5D) {
                            entity.fallDistance = 1.0F;
                        }
                    }
                }
            }
        }
    }

    public void markDirtyClient() {
        markDirty();
        if (getWorld() != null) {
            BlockState state = getWorld().getBlockState(getPos());
            getWorld().notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    public void markDirtyQuick() {
        if (getWorld() != null) {
            getWorld().markChunkDirty(this.pos, this);
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        power = compound.getInt("power");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("power", power);
        return super.write(compound);
    }
}
