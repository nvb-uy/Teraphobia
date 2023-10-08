package elocindev.teraphobia.forge.worldgen;

import java.util.List;
import java.util.Random;

import com.aetherteam.aether.entity.monster.Zephyr;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.Level;

public class SinsHandler {
    public static boolean handleAether(Entity entity, Level level) {
        if (entity instanceof Zephyr zephyr) {
            Ghast ghast = new Ghast(EntityType.GHAST, zephyr.getLevel());
            
            ghast.setPos(zephyr.getX(), zephyr.getY(), zephyr.getZ());
            zephyr.remove(RemovalReason.DISCARDED);
             if (new Random().nextDouble() < Teraphobia.Config.aether_infected_ghast_weight) level.addFreshEntity(ghast);
            
            return true;
        }
        
        return false;
    }

    public static Entity getRandomSin() {
        return getRandomSin(Teraphobia.Config.aether_sins);
    }

    public static Entity getRandomSin(List<String> entities) {
        // return a random sin from the list


        return null;
    }
}
