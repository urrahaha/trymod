package net.trymod.fluid;

import dev.architectury.core.block.ArchitecturyLiquidBlock;
import dev.architectury.core.fluid.ArchitecturyFlowingFluid;
import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;
import dev.architectury.core.item.ArchitecturyBucketItem;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

import static net.trymod.registry.TryRegistry.*;

public class TryFluid { // this is probably stupid
    public final String name;
    public final RegistrySupplier<FlowingFluid> sourceRegistry;
    public final RegistrySupplier<FlowingFluid> flowingRegistry;
    public final RegistrySupplier<LiquidBlock> blockRegistry;
    public final RegistrySupplier<Item> bucketRegistry;

    public TryFluid(String name, SimpleArchitecturyFluidAttributes attributes) {
        this.name = name;
        sourceRegistry = FLUIDS.register(name, () -> new ArchitecturyFlowingFluid.Source(attributes));
        flowingRegistry = FLUIDS.register(name + "_flowing", () -> new ArchitecturyFlowingFluid.Flowing(attributes));
        blockRegistry = BLOCKS.register(name + "_block", () -> new ArchitecturyLiquidBlock(sourceRegistry, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
        bucketRegistry = ITEMS.register(name + "_bucket", () -> new ArchitecturyBucketItem(sourceRegistry, new Item.Properties().craftRemainder(Items.BUCKET).arch$tab(TRY_TAB)));
        attributes.blockSupplier(() -> blockRegistry)
                .bucketItemSupplier(() -> bucketRegistry)
                .sourceTexture(new ResourceLocation("block/water_still"))
                .flowingTexture(new ResourceLocation("block/water_flow"))
                .overlayTexture(new ResourceLocation("block/water_overlay"));
    }
    public TryFluid(String name, SimpleArchitecturyFluidAttributes attributes, Supplier<ArchitecturyLiquidBlock> block) {
        this.name = name;
        sourceRegistry = FLUIDS.register(name, () -> new ArchitecturyFlowingFluid.Source(attributes));
        flowingRegistry = FLUIDS.register(name + "_flowing", () -> new ArchitecturyFlowingFluid.Flowing(attributes));
        blockRegistry = BLOCKS.register(name + "_block", block);
        bucketRegistry = ITEMS.register(name + "_bucket", () -> new ArchitecturyBucketItem(sourceRegistry, new Item.Properties().craftRemainder(Items.BUCKET).arch$tab(TRY_TAB)));
        attributes.blockSupplier(() -> blockRegistry)
                .bucketItemSupplier(() -> bucketRegistry)
                .sourceTexture(new ResourceLocation("block/water_still"))
                .flowingTexture(new ResourceLocation("block/water_flow"))
                .overlayTexture(new ResourceLocation("block/water_overlay"));
    }
}
