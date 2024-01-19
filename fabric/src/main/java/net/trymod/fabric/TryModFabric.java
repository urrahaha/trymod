package net.trymod.fabric;

import net.trymod.TryMod;
import net.fabricmc.api.ModInitializer;

public class TryModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TryMod.init();
    }
}
