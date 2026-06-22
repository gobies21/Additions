package net.gobies.additions.events;


import net.gobies.additions.config.CommonConfig;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AEvents {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (!CommonConfig.ENABLE_SPAWNER_MODULE.get()) return;
        BlockState blockState = event.getState();
        if (blockState.getBlock() == Blocks.SPAWNER) {
            event.setNewSpeed(event.getNewSpeed() * 0.15F);
        }
    }
}