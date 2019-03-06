package mcjty.restrictions;

import mcjty.lib.base.ModBase;
import mcjty.lib.proxy.CommonSetup;
import mcjty.lib.proxy.IProxy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Restrictions.MODID, name = "Restrictions",
        dependencies =
                "required-after:mcjtylib_ng@[" + Restrictions.MIN_MCJTYLIB_VER + ",);" +
                "after:forge@[" + Restrictions.MIN_FORGE_VER + ",)",
        version = Restrictions.VERSION,
        acceptedMinecraftVersions = "[1.12,1.13)")
public class Restrictions implements ModBase {
    public static final String MODID = "restrictions";
    public static final String MIN_MCJTYLIB_VER = "3.0.0";
    public static final String VERSION = "1.2.1";
    public static final String MIN_FORGE_VER = "14.22.0.2464";

    @SidedProxy(clientSide = "mcjty.restrictions.proxy.ClientProxy", serverSide = "mcjty.restrictions.proxy.ServerProxy")
    public static IProxy proxy;
    public static CommonSetup setup = new CommonSetup();

    @Mod.Instance(MODID)
    public static Restrictions instance;

    /**
     * Run before anything else. Read your config, create blocks, items, etc, and
     * register them with the GameRegistry.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        setup.preInit(e);
        proxy.preInit(e);
    }

    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        setup.init(e);
        proxy.init(e);
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        setup.postInit(e);
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
