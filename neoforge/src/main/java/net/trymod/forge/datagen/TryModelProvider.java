package net.trymod.forge.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trymod.fluid.OrangeJuiceFluid;

public class TryModelProvider extends BlockStateProvider {
    public TryModelProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.blockTexture(OrangeJuiceFluid.FLUID.blockRegistry.get());
    }
}
