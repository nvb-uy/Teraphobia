package elocindev.teraphobia.forge.mixin.end;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.dimension.end.EndDragonFight;

@Mixin(value = EndDragonFight.class, priority = 5000)
public class DisableDragonMixin {
    @Shadow private boolean dragonKilled;
    @Shadow private ServerLevel level;
    @Shadow private UUID dragonUUID;

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    public void tick(CallbackInfo info) {
        EndDragonFight endDragonFight = (EndDragonFight) (Object) this;

        if (!dragonKilled) {
            List<? extends EnderDragon> list = level.getDragons();
            
            if (!list.isEmpty()) {
                EnderDragon enderdragon = list.get(0);
                
                if (dragonUUID.equals(endDragonFight.getDragonUUID())) {
                    endDragonFight.setDragonKilled(enderdragon);
                }
            }
        }
    }
}

