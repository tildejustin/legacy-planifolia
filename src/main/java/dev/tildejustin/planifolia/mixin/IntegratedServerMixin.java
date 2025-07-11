package dev.tildejustin.planifolia.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.*;

import java.io.File;
import java.net.Proxy;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin extends MinecraftServer {
    public IntegratedServerMixin(Proxy proxy, File file) {
        super(proxy, file);
    }

    /**
     * @author tildejustin
     * @reason Ban OptiFine
     */
    @Dynamic
    @Overwrite
    @Override
    public void saveWorlds(boolean dontLog) {
        super.saveWorlds(dontLog);
    }
}
