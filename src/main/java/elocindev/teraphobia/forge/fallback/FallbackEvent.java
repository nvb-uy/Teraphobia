package elocindev.teraphobia.forge.fallback;

import elocindev.teraphobia.forge.spawn.SpawningHandler;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FallbackEvent {
    @SubscribeEvent
    public static void fallbackSpawnEvent(LivingSpawnEvent.CheckSpawn event) {
        if (event.getLevel().isClientSide()) return;

        if (SpawningHandler.shouldBeRemoved(event.getEntity().getLevel(), event.getEntity())) {
            event.setResult(Result.DENY);
        }
    }
}