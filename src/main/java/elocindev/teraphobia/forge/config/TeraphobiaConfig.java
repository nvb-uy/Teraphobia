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

    public List<String> night_only_spawn = List.of("boh:.*", "cnc:.*", "minecraft:zombie", "minecraft:skeleton");
}
