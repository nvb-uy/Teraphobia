package elocindev.teraphobia.forge;


import java.util.logging.Logger;

import elocindev.teraphobia.forge.registry.GameruleRegistry;
import elocindev.teraphobia.forge.registry.PacketRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Teraphobia.MODID)
public class Teraphobia {
    public static final String MODID = "teraphobia";
    public static final Logger LOGGER = Logger.getLogger(MODID);

    public static final boolean DEBUG = false;
    
    @SuppressWarnings("removal")
    public Teraphobia() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        GameruleRegistry.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Loaded Teraphobia Config");

        PacketRegistry.register();
    }
}
