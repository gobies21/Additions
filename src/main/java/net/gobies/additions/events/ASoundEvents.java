package net.gobies.additions.events;

import net.gobies.additions.Additions;
import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.compat.ironsspellbooks.IronsSpellbooksCompat;
import net.gobies.additions.compat.pml.PMLCompat;
import net.gobies.additions.compat.spartanweaponry.SpartanWeaponryCompat;
import net.gobies.additions.init.AdditionsSounds;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.network.SoundSyncPacket;
import net.gobies.additions.util.AdditionsUtil;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.*;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ASoundEvents {
     /*
     * Sounds used in this code originate from Dynamic Surroundings by OreCruncher under MIT License
     */
    private static final Map<UUID, Long> SWORD_ATTACK_COOLDOWN = new HashMap<>();
    private static final Map<UUID, Long> CRAFT_COOLDOWN = new HashMap<>();
    private static final long COOLDOWN = 200;
    private static final Map<UUID, Long> SWING_COOLDOWN = new HashMap<>();
    private static final Map<UUID, Integer> ARMOR_COOLDOWNS = new HashMap<>();
    private static final Map<UUID, Boolean> WAS_ON_GROUND = new HashMap<>();
    private static final Map<UUID, double[]> LAST_POSITIONS = new HashMap<>();

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        LivingEntity target = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (damageSource.isIndirect()) return;
        if (damageSource.getEntity() instanceof Player player) {
            if (player.level().isClientSide()) return;
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.getItem() instanceof TieredItem && (!(heldItem.getItem() instanceof DiggerItem)) || heldItem.getItem() instanceof TridentItem ||  AdditionsUtil.isValidWeapon(heldItem.getItem())) {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_SWORD_SWING.get(), SoundSource.PLAYERS, (CommonConfig.SOUND_VOLUME.get().floatValue() - 1) + 0.7F, 1.0F);
                if (PMLCompat.isCriticalHit(damageSource)) {
                    target.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRIT_HIT.get(), SoundSource.PLAYERS, CommonConfig.SOUND_VOLUME.get().floatValue() + 0.5F, 1.0F);
                } else {
                    target.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_SWORD_SLASH.get(), SoundSource.PLAYERS, CommonConfig.SOUND_VOLUME.get().floatValue(), 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onCrit(CriticalHitEvent event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        if (player.level().isClientSide()) return;
        if (entity instanceof LivingEntity) {
            if (event.isVanillaCritical()) {
                entity.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRIT_HIT.get(), SoundSource.PLAYERS, CommonConfig.SOUND_VOLUME.get().floatValue() + 0.5F, 1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onWeaponSwing(PlayerInteractEvent.LeftClickEmpty event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getItem() instanceof TieredItem && (!(heldItem.getItem() instanceof DiggerItem)) || heldItem.getItem() instanceof TridentItem || AdditionsUtil.isValidWeapon(heldItem.getItem())) {
            UUID playerUUID = player.getUUID();
            long currentTime = System.currentTimeMillis();
            Long lastAttackTime = SWORD_ATTACK_COOLDOWN.get(playerUUID);
            if (lastAttackTime == null || currentTime - lastAttackTime >= COOLDOWN) {
                PacketHandler.sendToServer(new SoundSyncPacket(SoundSyncPacket.SWORD_SWING));
                SWORD_ATTACK_COOLDOWN.put(playerUUID, currentTime);
            }
        }
    }

    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Start event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;

        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.level().isClientSide) return;

        ItemStack heldItem = event.getItem();
        Item item = heldItem.getItem();
        SoundEvent targetSound = null;
        if (item instanceof BowItem) {
            targetSound = AdditionsSounds.BOW_PULL.get();
        } else if (item instanceof CrossbowItem && !SpartanWeaponryCompat.isHeavyCrossbow(item)) {
            targetSound = AdditionsSounds.CROSSBOW_PULL.get();
        }

        if (targetSound != null) {
            SoundSource source = livingEntity instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), targetSound, source, CommonConfig.SOUND_VOLUME.get().floatValue(), 1.0F);
        }
    }

    @SubscribeEvent
    public static void onArrowShoot(EntityJoinLevelEvent event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (arrow.getOwner() instanceof LivingEntity shooter) {
            ItemStack weapon = shooter.getMainHandItem();
            SoundEvent targetSound = null;

            if (weapon.getItem() instanceof BowItem) {
                targetSound = AdditionsSounds.BOW_SHOOT.get();
            } else if (weapon.getItem() instanceof CrossbowItem) {
                targetSound = AdditionsSounds.CROSSBOW_SHOOT.get();
            }

            if (targetSound != null) {
                shooter.level().playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), targetSound, SoundSource.NEUTRAL, CommonConfig.SOUND_VOLUME.get().floatValue(), 1.0F);
            }
        }
    }

    @SubscribeEvent
    public static void onToolAttack(LivingHurtEvent event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        LivingEntity target = event.getEntity();
        DamageSource damageSource = event.getSource();
        if (damageSource.isIndirect()) return;
        if (damageSource.getEntity() instanceof Player player) {
            if (player.level().isClientSide()) return;
            ItemStack heldItem = player.getMainHandItem();
            float attackStrength = player.getAttackStrengthScale(0.5F);
            if (heldItem.getItem() instanceof DiggerItem || IronsSpellbooksCompat.isStaff(heldItem.getItem()) || AdditionsUtil.isValidTool(heldItem.getItem())) {
                if (player.fallDistance > 0.1F && attackStrength >= 0.2F)
                    target.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_TOOL_SWING.get(), SoundSource.PLAYERS, CommonConfig.SOUND_VOLUME.get().floatValue(), 1.0F);
                else if (attackStrength >= 0.2F) {
                    target.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_TOOL_SWING.get(), SoundSource.PLAYERS, CommonConfig.SOUND_VOLUME.get().floatValue(), 1.0F);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onToolSwing(PlayerInteractEvent.LeftClickEmpty event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getItem() instanceof DiggerItem || IronsSpellbooksCompat.isStaff(heldItem.getItem()) || AdditionsUtil.isValidTool(heldItem.getItem())) {
            UUID playerUUID = player.getUUID();
            long currentTime = System.currentTimeMillis();
            Long lastAttackTime = SWING_COOLDOWN.get(playerUUID);
            if (lastAttackTime == null || currentTime - lastAttackTime >= COOLDOWN) {
                PacketHandler.sendToServer(new SoundSyncPacket(SoundSyncPacket.TOOL_SWING));
                SWING_COOLDOWN.put(playerUUID, currentTime);
            }
        }
    }

    @SubscribeEvent
    public static void onArmorTick(LivingEvent.LivingTickEvent event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;

        LivingEntity livingEntity = event.getEntity();
        if (livingEntity.level().isClientSide) return;

        UUID uuid = livingEntity.getUUID();
        boolean onGround = livingEntity.onGround();
        boolean wasOnGround = WAS_ON_GROUND.getOrDefault(uuid, true);

        boolean justLanded = onGround && !wasOnGround;
        WAS_ON_GROUND.put(uuid, onGround);

        if (!onGround || livingEntity.isInWater() || livingEntity.isPassenger() || livingEntity.isCrouching()) {
            return;
        }

        int currentCooldown = ARMOR_COOLDOWNS.getOrDefault(uuid, 0);
        if (currentCooldown > 0) {
            currentCooldown--;
            ARMOR_COOLDOWNS.put(uuid, currentCooldown);
        }

        double currentX = livingEntity.getX();
        double currentZ = livingEntity.getZ();
        double[] lastPos = LAST_POSITIONS.get(uuid);

        if (lastPos == null) {
            LAST_POSITIONS.put(uuid, new double[]{currentX, currentZ});
            return;
        }

        double dx = currentX - lastPos[0];
        double dz = currentZ - lastPos[1];
        double distanceSq = dx * dx + dz * dz;

        if (justLanded || (distanceSq > 0.0016 && currentCooldown == 0)) {
            int armorPieces = 0;
            boolean hasCrystal = false;
            boolean hasLight = false;
            boolean hasMedium = false;
            boolean hasHeavy = false;

            for (ItemStack armor : livingEntity.getArmorSlots()) {
                if (armor.getItem() instanceof ArmorItem armorItem) {
                    armorPieces++;
                    ArmorMaterial material = armorItem.getMaterial();
                    if (material == ArmorMaterials.LEATHER || AdditionsUtil.isValidLightArmor(armor.getItem())) hasLight = true;
                    if (material == ArmorMaterials.CHAIN || AdditionsUtil.isValidMediumArmor(armor.getItem())) hasMedium = true;
                    if (material == ArmorMaterials.IRON || material == ArmorMaterials.GOLD || material == ArmorMaterials.NETHERITE || AdditionsUtil.isValidHeavyArmor(armor.getItem())) hasHeavy = true;
                    if (material == ArmorMaterials.DIAMOND || AdditionsUtil.isValidCrystalArmor(armor.getItem())) hasCrystal = true;
                }
            }

            if (armorPieces == 0) return;

            if (armorPieces > 0) {
                SoundEvent stepSound;
                boolean sprinting = livingEntity.isSprinting();

                if (livingEntity.isCrouching()) return;

                if (hasCrystal) {
                    if (sprinting) {
                        stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_CRYSTAL_RUN, SoundEvents.ARMOR_EQUIP_DIAMOND);
                    } else {
                        stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_CRYSTAL_WALK, SoundEvents.ARMOR_EQUIP_DIAMOND);
                    }
                } else if (hasHeavy) {
                    if (sprinting) {
                        stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_HEAVY_RUN, SoundEvents.ARMOR_EQUIP_NETHERITE);
                    } else {
                        stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_HEAVY_WALK, SoundEvents.ARMOR_EQUIP_NETHERITE);
                    }
                } else if (hasMedium) {
                    stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_MEDIUM, SoundEvents.ARMOR_EQUIP_CHAIN);
                } else if (hasLight) {
                    stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_LIGHT, SoundEvents.ARMOR_EQUIP_LEATHER);
                } else {
                    // Default
                    if (sprinting) {
                        stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_HEAVY_RUN, SoundEvents.ARMOR_EQUIP_NETHERITE);
                    } else {
                        stepSound = AdditionsSounds.getOrFallback(AdditionsSounds.ARMOR_HEAVY_WALK, SoundEvents.ARMOR_EQUIP_NETHERITE);
                    }
                }

                if (stepSound != null) {
                    float pitch = 1.0F + (livingEntity.level().getRandom().nextFloat() - 0.5F) * 0.08F;
                    livingEntity.level().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), stepSound, SoundSource.NEUTRAL, CommonConfig.SOUND_VOLUME.get().floatValue() - 0.6F, pitch);

                    if (!justLanded) {
                        int baseCooldown = sprinting ? 6 : 10;
                        double expectedSpeed = sprinting ? 0.2806 : 0.2158;
                        double actualSpeed = Math.sqrt(distanceSq);
                        if (actualSpeed > expectedSpeed) {
                            baseCooldown = (int) Math.round(baseCooldown * (expectedSpeed / actualSpeed));
                        }
                        ARMOR_COOLDOWNS.put(uuid, Math.max(3, baseCooldown));
                    }
                }
            }
        }
        lastPos[0] = currentX;
        lastPos[1] = currentZ;
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player) {
            if (player.level().isClientSide) return;
            if (player.isInWater() || player.isPassenger()) return;
            SoundEvent jumpSound = AdditionsSounds.PLAYER_JUMP.get();
            float pitch = 0.9F + (player.level().getRandom().nextFloat() - 0.2F) * 0.50F;

            player.playNotifySound(jumpSound, SoundSource.PLAYERS, CommonConfig.SOUND_VOLUME.get().floatValue() - 0.5F, pitch);
        }
    }

    @SubscribeEvent
    public static void onPlayerCraft(PlayerEvent.ItemCraftedEvent event) {
        if (!CommonConfig.ENABLE_SOUND_MODULE.get()) return;
        if (event.getCrafting().isEmpty()) return;
        Player player = event.getEntity();
        double cooldown = COOLDOWN + 300;

        boolean hasWood = false;
        if (player.level().isClientSide()) return;
        long currentTime = System.currentTimeMillis();
        Predicate<ItemStack> isWood = stack ->
                stack.is(ItemTags.PLANKS) ||
                        stack.is(ItemTags.LOGS) ||
                        stack.is(ItemTags.WOODEN_SLABS) ||
                        stack.is(ItemTags.WOODEN_STAIRS) ||
                        stack.is(ItemTags.WOODEN_DOORS) ||
                        stack.is(ItemTags.WOODEN_TRAPDOORS) ||
                        stack.is(ItemTags.WOODEN_FENCES) ||
                        stack.is(ItemTags.WOODEN_BUTTONS) ||
                        stack.is(ItemTags.SIGNS) ||
                        stack.is(ItemTags.HANGING_SIGNS) ||
                        stack.is(ItemTags.BOATS) ||
                        stack.is(ItemTags.CHEST_BOATS) ||
                        stack.is(ItemTags.WOODEN_PRESSURE_PLATES);
        if (isWood.test(event.getCrafting())) {
            hasWood = true;
        }
        Container container = event.getInventory();
        if (container instanceof CraftingContainer craftingContainer) {
            for (int i = 0; i < craftingContainer.getContainerSize(); i++) {
                ItemStack stack = craftingContainer.getItem(i);
                if (!stack.isEmpty() && isWood.test(stack)) {
                    hasWood = true;
                    break;
                }
            }
        }
        UUID playerUUID = player.getUUID();
        Long lastCraftTime = CRAFT_COOLDOWN.get(playerUUID);
        if (lastCraftTime == null || currentTime - lastCraftTime >= (cooldown + 500)) {
            if (!hasWood) {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRAFT_OTHER.get(), SoundSource.PLAYERS, (CommonConfig.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
            } else {
                player.level().playSound(null, player.blockPosition(), AdditionsSounds.PLAYER_CRAFT_WOOD.get(), SoundSource.PLAYERS, (CommonConfig.SOUND_VOLUME.get().floatValue() - 1) + 0.6F, 1.0F);
            }
            CRAFT_COOLDOWN.put(playerUUID, currentTime);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        UUID uuid = event.getEntity().getUUID();
        clearPlayerMaps(uuid);
    }

    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            clearPlayerMaps(player.getUUID());
        } else if (event.getEntity() instanceof LivingEntity livingEntity) {
            UUID uuid = livingEntity.getUUID();
            ARMOR_COOLDOWNS.remove(uuid);
            WAS_ON_GROUND.remove(uuid);
            LAST_POSITIONS.remove(uuid);
        }
    }

    private static void clearPlayerMaps(UUID uuid) {
        SWORD_ATTACK_COOLDOWN.remove(uuid);
        CRAFT_COOLDOWN.remove(uuid);
        SWING_COOLDOWN.remove(uuid);
        ARMOR_COOLDOWNS.remove(uuid);
        WAS_ON_GROUND.remove(uuid);
        LAST_POSITIONS.remove(uuid);
    }
}
