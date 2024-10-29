package elocindev.teraphobia.forge;


import java.util.logging.Logger;

import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import elocindev.teraphobia.forge.config.TeraphobiaConfig;
import elocindev.teraphobia.forge.registry.GameruleRegistry;
import elocindev.teraphobia.forge.registry.ItemGroupRegistry;
import elocindev.teraphobia.forge.registry.ItemRegistry;
import elocindev.teraphobia.forge.registry.LootRegistry;
import elocindev.teraphobia.forge.registry.PacketRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
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

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        NecConfigAPI.registerConfig(TeraphobiaConfig.class);

        ItemRegistry.register(bus);
        ItemGroupRegistry.register(bus);

        GameruleRegistry.register();
        LootRegistry.register();
    }

    private void setup(final FMLCommonSetupEvent event) {
		LOGGER.info("Loaded Teraphobia Config");
        
        PacketRegistry.register();
    }
}
