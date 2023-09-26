package elocindev.create_questing.forge.mixin.integrations.quests;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import dev.ftb.mods.ftblibrary.icon.Color4I;
import dev.ftb.mods.ftblibrary.math.PixelBuffer;
import dev.ftb.mods.ftbquests.quest.QuestShape;
import elocindev.create_questing.forge.CreateQuesting;

@Mixin(QuestShape.class)
public class QuestShapeMixin {
    @Shadow(remap = false)
    private PixelBuffer shapePixels;

    @Shadow(remap = false)
    private static QuestShape defaultShape;

    private int colorModifier = CreateQuesting.Config.debug_colorModifier;

    @Inject(method = "getShapePixels", remap = false, at = @At("RETURN"))
    public void create_questing_colorAtShapePixels(CallbackInfoReturnable<PixelBuffer> info) {
        QuestShape instance = (QuestShape)(Object)this;

        if (shapePixels != null && instance.shape != null) {
            instance.shape.color = Color4I.rgb(colorModifier);
            instance.outline.color = Color4I.rgb(colorModifier);

            shapePixels.setRGB(0, 0, colorModifier);
        }
    }
    @Inject(method = "<init>", remap = false, at = @At("TAIL"))
    public void create_questing_colorAtConstructor(CallbackInfo info) {   
        QuestShape instance = (QuestShape)(Object)this;     
        instance.shape.color = Color4I.rgb(colorModifier);
        instance.outline.color = Color4I.rgb(colorModifier);
    }
}