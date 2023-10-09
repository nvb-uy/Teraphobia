package elocindev.teraphobia.forge.resource;

import java.io.IOException;

import elocindev.teraphobia.forge.Teraphobia;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.resource.PathPackResources;

@SuppressWarnings("resource")
@Mod.EventBusSubscriber(modid = Teraphobia.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ResourceGen {
    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        try {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                var resourcePath = ModList.get().getModFileById(Teraphobia.MODID).getFile().findResource("fleshy_sinful_totem");
                var pack = new PathPackResources(ModList.get().getModFileById(Teraphobia.MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
                
                var metadataSection = pack.getMetadataSection(PackMetadataSection.SERIALIZER);
                
                if (metadataSection != null) {
                    event.addRepositorySource((packConsumer, packConstructor) ->
                            packConsumer.accept(packConstructor.create(
                                    "builtin/fleshy_sinful_totem", Component.literal("Fleshy Sinful Totem"), true,
                                    () -> pack, metadataSection, Pack.Position.TOP, PackSource.BUILT_IN, false)));
                }
            }
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
