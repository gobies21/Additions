package net.gobies.additions.network;

import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.init.AdditionsSounds;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SoundSyncPacket(byte soundId) {
    public static final byte SWORD_SWING = 0;
    public static final byte TOOL_SWING = 1;
    public static final byte BOW_PULL = 2;
    public static final byte CROSSBOW_PULL = 3;
    public static final byte BOW_SHOOT = 4;
    public static final byte CROSSBOW_SHOOT = 5;
    public static final byte CRIT_HIT = 6;

    public SoundSyncPacket(FriendlyByteBuf buf) {
        this(buf.readByte());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeByte(this.soundId);
    }

    public static void handle(SoundSyncPacket msg, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context context = ctxSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && CommonConfig.ENABLE_SOUND_MODULE.get()) {
                float baseVolume = CommonConfig.SOUND_VOLUME.get().floatValue();
                SoundEvent targetSound = null;
                float volume = baseVolume;

                switch (msg.soundId) {
                    case SWORD_SWING -> {
                        targetSound = AdditionsSounds.PLAYER_SWORD_SWING.get();
                        volume = (baseVolume - 1.0F) + 0.7F;
                    }
                    case TOOL_SWING -> targetSound = AdditionsSounds.PLAYER_TOOL_SWING.get();
                    case BOW_PULL -> targetSound = AdditionsSounds.BOW_PULL.get();
                    case CROSSBOW_PULL -> targetSound = AdditionsSounds.CROSSBOW_PULL.get();
                    case BOW_SHOOT -> targetSound = AdditionsSounds.BOW_SHOOT.get();
                    case CROSSBOW_SHOOT -> targetSound = AdditionsSounds.CROSSBOW_SHOOT.get();
                    case CRIT_HIT -> targetSound = AdditionsSounds.PLAYER_CRIT_HIT.get();
                }

                if (targetSound != null) {
                    player.level().playSound(null, player.blockPosition(), targetSound, SoundSource.PLAYERS, volume, 1.0F);
                }
            }
        });
        context.setPacketHandled(true);
    }
}