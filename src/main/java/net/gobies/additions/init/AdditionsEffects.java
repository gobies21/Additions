package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.gobies.additions.effect.MagicFocus;
import net.gobies.additions.effect.WellFed;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsEffects {

    public static final DeferredRegister<MobEffect> EFFECTS;
    public static final RegistryObject<MobEffect> WellFed;
    public static final RegistryObject<MobEffect> MagicFocus;

    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }

    static {
        EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Additions.MOD_ID);
        WellFed = EFFECTS.register("well_fed", () -> new WellFed(MobEffectCategory.BENEFICIAL, 0xBF6600));
        MagicFocus = EFFECTS.register("magic_focus", () -> new MagicFocus(MobEffectCategory.BENEFICIAL, 0x993399));
    }
}