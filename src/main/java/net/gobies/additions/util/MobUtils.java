package net.gobies.additions.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Random;

public class MobUtils {

    private static final Random random = new Random();

    public enum MobRarity {
        COMMON(1, "Common"),
        UNCOMMON(2, "Uncommon"),
        RARE(3, "Rare"),
        EPIC(4, "Epic"),
        LEGENDARY(5, "Legendary"),
        SHINY(6, "Shiny");

        private final int value;
        private final String name;

        MobRarity(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static boolean isCommonEntity(LivingEntity entity) {
            return getMobRarity(entity).equals(MobRarity.COMMON.name());
        }

        public static boolean isUncommonEntity(LivingEntity entity) {
            return getMobRarity(entity).equals(MobRarity.UNCOMMON.name());
        }

        public static boolean isRareEntity(LivingEntity entity) {
            return getMobRarity(entity).equals(MobRarity.RARE.name());
        }

        public static boolean isEpicEntity(LivingEntity entity) {
            return getMobRarity(entity).equals(MobRarity.EPIC.name());
        }

        public static boolean isLegendaryEntity(LivingEntity entity) {
            return getMobRarity(entity).equals(MobRarity.LEGENDARY.name());
        }

        public static boolean isShinyEntity(LivingEntity entity) {
            return getMobRarity(entity).equals(MobRarity.SHINY.name());
        }

        public static class MobHealthData {
            public String rarityType;
            public float extraHPPercentage;
            public float extraHPFlat;

            public MobHealthData(String rarityType, float extraHPPercentage, float extraHPFlat) {
                this.rarityType = rarityType;
                this.extraHPPercentage = extraHPPercentage;
                this.extraHPFlat = extraHPFlat;
            }
        }

        public static MobHealthData calculateMobHealth(LivingEntity livingEntity) {
            float extraHPPercentage;
            float extraHPFlat = 0;
            String rarityType;

            float rarity = random.nextFloat();
            if (rarity < 0.6f) { // 60% Chance
                rarityType = COMMON.name();
                extraHPPercentage = 0.01f + (random.nextFloat() * 0.01f); // 1-2% Health Increase
                if (random.nextFloat() < 0.5f) {
                    extraHPFlat = 1f;
                }

            } else if (rarity < 0.85f) { // 25% Chance
                rarityType = UNCOMMON.name();
                extraHPPercentage = 0.02f + (random.nextFloat() * 0.04f); // 2-4% Health Increase
                extraHPFlat = 2f;

            } else if (rarity < 0.95f) { // 10% Chance
                rarityType = RARE.name();
                extraHPPercentage = 0.04f + (random.nextFloat() * 0.02f); // 4-6% Health Increase
                extraHPFlat = 5f;

            } else if (rarity < 0.99f) { // 4% Chance
                rarityType = EPIC.name();
                extraHPPercentage = 0.06f + (random.nextFloat() * 0.02f); // 6-8% Health Increase
                extraHPFlat = 8f;

            } else if (rarity < 0.9999f) { // 1% Chance
                rarityType = LEGENDARY.name();
                extraHPPercentage = 0.08f + (random.nextFloat() * 0.07f); // 8-15% Health Increase
                extraHPFlat = 10f;

            } else { // 0.01% Chance
                rarityType = SHINY.name();
                extraHPPercentage = 0.20f + (random.nextFloat() * 0.20f); // 20-40% Health Increase
                extraHPFlat = 25f;
            }

            return new MobHealthData(rarityType, extraHPPercentage, extraHPFlat);
        }

        public CompoundTag toCompoundTag() {
            CompoundTag tag = new CompoundTag();
            tag.putInt("Rarity", this.getValue());
            return tag;
        }

        public MutableComponent getFormattedName(MutableComponent originalName) {
            return Component.translatable(this.getName() + " ").append(originalName);
        }
    }

    public static String getMobRarity(LivingEntity entity) {
        CompoundTag entityData = entity.getPersistentData();
        return entityData.getString("Rarity");
    }

    public static float getBonusHealth(LivingEntity entity) {
        CompoundTag entityData = entity.getPersistentData();
        return entityData.getFloat("BonusHealth");
    }

    public static MobRarity getMobRarityFromTag(CompoundTag entityTag) {
        CompoundTag rarityTag = entityTag.getCompound("MobRarity");
        int rarityValue = rarityTag.getInt("Rarity");
        return switch (rarityValue) {
            case 1 -> MobRarity.COMMON;
            case 2 -> MobRarity.UNCOMMON;
            case 3 -> MobRarity.RARE;
            case 4 -> MobRarity.EPIC;
            case 5 -> MobRarity.LEGENDARY;
            case 6 -> MobRarity.SHINY;
            default -> throw new IllegalArgumentException("Unknown Rarity");
        };
    }

    protected static void spawnShinyParticles(LivingEntity livingEntity) {
        Level level = livingEntity.level();
        if (level instanceof ServerLevel serverLevel) {
            ParticleOptions shinyParticles = ParticleTypes.ELECTRIC_SPARK;
            RandomSource random = livingEntity.getRandom();
            double offsetRange = livingEntity.getBbWidth() / 2.0D + 0.5;
            int numberOfParticles = 20;
            for (int i = 0; i < numberOfParticles; i++) {
                double x = livingEntity.getX() + (random.nextDouble() * 2 * offsetRange - offsetRange);
                double y = livingEntity.getY() + (random.nextDouble() * livingEntity.getBbHeight());
                double z = livingEntity.getZ() + (random.nextDouble() * 2 * offsetRange - offsetRange);

                if (Math.abs(x - livingEntity.getX()) < livingEntity.getBbWidth() / 2.0D) {
                    x += Math.signum(x - livingEntity.getX()) * (livingEntity.getBbWidth() / 2.0D + 0.1D);
                }
                if (Math.abs(z - livingEntity.getZ()) < livingEntity.getBbWidth() / 2.0D) {
                    z += Math.signum(z - livingEntity.getZ()) * (livingEntity.getBbWidth() / 2.0D + 0.1D);
                }

                serverLevel.sendParticles(shinyParticles, x, y, z, 1, 0.1, 0.1, 0.1, 0.05);
            }
        }
    }


    public static float getBonusHealthForRarity(MobRarity rarity, LivingEntity livingEntity) {
        float extraHPPercentage;
        float extraHPFlat = 0;

        switch (rarity) {
            case COMMON:
                extraHPPercentage = 0.01f + (random.nextFloat() * 0.01f);
                if (random.nextFloat() < 0.5f) {
                    extraHPFlat = 1f;
                }
                break;
            case UNCOMMON:
                extraHPPercentage = 0.02f + (random.nextFloat() * 0.04f);
                extraHPFlat = 2f;

                break;
            case RARE:
                extraHPPercentage = 0.04f + (random.nextFloat() * 0.02f);
                extraHPFlat = 5f;

                break;
            case EPIC:
                extraHPPercentage = 0.06f + (random.nextFloat() * 0.02f);
                extraHPFlat = 8f;

                break;
            case LEGENDARY:
                extraHPPercentage = 0.08f + (random.nextFloat() * 0.07f);
                extraHPFlat = 10f;

                break;
            case SHINY:
                extraHPPercentage = 0.20f + (random.nextFloat() * 0.20f);
                extraHPFlat = 25f;
                break;

            default:
                extraHPPercentage = 0;
                extraHPFlat = 0;
        }

        return (livingEntity.getMaxHealth() * extraHPPercentage) + extraHPFlat;
    }


    public static void setMobNameWithRarity(LivingEntity livingEntity, MobRarity rarity) {
        MutableComponent originalName = (MutableComponent) livingEntity.getDisplayName();
        MutableComponent formattedName = rarity.getFormattedName(originalName);

        livingEntity.setCustomName(formattedName);
        livingEntity.setCustomNameVisible(false);
    }
}