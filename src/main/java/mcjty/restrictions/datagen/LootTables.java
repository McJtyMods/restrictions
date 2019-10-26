package mcjty.restrictions.datagen;

import mcjty.lib.datagen.BaseLootTableProvider;
import mcjty.restrictions.blocks.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.LootTableProvider;

public class LootTables extends BaseLootTableProvider {

    public LootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.ATTRACTOR_BLOCK, createStandardTable("attractor", ModBlocks.ATTRACTOR_BLOCK));
        lootTables.put(ModBlocks.ONEWAY_BLOCK, createStandardTable("oneway", ModBlocks.ONEWAY_BLOCK));
        lootTables.put(ModBlocks.PUSHER_BLOCK, createStandardTable("pusher", ModBlocks.PUSHER_BLOCK));
}
}
