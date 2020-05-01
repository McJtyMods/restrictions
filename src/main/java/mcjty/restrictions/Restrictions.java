package mcjty.restrictions;

import mcjty.lib.base.ModBase;
import mcjty.restrictions.setup.ModSetup;
import mcjty.restrictions.setup.Registration;
import net.minecraft.item.Item;
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
        Registration.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener((FMLCommonSetupEvent event) -> setup.init(event));
    }


    @Override
    public String getModId() {
        return Restrictions.MODID;
    }


    public static Item.Properties createStandardProperties() {
        return new Item.Properties().group(setup.getTab());
    }
}
