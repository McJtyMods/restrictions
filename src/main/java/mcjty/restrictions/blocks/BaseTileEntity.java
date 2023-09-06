package mcjty.restrictions.blocks;

import mcjty.lib.tileentity.GenericTileEntity;
import mcjty.lib.tileentity.TickingTileEntity;
import mcjty.restrictions.items.GlassBoots;
import mcjty.restrictions.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class BaseTileEntity extends TickingTileEntity {

    private AABB aabb = null;
    private final double speed;

    public BaseTileEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, double speed) {
        super(type, pos, state);
        this.speed = speed;
    }

    @Override
    public void setPowerInput(int powered) {
        if (powerLevel != powered) {
            powerLevel = powered;
            setChanged();
            BlockState state = level.getBlockState(getBlockPos());
            level.sendBlockUpdated(getBlockPos(), state, state, Block.UPDATE_ALL);
        }
    }

    protected AABB getBox() {
        if (aabb == null) {
            assert level != null;
            Direction direction = level.getBlockState(getBlockPos()).getValue(BlockStateProperties.FACING);
            aabb = new AABB(getBlockPos().relative(direction));
            if (powerLevel > 1) {
                aabb = aabb.minmax(new AABB(getBlockPos().relative(direction, powerLevel)));
            }

        }
        return aabb;
    }

    @Override
    public void loadClientDataFromNBT(CompoundTag tagCompound) {
        if (tagCompound.contains("Info")) {
            CompoundTag infoTag = tagCompound.getCompound("Info");
            if (infoTag.contains("powered")) {
                this.powerLevel = infoTag.getByte("powered");
            }
        }
    }

    @Override
    public void saveClientDataToNBT(CompoundTag tagCompound) {
        CompoundTag infoTag = this.getOrCreateInfo(tagCompound);
        infoTag.putByte("powered", (byte)this.powerLevel);
    }

    @Override
    protected void tickServer() {
        assert level != null;
        Direction direction = level.getBlockState(getBlockPos()).getValue(BlockStateProperties.FACING);
        if (powerLevel > 0) {
            List<Entity> entities = level.getEntitiesOfClass(Entity.class, getBox());
            for (Entity entity : entities) {
                entity.push(direction.getStepX() * speed, direction.getStepY() * speed, direction.getStepZ() * speed);
                if (direction == Direction.UP && entity.getDeltaMovement().y > -0.5D) {
                    entity.fallDistance = 1.0F;
                }
            }
        }
    }

    @Override
    protected void tickClient() {
        if (powerLevel > 0) {
            BlockState state = level.getBlockState(getBlockPos());
            if (state.getBlock() == Registration.ATTRACTOR.get() || state.getBlock() == Registration.PUSHER.get()) {
                Direction direction = state.getValue(BlockStateProperties.FACING);
                List<Player> entities = level.getEntitiesOfClass(Player.class, getBox());
                for (Player entity : entities) {
                    ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);
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
