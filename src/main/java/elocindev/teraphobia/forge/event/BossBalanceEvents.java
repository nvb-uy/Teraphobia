package elocindev.teraphobia.forge.event;

import com.lgow.endofherobrine.entity.herobrine.boss.HerobrineBoss;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BossBalanceEvents {
    @SubscribeEvent
    public static void fallbackSpawnEvent(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;

        if (event.getEntity() instanceof EnderDragon dragon) {
            dragon.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Teraphobia.Config.ender_dragon_max_health);
            dragon.setHealth(Teraphobia.Config.ender_dragon_max_health);
            return;
        } else if (event.getEntity() instanceof HerobrineBoss herobrine) {
            herobrine.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Teraphobia.Config.herobrine_max_health);
            herobrine.getAttribute(Attributes.ARMOR).setBaseValue(Teraphobia.Config.herobrine_armor);
            
            return;
        }


    }
}
