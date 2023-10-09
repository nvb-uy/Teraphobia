package elocindev.teraphobia.forge.event;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.lgow.endofherobrine.entity.herobrine.boss.HerobrineBoss;

import elocindev.teraphobia.forge.api.TeraphobiaAPI;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class KillEvents {
    
    @SubscribeEvent
    public static void onHerobrineKill(LivingDeathEvent event) {
        if (event.getSource().getEntity() != null && event.getEntity() instanceof HerobrineBoss herobrine) {
            if (herobrine.getLevel() instanceof ServerLevel level && level.dimension().equals(AetherDimensions.AETHER_LEVEL) && TeraphobiaAPI.isAetherInfected(herobrine.getLevel())) {
                TeraphobiaAPI.setAetherInfected(level, false);

                TeraphobiaAPI.sendInfectionSyncPackets(level, false);

                LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(herobrine.level);
                lightningboltentity.moveTo(herobrine.getX(), herobrine.getY(), herobrine.getZ());
                lightningboltentity.setVisualOnly(true);


                herobrine.level.addFreshEntity(lightningboltentity);

            }
        }
    }
}
