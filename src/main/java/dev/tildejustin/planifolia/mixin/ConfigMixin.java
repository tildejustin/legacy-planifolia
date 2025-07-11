package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(targets = "Config", remap = false)
public abstract class ConfigMixin {
    @Shadow
    private static GameOptions gameSettings;

    @Dynamic
    @ModifyExpressionValue(method = "isCloudsFancy", at = @At(value = "FIELD", target = "Lnet/minecraft/class_347;field_979:Z"))
    private static boolean fixCloudsFancy(boolean original) {
        return gameSettings.cloudMode == 2;
    }

    @Dynamic
    @ModifyConstant(method = "isCloudsOff", constant = @Constant(intValue = 0, ordinal = 2))
    private static int fixCloudsOff(int original) {
        return gameSettings.cloudMode == 0 ? 1 : 0;
    }
}
