package elocindev.teraphobia.forge.fallback;

import elocindev.teraphobia.forge.Teraphobia;
import elocindev.teraphobia.forge.spawn.SpawningHandler;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
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

    @SubscribeEvent
    public static void fallbackSpawnEvent(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;

        if (event.getEntity() instanceof EnderDragon dragon) {
            dragon.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Teraphobia.Config.ender_dragon_max_health);
            dragon.setHealth(Teraphobia.Config.ender_dragon_max_health);
        }

        if (SpawningHandler.shouldBeRemoved(event.getEntity().getLevel(), event.getEntity())) {
            event.setCanceled(true);
        }
    }
}