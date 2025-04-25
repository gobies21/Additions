package net.gobies.additions.item.tools;

import net.gobies.additions.item.ModItems;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class TinHoeItem extends HoeItem {
    public TinHoeItem(Item.Properties properties) {
        super(new TinHoeItem.TinHoeTier(), 0, -4.0f + 1.5f, new Item.Properties().stacksTo(1).durability(212).rarity(Rarity.COMMON));
    }

    private static class TinHoeTier implements Tier {
        @Override
        public int getUses() {
            return 180;
        }

        @Override
        public float getSpeed() {
            return 5.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 0.0F;
        }

        @Override
        public int getLevel() {
            return 1;
        }

        @Override
        public int getEnchantmentValue() {
            return 15;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(ModItems.TinIngot.get());
        }
    }
}