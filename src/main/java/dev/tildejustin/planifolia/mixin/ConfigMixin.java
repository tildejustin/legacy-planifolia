package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Pseudo
@Mixin(targets = "Config", remap = false)
public abstract class ConfigMixin {
    @Shadow
    private static GameOptions gameSettings;

    @Dynamic
    @ModifyConstant(method = "isCloudsOff", constant = @Constant(intValue = 0, ordinal = 2))
    private static int deferToVanillaCloudToggle(int original) {
        return gameSettings.entityShadows ? 0 : 1;
    }
}
