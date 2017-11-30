package mcjty.restrictions.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public class OneWayTileEntity extends GenericTileEntity {

    public static final double SPEED = .1;

    @Override
    public void update() {
        EnumFacing direction = getWorld().getBlockState(getPos()).getValue(GenericBlock.FACING);
        if (!world.isRemote) {
            List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, getBox());
            for (Entity entity : entities) {
                entity.addVelocity(direction.getFrontOffsetX() * SPEED, direction.getFrontOffsetY() * SPEED, direction.getFrontOffsetZ() * SPEED);
            }
        } else {
            List<Entity> entities = world.getEntitiesWithinAABB(EntityPlayer.class, getBox());
            for (Entity entity : entities) {
                entity.addVelocity(direction.getFrontOffsetX() * SPEED, direction.getFrontOffsetY() * SPEED, direction.getFrontOffsetZ() * SPEED);
            }
        }
    }

    @Override
    protected AxisAlignedBB getBox() {
        if (aabb == null) {
            aabb = new AxisAlignedBB(getPos());
        }
        return aabb;
    }


}
