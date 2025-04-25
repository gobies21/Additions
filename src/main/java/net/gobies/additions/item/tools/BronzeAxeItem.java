package net.gobies.additions.item.tools;

import net.gobies.additions.item.ModItems;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class BronzeAxeItem extends AxeItem {
    public BronzeAxeItem(Properties properties) {
        super(new BronzeAxeItem.TinAxeTier(), 8, -4.0f + 0.9f, new Properties().stacksTo(1).durability(320).rarity(Rarity.COMMON));
    }

    private static class TinAxeTier implements Tier {
        @Override
        public int getUses() {
            return 320;
        }

        @Override
        public float getSpeed() {
            return 6.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0.0F;
        }

        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.BronzeIngot.get());
        }
    }
}

