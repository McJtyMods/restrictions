package mcjty.restrictions.datagen;

import mcjty.lib.datagen.BaseBlockStateProvider;
import mcjty.restrictions.Restrictions;
import mcjty.restrictions.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class BlockStates extends BaseBlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Restrictions.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        orientedBlock(Registration.ATTRACTOR.get(), models().cube("attractor",
                modLoc("block/side"),
                modLoc("block/side"),
                modLoc("block/attractor"),
                modLoc("block/side"),
                modLoc("block/side"),
                modLoc("block/side"))
                .texture("particle", modLoc("block/side")));
        orientedBlock(Registration.PUSHER.get(), models().cube("pusher",
                modLoc("block/side"),
                modLoc("block/side"),
                modLoc("block/pusher"),
                modLoc("block/side"),
                modLoc("block/side"),
                modLoc("block/side"))
                .texture("particle", modLoc("block/side")));
        orientedBlock(Registration.ONEWAY.get(), models().cube("oneway",
                modLoc("block/sideone"),
                modLoc("block/sideone"),
                modLoc("block/onewayn"),
                modLoc("block/oneways"),
                modLoc("block/sideone"),
                modLoc("block/sideone"))
                .texture("particle", modLoc("block/sideone")));
        orientedBlock(Registration.ONEWAY_WALL.get(), models().cube("oneway_wall",
                modLoc("block/sideone"),
                modLoc("block/sideone"),
                modLoc("block/sideone"),
                modLoc("block/side"),
                modLoc("block/sideone"),
                modLoc("block/sideone"))
                .texture("particle", modLoc("block/sideone")));
    }
}
