package elocindev.teraphobia.forge.fallback;

import elocindev.teraphobia.forge.Teraphobia;
import elocindev.teraphobia.forge.network.SyncStatusesS2CPacket;
import elocindev.teraphobia.forge.registry.GameruleRegistry;
import elocindev.teraphobia.forge.registry.PacketRegistry;
import elocindev.teraphobia.forge.worldgen.SpawningHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
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
    
    @SubscribeEvent
    public static void debugEvent(LivingTickEvent event) {
        if (!Teraphobia.Config.always_communicate
        || event.getEntity().tickCount % 20 != 0
        || event.getEntity().getLevel().isClientSide()) return;

        if (event.getEntity() instanceof Player player) {
            PacketRegistry.sendToPlayer(new SyncStatusesS2CPacket(player.getLevel().getGameRules().getBoolean(GameruleRegistry.AETHERINFECTED)), (ServerPlayer) player);
        }
    }
}