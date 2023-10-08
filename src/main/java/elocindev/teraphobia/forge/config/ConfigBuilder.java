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
            exampleConfig.nightonly_spawns_modwide.add("mutationcraft");
            exampleConfig.nightonly_spawns_modwide.add("kevin_trophy");
            exampleConfig.nightonly_spawns_modwide.add("skinandbonesremastered");
            exampleConfig.nightonly_spawns_modwide.add("born_in_chaos_v1");
            exampleConfig.nightonly_spawns_modwide.add("boh");
            exampleConfig.nightonly_spawns_modwide.add("mushys_fallen_monsters");

            exampleConfig.dayonly_spawns_modwide.add("some_mods_id");

            exampleConfig.mutationcraft_spawn_weight = 0.3F;
            exampleConfig.ryanzombies_spawn_weight = 0.5F;
            exampleConfig.skinandbones_spawn_weight = 0.7F;
            exampleConfig.borninchaos_spawn_weight = 0.6F;
            exampleConfig.theboxofhorrors_spawn_weight = 1.0F;
            exampleConfig.fallenmonsters_spawn_weight = 0.5F;

            exampleConfig.lunar_event_blacklist_entity.add("any_mod:example_entity");
            exampleConfig.lunar_event_blacklist_modwide.add("mutationcraft");
            exampleConfig.lunar_event_blacklist_modwide.add("kevin_trophy");
            exampleConfig.lunar_event_blacklist_modwide.add("skinandbonesremastered");
            exampleConfig.lunar_event_blacklist_modwide.add("born_in_chaos_v1");
            exampleConfig.lunar_event_blacklist_modwide.add("boh");
            exampleConfig.lunar_event_blacklist_modwide.add("mushys_fallen_monsters");

            exampleConfig.enable_creeper_cena = false;
            exampleConfig.ender_dragon_max_health = 600.0f;
            
            exampleConfig.overworld_only_spawns_modwide.add("mutationcraft");
            exampleConfig.overworld_only_spawns_modwide.add("kevin_trophy");
            exampleConfig.overworld_only_spawns_modwide.add("skinandbonesremastered");
            exampleConfig.overworld_only_spawns_modwide.add("born_in_chaos_v1");
            exampleConfig.overworld_only_spawns_modwide.add("boh");
            exampleConfig.overworld_only_spawns_modwide.add("mushys_fallen_monsters");

            exampleConfig.always_communicate = false;

            exampleConfig.aether_sins.add("sons_of_sins");

            exampleConfig.aether_infected_ghast_weight = 0.5F;

            exampleConfig.herobrine_spawn_only_in_aether = true;
            exampleConfig.herobrine_max_health = 1200.0f;
            exampleConfig.herobrine_armor = 6.0f;
            exampleConfig.herobrine_minion_spawn_rate = 200;
            exampleConfig.herobrine_minion_spawn_chance = 0.3f;

            String defaultJson = BUILDER.toJson(exampleConfig);
            Files.writeString(file, defaultJson);
        }

        return BUILDER.fromJson(Files.readString(file), ConfigEntries.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
