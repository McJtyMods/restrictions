package mcjty.restrictions.setup;

import mcjty.lib.setup.DefaultModSetup;
import mcjty.restrictions.blocks.ModBlocks;
import net.minecraft.item.ItemStack;

public class ModSetup extends DefaultModSetup {

    public ModSetup() {
        createTab("restrictions", () -> new ItemStack(ModBlocks.ATTRACTOR_BLOCK));
    }

    @Override
    public void createTabs() {
        // @todo get rid of this
    }

    @Override
    protected void setupModCompat() { }

    @Override
    protected void setupConfig() {

    }
}
