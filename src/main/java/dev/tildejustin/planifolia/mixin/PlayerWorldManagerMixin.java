package dev.tildejustin.planifolia.mixin;

import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.ChunkMapS2CPacket;
import net.minecraft.server.PlayerWorldManager;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.world.chunk.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.List;

// remove the spawn chunk resending OptiFine does for some inexplicable reason
@Mixin(PlayerWorldManager.class)
public abstract class PlayerWorldManagerMixin {
    @Unique
    private final ChunkMapS2CPacket packet = new ChunkMapS2CPacket();

    @Dynamic
    @Redirect(method = "method_2109", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/ServerChunkProvider;getOrGenerateChunk(II)Lnet/minecraft/world/chunk/Chunk;"))
    private Chunk stopForcedChunkGeneration(ServerChunkProvider provider, int x, int z) {
        return null;
    }

    @Dynamic
    @Redirect(method = "method_2109", at = @At(value = "NEW", target = "(Ljava/util/List;)Lnet/minecraft/network/packet/s2c/play/ChunkMapS2CPacket;"))
    private ChunkMapS2CPacket useDummyPacket(List<?> list) {
        return packet;
    }

    @Dynamic
    @Redirect(method = "method_2109", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V"))
    private void stopPacketSend(ServerPlayNetworkHandler instance, Packet packet) {
    }
}
