package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    private float viewDistance;

    @ModifyArg(method = "renderFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;fogEnd(F)V"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;isFogThick(II)Z")))
    private float fixNetherFog(float original) {
        return Math.min(original, 192.0F) * 0.5F;
    }

    @Dynamic
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/optifine/Lagometer;showLagometer(Lnet/minecraft/class_389;)V", remap = false))
    private void disableLagMeter(Window window) {
    }

    @Dynamic
    @WrapOperation(method = "setupCamera", at = {@At(value = "INVOKE", target = "LConfig;isFogFancy()Z"), @At(value = "INVOKE", target = "LConfig;isFogFast()Z")})
    private boolean noFogCulling(Operation<Boolean> operation) {
        return false;
    }

    @ModifyArg(method = {"setupCamera", "renderWorld(IFJ)V", "renderClouds"}, at = @At(value = "INVOKE:LAST", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"), index = 3)
    private float correctClipDistance(float original) {
        return this.viewDistance * MathHelper.SQUARE_ROOT_OF_TWO;
    }

    @ModifyArg(method = {"renderHand", "renderWorld(IFJ)V"}, at = @At(value = "INVOKE:FIRST", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"), index = 3)
    private float correctClipDistance2(float original) {
        return this.viewDistance * 2;
    }

    @ModifyArg(method = {"renderClouds"}, at = @At(value = "INVOKE:FIRST", target = "Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"), index = 3)
    private float correctClipDistance3(float original) {
        return this.viewDistance * 4;
    }

    // @ModifyArg(method = "renderWorld", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glAlphaFunc(IF)V", ordinal = 0))
    // private float higherAlphaCutoff(float func) {
    //         return 0.5f;
    // }
}
