package net.gobies.additions.block;

import net.gobies.additions.Additions;
import net.gobies.additions.block.ores.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS;
    public static final RegistryObject<Block> TinOre;
    public static final RegistryObject<Block> DeepslateTinOre;
    public static final RegistryObject<Block> RubyOre;
    public static final RegistryObject<Block> RawTinBlock;
    public static final RegistryObject<Block> RawBronzeBlock;
    public static final RegistryObject<Block> RawSteelBlock;
    public static final RegistryObject<Block> TinBlock;
    public static final RegistryObject<Block> BronzeBlock;
    public static final RegistryObject<Block> SteelBlock;

    public ModBlocks() {
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
        ITEMS.register(eventbus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Additions.MOD_ID);

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Additions.MOD_ID);
        TinOre = registerBlock("tin_ore", () -> new TinOre(BlockBehaviour.Properties.of().strength(3.0F, 1.5F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        DeepslateTinOre = registerBlock("deepslate_tin_ore", () -> new DeepslateTinOre(BlockBehaviour.Properties.of().strength(3.1F, 2.0F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
        RubyOre = registerBlock("ruby_ore", () -> new RubyOre(BlockBehaviour.Properties.of().strength(3.0F, 2.0F).requiresCorrectToolForDrops().sound(SoundType.NETHER_GOLD_ORE)));
        RawTinBlock = registerBlock("raw_tin_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        RawBronzeBlock = registerBlock("raw_bronze_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 4.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        RawSteelBlock = registerBlock("raw_steel_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 5.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        TinBlock = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 3.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        BronzeBlock = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 4.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        SteelBlock = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F, 5.0F).requiresCorrectToolForDrops().sound(SoundType.METAL)));


    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}