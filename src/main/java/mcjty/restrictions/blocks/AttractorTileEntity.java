package mcjty.restrictions.blocks;

import mcjty.lib.blocks.BaseBlock;
import mcjty.lib.builder.BlockBuilder;
import mcjty.restrictions.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

import static mcjty.lib.builder.TooltipBuilder.header;
import static mcjty.lib.builder.TooltipBuilder.key;

import BaseBlock;

public class AttractorTileEntity extends BaseTileEntity {

    public AttractorTileEntity() {
        super(Registration.TYPE_ATTRACTOR.get(), -.1);
    }

    public static BaseBlock createBlock() {
        return new BaseBlock(new BlockBuilder()
                .properties(Block.Properties.of(Material.METAL)
                        .harvestTool(ToolType.PICKAXE)
                        .harvestLevel(0)
                        .strength(2.0f)
                        .sound(SoundType.METAL))
                .tileEntitySupplier(AttractorTileEntity::new)
                .info(key("message.restrictions.shiftmessage"))
                .infoShift(header())
        );
    }

}
