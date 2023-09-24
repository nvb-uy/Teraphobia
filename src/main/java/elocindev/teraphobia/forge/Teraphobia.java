package elocindev.teraphobia.forge;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import elocindev.teraphobia.forge.config.ConfigBuilder;
import elocindev.teraphobia.forge.config.ConfigEntries;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Teraphobia.MODID)
public class Teraphobia {
    public static final String MODID = "teraphobia";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ConfigEntries Config = ConfigBuilder.loadConfig();

    
    public Teraphobia() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Config = ConfigBuilder.loadConfig();
		LOGGER.info("Loaded Teraphobia Config");
    }
}
