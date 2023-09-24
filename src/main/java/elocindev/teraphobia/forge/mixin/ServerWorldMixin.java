package elocindev.teraphobia.forge.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.teraphobia.forge.spawn.SpawningHandler;

@Mixin(ServerLevel.class)
public class ServerWorldMixin { 
    @Inject(method = "addEntity", at = @At("HEAD"), remap = false, cancellable = true)
	private void blacklist(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (SpawningHandler.shouldBeRemoved((ServerLevel)(Object)this, entity)) {
            entity.remove(RemovalReason.DISCARDED);
        }
    
	} 
}
