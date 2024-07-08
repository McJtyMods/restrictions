package mcjty.restrictions;

import mcjty.lib.datagen.DataGen;
import mcjty.restrictions.datagen.DataGenerators;
import mcjty.restrictions.setup.ClientSetup;
import mcjty.restrictions.setup.ModSetup;
import mcjty.restrictions.setup.Registration;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Supplier;


@Mod(Restrictions.MODID)
public class Restrictions {

    public static final String MODID = "restrictions";

    @SuppressWarnings("PublicField")
    public static ModSetup setup = new ModSetup();
    public static Restrictions instance;

    public Restrictions(IEventBus bus, Dist dist) {
        instance = this;
        Registration.register(bus);
        bus.addListener(setup::init);
        bus.addListener(this::onDataGen);

        if (dist.isClient()) {
            bus.addListener(ClientSetup::init);
            bus.addListener(ClientSetup::registerLayerDefinitions);
        }
    }

    public static <T extends Item> Supplier<T> tab(Supplier<T> supplier) {
        return instance.setup.tab(supplier);
    }

    private void onDataGen(GatherDataEvent event) {
        DataGen datagen = new DataGen(MODID, event);
        DataGenerators.datagen(datagen);
        datagen.generate();
    }

}
