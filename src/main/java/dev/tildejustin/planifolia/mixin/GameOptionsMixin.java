package dev.tildejustin.planifolia.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.client.option.GameOptions;
import net.minecraft.util.math.MathHelper;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Shadow
    public int viewDistance;

    @Unique
    private final Integer maxRd = 16;


    @ModifyArg(method = "<init>(Lnet/minecraft/client/MinecraftClient;Ljava/io/File;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOption;method_6658(F)V"))
    private float decreaseMaxRd(float f) {
        return maxRd;
    }

    @Dynamic
    @Redirect(method = "<init>(Lnet/minecraft/client/MinecraftClient;Ljava/io/File;)V", at = @At(value = "INVOKE", target = "Lorg/apache/commons/lang3/ArrayUtils;add([Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", remap = false))
    private Object[] removeZoomHotkey(Object[] original, Object zoomKey) {
        return original;
    }

    @ModifyReturnValue(method = "getStringOption", at = @At(value = "RETURN", ordinal = 0), remap = false)
    private String fixChunkText(String original) {
        return original.substring(0, original.lastIndexOf(" ")) + " chunks";
    }

    @Dynamic
    @WrapOperation(method = "setOption(Lnet/minecraft/client/option/GameOption;I)V", at = @At(value = "FIELD", target = "Lnet/minecraft/class_347;ofFogType:I", opcode = Opcodes.PUTFIELD, remap = false))
    private void neverFogOff(GameOptions instance, int value, Operation<Void> operation) {
        operation.call(instance, value == 3 ? 1 : value);
    }

    @Dynamic
    @Shadow(remap = false)
    private int ofFogType, ofPreloadedChunks, ofClouds, ofTrees, ofGrass, ofDroppedItems, ofRain, ofWater, ofAnimatedWater, ofAnimatedLava, ofMipmapType,
            ofAutoSaveTicks, ofBetterGrass, ofConnectedTextures, ofVignette, ofChunkLoading, ofTime, ofAaLevel, ofDynamicLights, ofTranslucentBlocks;

    @Dynamic
    @Shadow(remap = false)
    private boolean ofLoadFar, ofSmoothWorld, ofAnimatedFire, ofAnimatedPortal, ofAnimatedRedstone, ofAnimatedExplosion, ofAnimatedFlame, ofAnimatedSmoke,
            ofVoidParticles, ofWaterParticles, ofPortalParticles, ofPotionParticles, ofDrippingWaterLava, ofAnimatedTerrain, ofAnimatedTextures, ofAnimatedItems,
            ofRainSplash, ofLagometer, ofShowFps, ofWeather, ofSky, ofStars, ofSunMoon, ofClearWater, ofDepthFog, ofProfiler, ofBetterSnow, ofSwampColors,
            ofSmoothBiomes, ofCustomFonts, ofCustomColors, ofCustomSky, ofShowCapes, ofLazyChunkLoading, ofDynamicFov, ofFastMath, ofRandomMobs, ofNaturalTextures;

    @Dynamic
    @Shadow(remap = false)
    private float ofFogStart, ofAoLevel, ofCloudsHeight;

    @Dynamic
    @Shadow(remap = false)
    private String ofFullscreenMode;

    @Dynamic
    @Inject(method = "loadOfOptions", at = @At("RETURN"), remap = false)
    private void fixIllegalOptions(CallbackInfo ci) {
        this.viewDistance = MathHelper.clamp(this.viewDistance, 2, maxRd);
        this.ofFogType = MathHelper.clamp(this.ofFogType, 1, 2);
        this.ofFogStart = 0.75f;
        this.ofMipmapType = 0;
        this.ofLoadFar = false;
        this.ofPreloadedChunks = 0;
        this.ofSmoothWorld = false;
        this.ofAoLevel = 1;
        this.ofClouds = 0;
        this.ofCloudsHeight = 0;
        this.ofTrees = 0;
        this.ofGrass = 0;
        this.ofDroppedItems = 0;
        this.ofRain = 0;
        this.ofWater = 0;
        this.ofAnimatedWater = 0;
        this.ofAnimatedLava = 0;
        this.ofAnimatedFire = true;
        this.ofAnimatedPortal = true;
        this.ofAnimatedRedstone = true;
        this.ofAnimatedExplosion = true;
        this.ofAnimatedFlame = true;
        this.ofAnimatedSmoke = true;
        this.ofVoidParticles = true;
        this.ofWaterParticles = true;
        this.ofPortalParticles = true;
        this.ofPotionParticles = true;
        this.ofDrippingWaterLava = true;
        this.ofAnimatedTerrain = true;
        this.ofAnimatedTextures = true;
        this.ofAnimatedItems = true;
        this.ofRainSplash = true;
        this.ofLagometer = false;
        this.ofShowFps = false;
        this.ofAutoSaveTicks = 900; // doesn't do anything
        this.ofBetterGrass = 3;
        this.ofConnectedTextures = 3;
        this.ofWeather = true;
        this.ofSky = true;
        this.ofStars = true;
        this.ofSunMoon = true;
        this.ofVignette = 0;
        this.ofChunkLoading = 0;
        this.ofTime = 0;
        this.ofClearWater = false;
        this.ofDepthFog = true;
        this.ofAaLevel = 0;
        this.ofProfiler = false;
        this.ofBetterSnow = false;
        this.ofSwampColors = true;
        this.ofRandomMobs = false;
        this.ofSmoothBiomes = true;
        this.ofCustomFonts = false;
        this.ofCustomColors = false;
        this.ofCustomSky = false;
        this.ofShowCapes = true;
        this.ofNaturalTextures = false;
        this.ofLazyChunkLoading = false;
        this.ofDynamicFov = false;
        this.ofDynamicLights = 3;
        this.ofFullscreenMode = "Default";
        this.ofFastMath = false;
        this.ofTranslucentBlocks = 2;
    }
}
