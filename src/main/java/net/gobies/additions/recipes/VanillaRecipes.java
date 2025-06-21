package net.gobies.additions.recipes;

import com.mojang.logging.LogUtils;
import net.gobies.additions.Config;
import net.gobies.additions.init.AdditionsItems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber
public class VanillaRecipes {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static RegistryAccess registryAccess;

    private static void addRecipe(RecipeManager recipeManager, RegistryAccess registryAccess, Item resultItem, String recipeName, NonNullList<Ingredient> ingredients) {
        try {
            var recipes = recipeManager.getRecipes().stream()
                    .filter(recipe -> !(recipe instanceof ShapedRecipe && recipe.getResultItem(registryAccess).is(resultItem)))
                    .collect(java.util.stream.Collectors.toSet());

            ShapedRecipe newRecipe = new ShapedRecipe(
                    new ResourceLocation("additions:" + recipeName),
                    "minecraft:crafting",
                    CraftingBookCategory.MISC,
                    3, 3,
                    ingredients,
                    resultItem.getDefaultInstance()
            );
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
        if (Config.BLAST_FURNACE_RECIPE_CHANGE.get()) {
            addRecipe(recipeManager, registryAccess, Items.BLAST_FURNACE, "blast_furnace",
                    createIngredients(
                            AdditionsItems.SteelIngot.get(), AdditionsItems.SteelIngot.get(), AdditionsItems.SteelIngot.get(),
                            AdditionsItems.SteelIngot.get(), Items.FURNACE, AdditionsItems.SteelIngot.get(),
                            Items.SMOOTH_STONE, Items.SMOOTH_STONE, Items.SMOOTH_STONE
                    ));
        }

        if (Config.HORSE_ARMOR_RECIPES.get()) {
            addRecipe(recipeManager, registryAccess, Items.LEATHER_HORSE_ARMOR, "leather_horse_armor",
                    createIngredients(
                            Items.AIR, Items.AIR, Items.LEATHER_HELMET,
                            Items.LEATHER, Items.LEATHER, Items.LEATHER,
                            Items.LEATHER_LEGGINGS, Items.AIR, Items.LEATHER_LEGGINGS
                    ));

            addRecipe(recipeManager, registryAccess, Items.IRON_HORSE_ARMOR, "iron_horse_armor",
                    createIngredients(
                            Items.AIR, Items.AIR, Items.IRON_HELMET,
                            Items.IRON_INGOT, Items.IRON_INGOT, Items.IRON_INGOT,
                            Items.IRON_LEGGINGS, Items.AIR, Items.IRON_LEGGINGS
                    ));

            addRecipe(recipeManager, registryAccess, Items.GOLDEN_HORSE_ARMOR, "golden_horse_armor",
                    createIngredients(
                            Items.AIR, Items.AIR, Items.GOLDEN_HELMET,
                            Items.GOLD_INGOT, Items.GOLD_INGOT, Items.GOLD_INGOT,
                            Items.GOLDEN_LEGGINGS, Items.AIR, Items.GOLDEN_LEGGINGS
                    ));

            addRecipe(recipeManager, registryAccess, Items.DIAMOND_HORSE_ARMOR, "diamond_horse_armor",
                    createIngredients(
                            Items.AIR, Items.AIR, Items.DIAMOND_HELMET,
                            Items.DIAMOND, Items.DIAMOND, Items.DIAMOND,
                            Items.DIAMOND_LEGGINGS, Items.AIR, Items.DIAMOND_LEGGINGS
                    ));
        }

        if (Config.SADDLE_RECIPE.get()) {
            addRecipe(recipeManager, registryAccess, Items.SADDLE, "saddle",
                    createIngredients(
                            Items.LEATHER, Items.LEATHER, Items.LEATHER,
                            Items.STRING, Items.AIR, Items.STRING,
                            Items.IRON_INGOT, Items.AIR, Items.IRON_INGOT
                    ));
        }

        if (Config.ENABLE_BUNDLE.get()) {
            addRecipe(recipeManager, registryAccess, Items.BUNDLE, "bundle",
                    createIngredients(
                            Items.STRING, Items.LEATHER, Items.STRING,
                            Items.LEATHER, Items.AIR, Items.LEATHER,
                            Items.LEATHER, Items.LEATHER, Items.LEATHER
                    ));
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

