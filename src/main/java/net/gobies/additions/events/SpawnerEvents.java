package net.gobies.additions.events;

import net.gobies.additions.Additions;
import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.network.SpawnerSyncPackets;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID)
public class SpawnerEvents {

    public static final String SPAWNER_X = "SpawnerX";
    public static final String SPAWNER_Y = "SpawnerY";
    public static final String SPAWNER_Z = "SpawnerZ";
    public static final String CURRENT_COUNT = "CurrentCount";
    public static final String SPAWN_INSTANCES = "SpawnInstances";
    public static final String SPAWNER_EXTRA_XP = "SpawnerExtraXP";

    @SubscribeEvent
    public static void onFinalizeSpawn(MobSpawnEvent.FinalizeSpawn event) {
        if (!CommonConfig.ENABLE_SPAWNER_MODULE.get() || event.getSpawner() == null || event.getLevel().isClientSide()) return;

        if (event.getSpawner().getSpawnerBlockEntity() instanceof SpawnerBlockEntity spawner) {
            ServerLevel serverLevel = (ServerLevel) event.getLevel();
            BlockPos pos = spawner.getBlockPos();
            CompoundTag spawnerTag = spawner.getPersistentData();

            int spawnInstances = spawnerTag.getInt(SPAWN_INSTANCES);
            if (spawnInstances >= CommonConfig.MAX_SPAWNER_KILLS.get()) {
                serverLevel.destroyBlock(pos, true);
                event.setSpawnCancelled(true);
                return;
            }

            CompoundTag mobTag = event.getEntity().getPersistentData();
            CompoundTag mobDelta = new CompoundTag();
            mobDelta.putInt(SPAWNER_X, pos.getX());
            mobDelta.putInt(SPAWNER_Y, pos.getY());
            mobDelta.putInt(SPAWNER_Z, pos.getZ());

            int threshold = CommonConfig.SPAWNER_KILL_THRESHOLD.get();
            if (threshold > 0 && spawnInstances >= threshold) {
                int thresholdTier = spawnInstances / threshold;
                mobDelta.putInt(SPAWNER_EXTRA_XP, thresholdTier * CommonConfig.SPAWNER_BONUS_EXPERIENCE.get());
            }
            mobTag.merge(mobDelta);
            PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(event::getEntity), new SpawnerSyncPackets.SyncMobNBTDataPacket(event.getEntity().getId(), mobDelta));

            int nextCount = spawnerTag.getInt(CURRENT_COUNT) + 1;
            spawnerTag.putInt(CURRENT_COUNT, nextCount);

            CompoundTag spawnerDelta = new CompoundTag();
            spawnerDelta.putInt(CURRENT_COUNT, nextCount);

            PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> serverLevel.getChunkAt(pos)), new SpawnerSyncPackets.SyncSpawnerNBTDataPacket(pos, spawnerDelta));
            spawner.setChanged();
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (!CommonConfig.ENABLE_SPAWNER_MODULE.get()) return;

        LivingEntity victim = event.getEntity();
        if (victim.level().isClientSide() || !(victim instanceof Mob mob)) return;

        CompoundTag tag = mob.getPersistentData();
        if (!tag.contains(SPAWNER_X)) return;

        BlockPos spawnerPos = new BlockPos(tag.getInt(SPAWNER_X), tag.getInt(SPAWNER_Y), tag.getInt(SPAWNER_Z));
        ServerLevel serverLevel = (ServerLevel) mob.level();

        if (!serverLevel.isPositionEntityTicking(spawnerPos)) return;

        if (serverLevel.getChunk(spawnerPos).getBlockEntity(spawnerPos) instanceof SpawnerBlockEntity spawner) {
            CompoundTag spawnerTag = spawner.getPersistentData();
            int spawnInstances = spawnerTag.getInt(SPAWN_INSTANCES) + 1;
            spawnerTag.putInt(SPAWN_INSTANCES, spawnInstances);

            if (spawnInstances >= CommonConfig.MAX_SPAWNER_KILLS.get()) {
                serverLevel.destroyBlock(spawnerPos, true);
                for (int i = 0; i < 3; i++) {
                    serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, spawnerPos.getX(), spawnerPos.getY() + 0.5D, spawnerPos.getZ(), 1));
                }
                return;
            }

            int currentCount = Math.max(0, spawnerTag.getInt(CURRENT_COUNT) - 1);
            spawnerTag.putInt(CURRENT_COUNT, currentCount);

            int threshold = CommonConfig.SPAWNER_KILL_THRESHOLD.get();
            if (threshold > 0 && spawnInstances >= threshold) {
                int bonusXp = CommonConfig.SPAWNER_BONUS_EXPERIENCE.get();
                if (bonusXp > 0) {
                    serverLevel.addFreshEntity(new ExperienceOrb(serverLevel, mob.getX(), mob.getY(), mob.getZ(), bonusXp));
                }
            }

            CompoundTag delta = new CompoundTag();
            delta.putInt(SPAWN_INSTANCES, spawnInstances);
            delta.putInt(CURRENT_COUNT, currentCount);

            PacketHandler.INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> serverLevel.getChunkAt(spawnerPos)), new SpawnerSyncPackets.SyncSpawnerNBTDataPacket(spawnerPos, delta));
            spawner.setChanged();
        }
    }
}