package elocindev.teraphobia.forge.worldgen;

import java.util.List;
import java.util.Random;

import com.aetherteam.aether.entity.monster.Swet;
import com.aetherteam.aether.entity.monster.Zephyr;
import com.aetherteam.aether.entity.passive.Aerwhale;
import com.aetherteam.aether.entity.passive.FlyingCow;
import com.aetherteam.aether.entity.passive.Phyg;
import com.aetherteam.aether.entity.passive.Sheepuff;
import com.lgow.endofherobrine.entity.EntityInit;

import elocindev.teraphobia.forge.Teraphobia;
import net.mcreator.sonsofsins.init.SonsOfSinsModEntities;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.level.Level;

public class SinsHandler {
    public static final EntityType<?>[] SINS = {
        SonsOfSinsModEntities.BLOODY_BUTCHER.get(),
        SonsOfSinsModEntities.BLOODY_DRIVER.get(),
        SonsOfSinsModEntities.PROWLER.get(),
        SonsOfSinsModEntities.WISTIVER.get()
    };

    public static void handleAether(Entity entity, Level level) {
        if (entity instanceof Zephyr zephyr) { replaceWithChance(zephyr, EntityType.GHAST, level, 0.5f); return; }
        if (entity instanceof Aerwhale whale) { whale.remove(RemovalReason.DISCARDED); return; }
        if (entity instanceof FlyingCow cow) { replaceWith(cow, EntityInit.POS_COW.get(), level); return; }
        if (entity instanceof Sheepuff sheep) { replaceWith(sheep, EntityInit.POS_SHEEP.get(), level); return; }
        if (entity instanceof Phyg phyg) { replaceWith(phyg, EntityInit.POS_PIG.get(), level); return; }
        if (entity instanceof Swet swet) { replaceWithChance(swet, getRandomFromList(SINS), level, 0.1f); return; }
    }

    public static Entity getRandomSin(Level level) {
        return getRandomFromList(Teraphobia.Config.aether_sins).create(level);
    }

    public static EntityType<?> getRandomFromList(EntityType<?>[] entities) { 
        return entities[new Random().nextInt(entities.length)];
    }

    @SuppressWarnings("deprecation")
    public static EntityType<?> getRandomFromList(List<String> entities) {
        int index = new Random().nextInt(entities.size());
        EntityType<?> enttype = Registry.ENTITY_TYPE.get(new ResourceLocation(entities.get(index)));
        
        return enttype;
    }

    private static void replaceWithChance(Entity target, EntityType<?> replaceWith, Level level, float chance) {
        if (!(new Random().nextDouble() < chance)) return;
        
        replaceWith(target, replaceWith, level);
    }

    private static void replaceWith(Entity target, EntityType<?> replaceWith, Level level) {
        var newEntity = replaceWith.create(level);
        
        newEntity.setPos(target.getX(), target.getY(), target.getZ());
        target.remove(RemovalReason.DISCARDED);
        level.addFreshEntity(newEntity);
    }
}
