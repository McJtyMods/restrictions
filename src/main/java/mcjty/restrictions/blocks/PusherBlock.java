package mcjty.restrictions.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PusherBlock extends GenericBlock {

    public PusherBlock() {
        super("pusher");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PusherTileEntity();
    }
}
