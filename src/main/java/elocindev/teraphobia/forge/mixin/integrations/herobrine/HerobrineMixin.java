package elocindev.teraphobia.forge.mixin.integrations.herobrine;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.lgow.endofherobrine.entity.EntityInit;
import com.lgow.endofherobrine.entity.herobrine.boss.HerobrineBoss;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.Items;
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
    
    @Shadow
    private double getHeadX(int p_82214_1_) { return 0; }

    @Shadow
    private double getHeadY(int p_82208_1_) { return 0; }

    @Shadow
    private double getHeadZ(int p_82213_1_) { return 0; }

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

        if (inst.tickCount % Teraphobia.Config.herobrine_minion_spawn_rate == 0 && inst.getMaxHealth() / 2 < inst.getHealth()) {
            if (new Random().nextFloat() < Teraphobia.Config.herobrine_minion_spawn_chance) {
                spawnSmallMinionGroup(inst.level, inst.getX(), inst.getY(), inst.getZ());
            }

        }

        bossEvent.setProgress(inst.getHealth() / inst.getMaxHealth());
    }

    @Inject(method = "performRangedAttack(Lnet/minecraft/world/entity/LivingEntity;F)V", at = @At(value = "HEAD"), cancellable = true)
    private void performRangedAttack(LivingEntity pTarget, float pDistanceFactor, CallbackInfo ci) {
        HerobrineBoss inst = (HerobrineBoss) (Object) this;

        if (new Random().nextDouble() < Teraphobia.Config.herobrine_lightning_chance) {
            LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(inst.level);
            lightningboltentity.moveTo(pTarget.getX(), pTarget.getY(), pTarget.getZ());

            pTarget.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false, false));
            
            if (pTarget instanceof Player player && player.isBlocking()) {
                player.getCooldowns().addCooldown(player.getUseItem().getItem(), 200);
                player.disableShield(true);
            }

            inst.level.addFreshEntity(lightningboltentity);

            ci.cancel();
        }
    }

    @Inject(method = "performRangedAttack(IDDDZ)V", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private void performRangedAttack(int pHead, double pX, double pY, double pZ, boolean pIsDangerous, CallbackInfo ci) {
        HerobrineBoss inst = (HerobrineBoss) (Object) this;

        if (!inst.isSilent()) {
            inst.level.levelEvent((Player)null, 1024, inst.blockPosition(), 0);
        }

        double d0 = getHeadX(pHead); double d1 = getHeadY(pHead);double d2 = getHeadZ(pHead);
        double d3 = pX - d0; double d4 = pY - d1; double d5 = pZ - d2;

        WitherSkull witherskull = new WitherSkull(inst.level, inst, d3, d4, d5);
        witherskull.setOwner(inst); witherskull.setDeltaMovement(witherskull.getDeltaMovement().multiply(2.0D, 2.0D, 2.0D));
        if (pIsDangerous) {
            witherskull.setDangerous(true);
        }

        witherskull.setPosRaw(d0, d1, d2);
        inst.level.addFreshEntity(witherskull);

        ci.cancel();
    }

    private static void spawnSmallMinionGroup(Level level, double x, double y, double z) {
        EntityType<?> minion = getRandomMinionType();

        for (int i = 0; i < 3; i++) {
            double xOff = new Random().nextDouble() * 8 - 4; double zOff = new Random().nextDouble() * 8 - 4;
            
            var entity = minion.create(level); entity.setPos(x + xOff, y, z + zOff);
            
            if (entity.getType() == EntityInit.POS_SKELETON.get() || EntityInit.POS_STRAY.get() == entity.getType()) {
                ((LivingEntity) entity).setItemInHand(InteractionHand.MAIN_HAND, Items.BOW.getDefaultInstance());
            }
            
            level.addFreshEntity(entity);
        }
    }

    private static EntityType<?> getRandomMinionType() {
        return MINIONS[new Random().nextInt(MINIONS.length)];
    }
}
