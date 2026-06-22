package net.gobies.additions.network;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnerSyncPackets {

        public record SyncMobNBTDataPacket(int entityId, CompoundTag data) {

        public static void encode(SyncMobNBTDataPacket packet, FriendlyByteBuf buf) {
                buf.writeInt(packet.entityId);
                buf.writeNbt(packet.data);
            }

            public static SyncMobNBTDataPacket decode(FriendlyByteBuf buf) {
                return new SyncMobNBTDataPacket(buf.readInt(), buf.readNbt());
            }

            public static void handle(SyncMobNBTDataPacket packet, Supplier<NetworkEvent.Context> context) {
                context.get().enqueueWork(() -> {
                    Minecraft client = Minecraft.getInstance();
                    if (client.level != null) {
                        Entity entity = client.level.getEntity(packet.entityId);
                        if (entity instanceof LivingEntity livingMob) {
                            livingMob.getPersistentData().merge(packet.data);
                        }
                    }
                });
                context.get().setPacketHandled(true);
            }
        }

        public record SyncSpawnerNBTDataPacket(BlockPos spawnerPos, CompoundTag data) {

        public static void encode(SyncSpawnerNBTDataPacket packet, FriendlyByteBuf buf) {
                buf.writeBlockPos(packet.spawnerPos);
                buf.writeNbt(packet.data);
            }

            public static SyncSpawnerNBTDataPacket decode(FriendlyByteBuf buf) {
                return new SyncSpawnerNBTDataPacket(buf.readBlockPos(), buf.readNbt());
            }

            public static void handle(SyncSpawnerNBTDataPacket packet, Supplier<NetworkEvent.Context> context) {
                context.get().enqueueWork(() -> {
                    Minecraft client = Minecraft.getInstance();
                    if (client.level != null) {
                        BlockEntity blockEntity = client.level.getBlockEntity(packet.spawnerPos);
                        if (blockEntity != null) {
                            blockEntity.getPersistentData().merge(packet.data);
                        }
                    }
                });
                context.get().setPacketHandled(true);
            }
        }
}