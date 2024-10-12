package mcjty.restrictions.blocks;

import mcjty.lib.tileentity.TickingTileEntity;
import mcjty.restrictions.items.GlassBoots;
import mcjty.restrictions.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
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
        if (this.powerLevel != powered) {
            this.powerLevel = (byte) powered;
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
            if (this.powerLevel > 1) {
                aabb = aabb.minmax(new AABB(getBlockPos().relative(direction, this.powerLevel)));
            }

        }
        return aabb;
    }

    @Override
    public void loadClientDataFromNBT(CompoundTag tag, HolderLookup.Provider provider) {
        powerLevel = tag.getByte("powered");
    }

    @Override
    public void saveClientDataToNBT(CompoundTag tag, HolderLookup.Provider provider) {
        tag.putByte("powered", powerLevel);
    }

    @Override
    protected void tickServer() {
        assert level != null;
        Direction direction = level.getBlockState(getBlockPos()).getValue(BlockStateProperties.FACING);
        if (this.powerLevel > 0) {
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
        if (this.powerLevel > 0) {
            BlockState state = level.getBlockState(getBlockPos());
            Direction direction = state.getValue(BlockStateProperties.FACING);
            if (state.getBlock() == Registration.ATTRACTOR.get() || state.getBlock() == Registration.PUSHER.get()) {
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
