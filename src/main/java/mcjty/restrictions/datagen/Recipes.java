package mcjty.restrictions.datagen;

import mcjty.lib.datagen.BaseRecipeProvider;
import mcjty.restrictions.blocks.ModBlocks;
import mcjty.restrictions.items.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends BaseRecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
        group("restrictions");
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        build(consumer, ShapedRecipeBuilder.shapedRecipe(ModBlocks.ATTRACTOR_BLOCK)
                        .key('f', Items.FEATHER)
                        .key('P', Blocks.STICKY_PISTON)
                        .addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE)),
                "frf", "rPr", "frf");
        build(consumer, ShapedRecipeBuilder.shapedRecipe(ModBlocks.PUSHER_BLOCK)
                        .key('f', Items.FEATHER)
                        .key('P', Blocks.PISTON)
                        .addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE)),
                "frf", "rPr", "frf");
        build(consumer, ShapedRecipeBuilder.shapedRecipe(ModBlocks.ONEWAY_BLOCK)
                        .key('f', Tags.Items.SLIMEBALLS)
                        .key('P', Items.RAIL)
                        .addCriterion("redstone", InventoryChangeTrigger.Instance.forItems(Items.REDSTONE)),
                "frf", "rPr", "frf");
        build(consumer, ShapedRecipeBuilder.shapedRecipe(ModItems.GLASSBOOTS)
                        .key('g', Tags.Items.GLASS_PANES)
                        .addCriterion("glass", InventoryChangeTrigger.Instance.forItems(Items.GLASS)),
                "   ", "g g", "G G");
    }
}
