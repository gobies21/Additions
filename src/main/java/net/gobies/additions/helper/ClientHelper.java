package net.gobies.additions.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientHelper {
    public static boolean isLocalPlayer(Player player) {
        return player == Minecraft.getInstance().player;
    }
}
