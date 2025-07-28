package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Dynamic
    @ModifyExpressionValue(method = "getEntitiesDebugString", at = @At(value = "INVOKE", target = "LConfig;getVersionDebug()Ljava/lang/String;", remap = false))
    private String simplifyOptiFineVersionText(String original) {
        return original.substring(original.indexOf("_", original.indexOf("_") + 1) + 1);
    }

    @Redirect(method = "renderSky", at = @At(value = "FIELD:LAST", target = "Lnet/minecraft/client/render/WorldRenderer;vbo:Z"))
    private boolean checkVBOs(WorldRenderer instance) {
        return false;
    }
}
