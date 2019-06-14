package mcjty.restrictions.setup;

import mcjty.lib.setup.DefaultModSetup;
import mcjty.restrictions.blocks.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModSetup extends DefaultModSetup {

    @Override
    public void init(FMLCommonSetupEvent e) {
        super.init(e);
        Registration.init();
    }

    @Override
    public void createTabs() {
        createTab("restrictions", () -> new ItemStack(ModBlocks.attractorBlock));
    }

    @Override
    protected void setupModCompat() { }

    @Override
    protected void setupConfig() {

    }
}
