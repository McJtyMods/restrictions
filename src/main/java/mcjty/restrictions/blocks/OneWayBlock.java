package mcjty.restrictions.blocks;

import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class OneWayBlock extends GenericBlockNoTE {

    public static final String REGNAME = "oneway";

    public OneWayBlock() {
        super(REGNAME);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(new StringTextComponent("This block only allows entities"));
        tooltip.add(new StringTextComponent("(items, mobs, players, ...)"));
        tooltip.add(new StringTextComponent("to move in a certain direction"));
    }

//
//    @Nullable
//    @Override
//    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
//        return NULL_AABB;
//    }

    public static final double SPEED = .2;

    @SuppressWarnings("deprecation")
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        Direction direction = world.getBlockState(pos).get(BlockStateProperties.FACING);
        if (!world.isRemote) {
            entity.addVelocity(direction.getXOffset() * SPEED, direction.getYOffset() * SPEED, direction.getZOffset() * SPEED);
            if (direction == Direction.UP && entity.getMotion().y > -0.5D) {
                entity.fallDistance = 1.0F;
            }
        } else if (entity instanceof PlayerEntity) {
            ItemStack boots = ((PlayerEntity) entity).getItemStackFromSlot(EquipmentSlotType.FEET);
            if (boots.isEmpty() || !(boots.getItem() instanceof GlassBoots)) {
                entity.addVelocity(direction.getXOffset() * SPEED, direction.getYOffset() * SPEED, direction.getZOffset() * SPEED);
                if (direction == Direction.UP && entity.getMotion().y > -0.5D) {
                    entity.fallDistance = 1.0F;
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean causesSuffocation(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos) {
        return false;
    }

    // @todo 1.14
//    @Override
//    public boolean isBlockNormalCube(IBlockState state) {
//        return false;
//    }
//
//    @Override
//    public boolean isNormalCube(IBlockState state) {
//        return false;
//    }


    @SuppressWarnings("deprecation")
    @Override
    public boolean allowsMovement(@Nonnull BlockState state, @Nonnull IBlockReader reader, @Nonnull BlockPos pos, PathType type) {
        return true;
    }

//    @Override
//    public boolean isOpaqueCube(IBlockState state) {
//        return false;
//    }
//
//    @Override
//    public boolean isFullCube(IBlockState state) {
//        return false;
//    }


    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }


//    @Override
//    @SideOnly(Side.CLIENT)
//    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, Direction side) {
//        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
//        if (state != iblockstate) {
//            return true;
//        }
//        Block block = iblockstate.getBlock();
//        if (block == this) {
//            return false;
//        }
//
//        return super.shouldSideBeRendered(state, blockAccess, pos, side);
//    }
}
