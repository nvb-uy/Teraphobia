package elocindev.teraphobia.forge.spawn;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SpawningHandler {
    public static boolean shouldBeRemoved(Level world, Entity entity) {
        float random = world.random.nextFloat();
        String id = EntityType.getKey(entity.getType()).toString();
            
        if (Teraphobia.Config.removed_entities.contains(id)) { entity.remove(RemovalReason.DISCARDED); return true; }

        if (world.isDay()) {
            if (Teraphobia.Config.nightonly_spawns_entity.contains(id)) {
                entity.remove(RemovalReason.DISCARDED); 
                return true;
            } else {
                for (String modid : Teraphobia.Config.nightonly_spawns_modwide) {
                    if (id.startsWith(modid)) {
                        entity.remove(RemovalReason.DISCARDED); 
                        return true;
                    }
                }
            }
        } else {
            if (Teraphobia.Config.dayonly_spawns_entity.contains(id)) {
                entity.remove(RemovalReason.DISCARDED); 
                return true;
            } else {
                for (String modid : Teraphobia.Config.dayonly_spawns_modwide) {
                    if (id.startsWith(modid)) { 
                        return true;
                    }
                }
            }
        }

        if (checkSpawnWeights(id, random)) {
            entity.remove(RemovalReason.DISCARDED); 
            return true;  
        }

        return false;
    }

    private static boolean checkSpawnWeights(String id, float random) {
        return (id.startsWith("mutationcraft") && random > Teraphobia.Config.mutationcraft_spawn_weight) ||
               (id.startsWith("kevin_trophy") && random > Teraphobia.Config.ryanzombies_spawn_weight) ||
               (id.startsWith("skinandbonesremastered") && random > Teraphobia.Config.skinandbones_spawn_weight) ||
               (id.startsWith("born_in_chaos_v1") && random > Teraphobia.Config.borninchaos_spawn_weight) ||
               (id.startsWith("boh") && random > Teraphobia.Config.theboxofhorrors_spawn_weight) ||
               (id.startsWith("mushys_fallen_monsters") && random > Teraphobia.Config.fallenmonsters_spawn_weight);
    }
}
