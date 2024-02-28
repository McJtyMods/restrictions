package mcjty.restrictions.datagen;

import mcjty.lib.datagen.DataGen;
import mcjty.lib.datagen.Dob;
import mcjty.restrictions.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

public class DataGenerators {

    public static void datagen(DataGen datagen) {
        datagen.add(
                Dob.blockBuilder(Registration.ATTRACTOR)
                        .stonePickaxeTags()
                        .standardLoot(Registration.TYPE_ATTRACTOR)
                        .blockState(p -> {
                            p.orientedBlock(Registration.ATTRACTOR.get(), p.models().cube("attractor",
                                            p.modLoc("block/side"),
                                            p.modLoc("block/side"),
                                            p.modLoc("block/attractor"),
                                            p.modLoc("block/side"),
                                            p.modLoc("block/side"),
                                            p.modLoc("block/side"))
                                    .texture("particle", p.modLoc("block/side")));
                        })
                        .shaped(builder -> builder
                                        .define('f', Items.FEATHER)
                                        .define('P', Blocks.STICKY_PISTON)
                                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                                "frf", "rPr", "frf"),
                Dob.blockBuilder(Registration.ONEWAY)
                        .stonePickaxeTags()
                        .simpleLoot()
                        .blockState(p -> {
                            p.orientedBlock(Registration.ONEWAY.get(), p.models().cube("oneway",
                                            p.modLoc("block/sideone"),
                                            p.modLoc("block/sideone"),
                                            p.modLoc("block/onewayn"),
                                            p.modLoc("block/oneways"),
                                            p.modLoc("block/sideone"),
                                            p.modLoc("block/sideone"))
                                    .texture("particle", p.modLoc("block/sideone")));
                        })
                        .shaped(builder -> builder
                                        .define('f', Tags.Items.SLIMEBALLS)
                                        .define('P', Items.RAIL)
                                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                                "frf", "rPr", "frf"),
                Dob.blockBuilder(Registration.PUSHER)
                        .stonePickaxeTags()
                        .standardLoot(Registration.TYPE_PUSHER)
                        .blockState(p -> {
                            p.orientedBlock(Registration.PUSHER.get(), p.models().cube("pusher",
                                            p.modLoc("block/side"),
                                            p.modLoc("block/side"),
                                            p.modLoc("block/pusher"),
                                            p.modLoc("block/side"),
                                            p.modLoc("block/side"),
                                            p.modLoc("block/side"))
                                    .texture("particle", p.modLoc("block/side")));
                        })
                        .shaped(builder -> builder
                                        .define('f', Items.FEATHER)
                                        .define('P', Blocks.PISTON)
                                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                                "frf", "rPr", "frf"),
                Dob.blockBuilder(Registration.ONEWAY_WALL)
                        .stonePickaxeTags()
                        .simpleLoot()
                        .blockState(p -> {
                            p.orientedBlock(Registration.ONEWAY_WALL.get(), p.models().cube("oneway_wall",
                                            p.modLoc("block/sideone"),
                                            p.modLoc("block/sideone"),
                                            p.modLoc("block/sideone"),
                                            p.modLoc("block/side"),
                                            p.modLoc("block/sideone"),
                                            p.modLoc("block/sideone"))
                                    .texture("particle", p.modLoc("block/sideone")));
                        })
                        .shaped(builder -> builder
                                        .define('f', Tags.Items.SLIMEBALLS)
                                        .define('P', Tags.Items.GLASS)
                                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                                "frf", "rPr", "frf"),
                Dob.itemBuilder(Registration.GLASSBOOTS)
                        .shaped(builder -> builder
                                        .define('g', Tags.Items.GLASS_PANES)
                                        .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS)),
                                "   ", "g g", "G G")
        );
    }
}
