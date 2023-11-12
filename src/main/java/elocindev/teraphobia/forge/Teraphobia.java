package elocindev.teraphobia.forge;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import elocindev.teraphobia.forge.config.ConfigBuilder;
import elocindev.teraphobia.forge.config.ConfigEntries;
import elocindev.teraphobia.forge.registry.GameruleRegistry;
import elocindev.teraphobia.forge.registry.PacketRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Teraphobia.MODID)
public class Teraphobia {
    public static final String MODID = "teraphobia";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ConfigEntries Config = ConfigBuilder.loadConfig();

    public static boolean INFECTION_STATUS = true;
    public static boolean AVAILABLE_STATUS = false;

    public static final boolean DEBUG = false;

    
    public Teraphobia() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        GameruleRegistry.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
        Config = ConfigBuilder.loadConfig();
		LOGGER.info("Loaded Teraphobia Config");

        PacketRegistry.register();

        if (ModList.get().isLoaded("essential") && ModList.get().isLoaded("luna")) {
            LOGGER.info("Essential is not supported with Luna.");
            System.exit(805);
        }
    }

    @Mod.EventBusSubscriber
    public static class Server {
        @SubscribeEvent
        public static void serverSetup(LevelEvent.Load event) {
            if (event.getLevel() instanceof ServerLevel level) {
                INFECTION_STATUS = level.getGameRules().getBoolean(GameruleRegistry.AETHERINFECTED);
                AVAILABLE_STATUS = level.getGameRules().getBoolean(GameruleRegistry.AETHERAVAILABLE);
            }
        }

        @SubscribeEvent
        public static void serverTick(ServerTickEvent event) {
            Level level = event.getServer().getLevel(Level.OVERWORLD);

            
            if (level != null && level.getDayTime() % 20 == 0) {
                GameRules rules = level.getGameRules();

                INFECTION_STATUS = rules.getBoolean(GameruleRegistry.AETHERINFECTED);
                AVAILABLE_STATUS = rules.getBoolean(GameruleRegistry.AETHERAVAILABLE);
            }
        }
    }
}
