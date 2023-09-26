package elocindev.create_questing.forge.theme;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import elocindev.create_questing.forge.CreateQuesting;
import elocindev.create_questing.forge.config.ConfigEntries;

public class ThemeSetup {
    public static void setup() {
        ConfigEntries ci = CreateQuesting.Config;
        CreateTheme.BACKGROUND_SQUARES = CreateTheme.BACKGROUND_SQUARES
            .withColor(Color4I.rgba(ci.blueprint_background_overlay_red, ci.blueprint_background_overlay_green, ci.blueprint_background_overlay_blue, (int)(ci.blueprint_background_opacity * 255)));
    }
}
