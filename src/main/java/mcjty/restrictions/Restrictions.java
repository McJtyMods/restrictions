package mcjty.restrictions;

import mcjty.lib.base.ModBase;
import mcjty.lib.setup.IProxy;
import mcjty.lib.setup.ModSetup;
import mcjty.restrictions.setup.ClientProxy;
import mcjty.restrictions.setup.ServerProxy;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Restrictions.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Restrictions implements ModBase {

    public static final String MODID = "restrictions";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    public static ModSetup setup = new ModSetup();

    public static Restrictions instance;

    public Restrictions() {
        instance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
    }

    public void init(final FMLCommonSetupEvent event) {
        setup.init(event);
        proxy.init(event);
    }


    @Override
    public String getModId() {
        return Restrictions.MODID;
    }

    @Override
    public void openManual(PlayerEntity entityPlayer, int i, String s) {
        // @todo
    }
}
