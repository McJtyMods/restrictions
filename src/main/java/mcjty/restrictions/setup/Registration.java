package mcjty.restrictions.setup;


import mcjty.lib.setup.DeferredBlocks;
import mcjty.lib.setup.DeferredItems;
import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.AttractorTileEntity;
import mcjty.restrictions.blocks.OneWayBlock;
import mcjty.restrictions.blocks.OneWayWall;
import mcjty.restrictions.blocks.PusherTileEntity;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

import java.util.function.Supplier;

import static mcjty.restrictions.Restrictions.MODID;
import static mcjty.restrictions.Restrictions.tab;

public class Registration {

    public static final DeferredItems ITEMS = DeferredItems.create(MODID);
    public static final DeferredBlocks BLOCKS = DeferredBlocks.create(MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
        TILES.register(bus);
        TABS.register(bus);
    }

    public static final DeferredBlock<Block> PUSHER = BLOCKS.register("pusher", PusherTileEntity::createBlock);
    public static final DeferredItem<Item> PUSHER_ITEM = ITEMS.register("pusher", tab(() -> new BlockItem(PUSHER.get(), createStandardProperties())));
    public static final Supplier<BlockEntityType<?>> TYPE_PUSHER = TILES.register("pusher", () -> BlockEntityType.Builder.of(PusherTileEntity::new, PUSHER.get()).build(null));

    public static final DeferredBlock<Block> ATTRACTOR = BLOCKS.register("attractor", AttractorTileEntity::createBlock);
    public static final DeferredItem<Item> ATTRACTOR_ITEM = ITEMS.register("attractor", tab(() -> new BlockItem(ATTRACTOR.get(), createStandardProperties())));
    public static final Supplier<BlockEntityType<?>> TYPE_ATTRACTOR = TILES.register("attractor", () -> BlockEntityType.Builder.of(AttractorTileEntity::new, ATTRACTOR.get()).build(null));

    public static final DeferredBlock<Block> ONEWAY = BLOCKS.register("oneway", OneWayBlock::new);
    public static final DeferredItem<Item> ONEWAY_ITEM = ITEMS.register("oneway", tab(() -> new BlockItem(ONEWAY.get(), createStandardProperties())));

    public static final DeferredBlock<Block> ONEWAY_WALL = BLOCKS.register("oneway_wall", OneWayWall::new);
    public static final DeferredItem<Item> ONEWAY_WALL_ITEM = ITEMS.register("oneway_wall", tab(() -> new BlockItem(ONEWAY_WALL.get(), createStandardProperties())));

    public static final DeferredItem<GlassBoots> GLASSBOOTS = ITEMS.register("glassboots", tab(GlassBoots::new));

    public static Item.Properties createStandardProperties() {
        return Restrictions.setup.defaultProperties();
    }

    public static RegistryObject<CreativeModeTab> TAB = TABS.register("restrictions", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MODID))
            .icon(() -> new ItemStack(ATTRACTOR.get()))
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .displayItems((featureFlags, output) -> {
                Restrictions.setup.populateTab(output);
            })
            .build());
}