package mcjty.restrictions.setup;


import mcjty.restrictions.items.GlassBootsModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Registration.ONEWAY.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Registration.ONEWAY_WALL.get(), RenderType.cutout());
    }

    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GlassBootsModel.GLASS_BOOTS, GlassBootsModel::createBootsLayer);
    }
}
