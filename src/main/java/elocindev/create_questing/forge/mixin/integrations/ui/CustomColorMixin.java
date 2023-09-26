package elocindev.create_questing.forge.mixin.integrations.ui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import dev.ftb.mods.ftbquests.quest.QuestShape;
import elocindev.create_questing.forge.CreateQuesting;

@Mixin(QuestShape.class)
public class CustomColorMixin {
    @Inject(method = "draw", remap = false, at = @At("HEAD"))
    public void create_questing_changeShapeTexture(PoseStack matrixStack, int x, int y, int w, int h, CallbackInfo info) {   
        QuestShape instance = (QuestShape)(Object)this;     

        // this is some debug stuff

        CreateQuesting.LOGGER.info("Hello world!");
        CreateQuesting.LOGGER.info("DRAW Shape: "+instance.shape.texture.getNamespace()+ " : "+ instance.shape.texture.getPath());
    }
}
