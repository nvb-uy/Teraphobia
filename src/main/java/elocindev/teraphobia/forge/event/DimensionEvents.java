package elocindev.teraphobia.forge.event;

import elocindev.teraphobia.forge.network.SyncStatusesS2CPacket;
import elocindev.teraphobia.forge.registry.GameruleRegistry;
import elocindev.teraphobia.forge.registry.PacketRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DimensionEvents {
    @SubscribeEvent
    public static void sendInfectionPacketS2C(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player && !event.getEntity().getLevel().isClientSide()) 
            PacketRegistry.sendToPlayer(new SyncStatusesS2CPacket(player.getLevel().getGameRules().getBoolean(GameruleRegistry.AETHERINFECTED)), (ServerPlayer) player);
    }
}
