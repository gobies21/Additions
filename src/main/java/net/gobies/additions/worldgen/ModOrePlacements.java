package net.gobies.additions.worldgen;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacements {
    public static List<PlacementModifier> orePlacement(PlacementModifier place, PlacementModifier placement) {
        return List.of(place, InSquarePlacement.spread(), placement, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int veins, PlacementModifier placement) {
        return orePlacement(CountPlacement.of(veins), placement);
    }

    public static List<PlacementModifier> rareOrePlacement(int veins, PlacementModifier placement) {
        return orePlacement(RarityFilter.onAverageOnceEvery(veins), placement);
    }
}