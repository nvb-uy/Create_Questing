package elocindev.create_questing.forge.mixin.integrations.ui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.ui.GuiHelper;
import dev.ftb.mods.ftblibrary.ui.Theme;
import dev.ftb.mods.ftbquests.gui.quests.QuestScreen;
import elocindev.create_questing.forge.CreateQuesting;
import elocindev.create_questing.forge.config.ConfigEntries;
import elocindev.create_questing.forge.theme.CreateTheme;

@Mixin(QuestScreen.class)
public class QuestScreenMixin {
    @Shadow
    public void drawBackground(PoseStack matrixStack, Theme theme, int x, int y, int w, int h) {}

    @Inject(method = "drawBackground", remap = false, at = @At("HEAD"), cancellable = true)
    public void create_questing_drawBlueprint(PoseStack matrixStack, Theme theme, int x, int y, int w, int h, CallbackInfo info) {        
        QuestScreen inst = (QuestScreen)(Object)this;
        ConfigEntries cfg = CreateQuesting.Config;
        boolean valid = false;

        if (inst.selectedChapter == null) return;

        if (cfg.enable_theme_only_in_create_chapters) {
            for (String keyword : cfg.create_chapter_names) {
                if ((inst.selectedChapter.getTitle().toString().toLowerCase().contains(keyword.toLowerCase()))) valid = true;
            }

            if (!valid) return;
        }
        
        int scale = (int)(cfg.blueprint_background_scale * 256);

        theme = new CreateTheme();

        int offsetX = (int)Math.ceil((double)w / scale);
        int offsetY = (int)Math.ceil((double)h / scale);

        for (int i = 0; i < offsetX; i++) {
            for (int j = 0; j < offsetY; j++) {
                CreateTheme.BACKGROUND_SQUARES.draw(matrixStack, x + i * scale, y + j * scale, scale, scale);
            }
        }

        if (inst.grabbed != null) {
            int mx = inst.getMouseX();
            int my = inst.getMouseY();
            if (inst.grabbed.isLeft()) {
                if (inst.scrollWidth > (double)inst.questPanel.width) {
                    inst.questPanel.setScrollX(Math.max(Math.min(inst.questPanel.getScrollX() + (double)(inst.prevMouseX - mx), inst.scrollWidth - (double)inst.questPanel.width), 0.0D));
                } else {
                    inst.questPanel.setScrollX((inst.scrollWidth - (double)inst.questPanel.width) / 2.0D);
                }

                if (inst.scrollHeight > (double)inst.questPanel.height) {
                    inst.questPanel.setScrollY(Math.max(Math.min(inst.questPanel.getScrollY() + (double)(inst.prevMouseY - my), inst.scrollHeight - (double)inst.questPanel.height), 0.0D));
                } else {
                    inst.questPanel.setScrollY((inst.scrollHeight - (double)inst.questPanel.height) / 2.0D);
                }

                inst.prevMouseX = mx;
                inst.prevMouseY = my;
            } else if (inst.grabbed.isMiddle()) {
                int boxX = Math.min(inst.prevMouseX, mx);
                int boxY = Math.min(inst.prevMouseY, my);
                int boxW = Math.abs(mx - inst.prevMouseX);
                int boxH = Math.abs(my - inst.prevMouseY);
                GuiHelper.drawHollowRect(matrixStack, boxX, boxY, boxW, boxH, Color4I.DARK_GRAY, false);
                Color4I.DARK_GRAY.withAlpha(40).draw(matrixStack, boxX, boxY, boxW, boxH);
            }
        }

        info.cancel();
    }
}
