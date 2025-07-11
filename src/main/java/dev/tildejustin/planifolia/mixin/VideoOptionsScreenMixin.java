package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.List;

@Mixin(VideoOptionsScreen.class)
public abstract class VideoOptionsScreenMixin extends Screen {
    @Shadow
    @SuppressWarnings("unused")
    private static final GameOptions.Option[] OPTIONS = new GameOptions.Option[]{
            GameOptions.Option.GRAPHICS,
            GameOptions.Option.RENDER_DISTANCE,
            GameOptions.Option.AMBIENT_OCCLUSION,
            GameOptions.Option.MAX_FPS,
            GameOptions.Option.ANAGLYPH,
            GameOptions.Option.VIEW_BOBBING,
            GameOptions.Option.GUI_SCALE,
            GameOptions.Option.BRIGHTNESS,
            GameOptions.Option.SHOW_CLOUDS,
            GameOptions.Option.PARTICLES,
            GameOptions.Option.USE_FULLSCREEN,
            GameOptions.Option.ENABLE_VSYNC,
            GameOptions.Option.MIPMAP_LEVELS,
            GameOptions.Option.BLOCK_ALTERNATIVES,
            GameOptions.Option.USE_VBO,
            GameOptions.Option.ENTITY_SHADOWS
    };

    @Dynamic
    @WrapWithCondition(
            method = "init", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", remap = false),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=of.options.quality"), to = @At(value = "CONSTANT", args = "stringValue=gui.done"))
    )
    private boolean stopOptiFineButtons(List<?> instance, Object e) {
        return false;
    }

    @Dynamic
    @ModifyArg(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/OptionButtonWidget;<init>(IIILjava/lang/String;)V", ordinal = 0), index = 0)
    private int changeId(int original) {
        return 212;
    }

    @Dynamic
    @ModifyConstant(method = "init", constant = @Constant(stringValue = "of.options.shaders"))
    private String changeName(String original) {
        return "of.options.performance";
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/VideoOptionsScreen;renderBackground()V"))
    public void renderDirtBackground(VideoOptionsScreen instance) {
        this.renderDirtBackground(0);
    }
}
