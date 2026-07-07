package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.gobies.additions.enchantment.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS;
    public static final RegistryObject<Enchantment> QUICK_DRAW;
    public static final RegistryObject<Enchantment> FREEZE;
    public static final RegistryObject<Enchantment> SPLITSHOT;
    public static final RegistryObject<Enchantment> SNIPE;
    public static final RegistryObject<Enchantment> ARROW_RECOVERY;

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

    static {
        ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Additions.MOD_ID);
        QUICK_DRAW = ENCHANTMENTS.register("quick_draw", QuickDrawEnchantment::new);
        FREEZE = ENCHANTMENTS.register("freeze", FreezeEnchantment::new);
        SPLITSHOT = ENCHANTMENTS.register("splitshot", SplitshotEnchantment::new);
        SNIPE = ENCHANTMENTS.register("snipe", SnipeEnchantment::new);
        ARROW_RECOVERY = ENCHANTMENTS.register("arrow_recovery", ArrowRecoveryEnchantment::new);
    }
}
