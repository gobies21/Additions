package net.gobies.additions.mixin;

import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.events.ForgeEvents;
import net.gobies.additions.events.SpawnerEvents;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.network.SpawnerSyncPackets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraftforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(
            method = "discard",
            at = @At("HEAD")
    )
    private void onDiscard(CallbackInfo ci) {
        if ((Object) this instanceof LivingEntity mob) {
            CompoundTag tag = mob.getPersistentData();

            if (tag.contains(SpawnerEvents.SPAWNER_X)) {
                BlockPos spawnerPos = new BlockPos(tag.getInt(SpawnerEvents.SPAWNER_X), tag.getInt(SpawnerEvents.SPAWNER_Y), tag.getInt(SpawnerEvents.SPAWNER_Z));

                BlockEntity blockEntity = mob.level().getBlockEntity(spawnerPos);
                if (blockEntity instanceof SpawnerBlockEntity spawner) {
                    CompoundTag spawnerTag = spawner.getPersistentData();
                    CompoundTag delta = new CompoundTag();

                    int currentCount = spawnerTag.getInt(SpawnerEvents.CURRENT_COUNT);

                    int updatedCount = Math.max(0, currentCount - 1);
                    spawnerTag.putInt(SpawnerEvents.CURRENT_COUNT, updatedCount);
                    delta.putInt(SpawnerEvents.CURRENT_COUNT, updatedCount);

                    Level level = spawner.getLevel();
                    if (level != null && !level.isClientSide()) {
                        PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> level.getChunkAt(spawnerPos)), new SpawnerSyncPackets.SyncSpawnerNBTDataPacket(spawnerPos, delta));

                        spawner.setChanged();
                        level.sendBlockUpdated(spawnerPos, spawner.getBlockState(), spawner.getBlockState(), 3);
                    }
                }
            }
        }
    }

    @Shadow
    public abstract CompoundTag getPersistentData();

    @Inject(
            method = "getDimensions",
            at = @At("RETURN"),
            cancellable = true
    )
    private void modifyDimensions(Pose pose, CallbackInfoReturnable<EntityDimensions> cir) {
        if (!CommonConfig.ENABLE_ENTITY_MODULE.get() && !CommonConfig.ENABLE_MOB_SIZES.get()) return;
        CompoundTag persistentData = this.getPersistentData();
        if (!persistentData.contains(ForgeEvents.SCALE)) return;

        float scale = persistentData.getFloat(ForgeEvents.SCALE);
        if (scale == 1.0F) return;

        cir.setReturnValue(cir.getReturnValue().scale(scale));
    }

    @Shadow
    private float eyeHeight;

    @Inject(
            method = "refreshDimensions",
            at = @At("TAIL")
    )
    private void modifyEyeHeight(CallbackInfo ci) {
        if (!CommonConfig.ENABLE_ENTITY_MODULE.get() && !CommonConfig.ENABLE_MOB_SIZES.get()) return;
        CompoundTag persistentData = this.getPersistentData();
        if (!persistentData.contains(ForgeEvents.SCALE)) return;

        float scale = persistentData.getFloat(ForgeEvents.SCALE);
        if (scale == 1.0F || scale == 0.0F) {
            return;
        }

        this.eyeHeight *= scale;
    }
}