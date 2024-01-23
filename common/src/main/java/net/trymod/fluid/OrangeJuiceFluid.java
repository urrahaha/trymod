package net.trymod.fluid;

import dev.architectury.core.block.ArchitecturyLiquidBlock;
import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.PlatformOnly;
import dev.architectury.platform.Platform;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

import static net.trymod.registry.TryRegistry.ORANGEJUICE_CUP;
import static net.trymod.registry.TryRegistry.ORANGEJUICE_CUP_EMPTY;

public class OrangeJuiceFluid {
    public static final SimpleArchitecturyFluidAttributes ATTRIBUTES = SimpleArchitecturyFluidAttributes.ofSupplier(() -> OrangeJuiceFluid.FLUID.flowingRegistry, () -> OrangeJuiceFluid.FLUID.sourceRegistry);
    public static final TryFluid FLUID = new TryFluid(
            "orangejuice_fluid",
            OrangeJuiceFluid.ATTRIBUTES,
            () -> new OrangeJuiceLiquidBlock(OrangeJuiceFluid.FLUID.sourceRegistry, BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)) {
            }
    );
    public static final int COLOR = 0xfeac01;

    public OrangeJuiceFluid init(){
        ATTRIBUTES.color(getColor());
        // real orange juice isn't translucent
        // registerFluids(RenderType.translucent(), FLUID.sourceRegistry, FLUID.flowingRegistry);
        return this;
    }

    public static int getColor(){
        // sodium uses ABGR instead of ARGB for world fluid rendering with color tints?
        // embeddium which is on forge seems to be using the color correctly
        return Platform.isModLoaded("sodium") ? 0x01acfe : 0xfeac01;
    }

    public static class OrangeJuiceLiquidBlock extends ArchitecturyLiquidBlock {

        public OrangeJuiceLiquidBlock(Supplier<? extends FlowingFluid> fluid, Properties properties) {
            super(fluid, properties);
        }

        @Override // entityInside is marked as deprecated but i don't know a better way to implement this
        public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity) {
            super.entityInside(blockState, level, blockPos, entity);
            if (entity instanceof ItemEntity && ((ItemEntity) entity).getItem().is(ORANGEJUICE_CUP_EMPTY.get()) && blockState.getFluidState().isSource()) {
                level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 11);
                ((ItemEntity) entity).setItem(new ItemStack(ORANGEJUICE_CUP));
            }
        }
    }
}
