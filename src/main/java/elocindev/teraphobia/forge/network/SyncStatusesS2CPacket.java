package elocindev.teraphobia.forge.network;

import java.util.function.Supplier;

import elocindev.teraphobia.forge.client.TeraphobiaClientStates;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SyncStatusesS2CPacket {
    private boolean isInfected;

    public SyncStatusesS2CPacket(boolean infected) {
        this.isInfected = infected;
    }

    public static SyncStatusesS2CPacket fromBytes(FriendlyByteBuf buf) {
        boolean infected = buf.readBoolean();
        return new SyncStatusesS2CPacket(infected);
    }

    public static void toBytes(SyncStatusesS2CPacket packet, FriendlyByteBuf buf) {
        buf.writeBoolean(packet.isInfected);
    }

    public static void handle(SyncStatusesS2CPacket packet, FriendlyByteBuf client) {
        boolean status = packet.isInfected;

        TeraphobiaClientStates.INFECTION = status;
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            boolean status = this.isInfected;

            TeraphobiaClientStates.INFECTION = status;
        });
        return true;
    }
}