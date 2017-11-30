package mcjty.restrictions.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class AttractorBlock extends GenericBlock {

    public AttractorBlock() {
        super("attractor");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new AttractorTileEntity();
    }
}
