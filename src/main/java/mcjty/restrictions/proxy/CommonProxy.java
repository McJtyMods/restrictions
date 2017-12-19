package mcjty.restrictions.proxy;

import com.google.common.util.concurrent.ListenableFuture;
import mcjty.lib.McJtyLib;
import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.*;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.concurrent.Callable;

@Mod.EventBusSubscriber
public class CommonProxy {

    // Config instance
//    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
//        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
        McJtyLib.preInit(e);

//        File directory = e.getModConfigurationDirectory();
//        config = new Configuration(new File(directory.getPath(), "meecreeps.cfg"));
//        Config.readConfig();

//        PacketHandler.registerMessages("meecreeps");

        // Initialization of blocks and items typically goes here:
//        ModEntities.init();
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
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

    public World getClientWorld() {
        throw new IllegalStateException("This should only be called from client side");
    }

    public EntityPlayer getClientPlayer() {
        throw new IllegalStateException("This should only be called from client side");
    }

    public <V> ListenableFuture<V> addScheduledTaskClient(Callable<V> callableToSchedule) {
        throw new IllegalStateException("This should only be called from client side");
    }

    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule) {
        throw new IllegalStateException("This should only be called from client side");
    }

}
