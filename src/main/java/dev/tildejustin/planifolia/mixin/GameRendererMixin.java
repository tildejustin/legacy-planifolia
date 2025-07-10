package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @ModifyArg(method = "renderFog", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;fogEnd(F)V"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;isFogThick(II)Z")))
    private float fixNetherFog(float original) {
        return Math.min(original, 192.0F) * 0.5F;
    }

    @Dynamic
    @Redirect(method = "getFov", at = @At(value = "FIELD", target = "LConfig;zoomMode:Z", opcode = Opcodes.PUTSTATIC, remap = false))
    private void keepZoomOff(boolean original) {
    }

    @Dynamic
    @Redirect(method = "method_9775", at = @At(value = "INVOKE", target = "LLagometer;showLagometer(Lnet/minecraft/class_389;)V", remap = false))
    private void disableLagMeter(Window window) {
    }
}
