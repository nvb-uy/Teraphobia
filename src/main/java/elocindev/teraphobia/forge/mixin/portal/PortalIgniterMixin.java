package elocindev.teraphobia.forge.mixin.portal;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.teraphobia.forge.registry.ItemRegistry;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.portal.PortalForcer;
import net.minecraft.world.phys.AABB;

@Mixin(PortalForcer.class)
public class PortalIgniterMixin {
    @Shadow 
    ServerLevel level;

    private boolean hasNearbyPlayerWithFlintAndSteel(BlockPos pos) {
        Entity dummy = null;
        List<Entity> nearbyEntities = level.getEntities(dummy, 
            new AABB(pos).inflate(12), 
            (Predicate<? super Entity>) entity -> entity instanceof Player);

        return nearbyEntities.stream()
            .map(entity -> (Player) entity)
            .anyMatch(player -> {
                ItemStack mainHandItem = player.getMainHandItem();
                return mainHandItem.getItem() == ItemRegistry.MARK_OF_CHAOS.get();
            });
    }

    @Inject(method = "canHostFrame", at = @At("HEAD"), cancellable = true)
    public void teraphobia$canHostFrame(BlockPos pos, BlockPos.MutableBlockPos p_77663_, Direction p_77664_, int p_77665_, CallbackInfoReturnable<Boolean> ci) {
        if (!hasNearbyPlayerWithFlintAndSteel(pos)) {
            ci.setReturnValue(false);
        }
    }

    @Inject(method = "createPortal", at = @At("HEAD"), cancellable = true)
    public void teraphobia$createPortal(BlockPos pos, Direction.Axis axis, CallbackInfoReturnable<Optional<BlockUtil.FoundRectangle>> ci) {
        if (!hasNearbyPlayerWithFlintAndSteel(pos)) {
            ci.setReturnValue(Optional.empty());
        }
    }

    @Inject(method = "canPortalReplaceBlock", at = @At("HEAD"), cancellable = true)
    private void canPortalReplaceBlock(BlockPos.MutableBlockPos pos, CallbackInfoReturnable<Boolean> ci) {
        if (!hasNearbyPlayerWithFlintAndSteel(pos)) {
            ci.setReturnValue(false);
        }
    }
}