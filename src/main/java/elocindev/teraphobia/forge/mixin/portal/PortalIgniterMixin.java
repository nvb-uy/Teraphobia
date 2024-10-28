package elocindev.teraphobia.forge.mixin.portal;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.portal.PortalForcer;

@Mixin(PortalForcer.class)
public class PortalIgniterMixin {
    @Shadow 
    ServerLevel level;

    @Inject(method = "canHostFrame", at = @At("HEAD"), cancellable = true)
    public void teraphobia$canHostFrame(BlockPos pos, BlockPos.MutableBlockPos p_77663_, Direction p_77664_, int p_77665_, CallbackInfoReturnable<Boolean> ci) {
        Entity dummy = null;
        
        List<Entity> nearbyEntities = level.getEntities(dummy, 
            new net.minecraft.world.phys.AABB(pos).inflate(12), 
            (Predicate<? super Entity>) entity -> entity instanceof Player);

        boolean hasFlintAndSteel = false;

        hasFlintAndSteel = nearbyEntities.stream()
            .map(entity -> (Player) entity)
            .anyMatch(player -> {
                ItemStack mainHandItem = player.getMainHandItem();
                return mainHandItem.getItem() == Items.FLINT_AND_STEEL;
            });
        
            Teraphobia.LOGGER.info(String.format("canHostFrame: Checking for Flint and Steel near position {}. Found {} players with Flint and Steel.", pos, hasFlintAndSteel ? "at least one" : "none"));

        if (!hasFlintAndSteel) {
            ci.setReturnValue(false);
        }
    }

    @Inject(method = "createPortal", at = @At("HEAD"), cancellable = true)
    public void teraphobia$createPortal(BlockPos pos, Direction.Axis axis, CallbackInfoReturnable<Optional<BlockUtil.FoundRectangle>> ci) {
        Entity dummy = null;
        
        List<Entity> nearbyEntities = level.getEntities(dummy, 
            new net.minecraft.world.phys.AABB(pos).inflate(12), 
            (Predicate<? super Entity>) entity -> entity instanceof Player);

        boolean hasFlintAndSteel = false;

        hasFlintAndSteel = nearbyEntities.stream()
            .map(entity -> (Player) entity)
            .anyMatch(player -> {
                ItemStack mainHandItem = player.getMainHandItem();
                return mainHandItem.getItem() == Items.FLINT_AND_STEEL;
            });
        
        Teraphobia.LOGGER.info(String.format("createPortal: Checking for Flint and Steel near position {}. Found {} players with Flint and Steel.", pos, hasFlintAndSteel ? "at least one" : "none"));

        if (!hasFlintAndSteel) {
            ci.setReturnValue(Optional.empty());
        }
    }
}