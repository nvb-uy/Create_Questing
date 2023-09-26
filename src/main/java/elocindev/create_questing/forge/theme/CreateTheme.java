package elocindev.create_questing.forge.theme;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.icon.ImageIcon;
import dev.ftb.mods.ftblibrary.icon.PartIcon;
import dev.ftb.mods.ftblibrary.ui.Theme;
import elocindev.create_questing.forge.CreateQuesting;
import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.booleans.BooleanStack;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("unused")
public class CreateTheme extends Theme {
    public static final ResourceLocation BLUEPRINT = new ResourceLocation(CreateQuesting.MODID, "textures/gui/background_squares.png");

    private static final ImageIcon TEXTURE_BEACON = (ImageIcon)Icon.getIcon("textures/gui/container/beacon.png");
    private static final ImageIcon TEXTURE_WIDGETS = (ImageIcon)Icon.getIcon("textures/gui/widgets.png");
    private static final ImageIcon TEXTURE_RECIPE_BOOK = (ImageIcon)Icon.getIcon("textures/gui/recipe_book.png");
    private static final ImageIcon TEXTURE_ENCHANTING_TABLE = (ImageIcon)Icon.getIcon("textures/gui/container/enchanting_table.png");
    public static final Icon GUI;
    private static final Icon GUI_MOUSE_OVER;
    private static final Icon BUTTON;
    private static final Icon BUTTON_MOUSE_OVER;
    private static final Icon BUTTON_DISABLED;
    private static final Icon WIDGET;
    private static final Icon WIDGET_MOUSE_OVER;
    private static final Icon WIDGET_DISABLED;
    private static final Icon SLOT;
    private static final Icon SLOT_MOUSE_OVER;
    private static final Icon SCROLL_BAR_BG;
    private static final Icon SCROLL_BAR_BG_DISABLED;
    private static final Icon TEXT_BOX;
    private static final Icon TAB_H_UNSELECTED;
    private static final Icon TAB_H_SELECTED;
    public static ImageIcon BACKGROUND_SQUARES = ((ImageIcon)Icon.getIcon(CreateQuesting.MODID + ":" + BLUEPRINT.getPath()));

    static {
      GUI = new PartIcon(TEXTURE_RECIPE_BOOK, 82, 208, 32, 32, 8);
      GUI_MOUSE_OVER = GUI.withTint(Color4I.rgb(11515610));
      BUTTON = new PartIcon(TEXTURE_WIDGETS, 0, 66, 200, 20, 4);
      BUTTON_MOUSE_OVER = new PartIcon(TEXTURE_WIDGETS, 0, 86, 200, 20, 4);
      BUTTON_DISABLED = new PartIcon(TEXTURE_WIDGETS, 0, 46, 200, 20, 4);
      WIDGET = new PartIcon(TEXTURE_BEACON, 0, 219, 22, 22, 6);
      WIDGET_MOUSE_OVER = new PartIcon(TEXTURE_BEACON, 66, 219, 22, 22, 6);
      WIDGET_DISABLED = new PartIcon(TEXTURE_BEACON, 44, 219, 22, 22, 6);
      SLOT = new PartIcon(TEXTURE_BEACON, 35, 136, 18, 18, 3);
      SLOT_MOUSE_OVER = SLOT.combineWith(Color4I.WHITE.withAlpha(33));
      SCROLL_BAR_BG = SLOT;
      SCROLL_BAR_BG_DISABLED = SCROLL_BAR_BG.withTint(Color4I.BLACK.withAlpha(100));
      TEXT_BOX = new PartIcon(TEXTURE_ENCHANTING_TABLE, 0, 185, 108, 19, 6);
      TAB_H_UNSELECTED = TEXTURE_RECIPE_BOOK.withUV(150.0F, 2.0F, 35.0F, 26.0F, 256.0F, 256.0F);
      TAB_H_SELECTED = TEXTURE_RECIPE_BOOK.withUV(188.0F, 2.0F, 35.0F, 26.0F, 256.0F, 256.0F);
   }
}
