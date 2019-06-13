package mcjty.restrictions.blocks;


import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("restrictions:pusher")
    public static PusherBlock pusherBlock;

    @ObjectHolder("restrictions:attractor")
    public static AttractorBlock attractorBlock;

    @ObjectHolder("restrictions:oneway")
    public static OneWayBlock oneWayBlock;

    public static TileEntityType<?> TYPE_ATTRACTOR;
    public static TileEntityType<?> TYPE_PUSHER;
}
