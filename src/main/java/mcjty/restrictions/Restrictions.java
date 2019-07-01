package mcjty.restrictions;

import mcjty.lib.base.ModBase;
import mcjty.restrictions.setup.ModSetup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Restrictions.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Restrictions implements ModBase {

    public static final String MODID = "restrictions";

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();

    public Restrictions() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLCommonSetupEvent event) -> setup.init(event));
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
