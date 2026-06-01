package net.gobies.additions.item.tools;

import net.gobies.additions.init.AdditionsItems;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class AdditionsTiers {

    //Tin Tier
    public static final Tier TIN = new Tier() {
        @Override
        public int getUses() {
            return 160;
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
            return Ingredient.of(AdditionsItems.TinIngot.get());
        }
    };
}
