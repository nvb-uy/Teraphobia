package elocindev.teraphobia.forge.event;

import net.minecraftforge.event.level.SleepFinishedTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class SleepEvents {
    @SubscribeEvent
    public static void onSleep(SleepFinishedTimeEvent event) {
        event.setTimeAddition(event.getLevel().dayTime());
    }
}