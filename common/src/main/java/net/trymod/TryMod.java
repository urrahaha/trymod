package net.trymod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.trymod.registry.TryRegistry.*;

public class TryMod {
    public static final String MOD_ID = "trymod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static void init() {
        FLUIDS.register();
        BLOCKS.register();
        ITEMS.register();
        TABS.register();
    }
}
