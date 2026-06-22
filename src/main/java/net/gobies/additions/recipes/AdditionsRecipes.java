package net.gobies.additions.recipes;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber
public class AdditionsRecipes {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static RegistryAccess registryAccess;

    private static void addRecipe(RecipeManager recipeManager, RegistryAccess registryAccess, Item resultItem, String recipeName, NonNullList<Ingredient> ingredients, boolean shaped, int count) {
        try {
            var recipes = recipeManager.getRecipes().stream()
                    .filter(recipe -> !(recipe instanceof ShapedRecipe && recipe.getResultItem(registryAccess).is(resultItem)))
                    .collect(java.util.stream.Collectors.toSet());

            var result = resultItem.getDefaultInstance().copyWithCount(count);

            Recipe<?> newRecipe;
            if (shaped) {
                newRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:" + recipeName),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        ingredients,
                        result
                );
            } else {
                newRecipe = new ShapelessRecipe(
                        new ResourceLocation("additions:" + recipeName),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        result,
                        ingredients
                );
            }
            recipes.add(newRecipe);
            recipeManager.replaceRecipes(recipes);
        } catch (Exception e) {
            LOGGER.error("Failed to add recipe for {}: {}", recipeName, e.getMessage());
        }
    }

    private static NonNullList<Ingredient> createPattern(String[] pattern, Object... items) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (String row : pattern) {
            for (char c : row.toCharArray()) {
                if (c == ' ') {
                    ingredients.add(Ingredient.EMPTY);
                } else if (c == '#' && items.length > 0 && items[0] instanceof Item) {
                    ingredients.add(Ingredient.of((Item) items[0]));
                } else if (c == '/' && items.length > 1 && items[1] instanceof Item) {
                    ingredients.add(Ingredient.of((Item) items[1]));
                } else {
                    ingredients.add(Ingredient.EMPTY);
                }
            }
        }
        return ingredients;
    }

    private static void addMirroredRecipe(RecipeManager recipeManager, RegistryAccess registryAccess, Item resultItem, NonNullList<Ingredient> ingredients, String[] pattern, int width, int height) {
        try {
            var recipes = recipeManager.getRecipes().stream()
                    .filter(recipe -> !(recipe instanceof ShapedRecipe && recipe.getResultItem(registryAccess).is(resultItem)))
                    .collect(java.util.stream.Collectors.toSet());

            var result = resultItem.getDefaultInstance().copyWithCount(1);

            Recipe<?> newRecipe = new ShapedRecipe(
                    new ResourceLocation("additions:" + resultItem.toString().toLowerCase()),
                    "minecraft:crafting",
                    CraftingBookCategory.MISC,
                    width, height,
                    ingredients,
                    result
            );
            recipes.add(newRecipe);
            recipeManager.replaceRecipes(recipes);
        } catch (Exception e) {
            LOGGER.error("Failed to add mirrored recipe for {}: {}", resultItem, e.getMessage());
        }
    }

    private static NonNullList<Ingredient> createIngredients(Object... items) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        for (Object item : items) {
            if (item instanceof Item) {
                ingredients.add(Ingredient.of((Item) item));
            } else {
                ingredients.add(Ingredient.EMPTY);
            }
        }
        return ingredients;
    }
}

