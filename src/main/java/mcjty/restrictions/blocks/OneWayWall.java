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

public class OneWayWall extends BaseBlock {

    public OneWayWall() {
        super(new BlockBuilder()
                .properties(Properties.create(Material.GLASS)
                        .harvestTool(ToolType.PICKAXE)
                        .harvestLevel(0)
                        .hardnessAndResistance(2.0f)
                        .notSolid()
                        .sound(SoundType.GLASS))
                .info(key("message.restrictions.shiftmessage"))
                .infoShift(header())
        );
    }

    private static final double SPEED = .2;

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        Direction direction = state.get(BlockStateProperties.FACING);
        Entity entity = context.getEntity();
        if (entity != null) {
            if (entity instanceof PlayerEntity) {
                ItemStack boots = ((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET);
                if (!boots.isEmpty() && boots.getItem() instanceof GlassBoots) {
                    return VoxelShapes.empty();
                }
            }
            if (direction.getXOffset() == 1) {
                if (entity.getMotion().getX() > 0) {
                    return VoxelShapes.fullCube();
                }
            } else if (direction.getXOffset() == -1) {
                if (entity.getMotion().getX() < 0) {
                    return VoxelShapes.fullCube();
                }
            }
            if (direction.getYOffset() == 1) {
                if (entity.getMotion().getY() > 0) {
                    return VoxelShapes.fullCube();
                }
            } else if (direction.getYOffset() == -1) {
                if (entity.getMotion().getY() < 0) {
                    return VoxelShapes.fullCube();
                }
            }
            if (direction.getZOffset() == 1) {
                if (entity.getMotion().getZ() > 0) {
                    return VoxelShapes.fullCube();
                }
            } else if (direction.getZOffset() == -1) {
                if (entity.getMotion().getZ() < 0) {
                    return VoxelShapes.fullCube();
                }
            }
        }
        return VoxelShapes.empty();
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean causesSuffocation(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean allowsMovement(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos, PathType type) {
//        return true;
        return super.allowsMovement(state, reader, pos, type);
    }


    @SuppressWarnings("deprecation")
    @Override
    public boolean isNormalCube(BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos) {
        return false;
    }


    @SuppressWarnings("deprecation")
    @Override
    public int getOpacity(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos) {
        return 0;   // Let light pass through
    }

    // @todo 1.15
//    @Override
//    public boolean doesSideBlockRendering(BlockState state, IEnviromentBlockReader world, BlockPos pos, Direction face) {
//        return false;
//    }
}
