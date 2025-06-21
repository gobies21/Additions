package net.gobies.additions.recipes;

import net.gobies.additions.Config;
import net.gobies.additions.init.AdditionsItems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import com.mojang.logging.LogUtils;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

    private static void registerRecipes(RecipeManager recipeManager, RegistryAccess registryAccess) {
        if (Config.ENABLE_GEMS.get()) {
            addRecipe(recipeManager, registryAccess, Items.DIAMOND, "diamond",
                    createIngredients(
                            AdditionsItems.DiamondGem.get(), AdditionsItems.DiamondGem.get(), AdditionsItems.DiamondGem.get(),
                            AdditionsItems.DiamondGem.get(), AdditionsItems.DiamondGem.get(), AdditionsItems.DiamondGem.get(),
                            AdditionsItems.DiamondGem.get(), AdditionsItems.DiamondGem.get(), AdditionsItems.DiamondGem.get()
                    ), true, 1);

            addRecipe(recipeManager, registryAccess, AdditionsItems.DiamondGem.get(), "diamond_to_gems",
                    createIngredients(Items.DIAMOND), false, 9);

            addRecipe(recipeManager, registryAccess, Items.EMERALD, "emerald",
                    createIngredients(
                            AdditionsItems.EmeraldGem.get(), AdditionsItems.EmeraldGem.get(), AdditionsItems.EmeraldGem.get(),
                            AdditionsItems.EmeraldGem.get(), AdditionsItems.EmeraldGem.get(), AdditionsItems.EmeraldGem.get(),
                            AdditionsItems.EmeraldGem.get(), AdditionsItems.EmeraldGem.get(), AdditionsItems.EmeraldGem.get()
                    ), true, 1);

            addRecipe(recipeManager, registryAccess, AdditionsItems.EmeraldGem.get(), "emerald_to_gems",
                    createIngredients(Items.EMERALD), false, 9);

            addRecipe(recipeManager, registryAccess, AdditionsItems.Ruby.get(), "ruby",
                    createIngredients(
                            AdditionsItems.RubyGem.get(), AdditionsItems.RubyGem.get(), AdditionsItems.RubyGem.get(),
                            AdditionsItems.RubyGem.get(), AdditionsItems.RubyGem.get(), AdditionsItems.RubyGem.get(),
                            AdditionsItems.RubyGem.get(), AdditionsItems.RubyGem.get(), AdditionsItems.RubyGem.get()
                    ), true, 1);

            addRecipe(recipeManager, registryAccess, AdditionsItems.RubyGem.get(), "ruby_to_gems",
                    createIngredients(AdditionsItems.Ruby.get()), false, 9);
        }

        if (Config.ENABLE_FLINT_TOOLS.get()) {
            addMirroredRecipe(recipeManager, registryAccess, AdditionsItems.FlintSword.get(),
                    createIngredients(Items.FLINT, Items.FLINT, Items.STICK),
                    new String[]{"#",
                                 "#",
                                 "/"}, 1, 3);

            addMirroredRecipe(recipeManager, registryAccess, AdditionsItems.FlintPickaxe.get(),
                    createPattern(new String[]{"###", " / ", " / "}, Items.FLINT, Items.STICK),
                    new String[]{"###",
                                 " / ",
                                 " / "}, 3, 3);

            addMirroredRecipe(recipeManager, registryAccess, AdditionsItems.FlintAxe.get(),
                    createPattern(new String[]{"/#", "/ "}, Items.FLINT, Items.STICK),
                    new String[]{"/#",
                                 "/ "}, 2, 2);

            addMirroredRecipe(recipeManager, registryAccess, AdditionsItems.FlintHoe.get(),
                    createPattern(new String[]{"## ", " / ", " / "}, Items.FLINT, Items.STICK),
                    new String[]{"## ",
                                 " / ",
                                 " / "}, 3, 3);

            addMirroredRecipe(recipeManager, registryAccess, AdditionsItems.FlintShovel.get(),
                    createIngredients(Items.FLINT, Items.STICK, Items.STICK),
                    new String[]{"#",
                                 "/",
                                 "/"}, 1, 3);
        }
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        registryAccess = event.getServer().registryAccess();
        registerRecipes(event.getServer().getRecipeManager(), registryAccess);
    }

    @SubscribeEvent
    public static void onRecipesUpdated(AddReloadListenerEvent event) {
        registerRecipes(event.getServerResources().getRecipeManager(), registryAccess);
    }
}

