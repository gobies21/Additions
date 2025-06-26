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
            MobHealthConfig config = new MobHealthConfig();

            float extraHPPercentage;
            float extraHPFlat = 0;
            String rarityType;

            float rarity = random.nextFloat();
            if (rarity < 0.6f) { // 60% Chance
                rarityType = COMMON.name();
                extraHPPercentage = (float) (config.getCommonMinHP() + (random.nextFloat() * config.getCommonMaxHP()));
                if (random.nextFloat() < 0.5f) {
                    extraHPFlat = (float) config.getCommonFlatHP();
                }

            } else if (rarity < 0.85f) { // 25% Chance
                rarityType = UNCOMMON.name();
                extraHPPercentage = (float) (config.getUncommonMinHP() + (random.nextFloat() * config.getUncommonMaxHP()));
                extraHPFlat = (float) config.getUncommonFlatHP();

            } else if (rarity < 0.95f) { // 10% Chance
                rarityType = RARE.name();
                extraHPPercentage = (float) (config.getRareMinHP() + (random.nextFloat() * config.getRareMaxHP()));
                extraHPFlat = (float) config.getRareFlatHP();

            } else if (rarity < 0.99f) { // 4% Chance
                rarityType = EPIC.name();
                extraHPPercentage = (float) (config.getEpicMinHP() + (random.nextFloat() * config.getEpicMaxHP()));
                extraHPFlat = (float) config.getEpicFlatHP();

            } else if (rarity < 0.9999f) { // 1% Chance
                rarityType = LEGENDARY.name();
                extraHPPercentage = (float) (config.getLegendaryMinHP() + (random.nextFloat() * config.getLegendaryMaxHP()));
                extraHPFlat = (float) config.getLegendaryFlatHP();

            } else { // 0.01% Chance
                rarityType = SHINY.name();
                extraHPPercentage = (float) (config.getShinyMinHP() + (random.nextFloat() * config.getShinyMaxHP()));
                extraHPFlat = (float) config.getShinyFlatHP();
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
        MobHealthConfig config = new MobHealthConfig();
        float extraHPPercentage;
        float extraHPFlat = 0;

        switch (rarity) {
            case COMMON:
                extraHPPercentage = (float) (config.getCommonMinHP() + (random.nextFloat() * config.getCommonMaxHP()));
                if (random.nextFloat() < 0.5f) {
                    extraHPFlat = (float) config.getCommonFlatHP();
                }
                break;
            case UNCOMMON:
                extraHPPercentage = (float) (config.getUncommonMinHP() + (random.nextFloat() * config.getUncommonMaxHP()));
                extraHPFlat = (float) config.getUncommonFlatHP();

                break;
            case RARE:
                extraHPPercentage = (float) (config.getRareMinHP() + (random.nextFloat() * config.getRareMaxHP()));
                extraHPFlat = (float) config.getRareFlatHP();

                break;
            case EPIC:
                extraHPPercentage = (float) (config.getEpicMinHP() + (random.nextFloat() * config.getEpicMaxHP()));
                extraHPFlat = (float) config.getEpicFlatHP();

                break;
            case LEGENDARY:
                extraHPPercentage = (float) (config.getLegendaryMinHP() + (random.nextFloat() * config.getLegendaryMaxHP()));
                extraHPFlat = (float) config.getLegendaryFlatHP();

                break;
            case SHINY:
                extraHPPercentage = (float) (config.getShinyMinHP() + (random.nextFloat() * config.getShinyMaxHP()));
                extraHPFlat = (float) config.getShinyFlatHP();
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