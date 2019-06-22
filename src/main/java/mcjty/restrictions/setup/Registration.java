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
        event.getRegistry().register(new BlockItem(ModBlocks.PUSHER_BLOCK, properties).setRegistryName(PusherBlock.REGNAME));
        event.getRegistry().register(new BlockItem(ModBlocks.ATTRACTOR_BLOCK, properties).setRegistryName(AttractorBlock.REGNAME));
        event.getRegistry().register(new BlockItem(ModBlocks.ONEWAY_BLOCK, properties).setRegistryName(OneWayBlock.REGNAME));
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerTiles(final RegistryEvent.Register<TileEntityType<?>> registry) {
        registry.getRegistry().register(TileEntityType.Builder.create(AttractorTileEntity::new, ModBlocks.ATTRACTOR_BLOCK).build(null).setRegistryName(new ResourceLocation(Restrictions.MODID, "attractor")));
        registry.getRegistry().register(TileEntityType.Builder.create(PusherTileEntity::new, ModBlocks.PUSHER_BLOCK).build(null).setRegistryName(new ResourceLocation(Restrictions.MODID, "pusher")));
    }

}
