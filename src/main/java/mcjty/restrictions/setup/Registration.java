package mcjty.restrictions.setup;


import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.*;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Restrictions.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registration {

    public static void init() {}

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new PusherBlock());
        event.getRegistry().register(new AttractorBlock());
        event.getRegistry().register(new OneWayBlock());
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new GlassBoots());
        Item.Properties properties = new Item.Properties().group(Restrictions.setup.getTab());
        event.getRegistry().register(new BlockItem(ModBlocks.pusherBlock, properties).setRegistryName(ModBlocks.pusherBlock.getRegistryName()));
        event.getRegistry().register(new BlockItem(ModBlocks.attractorBlock, properties).setRegistryName(ModBlocks.attractorBlock.getRegistryName()));
        event.getRegistry().register(new BlockItem(ModBlocks.oneWayBlock, properties).setRegistryName(ModBlocks.oneWayBlock.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerTiles(final RegistryEvent.Register<TileEntityType<?>> registry) {
        registry.getRegistry().register(ModBlocks.TYPE_ATTRACTOR = TileEntityType.Builder.create(AttractorTileEntity::new).build(null).setRegistryName(new ResourceLocation(Restrictions.MODID, "attractor")));
        registry.getRegistry().register(ModBlocks.TYPE_PUSHER = TileEntityType.Builder.create(PusherTileEntity::new).build(null).setRegistryName(new ResourceLocation(Restrictions.MODID, "pusher")));
    }

}
