package elocindev.teraphobia.forge.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity.RemovalReason;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    // Thanks forge, your living tick event is useless and doesn't work
    @Inject(at = @At("HEAD"), method = "tick")
    public void teraphobia_removeCreepers(CallbackInfo info) {
        if (!Teraphobia.Config.enable_creeper_cena && ((LivingEntity)(Object)this).getDisplayName().getString().toLowerCase().endsWith("cena")) {
            ((LivingEntity)(Object)this).remove(RemovalReason.DISCARDED);
        }
    }
}
