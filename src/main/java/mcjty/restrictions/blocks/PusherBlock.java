package mcjty.restrictions.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;
import java.util.List;

public class PusherBlock extends GenericBlock {

    public PusherBlock() {
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PusherTileEntity();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, world, tooltip, advanced);
        tooltip.add(new StringTextComponent("This block pushes entities"));
        tooltip.add(new StringTextComponent("(items, mobs, players, ...)"));
        tooltip.add(new StringTextComponent("a distance equal to the redstone"));
        tooltip.add(new StringTextComponent("signal strength it receives"));
    }

}
