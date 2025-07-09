package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.render.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @ModifyArg(method = "renderFog", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 1), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;isFogThick(II)Z")))
    private float fixNetherFog(float param) {
        return Math.min(param, 192.0F) * 0.5F;
    }

    @Dynamic
    @Redirect(method = "getFov", at = @At(value = "FIELD", target = "LConfig;zoomMode:Z", opcode = Opcodes.PUTSTATIC))
    private void keepZoomOff(boolean par2) {
    }
}
