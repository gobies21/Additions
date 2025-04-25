package net.gobies.additions.item.tools;

import net.gobies.additions.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class TinShovelItem extends ShovelItem {
    public TinShovelItem(Item.Properties properties) {
        super(new TinShovelItem.TinShovelTier(), 3, -4.0f + 1, new Item.Properties().stacksTo(1).durability(180).rarity(Rarity.COMMON));
    }

    private static class TinShovelTier implements Tier {
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
