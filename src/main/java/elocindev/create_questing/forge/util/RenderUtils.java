package elocindev.create_questing.forge.util;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.ftb.mods.ftblibrary.icon.Icon;
import dev.ftb.mods.ftblibrary.icon.ImageIcon;
import dev.ftb.mods.ftbquests.quest.QuestShape;
import elocindev.create_questing.forge.CreateQuesting;

public class RenderUtils {
    public static void renderCreateQuestShapes(QuestShape shape, PoseStack matrixStack, int x, int y, int w, int h) {
        renderCreateQuestShapes(shape, getTextureForShape(shape), getTextureForOutline(shape), matrixStack, x, y, w, h);
    }

    public static void renderCreateQuestShapes(QuestShape shape, ImageIcon background, ImageIcon outline, PoseStack matrixStack, int x, int y, int w, int h) {        
        background.draw(matrixStack, x, y, w, h);
        outline.draw(matrixStack, x, y, w, h);
    }

    public static ImageIcon getTextureForShape(QuestShape shape) {
        return ((ImageIcon)Icon.getIcon(CreateQuesting.MODID + ":textures/shapes/"+shape.id+"/shape.png"));
    }

    public static ImageIcon getTextureForOutline(QuestShape shape) {
        return ((ImageIcon)Icon.getIcon(CreateQuesting.MODID + ":textures/shapes/"+shape.id+"/outline.png"));
    }
}
