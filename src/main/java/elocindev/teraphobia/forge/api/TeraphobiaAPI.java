package elocindev.teraphobia.forge.api;

import elocindev.teraphobia.forge.Teraphobia;
import elocindev.teraphobia.forge.network.SyncStatusesS2CPacket;
import elocindev.teraphobia.forge.registry.GameruleRegistry;
import elocindev.teraphobia.forge.registry.PacketRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class TeraphobiaAPI {
    
    public static boolean isAetherInfected(Level level) {
        return Teraphobia.INFECTION_STATUS;
    }

    public static boolean isAetherAccessible(Level level) {
        return Teraphobia.AVAILABLE_STATUS;
    }

    public static void setAetherInfected(Level level, boolean infected) {
        level.getGameRules().getRule(GameruleRegistry.AETHERINFECTED).set(infected, level.getServer());
    }

    public static void setAetherAccessible(Level level, boolean accessible) {
        level.getGameRules().getRule(GameruleRegistry.AETHERAVAILABLE).set(accessible, level.getServer());
    }

    public static void sendInfectionSyncPackets(ServerLevel level) {
        sendInfectionSyncPackets(level, Teraphobia.INFECTION_STATUS, Teraphobia.AVAILABLE_STATUS);
    }

    public static void sendInfectionSyncPackets(ServerLevel level, boolean infected, boolean available) {
        level.getPlayers(null).forEach(player -> {
            PacketRegistry.sendToPlayer(new SyncStatusesS2CPacket(infected, available), (ServerPlayer) player);
        });
    }
}
