package net.trymod;

import static net.trymod.registry.TryRegistry.ITEMS;
import static net.trymod.registry.TryRegistry.TABS;

public class TryMod {
    public static final String MOD_ID = "trymod";
    
    public static void init() {
        TABS.register();
        ITEMS.register();
    }
}
