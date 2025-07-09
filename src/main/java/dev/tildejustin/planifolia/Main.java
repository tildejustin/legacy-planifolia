package dev.tildejustin.planifolia;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.spongepowered.asm.mixin.Mixins;

public class Main implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        Mixins.addConfiguration("planifolia.mixins.json");
    }
}
