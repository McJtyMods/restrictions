package mcjty.restrictions.setup;

import mcjty.lib.datafix.fixes.TileEntityNamespace;
import mcjty.lib.setup.DefaultCommonSetup;
import mcjty.restrictions.Restrictions;
import mcjty.restrictions.blocks.*;
import mcjty.restrictions.items.GlassBoots;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.datafix.FixTypes;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber
public class CommonSetup extends DefaultCommonSetup {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void createTabs() {
        createTab("restrictions", new ItemStack(ModBlocks.attractorBlock));
    }

    @Override
    protected void setupModCompat() { }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModFixs modFixs = FMLCommonHandler.instance().getDataFixer().init(Restrictions.MODID, 2);

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
        modFixs.registerFix(FixTypes.BLOCK_ENTITY, new TileEntityNamespace(oldToNewIdMap, 2));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new GlassBoots());
        event.getRegistry().register(new ItemBlock(ModBlocks.pusherBlock).setRegistryName(ModBlocks.pusherBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.attractorBlock).setRegistryName(ModBlocks.attractorBlock.getRegistryName()));
        event.getRegistry().register(new ItemBlock(ModBlocks.oneWayBlock).setRegistryName(ModBlocks.oneWayBlock.getRegistryName()));
    }
}
