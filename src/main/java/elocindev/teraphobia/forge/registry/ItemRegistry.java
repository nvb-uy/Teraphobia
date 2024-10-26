package elocindev.teraphobia.forge.registry;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Teraphobia.MODID);
    
    public static final RegistryObject<Item> CHAOS_ESSENCE = reg(new Item(
        new Item.Properties()
        .fireResistant()
        .rarity(Rarity.UNCOMMON)
        .stacksTo(8)), 
        "chaos_essence");

	private static RegistryObject<Item> reg(Item item, String id) {
		return REGISTRY.register(id, () -> item);
	}

    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
}
