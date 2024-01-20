package net.trymod.fluid;

import dev.architectury.core.fluid.SimpleArchitecturyFluidAttributes;

public class OrangeJuiceFluid {
    public static final SimpleArchitecturyFluidAttributes ATTRIBUTES = SimpleArchitecturyFluidAttributes.ofSupplier(() -> OrangeJuiceFluid.FLUID.flowingRegistry, () -> OrangeJuiceFluid.FLUID.sourceRegistry);
    public static final TryFluid FLUID = new TryFluid("orangejuice_fluid", OrangeJuiceFluid.ATTRIBUTES);

    public OrangeJuiceFluid init(){
        ATTRIBUTES.color(0xffb700);
        return this;
    }
}
