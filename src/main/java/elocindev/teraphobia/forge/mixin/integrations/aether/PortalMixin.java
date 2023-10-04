package elocindev.teraphobia.forge.mixin.integrations.aether;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.aetherteam.aether.block.portal.AetherPortalBlock;

import elocindev.teraphobia.forge.registry.GameruleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(AetherPortalBlock.class)
public class PortalMixin {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void teraphobia_disableAether(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo info) {
        if (!level.getGameRules().getBoolean(GameruleRegistry.AETHERAVAILABLE))
            info.cancel();
    }
}
