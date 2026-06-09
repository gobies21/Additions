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
    private static final Set<String> LIGHT_ARMOR_CACHE = new HashSet<>();
    private static final Set<String> MEDIUM_ARMOR_CACHE = new HashSet<>();
    private static final Set<String> HEAVY_ARMOR_CACHE = new HashSet<>();
    private static final Set<String> CRYSTAL_ARMOR_CACHE = new HashSet<>();


    // Cache item lists for performance
    public static void bakeConfigCaches() {
        WEAPON_CACHE.clear();
        TOOL_CACHE.clear();
        LIGHT_ARMOR_CACHE.clear();
        MEDIUM_ARMOR_CACHE.clear();
        HEAVY_ARMOR_CACHE.clear();
        CRYSTAL_ARMOR_CACHE.clear();

        if (Config.weapon_sound_additions != null) {
            WEAPON_CACHE.addAll(Config.weapon_sound_additions);
        }
        if (Config.tool_sound_additions != null) {
            TOOL_CACHE.addAll(Config.tool_sound_additions);
        }
        if (Config.armor_light_additions != null) {
            LIGHT_ARMOR_CACHE.addAll(Config.armor_light_additions);
        }
        if (Config.armor_medium_additions != null) {
            MEDIUM_ARMOR_CACHE.addAll(Config.armor_medium_additions);
        }
        if (Config.armor_heavy_additions != null) {
            HEAVY_ARMOR_CACHE.addAll(Config.armor_heavy_additions);
        }
        if (Config.armor_crystal_additions != null) {
            CRYSTAL_ARMOR_CACHE.addAll(Config.armor_crystal_additions);
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
    public static boolean isValidLightArmor(Item item) {
        String registryName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return LIGHT_ARMOR_CACHE.contains(registryName);
    }
    public static boolean isValidMediumArmor(Item item) {
        String registryName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return MEDIUM_ARMOR_CACHE.contains(registryName);
    }
    public static boolean isValidHeavyArmor(Item item) {
        String registryName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return HEAVY_ARMOR_CACHE.contains(registryName);
    }
    public static boolean isValidCrystalArmor(Item item) {
        String registryName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).toString();
        return CRYSTAL_ARMOR_CACHE.contains(registryName);
    }
}
