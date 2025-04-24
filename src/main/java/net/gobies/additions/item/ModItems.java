package net.gobies.additions.item;

import net.gobies.additions.Additions;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> RawTin;
    public static final RegistryObject<Item> TinIngot;
    public static final RegistryObject<Item> TinNugget;
    public static final RegistryObject<Item> RawBronzeAlloy;
    public static final RegistryObject<Item> BronzeIngot;
    public static final RegistryObject<Item> BronzeNugget;
    public static final RegistryObject<Item> RawSteelAlloy;
    public static final RegistryObject<Item> SteelIngot;
    public static final RegistryObject<Item> SteelNugget;
    public static final RegistryObject<Item> Ruby;


    public ModItems() {
    }

    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Additions.MOD_ID);
        RawTin = ITEMS.register("raw_tin", () -> new Item(new Item.Properties()));
        TinIngot = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));
        TinNugget = ITEMS.register("tin_nugget", () -> new Item(new Item.Properties()));
        RawBronzeAlloy = ITEMS.register("raw_bronze_alloy", () -> new Item(new Item.Properties()));
        BronzeIngot = ITEMS.register("bronze_ingot", () -> new Item(new Item.Properties()));
        BronzeNugget = ITEMS.register("bronze_nugget", () -> new Item(new Item.Properties()));
        RawSteelAlloy = ITEMS.register("raw_steel_alloy", () -> new Item(new Item.Properties()));
        SteelIngot = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
        SteelNugget = ITEMS.register("steel_nugget", () -> new Item(new Item.Properties()));
        Ruby = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
    }
}
