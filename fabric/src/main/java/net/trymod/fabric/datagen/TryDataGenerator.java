package net.trymod.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.trymod.fluid.OrangeJuiceFluid;

import static net.trymod.registry.TryRegistry.ORANGEJUICE_CUP;
import static net.trymod.registry.TryRegistry.ORANGEJUICE_CUP_EMPTY;

public class TryDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(TryRecipeGenerator::new);
    }

    private static class TryRecipeGenerator extends FabricRecipeProvider {

        public TryRecipeGenerator(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void buildRecipes(RecipeOutput output) {
            Item orangejuicebucket = OrangeJuiceFluid.FLUID.bucketRegistry.get();
            Criterion<InventoryChangeTrigger.TriggerInstance> has_orangejuicebucket = FabricRecipeProvider.has(orangejuicebucket);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ORANGEJUICE_CUP.get())
                    .requires(OrangeJuiceFluid.FLUID.bucketRegistry.get())
                    .requires(Items.GLASS_BOTTLE)
                    .unlockedBy(FabricRecipeProvider.getHasName(orangejuicebucket), has_orangejuicebucket)
                    .group("orangejuice_cup")
                    .save(output);

            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ORANGEJUICE_CUP.get())
                    .requires(OrangeJuiceFluid.FLUID.bucketRegistry.get())
                    .requires(ORANGEJUICE_CUP_EMPTY.get())
                    .unlockedBy(FabricRecipeProvider.getHasName(orangejuicebucket), has_orangejuicebucket)
                    .group("orangejuice_cup")
                    .save(output, ORANGEJUICE_CUP.getId().toString() + "_from_cup");
        }
    }
}
