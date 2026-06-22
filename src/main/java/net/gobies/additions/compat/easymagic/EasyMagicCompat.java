package net.gobies.additions.compat.easymagic;

import net.gobies.additions.Additions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class EasyMagicCompat {

    public static void refreshMenu(LivingEntity entity) {
        if (Additions.isEasyMagicLoaded()) {
            if (entity instanceof ServerPlayer player) {
                if (player.containerMenu.getClass().getName().equals("fuzs.easymagic.world.inventory.ModEnchantmentMenu")) {
                    var container = player.containerMenu.getSlot(0).container;
                    player.containerMenu.slotsChanged(container);
                }
            }
        }
    }
}
