package net.gobies.additions.events;

import net.gobies.additions.Additions;
import net.gobies.additions.init.AdditionsEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID)
public class FoodEvents {
    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        if (!(entity instanceof Player player)) return;

        ItemStack stack = event.getItem();
        if (!stack.isEdible()) return;

        FoodProperties food = stack.getItem().getFoodProperties(stack, player);
        if (food == null) return;

        if (food.getNutrition() >= 12 && food.getNutrition() <= 14) {
            player.addEffect(new MobEffectInstance(AdditionsEffects.WellFed.get(), 4800, 0, false, false));
        } else if (food.getNutrition() > 14) {
            player.addEffect(new MobEffectInstance(AdditionsEffects.WellFed.get(), 9600, 1, false, false));
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        Entity entity = event.getEntity();
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            if (attacker.hasEffect(AdditionsEffects.WellFed.get())) {
                int amplifier = Objects.requireNonNull(attacker.getEffect(AdditionsEffects.WellFed.get())).getAmplifier();
                float increasedDamage = (float) (event.getAmount() * (1.0f + (0.05 * (amplifier + 1))));
                event.setAmount(increasedDamage);
            }
        }
        if (entity instanceof LivingEntity defender) {
            if (defender.hasEffect(AdditionsEffects.WellFed.get())) {
                int amplifier = Objects.requireNonNull(defender.getEffect(AdditionsEffects.WellFed.get())).getAmplifier();
                float resistedDamage = (float) (event.getAmount() * (1.0f - (0.05 * (amplifier + 1))));
                event.setAmount(resistedDamage);
            }
        }
    }
}
