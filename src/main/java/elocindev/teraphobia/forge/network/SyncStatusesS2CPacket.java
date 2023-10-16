package elocindev.teraphobia.forge.network;

import java.util.function.Supplier;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SyncStatusesS2CPacket {
    private boolean isInfected;
    private boolean isAvailable;

    public SyncStatusesS2CPacket(boolean infected, boolean available) {
        this.isInfected = infected;
        this.isAvailable = available;
    }

    public static SyncStatusesS2CPacket fromBytes(FriendlyByteBuf buf) {
        boolean infected = buf.readBoolean();
        boolean available = buf.readBoolean();
        return new SyncStatusesS2CPacket(infected, available);
    }

    public static void toBytes(SyncStatusesS2CPacket packet, FriendlyByteBuf buf) {
        buf.writeBoolean(packet.isInfected);
        buf.writeBoolean(packet.isAvailable);
    }

    public static void handle(SyncStatusesS2CPacket packet, FriendlyByteBuf client) {
        boolean infection = packet.isInfected;
        boolean available = packet.isAvailable;

        Teraphobia.INFECTION_STATUS = infection;
        Teraphobia.AVAILABLE_STATUS = available;
    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            boolean status = this.isInfected;
            boolean available = this.isAvailable;

            Teraphobia.INFECTION_STATUS = status;
            Teraphobia.AVAILABLE_STATUS = available;
        });
        return true;
    }
}