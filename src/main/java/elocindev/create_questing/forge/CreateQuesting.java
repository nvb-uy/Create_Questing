package elocindev.create_questing.forge;

import java.io.IOException;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import elocindev.create_questing.forge.config.ConfigBuilder;
import elocindev.create_questing.forge.config.ConfigEntries;
import elocindev.create_questing.forge.registry.ItemRegistry;
import elocindev.create_questing.forge.theme.ThemeSetup;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.resource.PathPackResources;

@Mod(CreateQuesting.MODID)
@Mod.EventBusSubscriber(modid = CreateQuesting.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateQuesting {
    public static final String MODID = "create_questing";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ConfigEntries Config = ConfigBuilder.loadConfig();

    
    public CreateQuesting() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        ItemRegistry.REGISTRY.register(bus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Config = ConfigBuilder.loadConfig();
        ThemeSetup.setup();

		LOGGER.info("Loaded Create Questing Config");
    }

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        try {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                var resourcePath = ModList.get().getModFileById(MODID).getFile().findResource("quest_shapes");
                var pack = new PathPackResources(ModList.get().getModFileById(MODID).getFile().getFileName() + ":" + resourcePath, resourcePath);
                
                var metadataSection = pack.getMetadataSection(PackMetadataSection.SERIALIZER);
                
                if (metadataSection != null)
                {
                    event.addRepositorySource((packConsumer, packConstructor) ->
                            packConsumer.accept(packConstructor.create(
                                    "builtin/create_questing", Component.literal("Create Styled Quest Shapes"), false,
                                    () -> pack, metadataSection, Pack.Position.TOP, PackSource.BUILT_IN, false)));
                }
            }
        }
        catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
