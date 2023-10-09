package elocindev.teraphobia.forge.event;

import com.aetherteam.aether.data.resources.registries.AetherDimensions;
import com.github.elenterius.biomancy.init.ModBlocks;
import com.lgow.endofherobrine.block.TotemBlock;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class InteractionEvents {
    

    @SubscribeEvent
    public static void onInteract(PlayerInteractEvent.RightClickBlock event) {
        if (event.getItemStack().getItem().equals(Items.NETHER_STAR) && event.getLevel().getBlockState(event.getPos()).getBlock() instanceof TotemBlock) {
            if (Teraphobia.Config.herobrine_spawn_only_in_aether && !event.getLevel().dimension().equals(AetherDimensions.AETHER_LEVEL)) {
                event.getEntity().displayClientMessage(Component.literal("Herobrine cannot be summoned in this dimension."), true);
                event.setCanceled(true);
            }

            if (Teraphobia.Config.sinful_totem_require_full) {
                if (event.getLevel().getBlockState(event.getPos()).getBlock() instanceof TotemBlock) {
                    if (!(event.getLevel().getBlockState(event.getPos().below()).getBlock().equals(ModBlocks.TUBULAR_FLESH_BLOCK.get()))) {
                        event.getEntity().displayClientMessage(Component.literal("Requires a tubular flesh block below."), true);
                        event.setCanceled(true);
                    }
                    if (!(event.getLevel().getBlockState(event.getPos().above()).getBlock().equals(ModBlocks.FLESH_WALL.get()))) {
                        event.getEntity().displayClientMessage(Component.literal("Requires a flesh wall above."), true);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
