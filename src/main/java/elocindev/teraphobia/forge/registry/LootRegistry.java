package elocindev.teraphobia.forge.registry;

import net.fabricmc.fabric.api.loot.v2.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;

public class LootRegistry {
    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (BuiltInLootTables.ANCIENT_CITY.equals(id) || BuiltInLootTables.DESERT_PYRAMID.equals(id)) {
                var markOfChaos = ((FabricLootPoolBuilder) LootPool.lootPool()).with(LootItem.lootTableItem(ItemRegistry.MARK_OF_CHAOS_RECIPE.get()).build()).setRolls(BinomialDistributionGenerator.binomial(1, 0.15f));

                tableBuilder.withPool(markOfChaos);
            }
        });
    }
}
