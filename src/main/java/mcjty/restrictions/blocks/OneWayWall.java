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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static mcjty.lib.builder.TooltipBuilder.header;
import static mcjty.lib.builder.TooltipBuilder.key;

public class OneWayWall extends BaseBlock {

    public OneWayWall() {
        super(new BlockBuilder()
                .properties(Properties.of(Material.GLASS)
//                        .harvestTool(ToolType.PICKAXE)    // @todo 1.18 tags
//                        .harvestLevel(0)
                        .isSuffocating((state, reader, pos) -> false)
                        .isRedstoneConductor((state, reader, pos) -> false)
                        .strength(2.0f)
                        .noOcclusion()
                        .sound(SoundType.GLASS))
                .info(key("message.restrictions.shiftmessage"))
                .infoShift(header())
        );
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(BlockStateProperties.FACING);
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity != null) {
                if (entity instanceof Player player) {
                    ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);
                    if (!boots.isEmpty() && boots.getItem() instanceof GlassBoots) {
                        return Shapes.empty();
                    }
                }
                if (direction.getStepX() == 1) {
                    if (entity.getDeltaMovement().x() > 0) {
                        return Shapes.block();
                    }
                } else if (direction.getStepX() == -1) {
                    if (entity.getDeltaMovement().x() < 0) {
                        return Shapes.block();
                    }
                }
                if (direction.getStepY() == 1) {
                    if (entity.getDeltaMovement().y() > 0) {
                        return Shapes.block();
                    }
                } else if (direction.getStepY() == -1) {
                    if (entity.getDeltaMovement().y() < 0) {
                        return Shapes.block();
                    }
                }
                if (direction.getStepZ() == 1) {
                    if (entity.getDeltaMovement().z() > 0) {
                        return Shapes.block();
                    }
                } else if (direction.getStepZ() == -1) {
                    if (entity.getDeltaMovement().z() < 0) {
                        return Shapes.block();
                    }
                }
            }
        }
        return Shapes.empty();
    }

//    @SuppressWarnings("deprecation")
//    @Override
//    public boolean isNormalCube(BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos) {
//        return false;
//    }


    @Override
    public int getLightBlock(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return 15;   // Block light
    }
}
