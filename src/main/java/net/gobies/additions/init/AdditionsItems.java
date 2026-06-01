package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.gobies.additions.item.armor.*;
import net.gobies.additions.item.supplies.MatchBoxItem;
import net.gobies.additions.item.supplies.RarityScannerItem;
import net.gobies.additions.item.tools.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsItems {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> RawTin;
    public static final RegistryObject<Item> TinIngot;
    public static final RegistryObject<Item> TinNugget;
    public static final RegistryObject<Item> Ruby;
    public static final RegistryObject<Item> DiamondGem;
    public static final RegistryObject<Item> EmeraldGem;
    public static final RegistryObject<Item> RubyGem;
    public static final RegistryObject<Item> SapphireGem;
    public static final RegistryObject<Item> MatchBox;
    public static final RegistryObject<Item> RarityScanner;

    //Tin Gear
    public static final RegistryObject<Item> TinHelmet;
    public static final RegistryObject<Item> TinChestplate;
    public static final RegistryObject<Item> TinLeggings;
    public static final RegistryObject<Item> TinBoots;
    public static final RegistryObject<Item> TinSword;
    public static final RegistryObject<Item> TinPickaxe;
    public static final RegistryObject<Item> TinAxe;
    public static final RegistryObject<Item> TinShovel;
    public static final RegistryObject<Item> TinHoe;

    public static void register(IEventBus eventbus) {
        ITEMS.register(eventbus);
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Additions.MOD_ID);
        RawTin = ITEMS.register("raw_tin", () -> new Item(new Item.Properties()));
        TinIngot = ITEMS.register("tin_ingot", () -> new Item(new Item.Properties()));
        TinNugget = ITEMS.register("tin_nugget", () -> new Item(new Item.Properties()));
        Ruby = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
        DiamondGem = ITEMS.register("diamond_gem", () -> new Item(new Item.Properties()));
        EmeraldGem = ITEMS.register("emerald_gem", () -> new Item(new Item.Properties()));
        RubyGem = ITEMS.register("ruby_gem", () -> new Item(new Item.Properties()));
        SapphireGem = ITEMS.register("sapphire_gem", () -> new Item(new Item.Properties()));
        MatchBox = ITEMS.register("match_box", () -> new MatchBoxItem(new Item.Properties()));
        RarityScanner = ITEMS.register("rarity_scanner", () -> new RarityScannerItem(new Item.Properties()));

        // Register Tin Gear
        TinHelmet = ITEMS.register("tin_helmet", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.HELMET, new ArmorItem.Properties()));
        TinChestplate = ITEMS.register("tin_chestplate", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.CHESTPLATE, new ArmorItem.Properties()));
        TinLeggings = ITEMS.register("tin_leggings", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.LEGGINGS, new ArmorItem.Properties()));
        TinBoots = ITEMS.register("tin_boots", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.BOOTS, new ArmorItem.Properties()));
        TinSword = ITEMS.register("tin_sword", () -> new SwordItem(AdditionsTiers.TIN, 4, -4.0f + 1.6f, new Item.Properties()));
        TinPickaxe = ITEMS.register("tin_pickaxe", () -> new PickaxeItem(AdditionsTiers.TIN, 2, -4.0f + 1.2f, new Item.Properties()));
        TinAxe = ITEMS.register("tin_axe", () -> new AxeItem(AdditionsTiers.TIN, 8, -4.0f + 0.85f, new Item.Properties()));
        TinShovel = ITEMS.register("tin_shovel", () -> new ShovelItem(AdditionsTiers.TIN, 3, -4.0f + 1.0f, new Item.Properties()));
        TinHoe = ITEMS.register("tin_hoe", () -> new HoeItem(AdditionsTiers.TIN, 1, -4.0f + 1.0f, new Item.Properties()));
    }
}