package elocindev.create_questing.forge;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import elocindev.create_questing.forge.config.ConfigEntries;
import elocindev.create_questing.forge.registry.ItemRegistry;
import elocindev.create_questing.forge.theme.ThemeSetup;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import elocindev.necronomicon.api.resource.v1.ResourceBuilderAPI;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateQuesting.MODID)
@Mod.EventBusSubscriber(modid = CreateQuesting.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateQuesting {
    public static final String MODID = "create_questing";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ConfigEntries Config;

    
    public CreateQuesting() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

        ItemRegistry.REGISTRY.register(bus);

        ResourceBuilderAPI.registerBuiltinPack(MODID, ModList.get().getModFileById(MODID).getFile().findResource("quest_shapes"), 
                Component.literal("Create Styled Quest Shapes"), false, Component.literal("Adds create-like quest shapes"), PackType.CLIENT_RESOURCES, Position.TOP, false);
    }

    private void setup(final FMLCommonSetupEvent event) {
        NecConfigAPI.registerConfig(ConfigEntries.class);

        Config = ConfigEntries.INSTANCE;
        ThemeSetup.setup();

		LOGGER.info("Loaded Create Questing Config");
    }

    @SubscribeEvent
    public static void addPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {            
            
        }
    }
}
