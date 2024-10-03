package elocindev.teraphobia.forge.network;

import java.util.function.Supplier;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SyncStatusesS2CPacket {

    public SyncStatusesS2CPacket() {

    }

    public static SyncStatusesS2CPacket fromBytes(FriendlyByteBuf buf) {
        return new SyncStatusesS2CPacket();
    }

    public static void toBytes(SyncStatusesS2CPacket packet, FriendlyByteBuf buf) {

    }

    public static void handle(SyncStatusesS2CPacket packet, FriendlyByteBuf client) {

    }
    
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

        });
        return true;
    }
}