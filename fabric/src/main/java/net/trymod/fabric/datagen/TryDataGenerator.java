package net.trymod.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Blocks;
import net.trymod.fluid.OrangeJuiceFluid;

public class TryDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(TryModelGenerator::new);
    }

    private static class TryModelGenerator extends FabricModelProvider {

        public TryModelGenerator(FabricDataOutput generator) {
            super(generator);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        }

        @Override
        public void generateItemModels(ItemModelGenerators itemModelGenerator) {
            itemModelGenerator.generateFlatItem(OrangeJuiceFluid.FLUID.bucketRegistry.get(), ModelTemplates.FLAT_ITEM);
        }
    }
}
