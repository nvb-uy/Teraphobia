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
  public boolean always_communicate = true;
  public List<String> aether_sins = new ArrayList<>();
  public float aether_infected_ghast_weight = 0.5F;
  public boolean sinful_totem_require_full = true;
  public boolean herobrine_spawn_only_in_aether = true;
  public boolean herobrine_spawn_only_if_not_purged = true;
  public float herobrine_max_health = 1200.0f;
  public float herobrine_armor = 6.0f;
  public int herobrine_minion_spawn_rate = 200;
  public float herobrine_minion_spawn_chance = 0.3f;
  public float herobrine_lightning_chance = 0.4f;
}