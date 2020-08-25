package mcjty.restrictions.setup;


import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(Registration.ONEWAY.get(), RenderType.getCutout());
    }

    public static void initModels(ModelRegistryEvent event) {
    }
}
