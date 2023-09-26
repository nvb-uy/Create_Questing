package elocindev.create_questing.forge;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import elocindev.create_questing.forge.config.ConfigBuilder;
import elocindev.create_questing.forge.config.ConfigEntries;
import elocindev.create_questing.forge.theme.ThemeSetup;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateQuesting.MODID)
public class CreateQuesting {
    public static final String MODID = "create_questing";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ConfigEntries Config = ConfigBuilder.loadConfig();

    
    public CreateQuesting() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Config = ConfigBuilder.loadConfig();
        ThemeSetup.setup();

		LOGGER.info("Loaded Create Questing Config");
    }
}
