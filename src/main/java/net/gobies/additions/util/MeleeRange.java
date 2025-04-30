package net.gobies.additions.util;

import net.gobies.additions.Config;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MeleeRange {
    private static final AttributeModifier REACH_MODIFIER = new AttributeModifier("additions_reach_bonus", 1.0D, AttributeModifier.Operation.ADDITION);

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            var reachAttribute = player.getAttribute(ForgeMod.ENTITY_REACH.get());
            if (reachAttribute != null) {
                if (Config.INCREASED_REACH.get()) {
                    if (!reachAttribute.hasModifier(REACH_MODIFIER)) {
                        reachAttribute.addPermanentModifier(REACH_MODIFIER);
                    }
                } else {
                    if (reachAttribute.hasModifier(REACH_MODIFIER)) {
                        reachAttribute.removeModifier(REACH_MODIFIER);
                    }
                }
            }
        }
    }
}
