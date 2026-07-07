package net.gobies.additions.events;
import net.gobies.additions.Additions;
import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.init.AdditionsAttributes;
import net.gobies.additions.init.AdditionsEnchantments;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.network.ScaleSyncPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEnchantmentLevelSet(EnchantmentLevelSetEvent event) {
        int originalLevel = event.getEnchantLevel();
        if (originalLevel <= 0) return;

        BlockPos pos = event.getPos();
        Player player = event.getLevel().getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 8.0D, false);
        if (player == null) return;

        AttributeInstance focusAttribute = player.getAttribute(AdditionsAttributes.ENCHANTING_FOCUS.get());
        if (focusAttribute == null) return;

        double focusValue = focusAttribute.getValue();
        if (focusValue <= 0) return;

        int maxAllowedLevel = switch (event.getEnchantRow()) {
            case 0 -> 8 + (int) focusValue;
            case 1 -> 20 + (int) (focusValue * Math.max(0.1, CommonConfig.FOCUS_LEVEL_SCALE.get() - 0.5));
            case 2 -> 30 + (int) (focusValue * CommonConfig.FOCUS_LEVEL_SCALE.get());
            default -> 30;
        };

        event.setEnchantLevel(Math.min(originalLevel + (int) (focusValue * CommonConfig.FOCUS_LEVEL_SCALE.get()), maxAllowedLevel));
    }

    @SubscribeEvent
    public static void arrow(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof Projectile arrow)) return;

        var owner = arrow.getOwner();
        if (!(owner instanceof Player player)) return;
        var attribute = player.getAttribute(AdditionsAttributes.ARROW_VELOCITY.get());
        if (attribute == null) return;

        arrow.setDeltaMovement(arrow.getDeltaMovement().scale(attribute.getValue()));
    }

    @SubscribeEvent
    public static void onArrowImpact(ProjectileImpactEvent event) {
        if (!(event.getProjectile() instanceof AbstractArrow arrow) || !(event.getRayTraceResult() instanceof EntityHitResult entityHit)) return;
        if (!(entityHit.getEntity() instanceof LivingEntity livingTarget)) return;

        if (arrow.pickup == AbstractArrow.Pickup.CREATIVE_ONLY) {
            if (livingTarget.hurtTime > 0 || livingTarget.invulnerableTime > 0) {
                arrow.discard();
                event.setImpactResult(ProjectileImpactEvent.ImpactResult.STOP_AT_CURRENT_NO_DAMAGE);
                return;
            }
        }

        if (arrow.getOwner() instanceof LivingEntity shooter) {
            ItemStack weapon = shooter.getUseItem().isEmpty() ? shooter.getMainHandItem() : shooter.getUseItem();

            if (EnchantmentHelper.getTagEnchantmentLevel(AdditionsEnchantments.FREEZE.get(), weapon) > 0) {
                if (livingTarget.isOnFire()) {
                    livingTarget.clearFire();
                }

                livingTarget.setTicksFrozen(300);
            }
        }
    }

    private static final ThreadLocal<Boolean> SPAWN_SPLITSHOT = ThreadLocal.withInitial(() -> false);

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (SPAWN_SPLITSHOT.get() || event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof AbstractArrow originalArrow) || !(originalArrow.getOwner() instanceof LivingEntity shooter)) return;

        InteractionHand hand = shooter.getUsedItemHand();
        ItemStack weapon = shooter.getItemInHand(hand);
        if (weapon.isEmpty() || !(weapon.getItem() instanceof BowItem)) {
            hand = shooter.getMainHandItem().getItem() instanceof BowItem ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
            weapon = shooter.getItemInHand(hand);
        }

        if (weapon.isEmpty() || !(weapon.getItem() instanceof BowItem)) return;

        int level = EnchantmentHelper.getTagEnchantmentLevel(AdditionsEnchantments.SPLITSHOT.get(), weapon);
        if (level <= 0) {
            return;
        }

        try {
            SPAWN_SPLITSHOT.set(true);

            Vec3 velocity = originalArrow.getDeltaMovement();
            float speed = (float) velocity.length();
            final InteractionHand finalHand = hand;
            if (shooter instanceof ServerPlayer serverPlayer) {
                weapon.hurtAndBreak(level, serverPlayer, (player) -> player.broadcastBreakEvent(finalHand));
            }
            for (int i = 0; i < level; i++) {
                float randomOffset = (shooter.getRandom().nextFloat() - 0.5f) * 40.0f;

                spawnExtraArrow(event, originalArrow, shooter, speed, randomOffset);
            }
        } finally {
            SPAWN_SPLITSHOT.set(false);
        }
    }

    private static void spawnExtraArrow(EntityJoinLevelEvent event, AbstractArrow original, LivingEntity shooter, float speed, float offset) {
        Entity extraArrow = original.getType().create(event.getLevel());
        if (extraArrow instanceof AbstractArrow arrow) {
            arrow.setOwner(shooter);
            arrow.setPos(original.getX(), original.getY(), original.getZ());
            arrow.setBaseDamage(original.getBaseDamage());
            arrow.setCritArrow(original.isCritArrow());
            arrow.setKnockback(original.getKnockback());

            if (original.isOnFire()) {
                arrow.setSecondsOnFire(100);
            }

            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;

            try {
                List<SynchedEntityData.DataValue<?>> values = original.getEntityData().getNonDefaultValues();
                if (values != null) {
                    arrow.getEntityData().assignValues(values);
                }
            } catch (Exception e) {
                Additions.LOGGER.error("Failed sync arrow data for splitshot arrows", e);
            }

            float adjustedOffset = shooter.getYRot() + offset;
            float pitch = shooter.getXRot();

            arrow.shootFromRotation(shooter, pitch, adjustedOffset, 0.0F, speed, 8.0F);
            event.getLevel().addFreshEntity(extraArrow);
        }
    }


    @SubscribeEvent
    public static void fixCritTooltip(ItemTooltipEvent event) {
        for (int i = 0; i < event.getToolTip().size(); i++) {
            String text = event.getToolTip().get(i).getString();
            if (text.contains("Critical Rate")) {
                String cleanNumber = text.replace("+", "").replace("-", "").replace(" Critical Rate", "").trim();
                int percent = (int) Math.round(Double.parseDouble(cleanNumber) * 100);

                String prefix = "+";
                net.minecraft.ChatFormatting color = net.minecraft.ChatFormatting.BLUE;

                if (text.contains("-")) {
                    prefix = "-";
                    color = net.minecraft.ChatFormatting.RED;
                }
                event.getToolTip().set(i, Component.literal(prefix + percent + "% Critical Rate").withStyle(color));
            }
        }
    }

    public static final String SCALE = "Scale";
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onMobSpawn(EntityJoinLevelEvent event) {
        if (!CommonConfig.ENABLE_ENTITY_MODULE.get() && !CommonConfig.ENABLE_MOB_SIZES.get()) return;
        if (event.getLevel().isClientSide()) return;
        if (!(event.getEntity() instanceof LivingEntity entity)) return;
        if (entity instanceof Player) return;

        CompoundTag persistentData = entity.getPersistentData();
        if (persistentData.contains(SCALE)) return;

        if (CommonConfig.ONLY_SCALE_ANIMALS.get()) {
            if (!(entity instanceof Animal)) {
                return;
            }
        }
        if (!persistentData.contains(SCALE)) {
            float randomScale = getRandomScale();
            persistentData.putFloat(SCALE, randomScale);
        }

        entity.refreshDimensions();
    }

    private static float getRandomScale() {
        float min = CommonConfig.MIN_MOB_SIZE.get().floatValue();
        float max = CommonConfig.MAX_MOB_SIZE.get().floatValue();

        if (min >= max) {
            return min;
        }
        return min + (RANDOM.nextFloat() * (max - min));
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!CommonConfig.ENABLE_ENTITY_MODULE.get() && !CommonConfig.ENABLE_MOB_SIZES.get()) return;
        Entity target = event.getTarget();
        if (!target.level().isClientSide() && target instanceof LivingEntity) {
            CompoundTag persistentData = target.getPersistentData();

            if (persistentData.contains(SCALE)) {
                float scale = persistentData.getFloat(SCALE);
                ServerPlayer player = (ServerPlayer) event.getEntity();
                PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ScaleSyncPacket(target.getId(), scale));
            }
        }
    }
}