package net.gobies.additions.mixin;

import net.gobies.additions.config.CommonConfig;
import net.gobies.additions.init.AdditionsAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @ModifyVariable(
            method = "attack(Lnet/minecraft/world/entity/Entity;)V",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 2
    )
    private boolean modifyCrit(boolean original, Entity target) {
        if (!CommonConfig.ENABLE_CRITS.get()) {
            return original;
        }
        Player player = (Player) (Object) this;
        double critRateChance = player.getAttributeValue(AdditionsAttributes.CRITICAL_RATE.get());

        boolean chance = player.getRandom().nextFloat() < critRateChance;
        boolean crit = false;

        if (player.getAttackStrengthScale(0.5F) > 0.9F) {
            crit = chance;
        }

        return crit;
    }
}