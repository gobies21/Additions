package net.gobies.additions.helper;

import net.gobies.additions.Additions;
import net.gobies.additions.init.AdditionsAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeSetter {

    @SubscribeEvent
    public static void onAttributeModification(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, AdditionsAttributes.CRITICAL_RATE.get());
        event.add(EntityType.PLAYER, AdditionsAttributes.ENCHANTING_FOCUS.get());
        event.add(EntityType.PLAYER, AdditionsAttributes.ARROW_VELOCITY.get());
    }
}
