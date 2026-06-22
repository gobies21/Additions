package net.gobies.additions.recipe;

import net.gobies.additions.init.AdditionsPotions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.registries.ForgeRegistries;

public class AdditionsBrewing {

    public static void register() {
        addBrewingRecipe(Potions.AWKWARD, Ingredient.of(ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:enchanted_book"))), AdditionsPotions.MagicFocus.get());
        addBrewingRecipe(AdditionsPotions.MagicFocus.get(), Ingredient.of(Items.REDSTONE), AdditionsPotions.LongMagicFocus.get());
        addBrewingRecipe(AdditionsPotions.MagicFocus.get(), Ingredient.of(Items.GLOWSTONE_DUST), AdditionsPotions.StrongMagicFocus.get());
    }

    public static void addBrewingRecipe(Potion basePotion, Ingredient ingredient, Potion resultPotion) {
        ItemStack basePotionStack = PotionUtils.setPotion(new ItemStack(Items.POTION), basePotion);
        ItemStack resultPotionStack = PotionUtils.setPotion(new ItemStack(Items.POTION), resultPotion);
        BrewingRecipeRegistry.addRecipe(new TrueBrewingRecipe(
                Ingredient.of(basePotionStack),
                ingredient,
                resultPotionStack
        ));
    }
}
