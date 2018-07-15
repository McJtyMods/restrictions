package mcjty.restrictions.proxy;

import java.util.HashMap;
import java.util.Map;

import mcjty.lib.datafix.fixes.TileEntityNamespace;
import mcjty.lib.proxy.AbstractCommonProxy;
import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.*;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy extends AbstractCommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
//        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());

//        File directory = e.getModConfigurationDirectory();
//        config = new Configuration(new File(directory.getPath(), "meecreeps.cfg"));
//        Config.readConfig();

//        PacketHandler.registerMessages("meecreeps");

        // Initialization of blocks and items typically goes here:
//        ModEntities.init();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
//        if (config.hasChanged()) {
//            config.save();
//        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModFixs modFixs = FMLCommonHandler.instance().getDataFixer().init(Restrictions.MODID, 1);

        event.getRegistry().register(new PusherBlock());
        GameRegistry.registerTileEntity(PusherTileEntity.class, Restrictions.MODID + ":pusher");
        event.getRegistry().register(new AttractorBlock());
        GameRegistry.registerTileEntity(AttractorTileEntity.class, Restrictions.MODID + ":attractor");
        event.getRegistry().register(new OneWayBlock());

        // We used to accidentally register TEs with names like "minecraft:restrictions_pusher" instead of "restrictions:pusher".
        // Set up a DataFixer to map these incorrect names to the correct ones, so that we don't break old saved games.
        // @todo Remove all this if we ever break saved-game compatibility.
        Map<String, String> oldToNewIdMap = new HashMap<>();
        oldToNewIdMap.put(Restrictions.MODID + "_pusher", Restrictions.MODID + ":pusher");
        oldToNewIdMap.put("minecraft:" + Restrictions.MODID + "_pusher", Restrictions.MODID + ":pusher");
        oldToNewIdMap.put(Restrictions.MODID + "_attractor", Restrictions.MODID + ":attractor");
        oldToNewIdMap.put("minecraft:" + Restrictions.MODID + "_attractor", Restrictions.MODID + ":attractor");
        modFixs.registerFix(FixTypes.BLOCK_ENTITY, new TileEntityNamespace(oldToNewIdMap, 1));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new GlassBoots());
        event.getRegistry().register(new ItemBlock(ModBlocks.pusherBlock).setRegistryName(ModBlocks.pusherBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.attractorBlock).setRegistryName(ModBlocks.attractorBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.oneWayBlock).setRegistryName(ModBlocks.oneWayBlock.getRegistryName()));
    }
}
