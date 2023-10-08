package elocindev.teraphobia.forge.api;

import elocindev.teraphobia.forge.registry.GameruleRegistry;
import net.minecraft.world.level.Level;

public class TeraphobiaAPI {
    
    public static boolean isAetherInfected(Level level) {
        return level.getGameRules().getBoolean(GameruleRegistry.AETHERINFECTED);
    }

    public static boolean isAetherAccessible(Level level) {
        return level.getGameRules().getBoolean(GameruleRegistry.AETHERAVAILABLE);
    }
}
