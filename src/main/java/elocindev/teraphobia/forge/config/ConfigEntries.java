package elocindev.teraphobia.forge.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigEntries {
  public List<String> removed_entities = new ArrayList<>();
  public List<String> dayonly_spawns_entity = new ArrayList<>();
  public List<String> nightonly_spawns_entity = new ArrayList<>();
  public List<String> dayonly_spawns_modwide = new ArrayList<>();
  public List<String> nightonly_spawns_modwide = new ArrayList<>();
  public float mutationcraft_spawn_weight = 0.3F;
  public float ryanzombies_spawn_weight = 0.4F;
  public float skinandbones_spawn_weight = 1.0F;
  public float borninchaos_spawn_weight = 0.4F;
  public float theboxofhorrors_spawn_weight = 1.0F;
  public float fallenmonsters_spawn_weight = 0.8F;
  public List<String> lunar_event_blacklist_entity = new ArrayList<>();
  public List<String> lunar_event_blacklist_modwide = new ArrayList<>();
  public boolean enable_creeper_cena = false;
  public float ender_dragon_max_health = 600.0f;
  public List<String> overworld_only_spawns_modwide = new ArrayList<>();
  public boolean always_communicate = false;
}