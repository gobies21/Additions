package net.gobies.additions.mixin.easymagic;

import fuzs.easymagic.world.inventory.ModEnchantmentMenu;
import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.init.AdditionsAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModEnchantmentMenu.class)
public abstract class ModEnchantmentMenuMixin extends EnchantmentMenu {

    @Final
    @Shadow(remap = false)
    private Player player;

    protected ModEnchantmentMenuMixin(int id, Inventory playerInventory) {
        super(id, playerInventory);
    }

    @Inject(
            method = "updateLevels",
            at = @At("TAIL"),
            remap = false
    )
    private void onUpdateLevels(ItemStack itemstack, Level world, BlockPos pos, int power, CallbackInfo ci) {
        if (this.player == null) return;

        AttributeInstance focusAttribute = this.player.getAttribute(AdditionsAttributes.ENCHANTING_FOCUS.get());
        if (focusAttribute == null) return;

        double focusValue = focusAttribute.getValue();
        if (focusValue <= 0) return;

        double finalScale = CommonConfig.FOCUS_LEVEL_SCALE.get();
        int addedLevels = (int) (focusValue * finalScale);
        double middleSlotMultiplier = Math.max(0.1, finalScale - 0.5);

        for (int slot = 0; slot < 3; slot++) {
            int originalLevel = this.costs[slot];
            if (originalLevel <= 0) continue;

            int maxAllowedLevel = switch (slot) {
                case 0 -> 8 + (int) focusValue;
                case 1 -> 20 + (int) (focusValue * middleSlotMultiplier);
                case 2 -> 30 + addedLevels;
                default -> 30;
            };

            this.costs[slot] = Math.min(originalLevel + addedLevels, maxAllowedLevel);
        }
    }
}