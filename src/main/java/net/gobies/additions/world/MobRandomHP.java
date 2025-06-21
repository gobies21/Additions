package net.gobies.additions.world;

import net.gobies.additions.Config;
import net.gobies.additions.network.MobHPSyncPacket;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.util.MobUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;
import java.util.Random;

@Mod.EventBusSubscriber(modid = "additions", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobRandomHP extends MobUtils {

    private static final Random random = new Random();

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onMobSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        Level world = event.getLevel();
        if (Config.ENABLE_RANDOM_MOB_HP.get()) {
            if (!world.isClientSide() && entity instanceof LivingEntity livingEntity && !(entity instanceof Player)) {
                if (livingEntity.getMaxHealth() > Config.BOSS_HP_THRESHOLD.get()) {
                    return;
                }
                float extraHPPercentage;
                float extraHPFlat = 0;

                CompoundTag entityData = livingEntity.getPersistentData();
                if (!entityData.contains("Rarity")) {
                    String rarityType;

                    float rarity = random.nextFloat();
                    if (rarity < 0.8f) { // 80% chance
                        // Common: 1% to 3% and possibly flat 1
                        rarityType = MobRarity.COMMON.name();
                        extraHPPercentage = 0.01f + (random.nextFloat() * 0.02f);
                        if (random.nextFloat() < 0.4f) { // 50% chance to add flat 1
                            extraHPFlat = 1f;
                        }

                    } else if (rarity < 0.95f) { // 15% chance
                        // Rare: 3% to 5% and possibly flat 2
                        rarityType = MobRarity.RARE.name();
                        extraHPPercentage = 0.03f + (random.nextFloat() * 0.02f);
                        if (random.nextFloat() < 0.4f) { // 50% chance to add flat 2
                            extraHPFlat = 2f;
                        }
                    } else if (rarity < 0.99f) { // 4% chance
                        // Very rare: 5% to 8% and possibly flat 4
                        rarityType = MobRarity.EPIC.name();
                        extraHPPercentage = 0.05f + (random.nextFloat() * 0.03f);
                        if (random.nextFloat() < 0.4f) { // 50% chance to add flat 4
                            extraHPFlat = 4f;
                        }
                    } else { // 1% chance
                        // Legendary: 8% to 12% and possibly flat 8
                        rarityType = MobRarity.LEGENDARY.name();
                        extraHPPercentage = 0.08f + (random.nextFloat() * 0.04f);
                        if (random.nextFloat() < 0.4f) { // 50% chance to add flat 8
                            extraHPFlat = 8f;
                        }
                    }

                    float maxHealth = livingEntity.getMaxHealth();
                    float extraHP = (maxHealth * extraHPPercentage) + extraHPFlat;
                    float BonusHealth = maxHealth + extraHP;

                    Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(BonusHealth);
                    livingEntity.setHealth(livingEntity.getMaxHealth());

                    MobHPSyncPacket packet = new MobHPSyncPacket(BonusHealth, entity.getId());
                    PacketHandler.sendToAllClients(packet);

                    entityData.putString("Rarity", rarityType);
                    entityData.putFloat("BonusHealth", BonusHealth);
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
                float BonusHealth = entityData.getFloat("BonusHealth");

                Objects.requireNonNull(livingEntity.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(BonusHealth);
                livingEntity.setHealth(livingEntity.getMaxHealth());

                MobHPSyncPacket packet = new MobHPSyncPacket(BonusHealth, livingEntity.getId());
                PacketHandler.sendToAllClients(packet);
            } else {
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
        LivingEntity livingEntity = event.getEntity();
        if (isEpicEntity(livingEntity)) {
            int originalExperience = event.getDroppedExperience();
            int newExperience = (int) (originalExperience * 1.5);
            event.setDroppedExperience(newExperience);
        }
        if (isLegendaryEntity(livingEntity)) {
            int originalExperience = event.getDroppedExperience();
            int newExperience = (int) (originalExperience * 2.0);
            event.setDroppedExperience(newExperience);
        }
    }

    private static boolean isEpicEntity(LivingEntity entity) {
        CompoundTag entityData = entity.getPersistentData();
        return entityData.contains("Rarity") && entityData.getString("Rarity").equals(MobRarity.EPIC.name());
    }

    private static boolean isLegendaryEntity(LivingEntity entity) {
        CompoundTag entityData = entity.getPersistentData();
        return entityData.contains("Rarity") && entityData.getString("Rarity").equals(MobRarity.LEGENDARY.name());
    }
}