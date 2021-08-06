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
            setChanged();
            BlockState state = level.getBlockState(getBlockPos());
            level.sendBlockUpdated(getBlockPos(), state, state, Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    protected AxisAlignedBB getBox() {
        if (aabb == null) {
            assert level != null;
            Direction direction = level.getBlockState(getBlockPos()).getValue(BlockStateProperties.FACING);
            aabb = new AxisAlignedBB(getBlockPos().relative(direction));
            if (powerLevel > 1) {
                aabb = aabb.minmax(new AxisAlignedBB(getBlockPos().relative(direction, powerLevel)));
            }

        }
        return aabb;
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
        int oldpower = powerLevel;
        this.read(packet.getTag());
        if (oldpower != powerLevel) {
            aabb = null;
        }
    }


    @Override
    public void tick() {
        assert level != null;
        Direction direction = level.getBlockState(getBlockPos()).getValue(BlockStateProperties.FACING);
        if (!level.isClientSide) {
            if (powerLevel > 0) {
                List<Entity> entities = level.getEntitiesOfClass(Entity.class, getBox());
                for (Entity entity : entities) {
                    entity.push(direction.getStepX() * speed, direction.getStepY() * speed, direction.getStepZ() * speed);
                    if (direction == Direction.UP && entity.getDeltaMovement().y > -0.5D) {
                        entity.fallDistance = 1.0F;
                    }
                }
            }
        } else {
            if (powerLevel > 0) {
                List<Entity> entities = level.getEntitiesOfClass(PlayerEntity.class, getBox());
                for (Entity entity : entities) {
                    ItemStack boots = ((PlayerEntity) entity).getItemBySlot(EquipmentSlotType.FEET);
                    if (boots.isEmpty() || !(boots.getItem() instanceof GlassBoots)) {
                        entity.push(direction.getStepX() * speed, direction.getStepY() * speed, direction.getStepZ() * speed);
                        if (direction == Direction.UP && entity.getDeltaMovement().y > -0.5D) {
                            entity.fallDistance = 1.0F;
                        }
                    }
                }
            }
        }
    }
}
