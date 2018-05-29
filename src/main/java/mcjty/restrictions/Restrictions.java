package mcjty.restrictions;

import mcjty.lib.base.ModBase;
import mcjty.restrictions.blocks.ModBlocks;
import mcjty.restrictions.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Restrictions.MODID, name = "Restrictions",
        dependencies =
                "required-after:mcjtylib_ng@[" + Restrictions.MIN_MCJTYLIB_VER + ",);" +
                "after:forge@[" + Restrictions.MIN_FORGE_VER + ",)",
        version = Restrictions.VERSION,
        acceptedMinecraftVersions = "[1.12,1.13)")
public class Restrictions implements ModBase {
    public static final String MODID = "restrictions";
    public static final String MIN_MCJTYLIB_VER = "3.0.0-alpha";
    public static final String VERSION = "1.2.0-alpha";
    public static final String MIN_FORGE_VER = "14.22.0.2464";

    @SidedProxy(clientSide = "mcjty.restrictions.proxy.ClientProxy", serverSide = "mcjty.restrictions.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Restrictions instance;

    public static Logger logger;

    public static CreativeTabs creativeTab = new CreativeTabs("restrictions") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModBlocks.attractorBlock);
        }
    };


    /**
     * Run before anything else. Read your config, create blocks, items, etc, and
     * register them with the GameRegistry.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        proxy.preInit(e);
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }

    @Override
    public String getModId() {
        return Restrictions.MODID;
    }

    @Override
    public void openManual(EntityPlayer entityPlayer, int i, String s) {
        // @todo
    }
}
