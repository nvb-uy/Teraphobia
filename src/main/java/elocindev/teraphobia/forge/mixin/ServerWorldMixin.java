package elocindev.teraphobia.forge.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity.RemovalReason;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.teraphobia.forge.Teraphobia;

@Mixin(ServerLevel.class)
public class ServerWorldMixin { 
    @Inject(method = "addEntity", at = @At("HEAD"), remap = false, cancellable = true)
	private void blacklist(Entity p_8873_, CallbackInfoReturnable<Boolean> info) {
        String id = EntityType.getKey(p_8873_.getType()).toString();

        if (Teraphobia.Config.dayonly_spawns.contains(id) && !((ServerLevel)(Object)this).isDay()) {
            p_8873_.remove(RemovalReason.DISCARDED); 
            info.setReturnValue(false);
        } else if (Teraphobia.Config.nightonly_spawns.contains(id) && ((ServerLevel)(Object)this).isDay()) {
            p_8873_.remove(RemovalReason.DISCARDED); 
            info.setReturnValue(false);
        }

        if (id.startsWith("mutationcraft")) {
            float random = ((ServerLevel)(Object)this).random.nextFloat();

            if (random > Teraphobia.Config.mutationcraft_spawn_weight) {
                p_8873_.remove(RemovalReason.DISCARDED); 
                info.setReturnValue(false);
            }
        } else if (id.startsWith("kevin_trophy")) {
            float random = ((ServerLevel)(Object)this).random.nextFloat();

            if (random > Teraphobia.Config.ryanzombies_spawn_weight) {
                p_8873_.remove(RemovalReason.DISCARDED); 
                info.setReturnValue(false);
            }
        }

        
	}
}
