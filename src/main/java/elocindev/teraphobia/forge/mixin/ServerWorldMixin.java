package elocindev.teraphobia.forge.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;

import elocindev.teraphobia.forge.api.TeraphobiaAPI;
import elocindev.teraphobia.forge.worldgen.SinsHandler;
import elocindev.teraphobia.forge.worldgen.SpawningHandler;

@Mixin(ServerLevel.class)
public class ServerWorldMixin { 
    @Inject(method = "addEntity", at = @At("HEAD"), remap = false, cancellable = true)
	private void teraphobia_blacklistEntities(Entity entity, CallbackInfoReturnable<Boolean> info) {
        if (entity.getLevel().dimension().equals(AetherDimensions.AETHER_LEVEL) && TeraphobiaAPI.isAetherInfected(entity.getLevel())) {
            SinsHandler.handleAether(entity, entity.getLevel());
            return;
        }
        
        if (SpawningHandler.shouldBeRemoved((ServerLevel)(Object)this, entity)) {
            entity.remove(RemovalReason.DISCARDED);
        }
    
	} 
}
