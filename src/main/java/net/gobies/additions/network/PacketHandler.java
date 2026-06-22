package net.gobies.additions.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    private static int packetId = 0;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("additions", "additions_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int nextId() {
        return packetId++;
    }

    public static void registerMessages() {
        packetId = 100;
        INSTANCE.messageBuilder(SoundSyncPacket.class, nextId()).encoder(SoundSyncPacket::toBytes).decoder(SoundSyncPacket::new).consumerMainThread(SoundSyncPacket::handle).add();
        INSTANCE.messageBuilder(ScaleSyncPacket.class, nextId()).encoder(ScaleSyncPacket::toBytes).decoder(ScaleSyncPacket::new).consumerMainThread(ScaleSyncPacket::handle).add();
        INSTANCE.registerMessage(nextId(), SpawnerSyncPackets.SyncMobNBTDataPacket.class, SpawnerSyncPackets.SyncMobNBTDataPacket::encode, SpawnerSyncPackets.SyncMobNBTDataPacket::decode, SpawnerSyncPackets.SyncMobNBTDataPacket::handle);
        INSTANCE.registerMessage(nextId(), SpawnerSyncPackets.SyncSpawnerNBTDataPacket.class, SpawnerSyncPackets.SyncSpawnerNBTDataPacket::encode, SpawnerSyncPackets.SyncSpawnerNBTDataPacket::decode, SpawnerSyncPackets.SyncSpawnerNBTDataPacket::handle);
        INSTANCE.registerMessage(nextId(), MobHPSyncPacket.class, MobHPSyncPacket::encode, MobHPSyncPacket::decode, MobHPSyncPacket::handle);
    }

    public static void sendToAllClients(Object packet) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), packet);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}