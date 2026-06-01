package net.gobies.additions.world;

import net.gobies.additions.Config;
import net.gobies.additions.compat.champions.ChampionsCompat;
import net.gobies.additions.network.MobHPSyncPacket;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.util.MobUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = "additions", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobRandomHP extends MobUtils {

    private static int tickCounter = 0;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onMobSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level world = event.getLevel();
        if (Config.ENABLE_RANDOM_MOB_HP.get()) {
            if (world.isClientSide() || !(entity instanceof LivingEntity livingEntity) || entity instanceof Player) {
                return;
            }

            CompoundTag entityData = livingEntity.getPersistentData();
            if (entityData.contains("Rarity") || entityData.contains("BonusHealth")) return;

            ResourceLocation entityName = ForgeRegistries.ENTITY_TYPES.getKey(livingEntity.getType());
            if (entityName != null && Config.BLACKLISTED_ENTITIES.get().contains(entityName.toString())) return;

            if (ChampionsCompat.isChampion(livingEntity)) return;

            MobRarity.MobRarityData mobHealthData = MobRarity.calculateMobStats(livingEntity);
            float maxHealth = livingEntity.getMaxHealth();
            if (maxHealth > Config.BOSS_HP_THRESHOLD.get()) return;

            float bonusHealth = (maxHealth * mobHealthData.extraHPPercentage) + mobHealthData.extraHPFlat;
            float newMaxHealth = maxHealth + bonusHealth;

            Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(newMaxHealth);
            livingEntity.setHealth(newMaxHealth);

            MobHPSyncPacket packet = new MobHPSyncPacket(newMaxHealth, entity.getId());
            PacketHandler.sendToAllClients(packet);

            MobUtils.setMobRarity(entityData, MobRarity.valueOf(mobHealthData.rarityType));
            MobUtils.setBonusHealth(entityData, bonusHealth);

            if (Config.MOB_RARITY_DISPLAY_NAME.get()) {
                setMobNameWithRarity(livingEntity, MobRarity.valueOf(mobHealthData.rarityType));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBabySpawn(BabyEntitySpawnEvent event) {
        if (Config.ENABLE_RANDOM_MOB_HP.get()) {
            LivingEntity baby = event.getChild();
            LivingEntity parentA = event.getParentA();
            LivingEntity parentB = event.getParentB();
            if (baby == null) return;
            MobRarity rarity = null;
            if (parentA != null && parentB != null) {
                RandomSource random = baby.getRandom();
                float randomFloat = random.nextFloat();
                CompoundTag babyData = baby.getPersistentData();

                // Common Breeding Results
                if (MobRarity.isCommonEntity(parentA) && MobRarity.isCommonEntity(parentB)) {
                    MobUtils.setMobRarity(babyData, MobRarity.UNCOMMON);
                    rarity = MobRarity.UNCOMMON;
                } else if (MobRarity.isCommonEntity(parentA) && MobRarity.isUncommonEntity(parentB)) {
                    if (randomFloat < 0.5f) {
                        MobUtils.setMobRarity(babyData, MobRarity.COMMON);
                        rarity = MobRarity.COMMON;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.UNCOMMON);
                        rarity = MobRarity.UNCOMMON;
                    }
                } else if (MobRarity.isCommonEntity(parentA) && MobRarity.isRareEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.COMMON);
                        rarity = MobRarity.COMMON;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.UNCOMMON);
                        rarity = MobRarity.UNCOMMON;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    }
                } else if (MobRarity.isCommonEntity(parentA) && MobRarity.isEpicEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.UNCOMMON);
                        rarity = MobRarity.UNCOMMON;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    }
                } else if (MobRarity.isCommonEntity(parentA) && MobRarity.isLegendaryEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                } else if (MobRarity.isCommonEntity(parentA) && MobRarity.isShinyEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                }

                // Uncommon Breeding Results
                if (MobRarity.isUncommonEntity(parentA) && MobRarity.isUncommonEntity(parentB)) {
                    MobUtils.setMobRarity(babyData, MobRarity.RARE);
                    rarity = MobRarity.RARE;
                } else if (MobRarity.isUncommonEntity(parentA) && MobRarity.isRareEntity(parentB)) {
                    if (randomFloat < 0.5f) {
                        MobUtils.setMobRarity(babyData, MobRarity.UNCOMMON);
                        rarity = MobRarity.UNCOMMON;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    }
                } else if (MobRarity.isUncommonEntity(parentA) && MobRarity.isEpicEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.UNCOMMON);
                        rarity = MobRarity.UNCOMMON;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    }
                } else if (MobRarity.isUncommonEntity(parentA) && MobRarity.isLegendaryEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                } else if (MobRarity.isUncommonEntity(parentA) && MobRarity.isShinyEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                }

                // Rare Breeding Results
                if (MobRarity.isRareEntity(parentA) && MobRarity.isRareEntity(parentB)) {
                    MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                    rarity = MobRarity.EPIC;
                } else if (MobRarity.isRareEntity(parentA) && MobRarity.isEpicEntity(parentB)) {
                    if (randomFloat < 0.5f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    }
                } else if (MobRarity.isRareEntity(parentA) && MobRarity.isLegendaryEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                } else if (MobRarity.isRareEntity(parentA) && MobRarity.isShinyEntity(parentB)) {
                    if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.RARE);
                        rarity = MobRarity.RARE;
                    } else if (randomFloat < 0.7f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                }

                // Epic Breeding Results
                if (MobRarity.isEpicEntity(parentA) && MobRarity.isEpicEntity(parentB)) {
                    MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                    rarity = MobRarity.LEGENDARY;
                } else if (MobRarity.isEpicEntity(parentA) && MobRarity.isLegendaryEntity(parentB)) {
                    if (randomFloat < 0.5f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                } else if (MobRarity.isEpicEntity(parentA) && MobRarity.isShinyEntity(parentB)) {
                    if (randomFloat < 0.5f) {
                        MobUtils.setMobRarity(babyData, MobRarity.EPIC);
                        rarity = MobRarity.EPIC;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                }

                // Legendary Breeding Results
                if (MobRarity.isLegendaryEntity(parentA) && MobRarity.isLegendaryEntity(parentB)) {
                    if (randomFloat < 0.05f) {
                        MobUtils.setMobRarity(babyData, MobRarity.SHINY);
                        rarity = MobRarity.SHINY;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                } else if (MobRarity.isLegendaryEntity(parentA) && MobRarity.isShinyEntity(parentB)) {
                    if (randomFloat < 0.1f) {
                        MobUtils.setMobRarity(babyData, MobRarity.SHINY);
                        rarity = MobRarity.SHINY;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                }

                // Shiny Breeding Results
                if (MobRarity.isShinyEntity(parentA) && MobRarity.isShinyEntity(parentB)) {
                    if (randomFloat < 0.2f) {
                        MobUtils.setMobRarity(babyData, MobRarity.SHINY);
                        rarity = MobRarity.SHINY;
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.LEGENDARY);
                        rarity = MobRarity.LEGENDARY;
                    }
                }

                if (rarity == null) {
                    String parentARarity = MobUtils.getMobRarity(parentA);
                    String parentBRarity = MobUtils.getMobRarity(parentB);
                    if (randomFloat < 0.2f) {
                        MobUtils.setMobRarity(babyData, MobRarity.valueOf(parentARarity));
                        rarity = MobRarity.valueOf(parentARarity);
                    } else if (randomFloat < 0.4f) {
                        MobUtils.setMobRarity(babyData, MobRarity.valueOf(parentBRarity));
                        rarity = MobRarity.valueOf(parentBRarity);
                    } else {
                        MobUtils.setMobRarity(babyData, MobRarity.COMMON);
                        rarity = MobRarity.COMMON;
                    }
                }

                float maxHealth = baby.getMaxHealth();
                float bonusHealth = MobUtils.getBonusHealthForRarity(rarity, baby);
                float newMaxHealth = maxHealth + bonusHealth;
                MobUtils.setBonusHealth(babyData, bonusHealth);
                Objects.requireNonNull(baby.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(newMaxHealth);
                baby.setHealth(newMaxHealth);
                if (Config.MOB_RARITY_DISPLAY_NAME.get()) {
                    setMobNameWithRarity(baby, MobRarity.valueOf(rarity.name()));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (Config.ENABLE_RANDOM_MOB_HP.get()) {
            LivingEntity livingEntity = event.getEntity();
            if (MobRarity.isShinyEntity(livingEntity)) {
                if (tickCounter % 20 == 0) {
                    tickCounter = 0;
                    MobUtils.spawnShinyParticles(livingEntity);
                }
                tickCounter++;
            }
        }
    }

    @SubscribeEvent
    public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
        if (Config.ENABLE_RANDOM_MOB_HP.get()) {
            LivingEntity livingEntity = event.getEntity();
            if (MobRarity.isRareEntity(livingEntity)) {
                int originalExperience = event.getDroppedExperience();
                int newExperience = (int) (originalExperience * 1.2);
                event.setDroppedExperience(newExperience);
            }

            if (MobRarity.isEpicEntity(livingEntity)) {
                int originalExperience = event.getDroppedExperience();
                int newExperience = (int) (originalExperience * 1.5);
                event.setDroppedExperience(newExperience);
            }

            if (MobRarity.isLegendaryEntity(livingEntity)) {
                int originalExperience = event.getDroppedExperience();
                int newExperience = (int) (originalExperience * 2.0);
                event.setDroppedExperience(newExperience);
            }

            if (MobRarity.isShinyEntity(livingEntity)) {
                int originalExperience = event.getDroppedExperience();
                int newExperience = (int) (originalExperience * 5.0);
                event.setDroppedExperience(newExperience);
            }
        }
    }
}