package net.gobies.additions.mixin;

import net.gobies.additions.init.AdditionsEnchantments;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ArrowItem.class)
public class ArrowItemMixin {
    @Inject(
            method = "isInfinite",
            at = @At("HEAD"),
            remap = false,
            cancellable = true
    )
    private void saveArrow(ItemStack stack, ItemStack bow, Player player, CallbackInfoReturnable<Boolean> cir) {
        int level = EnchantmentHelper.getTagEnchantmentLevel(AdditionsEnchantments.ARROW_RECOVERY.get(), bow);
        if (level > 0) {
            double saveChance = level * 0.10;
            if (player.getRandom().nextDouble() < saveChance) {
                cir.setReturnValue(true);
            }
        }
    }
}