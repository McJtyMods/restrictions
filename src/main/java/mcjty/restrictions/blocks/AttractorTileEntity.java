package mcjty.restrictions.blocks;

import mcjty.lib.blocks.BaseBlock;
import mcjty.lib.builder.BlockBuilder;
import mcjty.restrictions.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import static mcjty.lib.builder.TooltipBuilder.header;
import static mcjty.lib.builder.TooltipBuilder.key;

public class AttractorTileEntity extends BaseTileEntity {

    public AttractorTileEntity(BlockPos pos, BlockState state) {
        super(Registration.TYPE_ATTRACTOR.get(), pos, state, -.1);
    }

    public static BaseBlock createBlock() {
        return new BaseBlock(new BlockBuilder()
                .properties(Block.Properties.of(Material.METAL)
                        .strength(2.0f)
                        .sound(SoundType.METAL))
                .tileEntitySupplier(AttractorTileEntity::new)
                .info(key("message.restrictions.shiftmessage"))
                .infoShift(header())
        );
    }

}
