package net.gobies.additions.recipes;

import net.gobies.additions.Config;
import net.gobies.additions.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import com.mojang.logging.LogUtils;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber
public class VanillaRecipes {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        try {
            if (Config.BLAST_FURNACE_RECIPE_CHANGE.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                recipe.getResultItem(event.getServer().registryAccess()).is(Items.BLAST_FURNACE)))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe blastFurnaceRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:blast_furnace"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(ModItems.SteelIngot.get()), Ingredient.of(ModItems.SteelIngot.get()), Ingredient.of(ModItems.SteelIngot.get()),
                                Ingredient.of(ModItems.SteelIngot.get()), Ingredient.of(Items.FURNACE), Ingredient.of(ModItems.SteelIngot.get()),
                                Ingredient.of(Items.SMOOTH_STONE), Ingredient.of(Items.SMOOTH_STONE), Ingredient.of(Items.SMOOTH_STONE)
                        ),
                        Items.BLAST_FURNACE.getDefaultInstance()
                );
                recipes.add(blastFurnaceRecipe);
                recipeManager.replaceRecipes(recipes);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to modify blast furnace recipe: {}", e.getMessage());
        }

        try {
            if (Config.HORSE_ARMOR_RECIPES.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                recipe.getResultItem(event.getServer().registryAccess()).is(Items.LEATHER_HORSE_ARMOR)))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe leatherHorseRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:leather_horse_armor"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.AIR), Ingredient.of(Items.AIR), Ingredient.of(Items.LEATHER_HELMET),
                                Ingredient.of(Items.LEATHER), Ingredient.of(Items.LEATHER), Ingredient.of(Items.LEATHER),
                                Ingredient.of(Items.LEATHER_LEGGINGS), Ingredient.of(Items.AIR), Ingredient.of(Items.LEATHER_LEGGINGS)
                        ),
                        Items.LEATHER_HORSE_ARMOR.getDefaultInstance()
                );
                recipes.add(leatherHorseRecipe);
                recipeManager.replaceRecipes(recipes);
            }

        } catch (Exception e) {
            LOGGER.error("Failed to modify leather horse armor recipe: {}", e.getMessage());
        }

        try {
            if (Config.HORSE_ARMOR_RECIPES.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                recipe.getResultItem(event.getServer().registryAccess()).is(Items.IRON_HORSE_ARMOR)))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe ironHorseRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:iron_horse_armor"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.AIR), Ingredient.of(Items.AIR), Ingredient.of(Items.IRON_HELMET),
                                Ingredient.of(Items.IRON_INGOT), Ingredient.of(Items.IRON_INGOT), Ingredient.of(Items.IRON_INGOT),
                                Ingredient.of(Items.IRON_LEGGINGS), Ingredient.of(Items.AIR), Ingredient.of(Items.IRON_LEGGINGS)
                        ),
                        Items.IRON_HORSE_ARMOR.getDefaultInstance()
                );
                recipes.add(ironHorseRecipe);
                recipeManager.replaceRecipes(recipes);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add iron horse armor recipe: {}", e.getMessage());
        }

        try {
            if (Config.HORSE_ARMOR_RECIPES.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                recipe.getResultItem(event.getServer().registryAccess()).is(Items.GOLDEN_HORSE_ARMOR)))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe goldHorseRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:golden_horse_armor"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.AIR), Ingredient.of(Items.AIR), Ingredient.of(Items.GOLDEN_HELMET),
                                Ingredient.of(Items.GOLD_INGOT), Ingredient.of(Items.GOLD_INGOT), Ingredient.of(Items.GOLD_INGOT),
                                Ingredient.of(Items.GOLDEN_LEGGINGS), Ingredient.of(Items.AIR), Ingredient.of(Items.GOLDEN_LEGGINGS)
                        ),
                        Items.GOLDEN_HORSE_ARMOR.getDefaultInstance()
                );
                recipes.add(goldHorseRecipe);
                recipeManager.replaceRecipes(recipes);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add golden horse armor recipe: {}", e.getMessage());
        }

        try {
            if (Config.HORSE_ARMOR_RECIPES.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                recipe.getResultItem(event.getServer().registryAccess()).is(Items.DIAMOND_HORSE_ARMOR)))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe diamondHorseRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:diamond_horse_armor"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.AIR), Ingredient.of(Items.AIR), Ingredient.of(Items.DIAMOND_HELMET),
                                Ingredient.of(Items.DIAMOND), Ingredient.of(Items.DIAMOND), Ingredient.of(Items.DIAMOND),
                                Ingredient.of(Items.DIAMOND_LEGGINGS), Ingredient.of(Items.AIR), Ingredient.of(Items.DIAMOND_LEGGINGS)
                        ),
                        Items.DIAMOND_HORSE_ARMOR.getDefaultInstance()
                );
                recipes.add(diamondHorseRecipe);
                recipeManager.replaceRecipes(recipes);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add diamond horse armor recipe: {}", e.getMessage());
        }

        try {
            if (Config.SADDLE_RECIPE.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                recipe.getResultItem(event.getServer().registryAccess()).is(Items.SADDLE)))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe saddleRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:saddle"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.LEATHER), Ingredient.of(Items.LEATHER), Ingredient.of(Items.LEATHER),
                                Ingredient.of(Items.STRING), Ingredient.of(Items.AIR), Ingredient.of(Items.STRING),
                                Ingredient.of(Items.IRON_INGOT), Ingredient.of(Items.AIR), Ingredient.of(Items.IRON_INGOT)
                        ),
                        Items.SADDLE.getDefaultInstance()
                );
                recipes.add(saddleRecipe);
                recipeManager.replaceRecipes(recipes);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add saddle recipe: {}", e.getMessage());
        }
        
        try {
            if (Config.ENABLE_BUNDLE.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                recipe.getResultItem(event.getServer().registryAccess()).is(Items.BUNDLE)))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe bundleRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:bundle"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.STRING), Ingredient.of(Items.LEATHER), Ingredient.of(Items.STRING),
                                Ingredient.of(Items.LEATHER), Ingredient.of(Items.AIR), Ingredient.of(Items.LEATHER),
                                Ingredient.of(Items.LEATHER), Ingredient.of(Items.LEATHER), Ingredient.of(Items.LEATHER)
                        ),
                        Items.BUNDLE.getDefaultInstance()
                );
                recipes.add(bundleRecipe);
                recipeManager.replaceRecipes(recipes);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to add bundle recipe: {}", e.getMessage());
        }
    }
}
