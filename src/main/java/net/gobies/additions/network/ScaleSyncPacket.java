package net.gobies.additions.network;

import net.gobies.additions.events.ForgeEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ScaleSyncPacket {
    private final int entityId;
    private final float scale;

    public ScaleSyncPacket(int entityId, float scale) {
        this.entityId = entityId;
        this.scale = scale;
    }

    public ScaleSyncPacket(FriendlyByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.scale = buffer.readFloat();
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeFloat(this.scale);
    }

    public static void handle(ScaleSyncPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.level != null) {
                Entity entity = minecraft.level.getEntity(packet.entityId);
                if (entity != null) {
                    entity.getPersistentData().putFloat(ForgeEvents.SCALE, packet.scale);
                    entity.refreshDimensions();
                }
            }
        });
        context.setPacketHandled(true);
    }
}