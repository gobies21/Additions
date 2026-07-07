package net.gobies.additions.events;


import net.gobies.additions.Additions;
import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.init.AdditionsAttributes;
import net.gobies.additions.init.AdditionsEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID)
public class AEvents {

    private static final UUID SNIPE_VELOCITY_UUID = UUID.fromString("0cd15420-cbc0-4517-a603-cb85b6af8f44");
    private static final AttributeModifier VELOCITY_MODIFIER = new AttributeModifier(SNIPE_VELOCITY_UUID, "Snipe Enchantment Velocity Bonus", 0.5D, AttributeModifier.Operation.ADDITION);

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof Player player) || player.level().isClientSide()) return;

        if (event.getSlot() == EquipmentSlot.MAINHAND || event.getSlot() == EquipmentSlot.OFFHAND) {
            AttributeInstance velocityAttr = player.getAttribute(AdditionsAttributes.ARROW_VELOCITY.get());
            if (velocityAttr == null) return;

            int mainHandLevel = EnchantmentHelper.getTagEnchantmentLevel(AdditionsEnchantments.SNIPE.get(), player.getMainHandItem());
            int offHandLevel = EnchantmentHelper.getTagEnchantmentLevel(AdditionsEnchantments.SNIPE.get(), player.getOffhandItem());

            if (mainHandLevel > 0 || offHandLevel > 0) {
                if (!velocityAttr.hasModifier(VELOCITY_MODIFIER)) {
                    velocityAttr.addTransientModifier(VELOCITY_MODIFIER);
                }
            } else {
                if (velocityAttr.hasModifier(VELOCITY_MODIFIER)) {
                    velocityAttr.removeModifier(VELOCITY_MODIFIER);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!CommonConfig.ENABLE_SPAWNER_MODULE.get()) return;
        BlockState blockState = event.getState();
        if (blockState.getBlock() == Blocks.SPAWNER) {
            event.setNewSpeed(event.getNewSpeed() * 0.15F);
        }
    }
}