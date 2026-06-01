package net.gobies.additions.util;

import net.gobies.additions.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class AdditionsUtil {

    public static boolean isValidWeapon(Player player) {
        ItemStack heldItem = player.getMainHandItem();
        ResourceLocation weaponId = ForgeRegistries.ITEMS.getKey(heldItem.getItem());
        return weaponId != null && Config.WEAPON_SOUND_ADDITIONS.get().contains(weaponId.toString());
    }

    public static boolean isValidTool(Player player) {
        ItemStack heldItem = player.getMainHandItem();
        ResourceLocation toolId = ForgeRegistries.ITEMS.getKey(heldItem.getItem());
        return toolId != null && Config.TOOL_SOUND_ADDITIONS.get().contains(toolId.toString());
    }
}
