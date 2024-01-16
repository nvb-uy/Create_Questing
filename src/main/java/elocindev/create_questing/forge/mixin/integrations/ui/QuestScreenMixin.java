package elocindev.create_questing.forge.mixin.integrations.ui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.GuiHelper;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftblibrary.ui.input.MouseButton;
import dev.ftb.mods.ftbquests.client.gui.quests.QuestScreen;
import dev.ftb.mods.ftbquests.quest.Chapter;
import elocindev.create_questing.forge.CreateQuesting;
import elocindev.create_questing.forge.config.ConfigEntries;
import elocindev.create_questing.forge.theme.CreateTheme;
import net.minecraft.client.gui.GuiGraphics;

@Mixin(QuestScreen.class)
public class QuestScreenMixin {
    @Shadow
    Chapter selectedChapter;

    @Shadow
    double scrollWidth;

    @Shadow
    double scrollHeight;

    @Shadow
    int prevMouseX;

    @Shadow
    int prevMouseY;

    @Shadow
    MouseButton grabbed;
    
    @Inject(method = "drawBackground", remap = false, at = @At("HEAD"), cancellable = true)
    public void create_questing_drawBlueprint(GuiGraphics graphics, Theme theme, int x, int y, int w, int h, CallbackInfo info) {        
        QuestScreen inst = (QuestScreen)(Object)this;
        ConfigEntries cfg = CreateQuesting.Config;
        boolean valid = false;

        if (selectedChapter == null) return;

        if (cfg.enable_theme_only_in_create_chapters) {
            for (String keyword : cfg.create_chapter_names) {
                if ((selectedChapter.getTitle().toString().toLowerCase().contains(keyword.toLowerCase()))) valid = true;
            }

            if (!valid) return;
        }
        
        int scale = (int)(cfg.blueprint_background_scale * 256);

        theme = new CreateTheme();

        int offsetX = (int)Math.ceil((double)w / scale);
        int offsetY = (int)Math.ceil((double)h / scale);

        for (int i = 0; i < offsetX; i++) {
            for (int j = 0; j < offsetY; j++) {
                CreateTheme.BACKGROUND_SQUARES.draw(graphics, x + i * scale, y + j * scale, scale, scale);
            }
        }

        if (grabbed != null) {
            int mx = inst.getMouseX();
            int my = inst.getMouseY();
            if (grabbed.isLeft()) {
                if (scrollWidth > (double)inst.questPanel.width) {
                    inst.questPanel.setScrollX(Math.max(Math.min(inst.questPanel.getScrollX() + (double)(prevMouseX - mx), scrollWidth - (double)inst.questPanel.width), 0.0D));
                } else {
                    inst.questPanel.setScrollX((scrollWidth - (double)inst.questPanel.width) / 2.0D);
                }

                if (scrollHeight > (double)inst.questPanel.height) {
                    inst.questPanel.setScrollY(Math.max(Math.min(inst.questPanel.getScrollY() + (double)(prevMouseY - my), scrollHeight - (double)inst.questPanel.height), 0.0D));
                } else {
                    inst.questPanel.setScrollY((scrollHeight - (double)inst.questPanel.height) / 2.0D);
                }

                prevMouseX = mx;
                prevMouseY = my;
            } else if (grabbed.isMiddle()) {
                int boxX = Math.min(prevMouseX, mx);
                int boxY = Math.min(prevMouseY, my);
                int boxW = Math.abs(mx - prevMouseX);
                int boxH = Math.abs(my - prevMouseY);
                GuiHelper.drawHollowRect(graphics, boxX, boxY, boxW, boxH, Color4I.DARK_GRAY, false);
                Color4I.DARK_GRAY.withAlpha(40).draw(graphics, boxX, boxY, boxW, boxH);
            }
        }

        info.cancel();
    }
}
