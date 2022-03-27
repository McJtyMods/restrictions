package mcjty.restrictions.datagen;

import mcjty.lib.datagen.BaseBlockTagsProvider;
import mcjty.restrictions.Restrictions;
import mcjty.restrictions.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTags extends BaseBlockTagsProvider {
	public BlockTags(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Restrictions.MODID, exFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(Registration.PUSHER.get());
		this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(Registration.ATTRACTOR.get());
		this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(Registration.ONEWAY.get());
		this.tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(Registration.ONEWAY_WALL.get());
	}
}
