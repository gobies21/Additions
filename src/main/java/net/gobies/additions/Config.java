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
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_GEMS;
    public static boolean enable_gems;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_FLINT_TOOLS;
    public static boolean enable_flint_tools;
    public static ForgeConfigSpec.ConfigValue<Double> STEEL_ARMOR_DAMAGE_REDUCTION;
    public static float steel_armor_damage_reduction;
    public static ForgeConfigSpec.ConfigValue<Boolean> INCREASED_REACH;
    public static boolean increased_reach;
    public static ForgeConfigSpec.ConfigValue<Boolean> GRASS_ATTACK;
    public static boolean grass_attack;

    public Config() {
    }

    @SubscribeEvent
    static void onLoad(ModConfigEvent.Loading configEvent) {
        blast_furnace_recipe_change = BLAST_FURNACE_RECIPE_CHANGE.get();
        saddle_recipe = SADDLE_RECIPE.get();
        horse_armor_recipes = HORSE_ARMOR_RECIPES.get();
        enable_bundle = ENABLE_BUNDLE.get();
        enable_gems = ENABLE_GEMS.get();
        enable_flint_tools = ENABLE_FLINT_TOOLS.get();
        steel_armor_damage_reduction = STEEL_ARMOR_DAMAGE_REDUCTION.get().floatValue();
        increased_reach = INCREASED_REACH.get();
        grass_attack = GRASS_ATTACK.get();
    }

    static {
        BUILDER.push("Tweaks");
        BLAST_FURNACE_RECIPE_CHANGE = BUILDER.comment("Changes the blast furnace recipe to use steel instead of iron").worldRestart().define("Blast Furnace Recipe", true);
        SADDLE_RECIPE = BUILDER.comment("Adds a recipe for saddles").worldRestart().define("Saddle Recipe", true);
        HORSE_ARMOR_RECIPES = BUILDER.comment("Adds recipes for all horse armor").worldRestart().define("Horse Armor Recipes", true);
        ENABLE_BUNDLE = BUILDER.comment("Enables the bundle").worldRestart().define("Enable Bundle", true);
        INCREASED_REACH = BUILDER.comment("Increases the default melee reach of the player by +1").worldRestart().define("Increase Melee Reach", false);
        GRASS_ATTACK = BUILDER.comment("Allows the player to be able to hit entities through grass").worldRestart().define("Hit through grass", true);
        BUILDER.pop();

        BUILDER.push("Additions");
        ENABLE_GEMS = BUILDER.comment("Enable gems, nugget versions for minerals such as diamond").worldRestart().define("Enable Gems", true);
        ENABLE_FLINT_TOOLS = BUILDER.comment("Enable flint tools, early game tools weaker than wood").worldRestart().define("Enable Flint Tools", true);
        STEEL_ARMOR_DAMAGE_REDUCTION = BUILDER.comment("Damage reduction to fire type damage that each steel armor piece provides in percentage").defineInRange("Steel Fire Damage Reduction", 0.125, 0.01, 0.250);
        BUILDER.pop();

        SPEC = BUILDER.build();

    }
}
