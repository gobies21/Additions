package net.gobies.additions.enchantment;

import net.gobies.additions.init.AdditionsEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class SnipeEnchantment extends Enchantment {
    public SnipeEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int level) {
        return 20;
    }

    @Override
    public int getMaxCost(int level) {
        return 50;
    }

    @Override
    public boolean checkCompatibility(@NotNull Enchantment pOther) {
        return pOther != AdditionsEnchantments.SPLITSHOT.get() && pOther != AdditionsEnchantments.QUICK_DRAW.get() && super.checkCompatibility(pOther);
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof BowItem;
    }
}