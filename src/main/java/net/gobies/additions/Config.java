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
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_RANDOM_MOB_HP;
    public static boolean enable_random_mob_hp;
    public static ForgeConfigSpec.ConfigValue<Double> BOSS_HP_THRESHOLD;
    public static double boss_hp_threshold;
    public static ForgeConfigSpec.ConfigValue<Boolean> MOB_RARITY_DISPLAY_NAME;
    public static boolean mob_rarity_display_name;
    public static ForgeConfigSpec.ConfigValue<Boolean> LIGHTNING_DESTROY_ITEM;
    public static boolean lightning_destroy_item;
    public static ForgeConfigSpec.ConfigValue<Boolean> ATTACK_THROUGH_GRASS;
    public static boolean attack_through_grass;

    public static ForgeConfigSpec.ConfigValue<Double> COMMON_HP_PERCENTAGE_MIN;
    public static double common_hp_percentage_min;
    public static ForgeConfigSpec.ConfigValue<Double> COMMON_HP_PERCENTAGE_MAX;
    public static double common_hp_percentage_max;
    public static ForgeConfigSpec.ConfigValue<Integer> COMMON_FLAT_HP_INCREASE;
    public static int common_flat_hp_increase;


    public static ForgeConfigSpec.ConfigValue<Double> UNCOMMON_HP_PERCENTAGE_MIN;
    public static double uncommon_hp_percentage_min;
    public static ForgeConfigSpec.ConfigValue<Double> UNCOMMON_HP_PERCENTAGE_MAX;
    public static double uncommon_hp_percentage_max;
    public static ForgeConfigSpec.ConfigValue<Integer> UNCOMMON_FLAT_HP_INCREASE;
    public static int uncommon_flat_hp_increase;

    public static ForgeConfigSpec.ConfigValue<Double> RARE_HP_PERCENTAGE_MIN;
    public static double rare_hp_percentage_min;
    public static ForgeConfigSpec.ConfigValue<Double> RARE_HP_PERCENTAGE_MAX;
    public static double rare_hp_percentage_max;
    public static ForgeConfigSpec.ConfigValue<Integer> RARE_FLAT_HP_INCREASE;
    public static int rare_flat_hp_increase;

    public static ForgeConfigSpec.ConfigValue<Double> EPIC_HP_PERCENTAGE_MIN;
    public static double epic_hp_percentage_min;
    public static ForgeConfigSpec.ConfigValue<Double> EPIC_HP_PERCENTAGE_MAX;
    public static double epic_hp_percentage_max;
    public static ForgeConfigSpec.ConfigValue<Integer> EPIC_FLAT_HP_INCREASE;
    public static int epic_flat_hp_increase;

    public static ForgeConfigSpec.ConfigValue<Double> LEGENDARY_HP_PERCENTAGE_MIN;
    public static double legendary_hp_percentage_min;
    public static ForgeConfigSpec.ConfigValue<Double> LEGENDARY_HP_PERCENTAGE_MAX;
    public static double legendary_hp_percentage_max;
    public static ForgeConfigSpec.ConfigValue<Integer> LEGENDARY_FLAT_HP_INCREASE;
    public static int legendary_flat_hp_increase;

    public static ForgeConfigSpec.ConfigValue<Double> SHINY_HP_PERCENTAGE_MIN;
    public static double shiny_hp_percentage_min;
    public static ForgeConfigSpec.ConfigValue<Double> SHINY_HP_PERCENTAGE_MAX;
    public static double shiny_hp_percentage_max;
    public static ForgeConfigSpec.ConfigValue<Integer> SHINY_FLAT_HP_INCREASE;
    public static int shiny_flat_hp_increase;


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
        enable_random_mob_hp = ENABLE_RANDOM_MOB_HP.get();
        boss_hp_threshold = BOSS_HP_THRESHOLD.get();
        mob_rarity_display_name = MOB_RARITY_DISPLAY_NAME.get();
        lightning_destroy_item = LIGHTNING_DESTROY_ITEM.get();
        attack_through_grass = ATTACK_THROUGH_GRASS.get();
        common_hp_percentage_min = COMMON_HP_PERCENTAGE_MIN.get();
        common_hp_percentage_max = COMMON_HP_PERCENTAGE_MAX.get();
        common_flat_hp_increase = COMMON_FLAT_HP_INCREASE.get();
        uncommon_hp_percentage_min = UNCOMMON_HP_PERCENTAGE_MIN.get();
        uncommon_hp_percentage_max = UNCOMMON_HP_PERCENTAGE_MAX.get();
        uncommon_flat_hp_increase = UNCOMMON_FLAT_HP_INCREASE.get();
        rare_hp_percentage_min = RARE_HP_PERCENTAGE_MIN.get();
        rare_hp_percentage_max = RARE_HP_PERCENTAGE_MAX.get();
        rare_flat_hp_increase = RARE_FLAT_HP_INCREASE.get();
        epic_hp_percentage_min = EPIC_HP_PERCENTAGE_MIN.get();
        epic_hp_percentage_max = EPIC_HP_PERCENTAGE_MAX.get();
        epic_flat_hp_increase = EPIC_FLAT_HP_INCREASE.get();
        legendary_hp_percentage_min = LEGENDARY_HP_PERCENTAGE_MIN.get();
        legendary_hp_percentage_max = LEGENDARY_HP_PERCENTAGE_MAX.get();
        legendary_flat_hp_increase = LEGENDARY_FLAT_HP_INCREASE.get();
        shiny_hp_percentage_min = SHINY_HP_PERCENTAGE_MIN.get();
        shiny_hp_percentage_max = SHINY_HP_PERCENTAGE_MAX.get();
        shiny_flat_hp_increase = SHINY_FLAT_HP_INCREASE.get();
    }

    static {
        BUILDER.push("Tweaks");
        BLAST_FURNACE_RECIPE_CHANGE = BUILDER.comment("Changes the blast furnace recipe to use steel instead of iron").worldRestart().define("Blast_Furnace_Recipe", true);
        SADDLE_RECIPE = BUILDER.comment("Adds a recipe for saddles").worldRestart().define("Saddle_Recipe", true);
        HORSE_ARMOR_RECIPES = BUILDER.comment("Adds recipes for all horse armor").worldRestart().define("Horse_Armor_Recipes", true);
        ENABLE_BUNDLE = BUILDER.comment("Enables the bundle").worldRestart().define("Enable_Bundle", true);
        LIGHTNING_DESTROY_ITEM = BUILDER.comment("Prevents lightning from destroying items").define("Lightning_Prevention", true);
        ATTACK_THROUGH_GRASS = BUILDER.comment("Allows attacking mobs through grass without breaking the grass").define("Attack_Through_Grass", true);

        BUILDER.push("Mob_Rarity");
        ENABLE_RANDOM_MOB_HP = BUILDER.comment("Adds rarity to mobs to spawn with random slight increase in max health depending on their rarity type").define("Random_Mob_HP", true);
        BOSS_HP_THRESHOLD = BUILDER.comment("Limits the maximum health percentage that can be randomly increased for certain mobs, like bosses, to prevent their health from becoming too high").define("Boss_HP_Threshold", 1000.0);
        MOB_RARITY_DISPLAY_NAME = BUILDER.comment("Displays the rarity of mobs in their name").define("Mob_Rarity_Display_Name", false);

        BUILDER.push("Common");
        BUILDER.comment("Defines the health increase range for common mobs, e.g.: 0.01 + 0.01 = 1-2% max health increase");
        COMMON_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for common mobs").define("Common_HP_Percentage_Min", 0.01);
        COMMON_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value of max health that can be randomly increased for common mobs").define("Common_HP_Percentage_Max", 0.01);
        COMMON_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for common mobs").define("Common_Flat_HP_Increase", 1);
        BUILDER.pop();

        BUILDER.push("Uncommon");
        BUILDER.comment("Defines the health increase range for uncommon mobs, e.g.: 0.02 + 0.04 = 2-4% max health increase");
        UNCOMMON_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for uncommon mobs").define("Uncommon_HP_Percentage_Min", 0.02);
        UNCOMMON_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for uncommon mobs").define("Uncommon_HP_Percentage_Max", 0.04);
        UNCOMMON_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for uncommon mobs").define("Uncommon_Flat_HP_Increase", 2);
        BUILDER.pop();

        BUILDER.push("Rare");
        BUILDER.comment("Defines the health increase range for rare mobs, e.g.: 0.04 + 0.02 = 4-6% max health increase");
        RARE_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for rare mobs").define("Rare_HP_Percentage_Min", 0.04);
        RARE_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for rare mobs").define("Rare_HP_Percentage_Max", 0.02);
        RARE_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for rare mobs").define("Rare_Flat_HP_Increase", 5);
        BUILDER.pop();

        BUILDER.push("Epic");
        BUILDER.comment("Defines the health increase range for epic mobs, e.g.: 0.06 + 0.02 = 6-8% max health increase");
        EPIC_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for epic mobs").define("Epic_HP_Percentage_Min", 0.06);
        EPIC_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for epic mobs").define("Epic_HP_Percentage_Max", 0.02);
        EPIC_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for epic mobs").define("Epic_Flat_HP_Increase", 8);
        BUILDER.pop();

        BUILDER.push("Legendary");
        BUILDER.comment("Defines the health increase range for legendary mobs, e.g.: 0.08 + 0.07 = 8-15% max health increase");
        LEGENDARY_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for legendary mobs").define("Legendary_HP_Percentage_Min", 0.08);
        LEGENDARY_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for legendary mobs").define("Legendary_HP_Percentage_Max", 0.07);
        LEGENDARY_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for legendary mobs").define("Legendary_Flat_HP_Increase", 10);
        BUILDER.pop();

        BUILDER.push("Shiny");
        BUILDER.comment("Defines the health increase range for shiny mobs, e.g.: 0.20 + 0.20 = 20-40% max health increase");
        SHINY_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for shiny mobs").define("Shiny_HP_Percentage_Min", 0.20);
        SHINY_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for shiny mobs").define("Shiny_HP_Percentage_Max", 0.20);
        SHINY_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for shiny mobs").define("Shiny_Flat_HP_Increase", 25);
        BUILDER.pop();

        BUILDER.pop(); // Mob Rarity
        BUILDER.pop(); // Tweaks

        BUILDER.push("Additions");
        ENABLE_GEMS = BUILDER.comment("Enable gems, nugget versions for minerals such as diamond").worldRestart().define("Enable_Gems", true);
        ENABLE_FLINT_TOOLS = BUILDER.comment("Enable flint tools, early game tools weaker than wood").worldRestart().define("Enable_Flint_Tools", true);
        STEEL_ARMOR_DAMAGE_REDUCTION = BUILDER.comment("Damage reduction to fire type damage that each steel armor piece provides in percentage").defineInRange("Steel_Fire_Damage_Reduction", 0.125, 0.01, 0.250);
        BUILDER.pop();

        SPEC = BUILDER.build();

    }
}
