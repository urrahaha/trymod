package net.trymod.forge;

import dev.architectury.platform.hooks.EventBusesHooks;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.trymod.TryMod;
import net.neoforged.fml.common.Mod;

import java.util.Optional;

@Mod(TryMod.MOD_ID)
public class TryModForge {
    public TryModForge(IEventBus eventBus) {
        TryMod.init();
    }
}
