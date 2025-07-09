package dev.tildejustin.planifolia.mixin;

import net.minecraft.network.Packet;
import net.minecraft.server.PlayerWorldManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.world.chunk.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.*;

@Mixin(PlayerWorldManager.class)
public abstract class PlayerWorldManagerMixin {
    @Dynamic
    @Redirect(method = "method_2109", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/ServerChunkProvider;getOrGenerateChunk(II)Lnet/minecraft/world/chunk/Chunk;"))
    private Chunk NO(ServerChunkProvider provider, int x, int z) {
        return null;
    }

    @Dynamic
    @ModifyArg(method = "method_2109", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/packet/s2c/play/ChunkMapS2CPacket;<init>(Ljava/util/List;)V"))
    private List<?> Ihateoptifine(List<?> a) {
        return Collections.emptyList();
    }

    @Dynamic
    @Redirect(method = "method_2109", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V"))
    private void STOP(ServerPlayNetworkHandler instance, Packet packet) {
    }
}
