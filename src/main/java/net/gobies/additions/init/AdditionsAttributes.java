package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES;
    public static final RegistryObject<Attribute> CRITICAL_RATE;
    public static final RegistryObject<Attribute> ENCHANTING_FOCUS;
    public static final RegistryObject<Attribute> ARROW_VELOCITY;

    public static void register (IEventBus eventBus) {
        ATTRIBUTES.register(eventBus);
    }

    static {
        ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Additions.MOD_ID);
        CRITICAL_RATE = ATTRIBUTES.register("critical_rate", () -> new RangedAttribute("attribute.name.additions.critical_rate", 0.20D, 0.0D, 1.0D).setSyncable(true));
        ENCHANTING_FOCUS = ATTRIBUTES.register("enchanting_focus", () -> new RangedAttribute("attribute.name.additions.enchanting_focus", 0.0D, 0.0D, 100.0D).setSyncable(true));
        ARROW_VELOCITY = ATTRIBUTES.register("arrow_velocity", () -> new RangedAttribute("attribute.name.additions.arrow_velocity", 1.0D, 0.0D, 10.0D).setSyncable(true));
    }
}
