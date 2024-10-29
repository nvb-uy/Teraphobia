package elocindev.teraphobia.forge.registry;

import elocindev.teraphobia.forge.Teraphobia;
import elocindev.teraphobia.forge.item.MarkOfChaos;
import elocindev.teraphobia.forge.item.MarkOfChaosRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Teraphobia.MODID);

    public static final RegistryObject<Item> CHAOS_ESSENCE = REGISTRY.register("chaos_essence",
        () -> new Item(new Item.Properties()
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .stacksTo(7))
    );

    public static final RegistryObject<Item> MARK_OF_CHAOS = REGISTRY.register("mark_of_chaos",
        () -> new MarkOfChaos(new Item.Properties()
            .fireResistant()
            .rarity(Rarity.UNCOMMON)
            .durability(300))
    );

    public static final RegistryObject<Item> MARK_OF_CHAOS_RECIPE = REGISTRY.register("mark_of_chaos_recipe",
        () -> new MarkOfChaosRecipe(new Item.Properties()
            .rarity(Rarity.UNCOMMON)
            .stacksTo(1))
    );

    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
}