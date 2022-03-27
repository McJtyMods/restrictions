package mcjty.restrictions.datagen;

import mcjty.lib.datagen.BaseLootTableProvider;
import mcjty.restrictions.setup.Registration;
import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.ATTRACTOR.get(), createStandardTable("attractor", Registration.ATTRACTOR.get(), Registration.TYPE_ATTRACTOR.get()));
        addSimpleTable(Registration.ONEWAY.get());
        addSimpleTable(Registration.ONEWAY_WALL.get());
        lootTables.put(Registration.PUSHER.get(), createStandardTable("pusher", Registration.PUSHER.get(), Registration.TYPE_PUSHER.get()));
    }
}
