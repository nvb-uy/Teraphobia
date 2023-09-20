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

            exampleConfig.dayonly_spawns.add("any_mod:example_entity");
            exampleConfig.nightonly_spawns.add("any_mod:example_entity");

            exampleConfig.mutationcraft_spawn_weight = 1.0F;
            exampleConfig.ryanzombies_spawn_weight = 1.0F;

            String defaultJson = BUILDER.toJson(exampleConfig);
            Files.writeString(file, defaultJson);
        }

        return BUILDER.fromJson(Files.readString(file), ConfigEntries.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
