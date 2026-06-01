package net.gobies.additions.compat.champions;

import net.gobies.additions.Additions;
import net.minecraft.world.entity.Entity;
import top.theillusivec4.champions.common.util.ChampionHelper;

public class ChampionsCompat {

    public static boolean isChampion(Entity isChampion) {
        if (Additions.isChampionsLoaded()) {
            return ChampionHelper.isChampionEntity(isChampion);
        }
        return false;
    }
}