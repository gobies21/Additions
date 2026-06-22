package net.gobies.additions.config;

import net.gobies.additions.Additions;
import net.gobies.additions.util.AdditionsUtil;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonConfig {
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
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_RANDOM_MOB_HP;
    public static boolean enable_random_mob_hp;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_ENTITY_MODULE;
    public static boolean enable_entity_module;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_MOB_SIZES;
    public static boolean enable_mob_sizes;
    public static ForgeConfigSpec.ConfigValue<Boolean> ONLY_SCALE_ANIMALS;
    public static boolean only_scale_animals;
    public static ForgeConfigSpec.ConfigValue<Double> MIN_MOB_SIZE;
    public static double min_mob_size;
    public static ForgeConfigSpec.ConfigValue<Double> MAX_MOB_SIZE;
    public static double max_mob_size;
    public static ForgeConfigSpec.ConfigValue<Double> BOSS_HP_THRESHOLD;
    public static double boss_hp_threshold;
    public static ForgeConfigSpec.ConfigValue<Boolean> MOB_RARITY_DISPLAY_NAME;
    public static boolean mob_rarity_display_name;
    public static ForgeConfigSpec.ConfigValue<Boolean> LIGHTNING_DESTROY_ITEM;
    public static boolean lightning_destroy_item;
    public static ForgeConfigSpec.ConfigValue<Boolean> ATTACK_THROUGH_GRASS;
    public static boolean attack_through_grass;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_SOUND_MODULE;
    public static boolean enable_sound_module;

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
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ARMOR_LIGHT_ADDITIONS;
    public static List<? extends String> armor_light_additions;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ARMOR_MEDIUM_ADDITIONS;
    public static List<? extends String> armor_medium_additions;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ARMOR_HEAVY_ADDITIONS;
    public static List<? extends String> armor_heavy_additions;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ARMOR_CRYSTAL_ADDITIONS;
    public static List<? extends String> armor_crystal_additions;
    public static ForgeConfigSpec.ConfigValue<Double> SOUND_VOLUME;
    public static double sound_volume;

    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_CRITS;
    public static boolean enable_crits;

    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_SPAWNER_MODULE;
    public static boolean enable_spawner_module;
    public static ForgeConfigSpec.ConfigValue<Integer> MAX_SPAWNER_KILLS;
    public static int max_spawner_kills;
    public static ForgeConfigSpec.ConfigValue<Integer> SPAWNER_KILL_THRESHOLD;
    public static int spawner_kill_threshold;
    public static ForgeConfigSpec.ConfigValue<Integer> SPAWNER_SPEED_INCREASE;
    public static int spawner_speed_increase;
    public static ForgeConfigSpec.ConfigValue<Integer> SPAWNER_BONUS_EXPERIENCE;
    public static int spawner_bonus_experience;

    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_ENCHANTMENT_MODULE;
    public static boolean enable_enchantment_module;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ULTRA_RARE_ENCHANTMENTS;
    public static List<? extends String> ultra_rare_enchantments;
    public static ForgeConfigSpec.ConfigValue<Double> FOCUS_LEVEL_SCALE;
    public static double focus_level_scale;

    public static ForgeConfigSpec.ConfigValue<Boolean> HIDE_COORDINATES;
    public static boolean hide_coordinates;

    @SubscribeEvent
    static void onLoad(ModConfigEvent.Loading event) {
        blast_furnace_recipe_change = BLAST_FURNACE_RECIPE_CHANGE.get();
        saddle_recipe = SADDLE_RECIPE.get();
        horse_armor_recipes = HORSE_ARMOR_RECIPES.get();
        enable_bundle = ENABLE_BUNDLE.get();
        enable_random_mob_hp = ENABLE_RANDOM_MOB_HP.get();
        enable_entity_module = ENABLE_ENTITY_MODULE.get();
        enable_mob_sizes = ENABLE_MOB_SIZES.get();
        only_scale_animals = ONLY_SCALE_ANIMALS.get();
        min_mob_size = MIN_MOB_SIZE.get();
        max_mob_size = MAX_MOB_SIZE.get();
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
        enable_sound_module = ENABLE_SOUND_MODULE.get();
        weapon_sound_additions = WEAPON_SOUND_ADDITIONS.get();
        tool_sound_additions = TOOL_SOUND_ADDITIONS.get();
        armor_light_additions = ARMOR_LIGHT_ADDITIONS.get();
        armor_medium_additions = ARMOR_MEDIUM_ADDITIONS.get();
        armor_heavy_additions = ARMOR_HEAVY_ADDITIONS.get();
        armor_crystal_additions = ARMOR_CRYSTAL_ADDITIONS.get();
        sound_volume = SOUND_VOLUME.get();
        enable_crits = ENABLE_CRITS.get();
        enable_spawner_module = ENABLE_SPAWNER_MODULE.get();
        max_spawner_kills = MAX_SPAWNER_KILLS.get();
        spawner_kill_threshold = SPAWNER_KILL_THRESHOLD.get();
        spawner_speed_increase = SPAWNER_SPEED_INCREASE.get();
        spawner_bonus_experience = SPAWNER_BONUS_EXPERIENCE.get();
        enable_enchantment_module = ENABLE_ENCHANTMENT_MODULE.get();
        ultra_rare_enchantments = ULTRA_RARE_ENCHANTMENTS.get();
        focus_level_scale = FOCUS_LEVEL_SCALE.get();
        hide_coordinates =  HIDE_COORDINATES.get();

        AdditionsUtil.bakeConfigCaches();
    }

    static {
        BUILDER.push("Misc_Tweaks");
        BLAST_FURNACE_RECIPE_CHANGE = BUILDER.comment("Changes the blast furnace recipe to use steel instead of iron").define("Blast_Furnace_Recipe", true);
        SADDLE_RECIPE = BUILDER.comment("Adds a recipe for saddles").define("Saddle_Recipe", true);
        HORSE_ARMOR_RECIPES = BUILDER.comment("Adds recipes for all horse armor").define("Horse_Armor_Recipes", true);
        ENABLE_BUNDLE = BUILDER.comment("Enables the bundle").define("Enable_Bundle", true);
        ENABLE_CRITS = BUILDER.comment("Enables crits to follow the critical rate attribute instead of being based off of jumping").define("Enable_Crits", true);
        LIGHTNING_DESTROY_ITEM = BUILDER.comment("Prevents lightning from destroying items").define("Lightning_Prevention", true);
        ATTACK_THROUGH_GRASS = BUILDER.comment("Allows attacking mobs through grass without breaking the grass").define("Attack_Through_Grass", true);
        HIDE_COORDINATES = BUILDER.comment("Hide coordinates from the debug menu screen").define("Hide coordinates", false);
        BUILDER.pop();

        BUILDER.push("Spawner_Module");
        ENABLE_SPAWNER_MODULE = BUILDER.comment("Makes spawners have limited spawns while also increasing how fast mobs spawn per kill threshold reached").define("Enable_Spawner_Module", true);
        MAX_SPAWNER_KILLS = BUILDER.comment("Maximum amount of mobs that can be spawned from spawners").define("Max_Spawns", 20);
        SPAWNER_KILL_THRESHOLD = BUILDER.comment("Number of mobs killed to start increasing the speed of the spawner per amount").define("Spawner_Threshold", 5);
        SPAWNER_SPEED_INCREASE = BUILDER.comment("Amount of ticks to remove from spawn delays when the threshold is reached").define("Spawner_Speed_Increase", 100);
        SPAWNER_BONUS_EXPERIENCE = BUILDER.comment("Bonus experience from spawners after the threshold has been reached").define("Bonus_Experience", 1);
        BUILDER.pop();

        BUILDER.push("Entity_Module");
        ENABLE_ENTITY_MODULE = BUILDER.comment("Enable entity module, makes changes to various parts of entities").define("Entity_Module", true);
        BUILDER.push("Mob_Sizes");
        ENABLE_MOB_SIZES = BUILDER.comment("Adds random varying sizes to mob spawns to add more life to the world").define("Mob_Sizes", false);
        ONLY_SCALE_ANIMALS = BUILDER.comment("Only scale animals, not any other entity").define("Only_Scale_Animals", true);
        MIN_MOB_SIZE = BUILDER.comment("Minimum size a mob can be").define("Min_Size", 0.9);
        MAX_MOB_SIZE = BUILDER.comment("Maximum size a mob can be").define("Max_Size", 1.1);
        BUILDER.pop();
        BUILDER.push("Mob_Rarity");
        ENABLE_RANDOM_MOB_HP = BUILDER.comment("Adds rarity to mobs to spawn with random slight increase in max health depending on their rarity type").define("Mob_Rarity", true);
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
        BUILDER.pop(); // Entity Module

        BUILDER.push("Sound_Module");
        ENABLE_SOUND_MODULE = BUILDER.comment("Enable sound module, adds sounds where some should be").define("Enable_Sound_Module", false);
        WEAPON_SOUND_ADDITIONS = BUILDER.comment("Items to count as weapons for special sound effects").defineList("Weapon_Sound_Additions",List.of(), s -> s instanceof String);
        TOOL_SOUND_ADDITIONS = BUILDER.comment("Items to count as tools for special sound effects").defineList("Tool_Sound_Additions",List.of(), s -> s instanceof String);
        SOUND_VOLUME = BUILDER.comment("Global volume value for all special sounds").define("Sound_Volume", 1.0);

        BUILDER.push("Armor");
        ARMOR_LIGHT_ADDITIONS = BUILDER.comment("Armors that count as light armors").defineList("Light_Armors",List.of(), s -> s instanceof String);
        ARMOR_MEDIUM_ADDITIONS = BUILDER.comment("Armors that count as medium armors").defineList("Medium_Armors",List.of(), s -> s instanceof String);
        ARMOR_HEAVY_ADDITIONS = BUILDER.comment("Armors that count as heavy armors").defineList("Heavy_Armors",List.of(), s -> s instanceof String);
        ARMOR_CRYSTAL_ADDITIONS = BUILDER.comment("Armors that count as crystal armors").defineList("Crystal_Armors",List.of(), s -> s instanceof String);
        BUILDER.pop(); // Armor Settings

        BUILDER.pop(); // Sound Module

        BUILDER.push("Enchantment_Module");
        ENABLE_ENCHANTMENT_MODULE = BUILDER.comment("Enable enchantment module, makes lower level enchantments more common while higher level ones are more rare").define("Enable_Enchantment_Module", true);
        ULTRA_RARE_ENCHANTMENTS = BUILDER.comment("Enchantments that are considered rarer then all others").defineList("Ultra_Rare_Enchantments",List.of("minecraft:mending"), s -> s instanceof String);
        FOCUS_LEVEL_SCALE = BUILDER.comment("Enchantment levels added per enchantment focus attribute").defineInRange("Focus_Level_Scale", 2.5, 0.0, 10.0);
        BUILDER.pop();


        SPEC = BUILDER.build();

    }
}
