package net.gobies.additions.recipes;

import com.mojang.logging.LogUtils;
import net.gobies.additions.Config;
import net.gobies.additions.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber
public class AdditionsRecipes {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        try {
            if (Config.ENABLE_GEMS.get()) {
                RecipeManager recipeManager = event.getServer().getRecipeManager();
                var recipes = recipeManager.getRecipes().stream()
                        .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                (recipe.getResultItem(event.getServer().registryAccess()).is(Items.DIAMOND) ||
                                        recipe.getResultItem(event.getServer().registryAccess()).is(Items.EMERALD) ||
                                        recipe.getResultItem(event.getServer().registryAccess()).is(ModItems.Ruby.get()))))
                        .collect(java.util.stream.Collectors.toSet());

                ShapedRecipe diamondRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:diamond"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(ModItems.DiamondGem.get()), Ingredient.of(ModItems.DiamondGem.get()), Ingredient.of(ModItems.DiamondGem.get()),
                                Ingredient.of(ModItems.DiamondGem.get()), Ingredient.of(ModItems.DiamondGem.get()), Ingredient.of(ModItems.DiamondGem.get()),
                                Ingredient.of(ModItems.DiamondGem.get()), Ingredient.of(ModItems.DiamondGem.get()), Ingredient.of(ModItems.DiamondGem.get())
                        ),
                        Items.DIAMOND.getDefaultInstance()
                );

                ShapedRecipe emeraldRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:emerald"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(ModItems.EmeraldGem.get()), Ingredient.of(ModItems.EmeraldGem.get()), Ingredient.of(ModItems.EmeraldGem.get()),
                                Ingredient.of(ModItems.EmeraldGem.get()), Ingredient.of(ModItems.EmeraldGem.get()), Ingredient.of(ModItems.EmeraldGem.get()),
                                Ingredient.of(ModItems.EmeraldGem.get()), Ingredient.of(ModItems.EmeraldGem.get()), Ingredient.of(ModItems.EmeraldGem.get())
                        ),
                        Items.EMERALD.getDefaultInstance()
                );

                ShapedRecipe rubyRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:ruby"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        3, 3,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(ModItems.RubyGem.get()), Ingredient.of(ModItems.RubyGem.get()), Ingredient.of(ModItems.RubyGem.get()),
                                Ingredient.of(ModItems.RubyGem.get()), Ingredient.of(ModItems.RubyGem.get()), Ingredient.of(ModItems.RubyGem.get()),
                                Ingredient.of(ModItems.RubyGem.get()), Ingredient.of(ModItems.RubyGem.get()), Ingredient.of(ModItems.RubyGem.get())
                        ),
                        ModItems.Ruby.get().getDefaultInstance()
                );

                ShapedRecipe diamondToGemsRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:diamond_to_gems"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        1, 1,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.DIAMOND)
                        ),
                        ModItems.DiamondGem.get().getDefaultInstance().copyWithCount(9)
                );

                ShapedRecipe emeraldToGemsRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:emerald_to_gems"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        1, 1,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(Items.EMERALD)
                        ),
                        ModItems.EmeraldGem.get().getDefaultInstance().copyWithCount(9)
                );

                ShapedRecipe rubyToGemsRecipe = new ShapedRecipe(
                        new ResourceLocation("additions:ruby_to_gems"),
                        "minecraft:crafting",
                        CraftingBookCategory.MISC,
                        1, 1,
                        NonNullList.of(Ingredient.EMPTY,
                                Ingredient.of(ModItems.Ruby.get())
                        ),
                        ModItems.RubyGem.get().getDefaultInstance().copyWithCount(9)
                );

                recipes.add(diamondRecipe);
                recipes.add(emeraldRecipe);
                recipes.add(rubyRecipe);
                recipes.add(diamondToGemsRecipe);
                recipes.add(emeraldToGemsRecipe);
                recipes.add(rubyToGemsRecipe);
                recipeManager.replaceRecipes(recipes);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create gem recipes: {}", e.getMessage());
        }
    }
}

