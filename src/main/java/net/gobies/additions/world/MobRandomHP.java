package net.gobies.additions.world;

import net.gobies.additions.Config;
import net.gobies.additions.compat.champions.ChampionCompat;
import net.gobies.additions.network.MobHPSyncPacket;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.util.MobUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
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

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onMobSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level world = event.getLevel();
        if (Config.ENABLE_RANDOM_MOB_HP.get()) {
            if (!world.isClientSide() && entity instanceof LivingEntity livingEntity && !(entity instanceof Player)) {
                if (livingEntity.getMaxHealth() > Config.BOSS_HP_THRESHOLD.get()) {
                    return;
                }

                ResourceLocation entityName = ForgeRegistries.ENTITY_TYPES.getKey(livingEntity.getType());
                if (entityName != null && Config.BLACKLISTED_ENTITIES.get().contains(entityName.toString())) {
                    return;
                }

                CompoundTag entityData = livingEntity.getPersistentData();
                if (entityData.contains("Rarity")) {
                    return;
                }


                if (ChampionCompat.allowChampion(livingEntity)) {
                    return;
                }

                MobRarity.MobHealthData mobHealthData = MobUtils.MobRarity.calculateMobHealth(livingEntity);

                float maxHealth = livingEntity.getMaxHealth();
                float extraHP = (maxHealth * mobHealthData.extraHPPercentage) + mobHealthData.extraHPFlat;
                float bonusHealth = maxHealth + extraHP;

                Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(bonusHealth);
                livingEntity.setHealth(livingEntity.getMaxHealth());

                MobHPSyncPacket packet = new MobHPSyncPacket(bonusHealth, entity.getId());
                PacketHandler.sendToAllClients(packet);

                entityData.putString("Rarity", mobHealthData.rarityType);
                entityData.putFloat("BonusHealth", extraHP);

                if (Config.MOB_RARITY_DISPLAY_NAME.get()) {
                    setMobNameWithRarity(livingEntity, MobRarity.valueOf(mobHealthData.rarityType));
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerTameAnimal(AnimalTameEvent event) {
        LivingEntity livingEntity = event.getAnimal();
        Player player = event.getTamer();
        if (player == null || livingEntity == null) {
            return;
        }
        if (Config.ENABLE_RANDOM_MOB_HP.get()) {
            CompoundTag entityData = livingEntity.getPersistentData();
            if (entityData.contains("BonusHealth")) {
                float bonusHealth = entityData.getFloat("BonusHealth");

                Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(bonusHealth);
                livingEntity.setHealth(livingEntity.getMaxHealth());

                MobHPSyncPacket packet = new MobHPSyncPacket(bonusHealth, livingEntity.getId());
                PacketHandler.sendToAllClients(packet);
            } else {
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        tickCounter++;

        if (tickCounter % 10 == 0) {
            if (MobUtils.MobRarity.isShinyEntity(livingEntity)) {
                MobUtils.spawnShinyParticles(livingEntity);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (MobUtils.MobRarity.isEpicEntity(livingEntity)) {
            int originalExperience = event.getDroppedExperience();
            int newExperience = (int) (originalExperience * 1.5);
            event.setDroppedExperience(newExperience);
        }

        if (MobUtils.MobRarity.isLegendaryEntity(livingEntity)) {
            int originalExperience = event.getDroppedExperience();
            int newExperience = (int) (originalExperience * 2.0);
            event.setDroppedExperience(newExperience);
        }

        if (MobUtils.MobRarity.isShinyEntity(livingEntity)) {
            int originalExperience = event.getDroppedExperience();
            int newExperience = (int) (originalExperience * 5.0);
            event.setDroppedExperience(newExperience);
        }
    }
}