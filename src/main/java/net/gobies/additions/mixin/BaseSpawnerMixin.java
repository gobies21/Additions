package net.gobies.additions.mixin;

import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.events.SpawnerEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BaseSpawner.class, priority = 2000)
public abstract class BaseSpawnerMixin {

    @Shadow private int minSpawnDelay;
    @Shadow private int maxSpawnDelay;
    @Shadow private int spawnCount;

    @Inject(
            method = "serverTick",
            at = @At("HEAD")
    )
    private void setDelay(ServerLevel serverLevel, BlockPos pos, CallbackInfo ci) {
        BlockEntity blockEntity = serverLevel.getChunk(pos).getBlockEntity(pos);
        if (blockEntity instanceof SpawnerBlockEntity spawner) {
            CompoundTag tag = spawner.getPersistentData();

            if (tag.contains(SpawnerEvents.SPAWN_INSTANCES)) {
                int spawnInstances = tag.getInt(SpawnerEvents.SPAWN_INSTANCES);
                int threshold = CommonConfig.SPAWNER_KILL_THRESHOLD.get();

                if (threshold > 0 && spawnInstances >= threshold) {
                    int thresholdsReached = spawnInstances / threshold;
                    int speedReduction = thresholdsReached * CommonConfig.SPAWNER_SPEED_INCREASE.get();

                    this.spawnCount = 4 + thresholdsReached;
                    this.minSpawnDelay = Math.max(100, this.minSpawnDelay - speedReduction);
                    this.maxSpawnDelay = Math.max(200, this.maxSpawnDelay - speedReduction);
                }
            }
        }
    }
}