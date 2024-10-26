package elocindev.teraphobia.forge.config;

import java.util.List;

import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import elocindev.necronomicon.config.NecConfig;

public class TeraphobiaConfig {
    @NecConfig
    public static TeraphobiaConfig INSTANCE;

    public static String getFile() {
        return NecConfigAPI.getFile("teraphobia.json5");
    }

    public class TimedSpawnHolder {
        public int minDay;
        public String entity;

        public TimedSpawnHolder(int minDay, String entity) {
            this.minDay = minDay;
            this.entity = entity;
        }
    }

    public List<String> night_only_spawn = List.of("boh:.*", "born_in_chaos_v1", "cnc:.*", "minecraft:zombie", "minecraft:skeleton");
    public List<TimedSpawnHolder> timed_spawns = List.of(
        new TimedSpawnHolder(3, "born_in_chaos_v1:sir_pumpkinghead"),
        new TimedSpawnHolder(10, "apollyon:the_damned")
    );
}
