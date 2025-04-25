package net.gobies.additions.item.tools;

import net.gobies.additions.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class TinPickaxeItem extends PickaxeItem {
    public TinPickaxeItem(Item.Properties properties) {
        super(new TinPickaxeItem.TinPickaxeTier(), 2, -4.0f + 1.2f, new Item.Properties().stacksTo(1).durability(180).rarity(Rarity.COMMON));
    }

    private static class TinPickaxeTier implements Tier {
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
            return 0.5F;
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
