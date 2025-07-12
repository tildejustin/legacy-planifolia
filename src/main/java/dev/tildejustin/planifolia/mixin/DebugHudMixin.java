package dev.tildejustin.planifolia.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.Window;
import net.minecraft.util.MetricsData;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.List;

@Mixin(DebugHud.class)
public abstract class DebugHudMixin extends DrawableHelper {
    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    protected abstract int getMetricsLineColor(int value, int greenValue, int yellowValue, int redValue);

    @Shadow
    @Final
    private TextRenderer renderer;

    @Dynamic
    @Redirect(method = "getLeftText", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuffer;append(Ljava/lang/String;)Ljava/lang/StringBuffer;", remap = false))
    private StringBuffer removeOptiFineText(StringBuffer instance, String str) {
        return instance;
    }

    @Redirect(method = "getLeftText", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;", ordinal = 1, remap = false))
    private String removeAnimationsCount(StringBuilder instance) {
        return "";
    }

    @Dynamic
    @Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Ljava/util/List;set(ILjava/lang/Object;)Ljava/lang/Object;", remap = false))
    private Object stopListModification(List<?> instance, int idx, Object object) {
        return null;
    }

    @Dynamic
    @Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(ILjava/lang/Object;)V", remap = false))
    private void stopListAddition(List<?> instance, int idx, Object object) {
    }

    /**
     * @author tildejustin
     * @reason OptiFine just deletes this method, and for what
     */
    @Overwrite
    private void drawMetricsData() {
        GlStateManager.disableDepthTest();
        MetricsData metricsData = this.client.getMetricsData();
        int i = metricsData.getStartIndex();
        int j = metricsData.getCurrentIndex();
        long[] ls = metricsData.getSamples();
        Window window = new Window(this.client);
        int k = i;
        int l = 0;
        fill(0, window.getHeight() - 60, 240, window.getHeight(), -1873784752);

        while (k != j) {
            int m = metricsData.getFps(ls[k], 30);
            int n = this.getMetricsLineColor(MathHelper.clamp(m, 0, 60), 0, 30, 60);
            this.drawVerticalLine(l, window.getHeight(), window.getHeight() - m, n);
            l++;
            k = metricsData.wrapIndex(k + 1);
        }

        fill(1, window.getHeight() - 30 + 1, 14, window.getHeight() - 30 + 10, -1873784752);
        this.renderer.draw("60", 2, window.getHeight() - 30 + 2, 14737632);
        this.drawHorizontalLine(0, 239, window.getHeight() - 30, -1);
        fill(1, window.getHeight() - 60 + 1, 14, window.getHeight() - 60 + 10, -1873784752);
        this.renderer.draw("30", 2, window.getHeight() - 60 + 2, 14737632);
        this.drawHorizontalLine(0, 239, window.getHeight() - 60, -1);
        this.drawHorizontalLine(0, 239, window.getHeight() - 1, -1);
        this.drawVerticalLine(0, window.getHeight() - 60, window.getHeight(), -1);
        this.drawVerticalLine(239, window.getHeight() - 60, window.getHeight(), -1);
        if (this.client.options.maxFramerate <= 120) {
            this.drawHorizontalLine(0, 239, window.getHeight() - 60 + this.client.options.maxFramerate / 2, -16711681);
        }

        GlStateManager.enableDepthTest();
    }
}
