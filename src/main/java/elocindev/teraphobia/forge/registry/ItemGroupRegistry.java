package elocindev.teraphobia.forge.registry;

import elocindev.teraphobia.forge.Teraphobia;

import net.minecraft.world.item.ItemStack;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemGroupRegistry {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Teraphobia.MODID);

    public static final RegistryObject<CreativeModeTab> TERAPHOBIA_TAB = REGISTRY.register("teraphobia",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.CHAOS_ESSENCE.get()))
                    .title(Component.translatable("itemGroup.teraphobia.main"))
                    .displayItems((param, tab) -> {
                        tab.accept(ItemRegistry.CHAOS_ESSENCE.get());
                        tab.accept(ItemRegistry.MARK_OF_CHAOS_RECIPE.get());
                        tab.accept(ItemRegistry.MARK_OF_CHAOS.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        REGISTRY.register(eventBus);
    }
}
