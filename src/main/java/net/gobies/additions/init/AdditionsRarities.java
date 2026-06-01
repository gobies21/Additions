package net.gobies.additions.init;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class AdditionsRarities {

    public static Rarity LEGENDARY;
    public static Rarity MYTHIC;

    public static void register() {}

    // Mainly here for modpack authors adding new rarities
    static {
        LEGENDARY = Rarity.create("additions:legendary", ChatFormatting.GOLD);
        MYTHIC = Rarity.create("additions:mythic", ChatFormatting.RED);
    }
}