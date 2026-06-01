package net.gobies.additions.util;

import net.gobies.additions.Config;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AdditionsUtil {

    private static final Set<String> WEAPON_CACHE = new HashSet<>();
    private static final Set<String> TOOL_CACHE = new HashSet<>();

    // Cache item lists for performance
    public static void bakeConfigCaches() {
        WEAPON_CACHE.clear();
        TOOL_CACHE.clear();

        if (Config.weapon_sound_additions != null) {
            WEAPON_CACHE.addAll(Config.weapon_sound_additions);
        }
        if (Config.tool_sound_additions != null) {
            TOOL_CACHE.addAll(Config.tool_sound_additions);
        }
    }

    public static boolean isValidWeapon(Item item) {
        String registryName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return WEAPON_CACHE.contains(registryName);
    }

    public static boolean isValidTool(Item item) {
        String registryName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return TOOL_CACHE.contains(registryName);
    }
}
