package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.option.GameOption;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameOption.class)
public abstract class GameOptionMixin {
    @Dynamic
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 5))
    private static float fixFpsStep(float constant) {
        return 10;
    }

    @Dynamic
    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 0, ordinal = 0))
    private static float fixFpsLimit(float constant) {
        return 10;
    }
}
