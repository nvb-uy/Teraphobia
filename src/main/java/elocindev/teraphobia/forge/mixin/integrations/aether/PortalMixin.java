package elocindev.teraphobia.forge.mixin.integrations.aether;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.aetherteam.aether.block.portal.AetherPortalBlock;

import elocindev.teraphobia.forge.registry.GameruleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(AetherPortalBlock.class)
public class PortalMixin {
    @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    public void teraphobia_disableAether(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo info) {
        if (!level.isClientSide() && !level.getGameRules().getBoolean(GameruleRegistry.AETHERAVAILABLE)) {
            if (entity instanceof Player player) {
                player.displayClientMessage(Component.literal("\u00A7cAn unknown force is preventing you from going through."), true);
                if (!player.isCreative()) {
                    player.setSecondsOnFire(2);
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40, 0, false, false, false));
                }
            } 
            info.cancel();
        }
    }
}
