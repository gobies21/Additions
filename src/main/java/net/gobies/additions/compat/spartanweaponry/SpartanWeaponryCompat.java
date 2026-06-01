package net.gobies.additions.compat.spartanweaponry;

import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class SpartanWeaponryCompat {

    public static boolean isHeavyCrossbow(Item crossbow) {
        if (ModList.get().isLoaded("spartanweaponry")) {
            return crossbow instanceof HeavyCrossbowItem;
        }
        return false;
    }
}
