package net.gobies.additions.mixin;

import net.gobies.additions.config.CommonConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantRandomlyFunction.class)
public class EnchantRandomlyFunctionMixin {

    @ModifyVariable(
            method = "run(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/storage/loot/LootContext;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 0
    )
    private List<Enchantment> modifyEnchantmentList(List<Enchantment> originalList, ItemStack itemStack, LootContext lootContext) {
        if (!CommonConfig.ENABLE_ENCHANTMENT_MODULE.get() || originalList == null || originalList.isEmpty()) {
            return originalList;
        }
        return new ArrayList<>(originalList);
    }

    @ModifyVariable(
            method = "enchantItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(value = "STORE", ordinal = 0),
            ordinal = 0
    )
    private static int modifyEnchantmentLevel(int originalLevel, ItemStack stack, Enchantment enchantment, RandomSource randomSource) {
        if (!CommonConfig.ENABLE_ENCHANTMENT_MODULE.get() || enchantment == null) {
            return originalLevel;
        }

        int max = enchantment.getMaxLevel();
        int min = enchantment.getMinLevel();

        if (max <= min) {
            return originalLevel;
        }

        ResourceLocation resourceLocation= ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
        if (resourceLocation != null && CommonConfig.ULTRA_RARE_ENCHANTMENTS.get().contains(resourceLocation.toString())) {
            return additions$calculateRarityWeight(8, min, max, randomSource);
        }

        int rarity = switch (enchantment.getRarity()) {
            case COMMON -> 2;
            case UNCOMMON -> 3;
            case RARE -> 4;
            case VERY_RARE -> 5;
        };

        return additions$calculateRarityWeight(rarity, min, max, randomSource);
    }

    @Unique
    private static int additions$calculateRarityWeight(int rarity, int min, int max, RandomSource randomSource) {
        int size = (max - min) + 1;
        int[] weights = new int[size];
        int sum = 0;

        for (int i = 0; i < size; i++) {
            int level = min + i;
            int weight = (int) Math.pow(rarity, max - level);

            if (i == 1) {
                weight = (int) (weight  * 1.5);
            }

            weights[i] = weight;
            sum += weight;
        }

        int roll = randomSource.nextInt(sum);
        for (int i = 0; i < size; i++) {
            roll -= weights[i];
            if (roll < 0) {
                return min + i;
            }
        }

        return max;
    }
}