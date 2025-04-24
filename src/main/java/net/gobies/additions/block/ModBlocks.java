package net.gobies.additions.block;

import net.gobies.additions.Additions;
import net.gobies.additions.block.ores.RubyOre;
import net.gobies.additions.block.ores.TinOre;
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
    public static final RegistryObject<Block> RubyOre;


    public ModBlocks() {
    }

    public static void register(IEventBus eventbus) {
        BLOCKS.register(eventbus);
        ITEMS.register(eventbus);
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Additions.MOD_ID);

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Additions.MOD_ID);
        TinOre = registerBlock("tin_ore", () -> new TinOre(BlockBehaviour.Properties.of().strength(0.14F, 1.5F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
        RubyOre = registerBlock("ruby_ore", () -> new RubyOre(BlockBehaviour.Properties.of().strength(0.16F, 2.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
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