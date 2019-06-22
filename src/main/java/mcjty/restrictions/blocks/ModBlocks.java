package mcjty.restrictions.blocks;


import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@SuppressWarnings("PublicField")
public class ModBlocks {

    @ObjectHolder("restrictions:pusher")
    public static PusherBlock PUSHER_BLOCK;

    @ObjectHolder("restrictions:attractor")
    public static AttractorBlock ATTRACTOR_BLOCK;

    @ObjectHolder("restrictions:oneway")
    public static OneWayBlock ONEWAY_BLOCK;

    @ObjectHolder("restrictions:attractor")
    public static TileEntityType<?> TYPE_ATTRACTOR;

    @ObjectHolder("restrictions:pusher")
    public static TileEntityType<?> TYPE_PUSHER;
}
