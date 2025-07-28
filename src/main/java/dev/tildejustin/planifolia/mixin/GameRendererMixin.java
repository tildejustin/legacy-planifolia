package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    private float viewDistance;

    @Unique
    private static final Object object = new Object();

    @ModifyArg(method = "renderFog", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 1), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;isFogThick(II)Z")))
    private float fixNetherFog(float original) {
        return Math.min(original, 192.0F) * 0.5F;
    }

    @Dynamic
    @Coerce
    @ModifyExpressionValue(method = "getFov", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;", ordinal = 0))
    private Object keepZoomOff(Screen original) {
        return object;
    }

    @Dynamic
    @Redirect(method = "renderGui", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_524;showLagometer(JJ)V", remap = false))
    private void disableLagMeter(GameRenderer instance, long a, long b) {
    }

    @Dynamic
    @WrapOperation(method = "setupCamera", at = {@At(value = "INVOKE", target = "LConfig;isFogFancy()Z"), @At(value = "INVOKE", target = "LConfig;isFogFast()Z")})
    private boolean noFogCulling(Operation<Boolean> operation) {
        return false;
    }

    @ModifyArg(method = {"setupCamera", "renderHand"}, at = @At(value = "INVOKE", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"), index = 3)
    private float correctClipDistance(float original) {
        return this.viewDistance * 2;
    }

    // @ModifyArg(method = "renderWorld", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glAlphaFunc(IF)V", ordinal = 0))
    // private float higherAlphaCutoff(float func) {
    //         return 0.5f;
    // }
}
