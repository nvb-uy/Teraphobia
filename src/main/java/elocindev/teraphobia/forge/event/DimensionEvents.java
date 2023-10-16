package elocindev.teraphobia.forge.event;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.aetherteam.aether.world.AetherLevelData;

import elocindev.teraphobia.forge.api.TeraphobiaAPI;
import elocindev.teraphobia.forge.network.SyncStatusesS2CPacket;
import elocindev.teraphobia.forge.registry.GameruleRegistry;
import elocindev.teraphobia.forge.registry.PacketRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DimensionEvents {
    
    @SubscribeEvent
    public static void sendInfectionPacketS2C(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player && !event.getEntity().getLevel().isClientSide()) 
            PacketRegistry.sendToPlayer(new SyncStatusesS2CPacket(player.getLevel().getGameRules().getBoolean(GameruleRegistry.AETHERINFECTED), player.getLevel().getGameRules().getBoolean(GameruleRegistry.AETHERINFECTED)), (ServerPlayer) player);
    }

    @SubscribeEvent
    public static void serverTick(ServerTickEvent event) {
        Level level = event.getServer().getLevel(AetherDimensions.AETHER_LEVEL);

        if (level != null && level.getDayTime() % 20 == 0) {
            if (TeraphobiaAPI.isAetherInfected(level)) {
                ((AetherLevelData)level.getLevelData()).setDayTime(39000);
            } else {
                ((AetherLevelData)level.getLevelData()).setDayTime(0);
            }
        }
    }
}
