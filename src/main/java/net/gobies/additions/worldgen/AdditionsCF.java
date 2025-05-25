package net.gobies.additions.worldgen;

import net.gobies.additions.Additions;
import net.gobies.additions.item.blocks.AdditionsBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class AdditionsCF {

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DEEPSLATE_TIN_KEY = registerKey("deepslate_tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_RUBY_KEY = registerKey("nether_ruby_ore");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);

        List<OreConfiguration.TargetBlockState> overworldTinOre = List.of(OreConfiguration.target(stoneReplaceables,
                        AdditionsBlocks.TinOre.get().defaultBlockState()),
                OreConfiguration.target(stoneReplaceables, AdditionsBlocks.TinOre.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> overworldDeepslateTinOre = List.of(OreConfiguration.target(deepslateReplaceables,
                        AdditionsBlocks.DeepslateTinOre.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, AdditionsBlocks.DeepslateTinOre.get().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> netherRubyOre = List.of(OreConfiguration.target(netherrackReplaceables,
                        AdditionsBlocks.RubyOre.get().defaultBlockState()),
                OreConfiguration.target(netherrackReplaceables, AdditionsBlocks.RubyOre.get().defaultBlockState()));

        register(context, OVERWORLD_TIN_KEY, Feature.ORE, new OreConfiguration(overworldTinOre, 8));
        register(context, OVERWORLD_DEEPSLATE_TIN_KEY, Feature.ORE, new OreConfiguration(overworldDeepslateTinOre, 6));
        register(context, NETHER_RUBY_KEY, Feature.ORE, new OreConfiguration(netherRubyOre, 4));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Additions.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}