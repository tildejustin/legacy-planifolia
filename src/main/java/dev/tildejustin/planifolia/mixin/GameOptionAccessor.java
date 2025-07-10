package dev.tildejustin.planifolia.mixin;

import net.minecraft.client.option.GameOption;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameOption.class)
public interface GameOptionAccessor {
    @Dynamic
    @Accessor(value = "SMOOTH_FPS", remap = false)
    static GameOption getSmoothFPS() {
        return null;
    }

    @Dynamic
    @Accessor(value = "FAST_RENDER", remap = false)
    static GameOption getFastRender() {
        return null;
    }

    @Dynamic
    @Accessor(value = "CHUNK_UPDATES", remap = false)
    static GameOption getChunkUpdates() {
        return null;
    }

    @Dynamic
    @Accessor(value = "CHUNK_UPDATES_DYNAMIC", remap = false)
    static GameOption getChunkUpdatesDynamic() {
        return null;
    }

    @Dynamic
    @Accessor(value = "FOG_FANCY", remap = false)
    static GameOption getFogType() {
        return null;
    }
}
