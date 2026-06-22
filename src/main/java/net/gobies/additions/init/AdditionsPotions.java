package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsPotions {
    public static final DeferredRegister<Potion> POTIONS;
    public static final RegistryObject<Potion> MagicFocus;
    public static final RegistryObject<Potion> LongMagicFocus;
    public static final RegistryObject<Potion> StrongMagicFocus;

    public static void register (IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

    static {
        POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Additions.MOD_ID);
        MagicFocus = POTIONS.register("magic_focus", () -> new Potion(new MobEffectInstance(AdditionsEffects.MagicFocus.get(),3600,0)));
        LongMagicFocus = POTIONS.register("long_magic_focus", () -> new Potion(new MobEffectInstance(AdditionsEffects.MagicFocus.get(),9600,0)));
        StrongMagicFocus = POTIONS.register("strong_magic_focus", () -> new Potion(new MobEffectInstance(AdditionsEffects.MagicFocus.get(),1800,1)));
    }

}
