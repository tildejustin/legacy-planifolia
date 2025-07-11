package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOption;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(targets = "GuiPerformanceSettingsOF", remap = false)
public class GuiPerformanceSettingsOFMixin extends Screen {
    @Shadow
    @SuppressWarnings("unused")
    private static final GameOption[] enumOptions = new GameOption[]{
            GameOptionAccessor.getSmoothFPS(),
            GameOptionAccessor.getFastRender(),
            GameOptionAccessor.getChunkUpdates(),
            GameOptionAccessor.getChunkUpdatesDynamic(),
            GameOptionAccessor.getFogType()
    };

    @Dynamic
    @Redirect(method = "method_1025(IIF)V", at = @At(value = "INVOKE", target = "LGuiPerformanceSettingsOF;method_1043()V"))
    public void redirectRenderBackground(@Coerce Object instance) {
        this.renderDirtBackground(0);
    }
}
