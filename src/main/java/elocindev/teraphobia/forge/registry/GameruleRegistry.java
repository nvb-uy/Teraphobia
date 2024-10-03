package elocindev.teraphobia.forge.registry;

import java.lang.reflect.Method;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameRules.Type;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class GameruleRegistry {
    
    // public static final GameRules.Key<GameRules.BooleanValue> AETHERAVAILABLE = GameRules.register("aetherAvailable", GameRules.Category.MISC, register(false));
    // public static final GameRules.Key<GameRules.BooleanValue> AETHERINFECTED = GameRules.register("aetherInfected", GameRules.Category.MISC, register(true));

    @SuppressWarnings("unchecked")
	public static Type<GameRules.BooleanValue> register(boolean defaultValue) {
		try {
			Method createGameruleMethod = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "m_46250_", boolean.class);
			createGameruleMethod.setAccessible(true);
			return (Type<GameRules.BooleanValue>) createGameruleMethod.invoke(GameRules.BooleanValue.class, defaultValue);
		} catch (Exception e) { 
            e.printStackTrace(); 
        }
		
        return null;
	}

    public static void register() {}
}
