package net.gobies.additions.worldgen;

import net.gobies.additions.Additions;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class AdditionsPF {

    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED = createKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_TIN_ORE_PLACED = createKey("deepslate_tin_ore_placed");
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED = createKey("ruby_ore_placed");
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, TIN_ORE_PLACED, configuredFeatures.getOrThrow(AdditionsCF.OVERWORLD_TIN_KEY),
                AdditionsOP.commonOrePlacement(5, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(256))));

        register(context, DEEPSLATE_TIN_ORE_PLACED, configuredFeatures.getOrThrow(AdditionsCF.OVERWORLD_DEEPSLATE_TIN_KEY),
                AdditionsOP.commonOrePlacement(3, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(0))));

        register(context, RUBY_ORE_PLACED, configuredFeatures.getOrThrow(AdditionsCF.NETHER_RUBY_KEY),
                AdditionsOP.commonOrePlacement(5, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(115))));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Additions.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {register(context, key, configuration, List.of(modifiers));
    }
}