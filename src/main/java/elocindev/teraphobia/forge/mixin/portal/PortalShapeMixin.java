package elocindev.teraphobia.forge.mixin.portal;

import java.util.List;
import java.util.function.Predicate;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.AABB;

@Mixin(PortalShape.class)
public class PortalShapeMixin {
    @Shadow 
    private LevelAccessor level;

    @Shadow
    private BlockPos bottomLeft;

    private boolean hasNearbyPlayerWithFlintAndSteel(BlockPos pos) {
        Entity dummy = null;
        List<Entity> nearbyEntities = level.getEntities(dummy, 
            new AABB(pos).inflate(12), 
            (Predicate<? super Entity>) entity -> entity instanceof Player);

        return nearbyEntities.stream()
            .map(entity -> (Player) entity)
            .anyMatch(player -> {
                ItemStack mainHandItem = player.getMainHandItem();

                boolean isValid = mainHandItem.getItem() == Items.FLINT_AND_STEEL;

                if (isValid && player instanceof ServerPlayer ply) {
                   ply.sendSystemMessage(Component.translatable("teraphobia.portal_unusable").setStyle(Style.EMPTY.withColor(ChatFormatting.RED)), true); 
                }

                return isValid;
            });
    }

    @Inject(method = "createPortalBlocks", at = @At("HEAD"), cancellable = true)
    public void teraphobia$createPortal(CallbackInfo ci) {
        if (bottomLeft != null && !hasNearbyPlayerWithFlintAndSteel(bottomLeft)) {
            ci.cancel();
        }
    }
}