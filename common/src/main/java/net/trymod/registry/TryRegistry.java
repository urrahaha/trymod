package net.trymod.registry;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.trymod.fluid.OrangeJuiceFluid;
import net.trymod.fluid.TryFluid;
import net.trymod.item.OrangeJuiceCupItem;

import java.util.function.Supplier;

import static net.trymod.TryMod.MOD_ID;

public class TryRegistry {
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<Item> ORANGEJUICE_CUP = ITEMS.register("orangejuice_cup", () -> new OrangeJuiceCupItem(false));
    public static final RegistrySupplier<Item> ORANGEJUICE_CUP_EMPTY = ITEMS.register("orangejuice_cup_empty", () -> new OrangeJuiceCupItem(true));

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(MOD_ID, Registries.FLUID);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(MOD_ID, Registries.BLOCK);

    public static final OrangeJuiceFluid ORANGEJUICE_FLUID = new OrangeJuiceFluid().init();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> TRY_TAB = TABS.register(
            "try_tab",
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup.try"),
                    () -> new ItemStack(ORANGEJUICE_CUP)
            )
    );
}
