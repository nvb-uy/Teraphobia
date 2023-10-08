package elocindev.teraphobia.forge.mixin.integrations.aether;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.aetherteam.aether.world.AetherLevelData;

import elocindev.teraphobia.forge.api.TeraphobiaAPI;
import net.minecraft.server.level.ServerLevel;

@Mixin(ServerLevel.class)
public class InfectedEnviromentMixin {
    @Inject(method = "tickTime", at = @At("HEAD"), remap = false, cancellable = true)
    private void teraphobia_infectAether(CallbackInfo info) {
        ServerLevel inst = (ServerLevel)(Object)this;

        if (inst.getGameTime() % 20 == 0 && inst.dimension().equals(AetherDimensions.AETHER_LEVEL) && TeraphobiaAPI.isAetherInfected(inst)) {
            ((AetherLevelData)inst.getLevelData()).setDayTime(39000);
        }
    }
}
