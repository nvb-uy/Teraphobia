package elocindev.teraphobia.forge.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraftforge.fml.loading.FMLPaths;


public class ConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();
  
    public static final Path file = FMLPaths.GAMEDIR.get().toAbsolutePath().resolve("config").resolve("teraphobia.json");
    
    public static ConfigEntries loadConfig() {
      try {
        if (Files.notExists(file)) {
            ConfigEntries exampleConfig = new ConfigEntries();

            exampleConfig.removed_entities.add("mutationcraft:hazmat_helicopter");
            
            exampleConfig.dayonly_spawns_entity.add("any_mod:example_entity");
            exampleConfig.nightonly_spawns_entity.add("any_mod:example_entity");
            exampleConfig.nightonly_spawns_modwide.add("some_mods_id");
            exampleConfig.dayonly_spawns_modwide.add("some_mods_id");

            exampleConfig.mutationcraft_spawn_weight = 1.0F;
            exampleConfig.ryanzombies_spawn_weight = 1.0F;
            exampleConfig.skinandbones_spawn_weight = 1.0F;
            exampleConfig.borninchaos_spawn_weight = 1.0F;
            exampleConfig.theboxofhorrors_spawn_weight = 1.0F;
            exampleConfig.fallenmonsters_spawn_weight = 1.0F;

            exampleConfig.lunar_event_blacklist_entity.add("any_mod:example_entity");
            exampleConfig.lunar_event_blacklist_modwide.add("mutationcraft");
            exampleConfig.lunar_event_blacklist_modwide.add("kevin_trophy");
            exampleConfig.lunar_event_blacklist_modwide.add("skinandbonesremastered");
            exampleConfig.lunar_event_blacklist_modwide.add("born_in_chaos_v1");
            exampleConfig.lunar_event_blacklist_modwide.add("boh");
            exampleConfig.lunar_event_blacklist_modwide.add("mushys_fallen_monsters");

            exampleConfig.enable_creeper_cena = false;

            String defaultJson = BUILDER.toJson(exampleConfig);
            Files.writeString(file, defaultJson);
        }

        return BUILDER.fromJson(Files.readString(file), ConfigEntries.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
