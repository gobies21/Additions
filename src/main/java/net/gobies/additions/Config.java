package net.gobies.additions;

import net.gobies.additions.util.AdditionsUtil;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;

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
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_SPECIAL_SOUNDS;
    public static boolean enable_special_sounds;

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

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> BLACKLISTED_ENTITIES;
    public static List<? extends String> blacklisted_entities;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> WEAPON_SOUND_ADDITIONS;
    public static List<? extends String> weapon_sound_additions;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> TOOL_SOUND_ADDITIONS;
    public static List<? extends String> tool_sound_additions;
    public static ForgeConfigSpec.ConfigValue<Double> SOUND_VOLUME;
    public static double sound_volume;

    @SubscribeEvent
    static void onLoad(ModConfigEvent.Loading event) {
        blast_furnace_recipe_change = BLAST_FURNACE_RECIPE_CHANGE.get();
        saddle_recipe = SADDLE_RECIPE.get();
        horse_armor_recipes = HORSE_ARMOR_RECIPES.get();
        enable_bundle = ENABLE_BUNDLE.get();
        enable_gems = ENABLE_GEMS.get();
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
        blacklisted_entities = BLACKLISTED_ENTITIES.get();
        enable_special_sounds = ENABLE_SPECIAL_SOUNDS.get();
        weapon_sound_additions = WEAPON_SOUND_ADDITIONS.get();
        tool_sound_additions = TOOL_SOUND_ADDITIONS.get();
        sound_volume = SOUND_VOLUME.get();

        AdditionsUtil.bakeConfigCaches();
    }

    static {
        BUILDER.push("Tweaks");
        BLAST_FURNACE_RECIPE_CHANGE = BUILDER.comment("Changes the blast furnace recipe to use steel instead of iron").define("Blast_Furnace_Recipe", true);
        SADDLE_RECIPE = BUILDER.comment("Adds a recipe for saddles").define("Saddle_Recipe", true);
        HORSE_ARMOR_RECIPES = BUILDER.comment("Adds recipes for all horse armor").define("Horse_Armor_Recipes", true);
        ENABLE_BUNDLE = BUILDER.comment("Enables the bundle").define("Enable_Bundle", true);
        LIGHTNING_DESTROY_ITEM = BUILDER.comment("Prevents lightning from destroying items").define("Lightning_Prevention", true);
        ATTACK_THROUGH_GRASS = BUILDER.comment("Allows attacking mobs through grass without breaking the grass").define("Attack_Through_Grass", true);

        BUILDER.push("Mob_Rarity");
        ENABLE_RANDOM_MOB_HP = BUILDER.comment("Adds rarity to mobs to spawn with random slight increase in max health depending on their rarity type").define("Random_Mob_HP", true);
        BOSS_HP_THRESHOLD = BUILDER.comment("Limits the maximum health percentage that can be randomly increased for certain mobs, like bosses, to prevent their health from becoming too high").define("Boss_HP_Threshold", 1000.0);
        MOB_RARITY_DISPLAY_NAME = BUILDER.comment("Displays the rarity of mobs in their name").define("Mob_Rarity_Display_Name", false);
        BLACKLISTED_ENTITIES = BUILDER.comment("Blacklist for entities that cannot spawn with a rarity").defineList("Blacklisted_Entities",List.of("alexsmobs:cosmic_cod"), s -> s instanceof String);

        BUILDER.push("Common").comment("Defines the health increase range for common mobs, e.g. 0.01 + 0.01 = 1-2% max health increase");
        COMMON_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for common mobs").define("Common_HP_Percentage_Min", 0.01);
        COMMON_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value of max health that can be randomly increased for common mobs").define("Common_HP_Percentage_Max", 0.01);
        COMMON_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for common mobs").define("Common_Flat_HP_Increase", 1);
        BUILDER.pop();

        BUILDER.push("Uncommon").comment("Defines the health increase range for uncommon mobs, e.g. 0.02 + 0.02 = 2-4% max health increase");
        UNCOMMON_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for uncommon mobs").define("Uncommon_HP_Percentage_Min", 0.02);
        UNCOMMON_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for uncommon mobs").define("Uncommon_HP_Percentage_Max", 0.02);
        UNCOMMON_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for uncommon mobs").define("Uncommon_Flat_HP_Increase", 2);
        BUILDER.pop();

        BUILDER.push("Rare").comment("Defines the health increase range for rare mobs, e.g. 0.04 + 0.04 = 4-8% max health increase");
        RARE_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for rare mobs").define("Rare_HP_Percentage_Min", 0.04);
        RARE_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for rare mobs").define("Rare_HP_Percentage_Max", 0.04);
        RARE_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for rare mobs").define("Rare_Flat_HP_Increase", 4);
        BUILDER.pop();

        BUILDER.push("Epic").comment("Defines the health increase range for epic mobs, e.g. 0.08 + 0.08 = 8-16% max health increase");
        EPIC_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for epic mobs").define("Epic_HP_Percentage_Min", 0.08);
        EPIC_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for epic mobs").define("Epic_HP_Percentage_Max", 0.08);
        EPIC_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for epic mobs").define("Epic_Flat_HP_Increase", 8);
        BUILDER.pop();

        BUILDER.push("Legendary").comment("Defines the health increase range for legendary mobs, e.g. 0.16 + 0.16 = 16-32% max health increase");
        LEGENDARY_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for legendary mobs").define("Legendary_HP_Percentage_Min", 0.16);
        LEGENDARY_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for legendary mobs").define("Legendary_HP_Percentage_Max", 0.16);
        LEGENDARY_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for legendary mobs").define("Legendary_Flat_HP_Increase", 10);
        BUILDER.pop();

        BUILDER.push("Shiny").comment("Defines the health increase range for shiny mobs, e.g. 0.32 + 0.32 = 32-64% max health increase");
        SHINY_HP_PERCENTAGE_MIN = BUILDER.comment("Minimum percentage of max health that can be randomly increased for shiny mobs").define("Shiny_HP_Percentage_Min", 0.32);
        SHINY_HP_PERCENTAGE_MAX = BUILDER.comment("Maximum added percentage to minimum value that can be randomly increased for shiny mobs").define("Shiny_HP_Percentage_Max", 0.32);
        SHINY_FLAT_HP_INCREASE = BUILDER.comment("Flat increase in max health for shiny mobs").define("Shiny_Flat_HP_Increase", 25);
        BUILDER.pop();

        BUILDER.pop(); // Mob Rarity
        BUILDER.pop(); // Tweaks

        BUILDER.push("Additions");
        ENABLE_GEMS = BUILDER.comment("Enable gems, nugget versions for minerals such as diamond").define("Enable_Gems", true);
        STEEL_ARMOR_DAMAGE_REDUCTION = BUILDER.comment("Damage reduction to fire type damage that each steel armor piece provides in percentage").defineInRange("Steel_Fire_Damage_Reduction", 0.125, 0.01, 0.250);
        ENABLE_SPECIAL_SOUNDS = BUILDER.comment("Enable gems special sounds, an overhaul to common sounds").define("Enable_Special_Sounds", false);

        BUILDER.push("Sound_Settings");
        WEAPON_SOUND_ADDITIONS = BUILDER.comment("Items to count as weapons for special sound effects").defineList("Weapon_Sound_Additions",List.of(), s -> s instanceof String);
        TOOL_SOUND_ADDITIONS = BUILDER.comment("Items to count as tools for special sound effects").defineList("Tool_Sound_Additions",List.of(), s -> s instanceof String);
        SOUND_VOLUME = BUILDER.comment("Global volume value for all special sounds").define("Sound_Volume", 1.0);
        BUILDER.pop(); // Sound Settings

        BUILDER.pop(); // Config End

        SPEC = BUILDER.build();

    }
}
