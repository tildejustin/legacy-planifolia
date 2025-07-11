package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameOptions.Option.class)
public abstract class GameOptions$OptionMixin {
    @Dynamic
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 5, ordinal = 0))
    private static float fixFpsStep(float constant) {
        return 10;
    }

    @Dynamic
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 0, ordinal = 0))
    private static float fixFpsLimit(float constant) {
        return 10;
    }
}
