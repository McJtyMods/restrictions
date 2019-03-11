package mcjty.restrictions.setup;

import mcjty.lib.setup.DefaultCommonSetup;
import mcjty.restrictions.blocks.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonSetup extends DefaultCommonSetup {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @Override
    public void createTabs() {
        createTab("restrictions", new ItemStack(ModBlocks.attractorBlock));
    }

    @Override
    protected void setupModCompat() { }
}
