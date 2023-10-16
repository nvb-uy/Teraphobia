package elocindev.teraphobia.forge.event;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.lgow.endofherobrine.entity.herobrine.boss.HerobrineBoss;

import elocindev.teraphobia.forge.Teraphobia;
import elocindev.teraphobia.forge.api.TeraphobiaAPI;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class KillEvents {
    
    @SubscribeEvent
    public static void onHerobrineKill(LivingDeathEvent event) {
        if (event.getSource().getEntity() == null) return;

        if (event.getEntity() instanceof HerobrineBoss herobrine) {
            if (herobrine.getLevel() instanceof ServerLevel level && level.dimension().equals(AetherDimensions.AETHER_LEVEL) && TeraphobiaAPI.isAetherInfected(herobrine.getLevel())) {
                TeraphobiaAPI.setAetherInfected(level, false);

                Teraphobia.INFECTION_STATUS = false;
                TeraphobiaAPI.sendInfectionSyncPackets(level, Teraphobia.INFECTION_STATUS, Teraphobia.AVAILABLE_STATUS);

                LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(herobrine.level);
                lightningboltentity.moveTo(herobrine.getX(), herobrine.getY(), herobrine.getZ());

                herobrine.level.addFreshEntity(lightningboltentity);

                level.getPlayers(null).forEach(player -> {
                        player.sendSystemMessage(Component.literal("\u00A76The Aether has been purged from Herobrine's influence."));
                });
            }
        } else if (event.getEntity() instanceof EnderDragon dragon) {
            if (!TeraphobiaAPI.isAetherAccessible(dragon.getLevel())) {
                TeraphobiaAPI.setAetherAccessible(dragon.getLevel(), true);

                if (dragon.getLevel() instanceof ServerLevel level)
                    TeraphobiaAPI.sendInfectionSyncPackets(level, Teraphobia.INFECTION_STATUS, Teraphobia.AVAILABLE_STATUS);

                Teraphobia.AVAILABLE_STATUS = true;

                if (dragon.getLevel() instanceof ServerLevel level)
                    level.getPlayers(null).forEach(player -> {
                        player.sendSystemMessage(Component.literal("\u00A76The Aether's gates are no longer influenced by the Ender Dragon."));
                    });
            }
        }
    }
}
