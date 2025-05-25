package net.gobies.additions.item.supplies;

import net.minecraft.world.item.FlintAndSteelItem;

public class MatchBoxItem extends FlintAndSteelItem {
    public MatchBoxItem(Properties properties) {
        super(properties.stacksTo(1).durability(32));
    }
}
