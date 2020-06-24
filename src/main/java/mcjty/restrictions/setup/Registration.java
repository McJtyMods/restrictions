package mcjty.restrictions.setup;


import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.AttractorTileEntity;
import mcjty.restrictions.blocks.PusherBlock;
import mcjty.restrictions.blocks.PusherTileEntity;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static mcjty.restrictions.Restrictions.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registration {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Block> PUSHER = BLOCKS.register("pusher", PusherBlock::new);
    public static final RegistryObject<Item> PUSHER_ITEM = ITEMS.register("pusher", () -> new BlockItem(PUSHER.get(), Restrictions.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_PUSHER = TILES.register("pusher", () -> TileEntityType.Builder.create(PusherTileEntity::new, PUSHER.get()).build(null));

    public static final RegistryObject<Block> ATTRACTOR = BLOCKS.register("attractor", PusherBlock::new);
    public static final RegistryObject<Item> ATTRACTOR_ITEM = ITEMS.register("attractor", () -> new BlockItem(ATTRACTOR.get(), Restrictions.createStandardProperties()));
    public static final RegistryObject<TileEntityType<?>> TYPE_ATTRACTOR = TILES.register("attractor", () -> TileEntityType.Builder.create(AttractorTileEntity::new, ATTRACTOR.get()).build(null));

    public static final RegistryObject<Block> ONEWAY = BLOCKS.register("oneway", PusherBlock::new);
    public static final RegistryObject<Item> ONEWAY_ITEM = ITEMS.register("oneway", () -> new BlockItem(ONEWAY.get(), Restrictions.createStandardProperties()));

    public static final RegistryObject<GlassBoots> GLASSBOOTS = ITEMS.register("glassboots", GlassBoots::new);
}