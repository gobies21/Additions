package net.gobies.additions.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class MobHPSyncPacket {
    private final float maxHealth;
    private final int entityId;

    public MobHPSyncPacket(float maxHealth, int entityId) {
        this.maxHealth = maxHealth;
        this.entityId = entityId;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(maxHealth);
        buf.writeInt(entityId);
    }

    public static MobHPSyncPacket decode(FriendlyByteBuf buf) {
        float maxHealth = buf.readFloat();
        int entityId = buf.readInt();
        return new MobHPSyncPacket(maxHealth, entityId);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            Entity entity = Objects.requireNonNull(context.getSender()).level().getEntity(entityId);
            Objects.requireNonNull(context.getSender()).level().getEntity(this.entityId);
            if (entity instanceof LivingEntity livingEntity) {
                Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(this.maxHealth);
                livingEntity.setHealth(this.maxHealth);
            }
        });
        context.setPacketHandled(true);
    }


    public static void registerPackets(SimpleChannel channel) {
        int packetId = 0;
        channel.registerMessage(packetId++, MobHPSyncPacket.class, MobHPSyncPacket::encode, MobHPSyncPacket::decode, MobHPSyncPacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
    }
}