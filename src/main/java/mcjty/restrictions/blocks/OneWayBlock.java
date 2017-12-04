package mcjty.restrictions.blocks;

import mcjty.restrictions.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class OneWayBlock extends GenericBlockNoTE {

    public OneWayBlock() {
        super("oneway");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("This block only allows entities");
        tooltip.add("(items, mobs, players, ...)");
        tooltip.add("to move in a certain direction");
    }


    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    public static final double SPEED = .2;

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        EnumFacing direction = world.getBlockState(pos).getValue(GenericBlock.FACING);
        if (!world.isRemote) {
            entity.addVelocity(direction.getFrontOffsetX() * SPEED, direction.getFrontOffsetY() * SPEED, direction.getFrontOffsetZ() * SPEED);
        } else if (entity instanceof EntityPlayer) {
            ItemStack boots = ((EntityPlayer) entity).getItemStackFromSlot(EntityEquipmentSlot.FEET);
            if (boots.isEmpty() || boots.getItem() != ModItems.glassBoots) {
                entity.addVelocity(direction.getFrontOffsetX() * SPEED, direction.getFrontOffsetY() * SPEED, direction.getFrontOffsetZ() * SPEED);
            }
        }
    }

    @Override
    public boolean causesSuffocation(IBlockState state) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        if (blockState != iblockstate) {
            return true;
        }
        Block block = iblockstate.getBlock();
        if (block == this) {
            return false;
        }

        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}
