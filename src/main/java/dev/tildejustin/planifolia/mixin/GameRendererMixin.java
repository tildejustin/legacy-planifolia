package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @ModifyArg(method = "renderFog", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 1), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;isFogThick(II)Z")))
    private float fixNetherFog(float original) {
        return Math.min(original, 192.0F) * 0.5F;
    }

    @Dynamic
    @Redirect(method = "renderGui", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_524;showLagometer(JJ)V", remap = false))
    private void disableLagMeter(GameRenderer instance, long a, long b) {
    }
}
