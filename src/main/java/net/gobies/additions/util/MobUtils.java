package net.gobies.additions.util;

import net.minecraft.nbt.CompoundTag;

public class MobUtils {

    public enum MobRarity {
        COMMON(1),
        RARE(2),
        EPIC(3),
        LEGENDARY(4);

        private final int value;

        MobRarity(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public CompoundTag toCompoundTag() {
            CompoundTag tag = new CompoundTag();
            tag.putInt("Rarity", this.getValue());
            return tag;
        }
    }

    public static void setMobRarityTag(CompoundTag entityTag, MobRarity rarity) {
        entityTag.put("MobRarity", rarity.toCompoundTag());
    }

    public static MobRarity getMobRarityFromTag(CompoundTag entityTag) {
        CompoundTag rarityTag = entityTag.getCompound("MobRarity");
        int rarityValue = rarityTag.getInt("Rarity");
        return switch (rarityValue) {
            case 1 -> MobRarity.COMMON;
            case 2 -> MobRarity.RARE;
            case 3 -> MobRarity.EPIC;
            case 4 -> MobRarity.LEGENDARY;
            default -> throw new IllegalArgumentException("Unknown Rarity");
        };
    }
}