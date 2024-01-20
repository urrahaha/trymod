package net.trymod;

import static net.trymod.registry.TryRegistry.*;

public class TryMod {
    public static final String MOD_ID = "trymod";
    
    public static void init() {
        FLUIDS.register();
        BLOCKS.register();
        ITEMS.register();
        TABS.register();
    }
}
