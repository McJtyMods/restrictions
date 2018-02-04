package mcjty.restrictions.blocks;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class AttractorBlock extends GenericBlock {

    public AttractorBlock() {
        super("attractor");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new AttractorTileEntity();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("This block attracts entities");
        tooltip.add("(items, mobs, players, ...)");
        tooltip.add("from a distance equal to the");
        tooltip.add("redstone signal strength it receives");
    }
}
