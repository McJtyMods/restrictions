package mcjty.restrictions.blocks;

import mcjty.lib.blocks.BaseBlock;
import mcjty.lib.builder.BlockBuilder;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;

import static mcjty.lib.builder.TooltipBuilder.header;
import static mcjty.lib.builder.TooltipBuilder.key;

import net.minecraft.block.AbstractBlock.Properties;

public class OneWayWall extends BaseBlock {

    public OneWayWall() {
        super(new BlockBuilder()
                .properties(Properties.of(Material.GLASS)
                        .harvestTool(ToolType.PICKAXE)
                        .harvestLevel(0)
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
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        Direction direction = state.getValue(BlockStateProperties.FACING);
        Entity entity = context.getEntity();
        if (entity != null) {
            if (entity instanceof PlayerEntity) {
                ItemStack boots = ((PlayerEntity) entity).getItemBySlot(EquipmentSlotType.FEET);
                if (!boots.isEmpty() && boots.getItem() instanceof GlassBoots) {
                    return VoxelShapes.empty();
                }
            }
            if (direction.getStepX() == 1) {
                if (entity.getDeltaMovement().x() > 0) {
                    return VoxelShapes.block();
                }
            } else if (direction.getStepX() == -1) {
                if (entity.getDeltaMovement().x() < 0) {
                    return VoxelShapes.block();
                }
            }
            if (direction.getStepY() == 1) {
                if (entity.getDeltaMovement().y() > 0) {
                    return VoxelShapes.block();
                }
            } else if (direction.getStepY() == -1) {
                if (entity.getDeltaMovement().y() < 0) {
                    return VoxelShapes.block();
                }
            }
            if (direction.getStepZ() == 1) {
                if (entity.getDeltaMovement().z() > 0) {
                    return VoxelShapes.block();
                }
            } else if (direction.getStepZ() == -1) {
                if (entity.getDeltaMovement().z() < 0) {
                    return VoxelShapes.block();
                }
            }
        }
        return VoxelShapes.empty();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos, PathType type) {
//        return true;
        return super.isPathfindable(state, reader, pos, type);
    }


//    @SuppressWarnings("deprecation")
//    @Override
//    public boolean isNormalCube(BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos) {
//        return false;
//    }


    @SuppressWarnings("deprecation")
    @Override
    public int getLightBlock(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos) {
        return 15;   // Block light
    }
}
