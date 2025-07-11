package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameOptions.Option.class)
public interface GameOptionAccessor {
    @Dynamic
    @Accessor(value = "SMOOTH_FPS", remap = false)
    static GameOptions.Option getSmoothFPS() {
        return null;
    }

    @Dynamic
    @Accessor(value = "FAST_RENDER", remap = false)
    static GameOptions.Option getFastRender() {
        return null;
    }

    @Dynamic
    @Accessor(value = "CHUNK_UPDATES", remap = false)
    static GameOptions.Option getChunkUpdates() {
        return null;
    }

    @Dynamic
    @Accessor(value = "CHUNK_UPDATES_DYNAMIC", remap = false)
    static GameOptions.Option getChunkUpdatesDynamic() {
        return null;
    }

    @Dynamic
    @Accessor(value = "RENDER_REGIONS", remap = false)
    static GameOptions.Option getRenderRegions() {
        return null;
    }

    @Dynamic
    @Accessor(value = "SMART_ANIMATIONS", remap = false)
    static GameOptions.Option getSmartAnimations() {
        return null;
    }

    @Dynamic
    @Accessor(value = "FOG_FANCY", remap = false)
    static GameOptions.Option getFogType() {
        return null;
    }
}
