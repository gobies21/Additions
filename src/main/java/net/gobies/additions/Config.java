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
    public static ForgeConfigSpec.ConfigValue<Boolean> LIGHTNING_DESTROY_ITEM;
    public static boolean lightning_destroy_item;
    public static ForgeConfigSpec.ConfigValue<Boolean> ATTACK_THROUGH_GRASS;
    public static boolean attack_through_grass;


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
        lightning_destroy_item = LIGHTNING_DESTROY_ITEM.get();
        attack_through_grass = ATTACK_THROUGH_GRASS.get();
    }

    static {
        BUILDER.push("Tweaks");
        BLAST_FURNACE_RECIPE_CHANGE = BUILDER.comment("Changes the blast furnace recipe to use steel instead of iron").worldRestart().define("Blast_Furnace_Recipe", true);
        SADDLE_RECIPE = BUILDER.comment("Adds a recipe for saddles").worldRestart().define("Saddle_Recipe", true);
        HORSE_ARMOR_RECIPES = BUILDER.comment("Adds recipes for all horse armor").worldRestart().define("Horse_Armor_Recipes", true);
        ENABLE_BUNDLE = BUILDER.comment("Enables the bundle").worldRestart().define("Enable_Bundle", true);
        LIGHTNING_DESTROY_ITEM = BUILDER.comment("Prevents lightning from destroying items").define("Lightning_Prevention", true);
        ATTACK_THROUGH_GRASS = BUILDER.comment("Allows attacking mobs through grass without breaking the grass").define("Attack_Through_Grass", true);
        ENABLE_RANDOM_MOB_HP = BUILDER.comment("Adds rarity to mobs to spawn with random slight increase in max health depending on their rarity type").define("Random_Mob_HP", false);
        BOSS_HP_THRESHOLD = BUILDER.comment("Limits the maximum health percentage that can be randomly increased for certain mobs, like bosses, to prevent their health from becoming too high").define("Boss_HP_Threshold", 1000.0);
        BUILDER.pop();

        BUILDER.push("Additions");
        ENABLE_GEMS = BUILDER.comment("Enable gems, nugget versions for minerals such as diamond").worldRestart().define("Enable_Gems", true);
        ENABLE_FLINT_TOOLS = BUILDER.comment("Enable flint tools, early game tools weaker than wood").worldRestart().define("Enable_Flint_Tools", true);
        STEEL_ARMOR_DAMAGE_REDUCTION = BUILDER.comment("Damage reduction to fire type damage that each steel armor piece provides in percentage").defineInRange("Steel_Fire_Damage_Reduction", 0.125, 0.01, 0.250);
        BUILDER.pop();

        SPEC = BUILDER.build();

    }
}
