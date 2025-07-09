package dev.tildejustin.planifolia;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.jar.JarFile;

public class Main implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        Path modsDir = FabricLoader.getInstance().getGameDir().resolve("mods");
        try {
            for (File file : Objects.requireNonNull(modsDir.toFile().listFiles())) {
                if (file.getName().endsWith(".jar") && !file.isDirectory()) {
                    try (JarFile jarFile = new JarFile(file)) {
                        if (jarFile.getJarEntry("Config.class") != null || jarFile.getJarEntry("VersionThread.class") != null || jarFile.getJarEntry("net/optifine/Config.class") != null) {
                            Mixins.addConfiguration("planifolia.mixins.json");
                            return;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            System.out.println("Missing Optifine classes. Skip the planifolia mixins.");
        } catch (Exception e) {
            System.out.println("Failed to get Optifine classes. Skip the planifolia mixins.");
        }
    }
}
