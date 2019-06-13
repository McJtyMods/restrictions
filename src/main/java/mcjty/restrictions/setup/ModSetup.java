package mcjty.restrictions.setup;

import mcjty.lib.setup.DefaultModSetup;
import mcjty.restrictions.blocks.ModBlocks;
import net.minecraft.item.ItemStack;

public class ModSetup extends DefaultModSetup {

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
