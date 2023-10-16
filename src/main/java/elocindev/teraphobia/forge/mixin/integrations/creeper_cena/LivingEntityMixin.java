package elocindev.teraphobia.forge.mixin.integrations.creeper_cena;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.monster.Creeper;

@Mixin(Creeper.class)
public class LivingEntityMixin {
    // Thanks forge, your living tick event is useless and doesn't work
    @Inject(at = @At("HEAD"), method = "tick")
    // This removes some meme "Creeper cena" that plays the john cena song when exploding, funny, but not fit for Fear Nightfall
    public void teraphobia_removeCreeperCena(CallbackInfo info) {
        if (((LivingEntity)(Object)this) instanceof Creeper)
            if (!Teraphobia.Config.enable_creeper_cena && ((LivingEntity)(Object)this).getDisplayName().getString().toLowerCase().endsWith("cena")) {
                ((LivingEntity)(Object)this).remove(RemovalReason.DISCARDED);
            }
    }
}
