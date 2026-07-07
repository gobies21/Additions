package net.gobies.additions.mixin;

import net.gobies.additions.init.AdditionsEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow public abstract ItemStack getUseItem();
    @Shadow protected int useItemRemaining;

    @Unique
    private float additions$drawTicks = 0.0f;

    @Inject(
            method = "updateUsingItem",
            at = @At("HEAD")
    )
    private void quickDraw(CallbackInfo ci) {
        ItemStack activeItem = this.getUseItem();
        if (activeItem.isEmpty() || !(activeItem.getItem() instanceof BowItem)) {
            this.additions$drawTicks = 0.0f;
            return;
        }

        if (this.useItemRemaining > 0) {
            int enchantmentLevel = EnchantmentHelper.getTagEnchantmentLevel(AdditionsEnchantments.QUICK_DRAW.get(), activeItem);
            if (enchantmentLevel > 0) {
                float drawSpeedBonus = enchantmentLevel * 0.10f;
                this.additions$drawTicks += drawSpeedBonus;
                int ticksToSubtract = (int) this.additions$drawTicks;
                if (ticksToSubtract > 0) {
                    this.useItemRemaining = Math.max(0, this.useItemRemaining - ticksToSubtract);
                    this.additions$drawTicks -= ticksToSubtract;
                }
            }
        }
        int snipeLevel = EnchantmentHelper.getTagEnchantmentLevel(AdditionsEnchantments.SNIPE.get(), activeItem);
        if (snipeLevel > 0) {
            LivingEntity entity = (LivingEntity) (Object) this;
            if (entity.tickCount % 2 == 0) {
                this.useItemRemaining += 1;
            }
        }
    }
}