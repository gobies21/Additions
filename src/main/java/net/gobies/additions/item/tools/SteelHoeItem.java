package net.gobies.additions.item.tools;

import net.gobies.additions.item.ModItems;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class SteelHoeItem extends HoeItem {
    public SteelHoeItem(Properties properties) {
        super(new SteelHoeItem.TinHoeTier(), 0, -4.0f + 3.5f, new Properties().stacksTo(1).durability(480).rarity(Rarity.COMMON));
    }

    private static class TinHoeTier implements Tier {
        @Override
        public int getUses() {
            return 480;
        }

        @Override
        public float getSpeed() {
            return 7.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0.0F;
        }

        @Override
        public int getLevel() {
            return 3;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.SteelIngot.get());
        }
    }
}