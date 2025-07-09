package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOption;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(targets = "GuiPerformanceSettingsOF", remap = false)
public class GuiPerformanceSettingsOFMixin extends Screen {
    private static GameOption[] enumOptions = new GameOption[]{
            getGameOption("SMOOTH_FPS"),
            getGameOption("FAST_RENDER"),
            getGameOption("CHUNK_UPDATES"),
            getGameOption("CHUNK_UPDATES_DYNAMIC"),
            getGameOption("FOG_FANCY")
    };

    @Dynamic
    @Redirect(method = "method_1025(IIF)V", at = @At(value = "INVOKE", target = "LGuiPerformanceSettingsOF;method_1043()V"))
    public void redirectRenderBackground(@Coerce Object instance) {
        this.renderDirtBackground(0);
    }

    @Unique
    private static GameOption getGameOption(String name) {
        for (GameOption option : GameOption.class.getEnumConstants()) {
            if (option.name().equals(name)) {
                return option;
            }
        }
        throw new EnumConstantNotPresentException(GameOption.class, name);
    }
}
