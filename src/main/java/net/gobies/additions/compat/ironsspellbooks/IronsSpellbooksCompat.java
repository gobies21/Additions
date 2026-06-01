package net.gobies.additions.compat.ironsspellbooks;

import io.redspace.ironsspellbooks.item.weapons.StaffItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

public class IronsSpellbooksCompat {

    public static boolean isStaff(Item item) {
        if (ModList.get().isLoaded("irons_spellbooks")) {
            return item instanceof StaffItem;
        } else {
            return false;
        }
    }
}
