package mcjty.restrictions.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

    @GameRegistry.ObjectHolder("restrictions:pusher")
    public static PusherBlock pusherBlock;

    @GameRegistry.ObjectHolder("restrictions:attractor")
    public static AttractorBlock attractorBlock;

    @GameRegistry.ObjectHolder("restrictions:oneway")
    public static OneWayBlock oneWayBlock;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        pusherBlock.initModel();
        attractorBlock.initModel();
        oneWayBlock.initModel();
    }
}
