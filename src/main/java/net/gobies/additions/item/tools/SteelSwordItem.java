package net.gobies.additions.item.tools;

import net.gobies.additions.item.ModItems;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class SteelSwordItem extends SwordItem {
    public SteelSwordItem(Properties properties) {
        super(new SteelSwordItem.TinSwordTier(), 5, -4.0f + 1.6f, new Properties().stacksTo(1).durability(480).rarity(Rarity.COMMON));
    }

    private static class TinSwordTier implements Tier {
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
            return 0.5F;
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
