package elocindev.teraphobia.forge.mixin.integrations.lunar_events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mrbysco.lunar.handler.LunarHandler;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;

@Mixin(LunarHandler.class)
public class LunarHandlerMixin {
    @Inject(method = "onLivingSpawn", at = @At("HEAD"), remap = false, cancellable = true)
    // This fixes some mcreator mods basically spawning 100 times when a lunar event is active, kinda weird
    public void teraphobia_fixmcreatormodsnotworkinglol(MobSpawnType mobSpawnType, LivingEntity livingEntity, CallbackInfo info) {
        String id = EntityType.getKey(livingEntity.getType()).toString();
        if (Teraphobia.Config.lunar_event_blacklist_entity.contains(id)) {
            info.cancel();
        }

        for (String modid : Teraphobia.Config.lunar_event_blacklist_modwide) {
            if (id.startsWith(modid)) {
                info.cancel();
            }
        }
    }
}
