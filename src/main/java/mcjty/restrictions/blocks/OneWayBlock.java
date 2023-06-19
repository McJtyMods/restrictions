package mcjty.restrictions.blocks;

import mcjty.lib.blocks.BaseBlock;
import mcjty.lib.builder.BlockBuilder;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.pathfinder.PathComputationType;

import static mcjty.lib.builder.TooltipBuilder.header;
import static mcjty.lib.builder.TooltipBuilder.key;

public class OneWayBlock extends BaseBlock {

    public OneWayBlock() {
        super(new BlockBuilder()
                .properties(Properties.of()
//                        .harvestTool(ToolType.PICKAXE)    // @todo 1.18 tags
//                        .harvestLevel(0)
                        .isRedstoneConductor((state, reader, pos) -> false)
                        .isSuffocating((state, reader, pos) -> false)
                        .strength(2.0f)
                        .noCollission()
                        .sound(SoundType.GLASS))
                .info(key("message.restrictions.shiftmessage"))
                .infoShift(header())
        );
    }

    private static final double SPEED = .2;

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        Direction direction = world.getBlockState(pos).getValue(BlockStateProperties.FACING);
        if (!world.isClientSide) {
            entity.push(direction.getStepX() * SPEED, direction.getStepY() * SPEED, direction.getStepZ() * SPEED);
            if (direction == Direction.UP && entity.getDeltaMovement().y > -0.5D) {
                entity.fallDistance = 1.0F;
            }
        } else if (entity instanceof Player) {
            ItemStack boots = ((Player) entity).getItemBySlot(EquipmentSlot.FEET);
            if (boots.isEmpty() || !(boots.getItem() instanceof GlassBoots)) {
                entity.push(direction.getStepX() * SPEED, direction.getStepY() * SPEED, direction.getStepZ() * SPEED);
                if (direction == Direction.UP && entity.getDeltaMovement().y > -0.5D) {
                    entity.fallDistance = 1.0F;
                }
            }
        }
    }

    @Override
    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 0;   // Let light pass through
    }

    // @todo 1.15
//    @Override
//    public boolean doesSideBlockRendering(BlockState state, IEnviromentBlockReader world, BlockPos pos, Direction face) {
//        return false;
//    }
}
