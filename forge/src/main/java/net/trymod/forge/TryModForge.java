package net.trymod.forge;

import net.neoforged.bus.api.IEventBus;
import net.trymod.TryMod;
import net.neoforged.fml.common.Mod;

import static net.trymod.TryMod.MOD_ID;

@Mod(MOD_ID)
public class TryModForge {
    public TryModForge(IEventBus eventBus) {
        TryMod.init();
    }
}
