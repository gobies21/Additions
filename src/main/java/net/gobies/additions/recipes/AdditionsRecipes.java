package net.gobies.additions.recipes;

import net.gobies.additions.Config;
import net.gobies.additions.item.ModItems;
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
                            ModItems.DiamondGem.get(), ModItems.DiamondGem.get(), ModItems.DiamondGem.get(),
                            ModItems.DiamondGem.get(), ModItems.DiamondGem.get(), ModItems.DiamondGem.get(),
                            ModItems.DiamondGem.get(), ModItems.DiamondGem.get(), ModItems.DiamondGem.get()
                    ), true, 1);
            
            addRecipe(recipeManager, registryAccess, ModItems.DiamondGem.get(), "diamond_to_gems",
                    createIngredients(Items.DIAMOND), false, 9);

            addRecipe(recipeManager, registryAccess, Items.EMERALD, "emerald",
                    createIngredients(
                            ModItems.EmeraldGem.get(), ModItems.EmeraldGem.get(), ModItems.EmeraldGem.get(),
                            ModItems.EmeraldGem.get(), ModItems.EmeraldGem.get(), ModItems.EmeraldGem.get(),
                            ModItems.EmeraldGem.get(), ModItems.EmeraldGem.get(), ModItems.EmeraldGem.get()
                    ), true, 1);

            addRecipe(recipeManager, registryAccess, ModItems.EmeraldGem.get(), "emerald_to_gems",
                    createIngredients(Items.EMERALD), false, 9);

            addRecipe(recipeManager, registryAccess, ModItems.Ruby.get(), "ruby",
                    createIngredients(
                            ModItems.RubyGem.get(), ModItems.RubyGem.get(), ModItems.RubyGem.get(),
                            ModItems.RubyGem.get(), ModItems.RubyGem.get(), ModItems.RubyGem.get(),
                            ModItems.RubyGem.get(), ModItems.RubyGem.get(), ModItems.RubyGem.get()
                    ), true, 1);

            addRecipe(recipeManager, registryAccess, ModItems.RubyGem.get(), "ruby_to_gems",
                    createIngredients(ModItems.Ruby.get()), false, 9);
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

