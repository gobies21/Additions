package net.gobies.additions.compat;

import com.mojang.logging.LogUtils;
import net.gobies.additions.Config;
import net.gobies.additions.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.Objects;

@Mod.EventBusSubscriber
public class CompatRecipes {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        try {
            if (ModList.get().isLoaded("iceandfire")) {
                if (Config.ENABLE_GEMS.get()) {
                    RecipeManager recipeManager = event.getServer().getRecipeManager();
                    var recipes = recipeManager.getRecipes().stream()
                            .filter(recipe -> !(recipe instanceof ShapedRecipe &&
                                    (recipe.getResultItem(event.getServer().registryAccess()).is(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("iceandfire:sapphire_gem")))))))
                            .collect(java.util.stream.Collectors.toSet());
                    ShapedRecipe sapphireRecipe = new ShapedRecipe(
                            new ResourceLocation("additions:sapphire"),
                            "minecraft:crafting",
                            CraftingBookCategory.MISC,
                            3, 3,
                            NonNullList.of(Ingredient.EMPTY,
                                    Ingredient.of(ModItems.SapphireGem.get()), Ingredient.of(ModItems.SapphireGem.get()), Ingredient.of(ModItems.SapphireGem.get()),
                                    Ingredient.of(ModItems.SapphireGem.get()), Ingredient.of(ModItems.SapphireGem.get()), Ingredient.of(ModItems.SapphireGem.get()),
                                    Ingredient.of(ModItems.SapphireGem.get()), Ingredient.of(ModItems.SapphireGem.get()), Ingredient.of(ModItems.SapphireGem.get())
                            ),
                            Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("iceandfire:sapphire_gem"))).getDefaultInstance()
                    );

                    ShapedRecipe sapphireToGemsRecipe = new ShapedRecipe(
                            new ResourceLocation("additions:sapphire_to_gems"),
                            "minecraft:crafting",
                            CraftingBookCategory.MISC,
                            1, 1,
                            NonNullList.of(Ingredient.EMPTY,
                                    Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation("iceandfire:sapphire_gem")))
                            ),
                            ModItems.SapphireGem.get().getDefaultInstance().copyWithCount(9)
                    );

                    recipes.add(sapphireRecipe);
                    recipes.add(sapphireToGemsRecipe);
                    recipeManager.replaceRecipes(recipes);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to create gem compat recipes: {}", e.getMessage());
        }
    }
}
