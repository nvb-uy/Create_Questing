package elocindev.create_questing.forge.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraftforge.fml.loading.FMLPaths;


public class ConfigBuilder {
    public static final Gson BUILDER = (new GsonBuilder()).setPrettyPrinting().create();
  
    public static final Path file = FMLPaths.GAMEDIR.get().toAbsolutePath().resolve("config").resolve("create_questing.json");
    
    public static ConfigEntries loadConfig() {
      try {
        if (Files.notExists(file)) {
            ConfigEntries exampleConfig = new ConfigEntries();

            exampleConfig.enable_theme_only_in_create_chapters = true;
            exampleConfig.create_chapter_names.add("create");
            exampleConfig.create_chapter_names.add("blueprint");
            exampleConfig.blueprint_background_scale = 0.5F;
            exampleConfig.blueprint_background_opacity = 1.0F;
            exampleConfig.blueprint_background_overlay_red = 255;
            exampleConfig.blueprint_background_overlay_green = 255;
            exampleConfig.blueprint_background_overlay_blue = 255;
            exampleConfig.debug_colorModifier = 0xFFFFFF;

            String defaultJson = BUILDER.toJson(exampleConfig);
            Files.writeString(file, defaultJson);
        }

        return BUILDER.fromJson(Files.readString(file), ConfigEntries.class);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
