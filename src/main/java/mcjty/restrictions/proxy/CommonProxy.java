package mcjty.restrictions.proxy;

import mcjty.lib.proxy.AbstractCommonProxy;
import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.*;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
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
        event.getRegistry().register(new PusherBlock());
        GameRegistry.registerTileEntity(PusherTileEntity.class, Restrictions.MODID + "_pusher");
        event.getRegistry().register(new AttractorBlock());
        GameRegistry.registerTileEntity(AttractorTileEntity.class, Restrictions.MODID + "_attractor");
        event.getRegistry().register(new OneWayBlock());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new GlassBoots());
        event.getRegistry().register(new ItemBlock(ModBlocks.pusherBlock).setRegistryName(ModBlocks.pusherBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.attractorBlock).setRegistryName(ModBlocks.attractorBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.oneWayBlock).setRegistryName(ModBlocks.oneWayBlock.getRegistryName()));
    }
}
