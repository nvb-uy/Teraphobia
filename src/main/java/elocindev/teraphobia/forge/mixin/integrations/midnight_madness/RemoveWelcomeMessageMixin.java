package elocindev.teraphobia.forge.mixin.integrations.midnight_madness;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.mcreator.midnightmadness.procedures.WelcomingMSGProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.eventbus.api.Event;

@Mixin(WelcomingMSGProcedure.class)
public abstract class RemoveWelcomeMessageMixin {
    @Inject(method = "execute", at = @At("HEAD"), cancellable = true, remap = false)
    public void teraphobia$removeWelcomeMessage(@Nullable Event event, Entity entity, CallbackInfo ci) {
        ci.cancel();
    }
}