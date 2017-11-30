package mcjty.restrictions.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

import java.util.List;

public class PusherTileEntity extends GenericTileEntity {

    public static final double SPEED = .1;

    @Override
    public void update() {
        EnumFacing direction = getWorld().getBlockState(getPos()).getValue(GenericBlock.FACING);
        if (!world.isRemote) {
            if (power > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, getBox());
                for (Entity entity : entities) {
                    entity.addVelocity(direction.getFrontOffsetX() * SPEED, direction.getFrontOffsetY() * SPEED, direction.getFrontOffsetZ() * SPEED);
                }
            }
        } else {
            if (power > 0) {
                List<Entity> entities = world.getEntitiesWithinAABB(EntityPlayer.class, getBox());
                for (Entity entity : entities) {
                    entity.addVelocity(direction.getFrontOffsetX() * SPEED, direction.getFrontOffsetY() * SPEED, direction.getFrontOffsetZ() * SPEED);
                }
            }
        }
    }

}
