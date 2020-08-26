package mcjty.restrictions;

import mcjty.restrictions.setup.ClientSetup;
import mcjty.restrictions.setup.ModSetup;
import mcjty.restrictions.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Restrictions.MODID)
public class Restrictions {

    public static final String MODID = "restrictions";

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();

    public Restrictions() {
        Registration.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        });
    }
}
