package net.trymod.mixin;

import dev.architectury.platform.Platform;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.trymod.fluid.OrangeJuiceFluid;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// neoforge has a way to change fluid fog color but
// fabric doesn't in 1.20.4 as far as i know
@Mixin(FogRenderer.class)
public class MixinFogRenderer {
    @Shadow
    private static float fogRed;

    @Shadow
    private static float fogGreen;

    @Shadow
    private static float fogBlue;

    @Inject(method = "setupColor(Lnet/minecraft/client/Camera;FLnet/minecraft/client/multiplayer/ClientLevel;IF)V",
            at = @At(value = "JUMP", opcode = Opcodes.IF_ACMPNE, ordinal = 0),
            cancellable = true
    )
    private static void trymod_OrangeJuiceFogColor(
            Camera camera,
            float f,
            ClientLevel clientLevel,
            int i,
            float g,
            CallbackInfo ci
    ) {
        BlockPos blockPos = camera.getBlockPosition();
        FluidState fluidState = clientLevel.getFluidState(blockPos);
        boolean isBelowFluid = camera.getPosition().y < (blockPos.getY() + fluidState.getHeight(clientLevel, blockPos));
        if (!isBelowFluid) ci.cancel();
        FlowingFluid orangeSource = OrangeJuiceFluid.FLUID.sourceRegistry.get();
        FlowingFluid orangeFlowing = OrangeJuiceFluid.FLUID.flowingRegistry.get();
        if (fluidState.is(orangeSource) || fluidState.is(orangeFlowing)) {
            int orangeJuiceColor = OrangeJuiceFluid.getColor();
            if (Platform.isModLoaded("sodium")) {
                // turn back ABGR into ARGB
                fogRed = (orangeJuiceColor & 0xFF) / 255.0F;
                fogGreen = (orangeJuiceColor >> 8 & 0xFF) / 255.0F;
                fogBlue = (orangeJuiceColor >> 16 & 0xFF) / 255.0F;
            }
            else {
                fogRed = (orangeJuiceColor >> 16 & 0xFF) / 255.0F;
                fogGreen = (orangeJuiceColor >> 8 & 0xFF) / 255.0F;
                fogBlue = (orangeJuiceColor & 0xFF) / 255.0F;
            }
            ci.cancel();
        }
    }
}
