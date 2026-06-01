package net.gobies.additions.compat.pml;

import com.kettle.pml.core.PMLDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;

public class PMLCompat {

    public static boolean isCriticalHit(@Nullable DamageSource isCritical) {
        if (ModList.get().isLoaded("pml") && isCritical != null) {
            return PMLDamageTypes.isCritical(isCritical);
        }
        return false;
    }
}
