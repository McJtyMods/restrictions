package mcjty.restrictions.datagen;

import mcjty.lib.datagen.BaseRecipeProvider;
import mcjty.restrictions.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends BaseRecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
        group("restrictions");
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        build(consumer, ShapedRecipeBuilder.shaped(Registration.ATTRACTOR.get())
                        .define('f', Items.FEATHER)
                        .define('P', Blocks.STICKY_PISTON)
                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                "frf", "rPr", "frf");
        build(consumer, ShapedRecipeBuilder.shaped(Registration.PUSHER.get())
                        .define('f', Items.FEATHER)
                        .define('P', Blocks.PISTON)
                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                "frf", "rPr", "frf");
        build(consumer, ShapedRecipeBuilder.shaped(Registration.ONEWAY.get())
                        .define('f', Tags.Items.SLIMEBALLS)
                        .define('P', Items.RAIL)
                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                "frf", "rPr", "frf");
        build(consumer, ShapedRecipeBuilder.shaped(Registration.ONEWAY_WALL.get())
                        .define('f', Tags.Items.SLIMEBALLS)
                        .define('P', Tags.Items.GLASS)
                        .unlockedBy("redstone", InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE)),
                "frf", "rPr", "frf");
        build(consumer, ShapedRecipeBuilder.shaped(Registration.GLASSBOOTS.get())
                        .define('g', Tags.Items.GLASS_PANES)
                        .unlockedBy("glass", InventoryChangeTrigger.TriggerInstance.hasItems(Items.GLASS)),
                "   ", "g g", "G G");
    }
}
