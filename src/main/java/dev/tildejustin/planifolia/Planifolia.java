package dev.tildejustin.planifolia;

import me.modmuss50.optifabric.mod.*;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.spongepowered.asm.mixin.Mixins;

public class Planifolia implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        if (!FabricLoader.getInstance().isModLoaded("optifabric")) {
            System.err.println("OptiFabric is not present");
            return;
        }

        if (Optifabric.hasError()) {
            System.err.println("OptiFabric has an error");
            return;
        }

        if (!"OptiFine_1.8.9_HD_U_M5".equals(OptifineVersion.version)) {
            System.err.println("wrong OptiFine version");
            return;
        }

        Mixins.addConfiguration("planifolia.optifine.mixins.json");
    }
}
