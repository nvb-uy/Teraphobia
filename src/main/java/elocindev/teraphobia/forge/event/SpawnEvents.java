package elocindev.teraphobia.forge.event;

import elocindev.necronomicon.api.NecUtilsAPI;
import elocindev.teraphobia.forge.config.TeraphobiaConfig;
import elocindev.teraphobia.forge.config.TeraphobiaConfig.TimedSpawnHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SpawnEvents {
    public static void fallbackSpawnEvent(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getEntity() instanceof LivingEntity living && shouldDisable(living)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void fallbackFinalizeSpawnEvent(MobSpawnEvent.FinalizeSpawn event) {
        if (event.getLevel().isClientSide()) return;

        if (event.getEntity() instanceof LivingEntity && shouldDisable(event.getEntity())) {
            event.setSpawnCancelled(true);
        }
    }

    public static boolean shouldDisable(LivingEntity entity) {
        Level level = entity.level();
    
        String entityId = NecUtilsAPI.getEntityId(entity);
        

        if (level.isDay()) {
            for (String pattern : TeraphobiaConfig.INSTANCE.night_only_spawn) {
                if (entityId.matches(pattern)) {
                    return true;
                }
            }
        }
        
        long day = level.getDayTime() / 24000;
        for (TimedSpawnHolder holder : TeraphobiaConfig.INSTANCE.timed_spawns) {
            if (day < holder.minDay && entityId.equals(holder.entity)) {
                return true;
            }
        }
    
        return false;
    }
}