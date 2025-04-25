package net.gobies.additions.item;

import net.gobies.additions.Additions;
import net.gobies.additions.item.armor.*;
import net.gobies.additions.item.tools.*;
import net.minecraft.world.item.*;
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

        //Register Tin Gear
        TinHelmet = ITEMS.register("tin_helmet", () -> new TinHelmetItem(new ArmorItem.Properties()));
        TinChestplate = ITEMS.register("tin_chestplate", () -> new TinChestplateItem(new ArmorItem.Properties()));
        TinLeggings = ITEMS.register("tin_leggings", () -> new TinLeggingsItem(new ArmorItem.Properties()));
        TinBoots = ITEMS.register("tin_boots", () -> new TinBootsItem(new ArmorItem.Properties()));
        TinSword = ITEMS.register("tin_sword", () -> new TinSwordItem(new SwordItem.Properties()));
        TinPickaxe = ITEMS.register("tin_pickaxe", () -> new TinPickaxeItem(new PickaxeItem.Properties()));
        TinAxe = ITEMS.register("tin_axe", () -> new TinAxeItem(new AxeItem.Properties()));
        TinShovel = ITEMS.register("tin_shovel", () -> new TinShovelItem(new ShovelItem.Properties()));
        TinHoe = ITEMS.register("tin_hoe", () -> new TinHoeItem(new HoeItem.Properties()));

        //Register Bronze Gear
        BronzeHelmet = ITEMS.register("bronze_helmet", () -> new BronzeHelmetItem(new ArmorItem.Properties()));
        BronzeChestplate = ITEMS.register("bronze_chestplate", () -> new BronzeChestplateItem(new ArmorItem.Properties()));
        BronzeLeggings = ITEMS.register("bronze_leggings", () -> new BronzeLeggingsItem(new ArmorItem.Properties()));
        BronzeBoots = ITEMS.register("bronze_boots", () -> new BronzeBootsItem(new ArmorItem.Properties()));
        BronzeSword = ITEMS.register("bronze_sword", () -> new BronzeSwordItem(new SwordItem.Properties()));
        BronzePickaxe = ITEMS.register("bronze_pickaxe", () -> new BronzePickaxeItem(new PickaxeItem.Properties()));
        BronzeAxe = ITEMS.register("bronze_axe", () -> new BronzeAxeItem(new AxeItem.Properties()));
        BronzeShovel = ITEMS.register("bronze_shovel", () -> new BronzeShovelItem(new ShovelItem.Properties()));
        BronzeHoe = ITEMS.register("bronze_hoe", () -> new BronzeHoeItem(new HoeItem.Properties()));

        //Register Steel Gear
        SteelHelmet = ITEMS.register("steel_helmet", () -> new SteelHelmetItem(new ArmorItem.Properties()));
        SteelChestplate = ITEMS.register("steel_chestplate", () -> new SteelChestplateItem(new ArmorItem.Properties()));
        SteelLeggings = ITEMS.register("steel_leggings", () -> new SteelLeggingsItem(new ArmorItem.Properties()));
        SteelBoots = ITEMS.register("steel_boots", () -> new SteelBootsItem(new ArmorItem.Properties()));
        SteelSword = ITEMS.register("steel_sword", () -> new SteelSwordItem(new SwordItem.Properties()));
        SteelPickaxe = ITEMS.register("steel_pickaxe", () -> new SteelPickaxeItem(new PickaxeItem.Properties()));
        SteelAxe = ITEMS.register("steel_axe", () -> new SteelAxeItem(new AxeItem.Properties()));
        SteelShovel = ITEMS.register("steel_shovel", () -> new SteelShovelItem(new ShovelItem.Properties()));
        SteelHoe = ITEMS.register("steel_hoe", () -> new SteelHoeItem(new HoeItem.Properties()));
    }
}
