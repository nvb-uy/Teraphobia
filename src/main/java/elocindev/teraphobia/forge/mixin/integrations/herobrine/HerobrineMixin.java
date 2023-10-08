package elocindev.teraphobia.forge.mixin.integrations.herobrine;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.lgow.endofherobrine.entity.EntityInit;
import com.lgow.endofherobrine.entity.herobrine.boss.HerobrineBoss;

import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion.BlockInteraction;

@Mixin(HerobrineBoss.class)
public class HerobrineMixin {
    
    private static final EntityType<?>[] MINIONS = {
        EntityInit.POS_HUSK.get(),
        EntityInit.POS_ZOMBIE.get(),
        EntityInit.POS_SKELETON.get(),
        EntityInit.POS_STRAY.get(),
        EntityInit.POS_PIGMAN.get()
    };

    @Shadow
    private ServerBossEvent bossEvent;

    @Overwrite
    public void customServerAiStep() {
        HerobrineBoss inst = (HerobrineBoss) (Object) this;

        if (inst.getInvulnerableTicks() > 0) {
            int onSpawn = inst.getInvulnerableTicks() - 1;
            bossEvent.setProgress(1.0F - (float)onSpawn / 220.0F);
            if (onSpawn <= 0) {
                inst.level.explode(inst, inst.getX(), inst.getY(), inst.getZ(), 7.0F, BlockInteraction.DESTROY);
        
                if (!inst.isSilent()) {
                    inst.level.globalLevelEvent(1023, inst.blockPosition(), 0);
                }

                inst.setHealth(inst.getMaxHealth());
                spawnSmallMinionGroup(inst.level, inst.getX(), inst.getY(), inst.getZ());
            }

            inst.setInvulnerableTicks(onSpawn);

            if (inst.tickCount % 10 == 0) {
                inst.heal(10.0F);
            }
        }

        if (inst.tickCount % 200 == 0 && inst.getMaxHealth() / 2 < inst.getHealth()) {
            if (new Random().nextFloat() < 0.3f) {
                spawnSmallMinionGroup(inst.level, inst.getX(), inst.getY(), inst.getZ());
            }

        }

        bossEvent.setProgress(inst.getHealth() / inst.getMaxHealth());
    }

    private static void spawnSmallMinionGroup(Level level, double x, double y, double z) {
        EntityType<?> minion = getRandomMinionType();

        for (int i = 0; i < 3; i++) {
            double xOff = new Random().nextDouble() * 8 - 4; double zOff = new Random().nextDouble() * 8 - 4;
            
            var entity = minion.create(level); entity.setPos(x + xOff, y, z + zOff);
            level.addFreshEntity(entity);
        }
    }

    private static EntityType<?> getRandomMinionType() {
        return MINIONS[new Random().nextInt(MINIONS.length)];
    }
}
