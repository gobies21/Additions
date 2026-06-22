package net.gobies.additions.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.events.ForgeEvents;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> {

    @Inject(
            method = "render*",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;scale(Lnet/minecraft/world/entity/LivingEntity;Lcom/mojang/blaze3d/vertex/PoseStack;F)V",
                    shift = At.Shift.AFTER
            )
    )
    protected void additions$scaleModelPerfectPivot(T entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, CallbackInfo ci) {
        if (!CommonConfig.ENABLE_ENTITY_MODULE.get() && !CommonConfig.ENABLE_MOB_SIZES.get()) return;
        CompoundTag persistentData = entity.getPersistentData();
        if (!persistentData.contains(ForgeEvents.SCALE)) return;

        float scale = persistentData.getFloat(ForgeEvents.SCALE);
        if (scale == 1.0F || scale == 0.0F) {
            return;
        }
        poseStack.scale(scale, scale, scale);
    }
}