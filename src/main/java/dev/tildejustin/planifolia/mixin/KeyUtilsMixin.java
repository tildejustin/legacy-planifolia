package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.*;

@Mixin(targets = "net.optifine.util.KeyUtils", remap = false)
public abstract class KeyUtilsMixin {
    /**
     * @author tildejustin
     * @reason who in their right mind thinks a method like this is acceptable?
     */
    @Dynamic
    @Overwrite
    public static void fixKeyConflicts(KeyBinding[] keys, KeyBinding[] keysPrio) {
    }
}
