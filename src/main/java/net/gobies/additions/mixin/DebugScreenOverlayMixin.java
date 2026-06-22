package net.gobies.additions.mixin;

import net.gobies.additions.config.CommonConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

    @Inject(
            method = "getGameInformation()Ljava/util/List;",
            at = @At("RETURN"),
            cancellable = true
    )
    private void filterCoordinates(CallbackInfoReturnable<List<String>> cir) {
        if (!CommonConfig.HIDE_COORDINATES.get()) return;

        List<String> debugInfo = cir.getReturnValue();
        if (debugInfo == null || debugInfo.isEmpty()) return;
        List<String> filteredInfo = new ArrayList<>();
        for (String line : debugInfo) {
            if (line.startsWith("XYZ:") ||
                    line.startsWith("Block:") ||
                    line.startsWith("Chunk:") ||
                    line.startsWith("Facing:") ||
                    line.startsWith("Biome:")) {
                continue;
            }
            filteredInfo.add(line);
        }

        cir.setReturnValue(filteredInfo);
    }

    @Redirect(
            method = "getSystemInformation()Ljava/util/List;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;showOnlyReducedInfo()Z"
            )
    )
    private boolean redirectShowOnlyReducedInfo(Minecraft minecraft) {
        return CommonConfig.HIDE_COORDINATES.get() || minecraft.showOnlyReducedInfo();
    }
}