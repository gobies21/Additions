package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.gobies.additions.item.armor.*;
import net.gobies.additions.item.supplies.MatchBoxItem;
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
    public static final RegistryObject<Item> RawBronzeAlloy;
    public static final RegistryObject<Item> BronzeIngot;
    public static final RegistryObject<Item> BronzeNugget;
    public static final RegistryObject<Item> RawSteelAlloy;
    public static final RegistryObject<Item> SteelIngot;
    public static final RegistryObject<Item> SteelNugget;
    public static final RegistryObject<Item> Ruby;
    public static final RegistryObject<Item> DiamondGem;
    public static final RegistryObject<Item> EmeraldGem;
    public static final RegistryObject<Item> RubyGem;
    public static final RegistryObject<Item> SapphireGem;
    public static final RegistryObject<Item> MatchBox;

    //Flint Tools
    public static final RegistryObject<Item> FlintSword;
    public static final RegistryObject<Item> FlintPickaxe;
    public static final RegistryObject<Item> FlintAxe;
    public static final RegistryObject<Item> FlintShovel;
    public static final RegistryObject<Item> FlintHoe;

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

    //Bronze Gear
    public static final RegistryObject<Item> BronzeHelmet;
    public static final RegistryObject<Item> BronzeChestplate;
    public static final RegistryObject<Item> BronzeLeggings;
    public static final RegistryObject<Item> BronzeBoots;
    public static final RegistryObject<Item> BronzeSword;
    public static final RegistryObject<Item> BronzePickaxe;
    public static final RegistryObject<Item> BronzeAxe;
    public static final RegistryObject<Item> BronzeShovel;
    public static final RegistryObject<Item> BronzeHoe;

    //Steel Gear
    public static final RegistryObject<Item> SteelHelmet;
    public static final RegistryObject<Item> SteelChestplate;
    public static final RegistryObject<Item> SteelLeggings;
    public static final RegistryObject<Item> SteelBoots;
    public static final RegistryObject<Item> SteelSword;
    public static final RegistryObject<Item> SteelPickaxe;
    public static final RegistryObject<Item> SteelAxe;
    public static final RegistryObject<Item> SteelShovel;
    public static final RegistryObject<Item> SteelHoe;


    public AdditionsItems() {
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
        DiamondGem = ITEMS.register("diamond_gem", () -> new Item(new Item.Properties()));
        EmeraldGem = ITEMS.register("emerald_gem", () -> new Item(new Item.Properties()));
        RubyGem = ITEMS.register("ruby_gem", () -> new Item(new Item.Properties()));
        SapphireGem = ITEMS.register("sapphire_gem", () -> new Item(new Item.Properties()));
        MatchBox = ITEMS.register("match_box", () -> new MatchBoxItem(new Item.Properties()));

        //Register Flint Tools
        FlintSword = ITEMS.register("flint_sword", () -> new SwordItem(AdditionsTiers.FLINT, 3, -4.0f + 1.6f, new Item.Properties()));
        FlintPickaxe = ITEMS.register("flint_pickaxe", () -> (new PickaxeItem(AdditionsTiers.FLINT, 1, -4.0f + 1.2f, new Item.Properties())));
        FlintAxe = ITEMS.register("flint_axe", () -> new AxeItem(AdditionsTiers.FLINT, 3, -4.0f + 0.7f, (new Item.Properties())));
        FlintShovel = ITEMS.register("flint_shovel", () -> new ShovelItem(AdditionsTiers.FLINT, 0, -4.0f + 1.0f, (new Item.Properties())));
        FlintHoe = ITEMS.register("flint_hoe", () -> new HoeItem(AdditionsTiers.FLINT, -1, -4.0f + 1.0f, (new Item.Properties())));

        //Register Tin Gear
        TinHelmet = ITEMS.register("tin_helmet", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.HELMET, new ArmorItem.Properties()));
        TinChestplate = ITEMS.register("tin_chestplate", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.CHESTPLATE, new ArmorItem.Properties()));
        TinLeggings = ITEMS.register("tin_leggings", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.LEGGINGS, new ArmorItem.Properties()));
        TinBoots = ITEMS.register("tin_boots", () -> new ArmorItem(AdditionsArmorMaterials.TIN, ArmorItem.Type.BOOTS, new ArmorItem.Properties()));
        TinSword = ITEMS.register("tin_sword", () -> new SwordItem(AdditionsTiers.TIN, 4, -4.0f + 1.6f, new Item.Properties()));
        TinPickaxe = ITEMS.register("tin_pickaxe", () -> new PickaxeItem(AdditionsTiers.TIN, 2, -4.0f + 1.2f, new Item.Properties()));
        TinAxe = ITEMS.register("tin_axe", () -> new AxeItem(AdditionsTiers.TIN, 8, -4.0f + 0.85f, new Item.Properties()));
        TinShovel = ITEMS.register("tin_shovel", () -> new ShovelItem(AdditionsTiers.TIN, 3, -4.0f + 1.0f, new Item.Properties()));
        TinHoe = ITEMS.register("tin_hoe", () -> new HoeItem(AdditionsTiers.TIN, 1, -4.0f + 1.0f, new Item.Properties()));

        //Register Bronze Gear
        BronzeHelmet = ITEMS.register("bronze_helmet", () -> new ArmorItem(AdditionsArmorMaterials.BRONZE, ArmorItem.Type.HELMET, new ArmorItem.Properties()));
        BronzeChestplate = ITEMS.register("bronze_chestplate", () -> new ArmorItem(AdditionsArmorMaterials.BRONZE, ArmorItem.Type.CHESTPLATE, new ArmorItem.Properties()));
        BronzeLeggings = ITEMS.register("bronze_leggings", () -> new ArmorItem(AdditionsArmorMaterials.BRONZE, ArmorItem.Type.LEGGINGS, new ArmorItem.Properties()));
        BronzeBoots = ITEMS.register("bronze_boots", () -> new ArmorItem(AdditionsArmorMaterials.BRONZE, ArmorItem.Type.BOOTS, new ArmorItem.Properties()));
        BronzeSword = ITEMS.register("bronze_sword", () -> new SwordItem(AdditionsTiers.BRONZE, 5, -4.0f + 1.6f, new Item.Properties()));
        BronzePickaxe = ITEMS.register("bronze_pickaxe", () -> new PickaxeItem(AdditionsTiers.BRONZE, 3, -4.0f + 1.2f, new Item.Properties()));
        BronzeAxe = ITEMS.register("bronze_axe", () -> new AxeItem(AdditionsTiers.BRONZE, 8, -4.0f + 0.9f, new Item.Properties()));
        BronzeShovel = ITEMS.register("bronze_shovel", () -> new ShovelItem(AdditionsTiers.BRONZE, 3 + 0.5f, -4.0f + 1.0f, new Item.Properties()));
        BronzeHoe = ITEMS.register("bronze_hoe", () -> new HoeItem(AdditionsTiers.BRONZE, 0, -4.0f + 3.0f, new Item.Properties()));

        //Register Steel Gear
        SteelHelmet = ITEMS.register("steel_helmet", () -> new SteelArmor(AdditionsArmorMaterials.STEEL, ArmorItem.Type.HELMET, new ArmorItem.Properties()));
        SteelChestplate = ITEMS.register("steel_chestplate", () -> new SteelArmor(AdditionsArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, new ArmorItem.Properties()));
        SteelLeggings = ITEMS.register("steel_leggings", () -> new SteelArmor(AdditionsArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS, new ArmorItem.Properties()));
        SteelBoots = ITEMS.register("steel_boots", () -> new SteelArmor(AdditionsArmorMaterials.STEEL, ArmorItem.Type.BOOTS, new ArmorItem.Properties()));
        SteelSword = ITEMS.register("steel_sword", () -> new SwordItem(AdditionsTiers.STEEL, 5, -4.0f + 1.6f, new Item.Properties()));
        SteelPickaxe = ITEMS.register("steel_pickaxe", () -> new PickaxeItem(AdditionsTiers.STEEL, 3, -4.0f + 1.2f, new Item.Properties()));
        SteelAxe = ITEMS.register("steel_axe", () -> new AxeItem(AdditionsTiers.STEEL, 8, -4.0f + 0.9f, new Item.Properties()));
        SteelShovel = ITEMS.register("steel_shovel", () -> new ShovelItem(AdditionsTiers.STEEL, 4 - 0.5f, -4.0f + 1.0f, new Item.Properties()));
        SteelHoe = ITEMS.register("steel_hoe", () -> new HoeItem(AdditionsTiers.STEEL, 0, -4.0f + 3.5f, new Item.Properties()));
    }
}