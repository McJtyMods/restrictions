package mcjty.restrictions.setup;

import mcjty.lib.setup.DefaultModSetup;
import mcjty.restrictions.Restrictions;
import net.minecraft.world.item.ItemStack;

public class ModSetup extends DefaultModSetup {

    public ModSetup() {
        createTab(Restrictions.MODID, "restrictions", () -> new ItemStack(Registration.ATTRACTOR.get()));
    }

    @Override
    protected void setupModCompat() { }
}
