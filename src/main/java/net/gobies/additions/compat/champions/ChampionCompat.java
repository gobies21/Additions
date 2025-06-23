package net.gobies.additions.compat.champions;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.champions.common.util.ChampionHelper;

public class ChampionCompat {

    public static boolean allowChampion(Entity isChampion) {
        if (ModList.get().isLoaded("champions")) {
            return ChampionHelper.isChampionEntity(isChampion);
        }
        return false;
    }
}