package mcjty.restrictions.blocks;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PusherBlock extends GenericBlock {

    public PusherBlock() {
        super("pusher");
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PusherTileEntity();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add("This block pushes entities");
        tooltip.add("(items, mobs, players, ...)");
        tooltip.add("a distance equal to the redstone");
        tooltip.add("signal strength it receives");
    }

}
