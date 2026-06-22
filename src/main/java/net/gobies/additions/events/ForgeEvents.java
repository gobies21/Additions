package net.gobies.additions.events;
import net.gobies.additions.Additions;
import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.init.AdditionsAttributes;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.network.ScaleSyncPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

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