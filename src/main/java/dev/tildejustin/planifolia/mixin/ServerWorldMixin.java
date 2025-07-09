package dev.tildejustin.planifolia.mixin;

import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    // @ModifyExpressionValue(method = "tickBlocks", at = @At(value = "FIELD", target = "Lnet/minecraft/server/world/ServerWorld;field_4530:Ljava/util/Set;"))
    // private Set printList(Set original) {
    //     System.out.println(original);
    //     return original;
    // }
}
