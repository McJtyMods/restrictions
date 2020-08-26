package mcjty.restrictions.blocks;

import mcjty.lib.tileentity.GenericTileEntity;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.util.Constants;

import java.util.List;

public class BaseTileEntity extends GenericTileEntity implements ITickableTileEntity {

    private AxisAlignedBB aabb = null;
    private final double speed;

    public BaseTileEntity(TileEntityType<?> type, double speed) {
        super(type);
        this.speed = speed;
    }

    @Override
    public void setPowerInput(int powered) {
        if (powerLevel != powered) {
            powerLevel = powered;
            markDirty();
            BlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    protected AxisAlignedBB getBox() {
        if (aabb == null) {
            assert world != null;
            Direction direction = world.getBlockState(getPos()).get(BlockStateProperties.FACING);
            aabb = new AxisAlignedBB(getPos().offset(direction));
            if (powerLevel > 1) {
                aabb = aabb.union(new AxisAlignedBB(getPos().offset(direction, powerLevel)));
            }

        }
        return aabb;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        int oldpower = powerLevel;
        this.read(packet.getNbtCompound());
        if (oldpower != powerLevel) {
            aabb = null;
        }
    }


    @Override
    public void tick() {
        assert world != null;
        Direction direction = world.getBlockState(getPos()).get(BlockStateProperties.FACING);
        if (!world.isRemote) {
            if (powerLevel > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, getBox());
                for (Entity entity : entities) {
                    entity.addVelocity(direction.getXOffset() * speed, direction.getYOffset() * speed, direction.getZOffset() * speed);
                    if (direction == Direction.UP && entity.getMotion().y > -0.5D) {
                        entity.fallDistance = 1.0F;
                    }
                }
            }
        } else {
            if (powerLevel > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(PlayerEntity.class, getBox());
                for (Entity entity : entities) {
                    ItemStack boots = ((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET);
                    if (boots.isEmpty() || !(boots.getItem() instanceof GlassBoots)) {
                        entity.addVelocity(direction.getXOffset() * speed, direction.getYOffset() * speed, direction.getZOffset() * speed);
                        if (direction == Direction.UP && entity.getMotion().y > -0.5D) {
                            entity.fallDistance = 1.0F;
                        }
                    }
                }
            }
        }
    }
}
