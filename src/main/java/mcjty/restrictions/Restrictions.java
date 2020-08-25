package mcjty.restrictions;

import mcjty.restrictions.setup.ClientSetup;
import mcjty.restrictions.setup.ModSetup;
import mcjty.restrictions.setup.Registration;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Restrictions.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Restrictions {

    public static final String MODID = "restrictions";

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();

    public Restrictions() {
        Registration.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::initModels);
    }

    public static Item.Properties createStandardProperties() {
        return new Item.Properties().group(setup.getTab());
    }
}
