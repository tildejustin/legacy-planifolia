package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(targets = "net.optifine.gui.GuiPerformanceSettingsOF", remap = false)
public class GuiPerformanceSettingsOFMixin extends Screen {
    @Shadow
    @SuppressWarnings("unused")
    private static final GameOptions.Option[] enumOptions = new GameOptions.Option[]{
            GameOptionAccessor.getSmoothFPS(),
            GameOptionAccessor.getFastRender(),
            GameOptionAccessor.getChunkUpdates(),
            GameOptionAccessor.getChunkUpdatesDynamic(),
            GameOptionAccessor.getRenderRegions(),
            GameOptionAccessor.getSmartAnimations(),
            GameOptionAccessor.getFogType()
    };

    @Dynamic
    @Redirect(method = "method_1025(IIF)V", at = @At(value = "INVOKE", target = "Lnet/optifine/gui/GuiPerformanceSettingsOF;method_1043()V"))
    public void redirectRenderBackground(@Coerce Object instance) {
        this.renderDirtBackground(0);
    }
}
