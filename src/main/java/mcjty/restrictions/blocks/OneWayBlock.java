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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;

import static mcjty.lib.builder.TooltipBuilder.header;
import static mcjty.lib.builder.TooltipBuilder.key;

import net.minecraft.block.AbstractBlock.Properties;

public class OneWayBlock extends BaseBlock {

    public OneWayBlock() {
        super(new BlockBuilder()
                .properties(Properties.of(Material.GLASS)
                        .harvestTool(ToolType.PICKAXE)
                        .harvestLevel(0)
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

    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        Direction direction = world.getBlockState(pos).getValue(BlockStateProperties.FACING);
        if (!world.isClientSide) {
            entity.push(direction.getStepX() * SPEED, direction.getStepY() * SPEED, direction.getStepZ() * SPEED);
            if (direction == Direction.UP && entity.getDeltaMovement().y > -0.5D) {
                entity.fallDistance = 1.0F;
            }
        } else if (entity instanceof PlayerEntity) {
            ItemStack boots = ((PlayerEntity) entity).getItemBySlot(EquipmentSlotType.FEET);
            if (boots.isEmpty() || !(boots.getItem() instanceof GlassBoots)) {
                entity.push(direction.getStepX() * SPEED, direction.getStepY() * SPEED, direction.getStepZ() * SPEED);
                if (direction == Direction.UP && entity.getDeltaMovement().y > -0.5D) {
                    entity.fallDistance = 1.0F;
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isPathfindable(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos, PathType type) {
        return true;
    }



    @SuppressWarnings("deprecation")
    @Override
    public int getLightBlock(BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos) {
        return 0;   // Let light pass through
    }

    // @todo 1.15
//    @Override
//    public boolean doesSideBlockRendering(BlockState state, IEnviromentBlockReader world, BlockPos pos, Direction face) {
//        return false;
//    }
}
