package mcjty.restrictions.setup;


import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.AttractorTileEntity;
import mcjty.restrictions.blocks.OneWayBlock;
import mcjty.restrictions.blocks.OneWayWall;
import mcjty.restrictions.blocks.PusherTileEntity;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.world.level.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mcjty.restrictions.Restrictions.MODID;

public class Registration {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Block> PUSHER = BLOCKS.register("pusher", PusherTileEntity::createBlock);
    public static final RegistryObject<Item> PUSHER_ITEM = ITEMS.register("pusher", () -> new BlockItem(PUSHER.get(), createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_PUSHER = TILES.register("pusher", () -> TileEntityType.Builder.of(PusherTileEntity::new, PUSHER.get()).build(null));

    public static final RegistryObject<Block> ATTRACTOR = BLOCKS.register("attractor", AttractorTileEntity::createBlock);
    public static final RegistryObject<Item> ATTRACTOR_ITEM = ITEMS.register("attractor", () -> new BlockItem(ATTRACTOR.get(), createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_ATTRACTOR = TILES.register("attractor", () -> TileEntityType.Builder.of(AttractorTileEntity::new, ATTRACTOR.get()).build(null));

    public static final RegistryObject<Block> ONEWAY = BLOCKS.register("oneway", OneWayBlock::new);
    public static final RegistryObject<Item> ONEWAY_ITEM = ITEMS.register("oneway", () -> new BlockItem(ONEWAY.get(), createStandardProperties()));

    public static final RegistryObject<Block> ONEWAY_WALL = BLOCKS.register("oneway_wall", OneWayWall::new);
    public static final RegistryObject<Item> ONEWAY_WALL_ITEM = ITEMS.register("oneway_wall", () -> new BlockItem(ONEWAY_WALL.get(), createStandardProperties()));

    public static final RegistryObject<GlassBoots> GLASSBOOTS = ITEMS.register("glassboots", GlassBoots::new);

    public static Item.Properties createStandardProperties() {
        return new Item.Properties().tab(Restrictions.setup.getTab());
    }
}