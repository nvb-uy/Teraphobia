package elocindev.teraphobia.forge.mixin.integrations.phantasm;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.teraphobia.forge.Teraphobia;
import net.mcreator.phantasm.world.features.StarFeature;

@Mixin(StarFeature.class)
public class StarFeatureMixin {
    @Inject(method = "place", at = @At("HEAD"), remap = false, cancellable = true)
    // Adds a config to remove the stars from the sky which aren't really a fit for a horror modpack
    private void teraphobia_disableStars(CallbackInfoReturnable<Boolean> info) {
        if (!Teraphobia.Config.phanstasm_enable_stars) {
            info.setReturnValue(false);
        }
    }
}