package net.gobies.additions;


import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static ForgeConfigSpec.ConfigValue<Boolean> BLAST_FURNACE_RECIPE_CHANGE;
    public static boolean blast_furnace_recipe_change;
    public static ForgeConfigSpec.ConfigValue<Boolean> SADDLE_RECIPE;
    public static boolean saddle_recipe;
    public static ForgeConfigSpec.ConfigValue<Boolean> HORSE_ARMOR_RECIPES;
    public static boolean horse_armor_recipes;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_BUNDLE;
    public static boolean enable_bundle;

    public Config() {
    }

    @SubscribeEvent
    static void onLoad(ModConfigEvent.Loading configEvent) {
        blast_furnace_recipe_change = BLAST_FURNACE_RECIPE_CHANGE.get();
        saddle_recipe = SADDLE_RECIPE.get();
        horse_armor_recipes = HORSE_ARMOR_RECIPES.get();
        enable_bundle = ENABLE_BUNDLE.get();
    }

    static {
        BUILDER.push("Tweaks");
        BLAST_FURNACE_RECIPE_CHANGE = BUILDER.comment("Changes the blast furnace recipe to use steel instead of iron").define("Blast Furnace Recipe", true);
        SADDLE_RECIPE = BUILDER.comment("Adds a recipe for saddles").define("Saddle Recipe", true);
        HORSE_ARMOR_RECIPES = BUILDER.comment("Adds recipes for all horse armor").define("Horse Armor Recipes", true);
        ENABLE_BUNDLE = BUILDER.comment("Enables the bundle").define("Enable Bundle", true);
        BUILDER.pop();
        SPEC = BUILDER.build();

    }
}
