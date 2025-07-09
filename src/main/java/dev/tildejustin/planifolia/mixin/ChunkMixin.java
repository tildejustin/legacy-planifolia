package dev.tildejustin.planifolia.mixin;

import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.*;

@Mixin(Chunk.class)
public abstract class ChunkMixin {
    // @Shadow
    // public World world;
    //
    // @Shadow
    // public List<Entity>[] entities;
    //
    // @Shadow
    // @Final
    // public int chunkX;
    //
    // @Shadow
    // @Final
    // public int chunkZ;
    //
    // @Inject(method = "<init>(Lnet/minecraft/world/World;II)V", at = @At("TAIL"))
    // private void fix(World i, int j, int par3, CallbackInfo ci) {
    //     if (this.world.isClient && chunkX == 44 && chunkZ == 6) Thread.dumpStack();
    //     for (int var4 = 0; var4 < this.entities.length; var4++) {
    //         this.entities[var4] = new ArrayList() {
    //             @Override
    //             public boolean add(Object o) {
    //                 if (o instanceof ClientPlayerEntity) {
    //                     System.out.println("hi");
    //                 }
    //                 return super.add(o);
    //             }
    //
    //             @Override
    //             public boolean remove(Object o) {
    //                 if (o instanceof ClientPlayerEntity) {
    //                     Thread.dumpStack();
    //                 }
    //                 return super.remove(o);
    //             }
    //         };
    //     }
    // }
    //
    // @Inject(method = "addEntity", at = @At("TAIL"))
    // private void checkPlayerAdd(Entity par1, CallbackInfo ci) {
    //     if (par1 instanceof PlayerEntity && this.world.isClient) {
    //         System.out.println(par1.chunkX + ", " + par1.chunkY + ", " + par1.chunkZ);
    //         System.out.println(this.chunkX + ", " + this.chunkZ);
    //         Thread.dumpStack();
    //     }
    //     System.out.println(Arrays.toString(entities));
    // }
    //
    // @Inject(method = "removeEntity(Lnet/minecraft/entity/Entity;I)V", at = @At("TAIL"))
    // private void checkPlayerremove(Entity par1, int par2, CallbackInfo ci) {
    //     if (par1 instanceof ClientPlayerEntity) {
    //         System.out.println("removing the player?");
    //         Thread.dumpStack();
    //     }
    // }
    //
    // @Overwrite
    // public void getEntitiesInBox(Entity except, Box box, List<Entity> outList, EntityPredicate predicate) {
    //     if (except != null || predicate != null || !world.isClient) return;
    //     int minSection = MathHelper.floor((box.minY - 2.0) / 16.0);
    //     int maxSection = MathHelper.floor((box.maxY + 2.0) / 16.0);
    //     minSection = MathHelper.clamp(minSection, 0, this.entities.length - 1);
    //     maxSection = MathHelper.clamp(maxSection, 0, this.entities.length - 1);
    //
    //     System.out.println(Arrays.toString(entities));
    //     for (int i = minSection; i <= maxSection; i++) {
    //         List<Entity> sectionEntites = this.entities[i];
    //         System.out.println(this.chunkX + ", " + this.chunkZ);
    //
    //         for (Entity entity : sectionEntites) {
    //             if (entity instanceof PlayerEntity && this.world.isClient) {
    //                 System.out.println("a" + sectionEntites);
    //             }
    //             if (entity != except && entity.boundingBox.intersects(box) && (predicate == null || predicate.test(entity))) {
    //                 outList.add(entity);
    //             }
    //         }
    //     }
    // }
}
