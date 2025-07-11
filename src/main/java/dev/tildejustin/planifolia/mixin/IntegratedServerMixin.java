package dev.tildejustin.planifolia.mixin;

import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.datafixer.DataFixerUpper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.UserCache;
import org.spongepowered.asm.mixin.*;

import java.io.File;
import java.net.Proxy;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin extends MinecraftServer {
    public IntegratedServerMixin(File file, Proxy proxy, DataFixerUpper dataFixerUpper, YggdrasilAuthenticationService yggdrasilAuthenticationService, MinecraftSessionService minecraftSessionService, GameProfileRepository gameProfileRepository, UserCache userCache) {
        super(file, proxy, dataFixerUpper, yggdrasilAuthenticationService, minecraftSessionService, gameProfileRepository, userCache);
    }

    /**
     * @author tildejustin
     * @reason Ban OptiFine
     */
    @Dynamic
    @Overwrite
    @Override
    // public in OptiFine, protected in vanilla
    public void saveWorlds(boolean dontLog) {
        super.saveWorlds(dontLog);
    }
}
