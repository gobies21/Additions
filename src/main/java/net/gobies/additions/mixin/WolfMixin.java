package net.gobies.additions.mixin;

import net.gobies.additions.util.MobUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = Wolf.class, priority = 100)
public class WolfMixin {

    @ModifyConstant(
            method = "setTame",
            constant = @Constant(doubleValue = 20.0D)
    )
    public double setTame(double health) {
        LivingEntity wolf = (LivingEntity) (Object) this;
        double bonusHealth = MobUtils.getBonusHealth(wolf);
        return 20.0D + bonusHealth;
    }
    @ModifyConstant(
            method = "setTame",
            constant = @Constant(floatValue = 20.0F)
    )
    public float setTame(float health) {
        LivingEntity wolf = (LivingEntity) (Object) this;
        float bonusHealth = MobUtils.getBonusHealth(wolf);
        return 20.0F + bonusHealth;
    }
}

