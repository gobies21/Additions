package net.gobies.additions.datagen;

import net.gobies.additions.item.blocks.AdditionsBlocks;
import net.gobies.additions.item.AdditionsItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(AdditionsBlocks.TinBlock.get());
        this.dropSelf(AdditionsBlocks.BronzeBlock.get());
        this.dropSelf(AdditionsBlocks.SteelBlock.get());
        this.dropSelf(AdditionsBlocks.RubyBlock.get());
        this.dropSelf(AdditionsBlocks.RawTinBlock.get());
        this.dropSelf(AdditionsBlocks.RawBronzeBlock.get());
        this.dropSelf(AdditionsBlocks.RawSteelBlock.get());

        this.add(AdditionsBlocks.TinOre.get(),
                (block) -> createOreDrop(AdditionsBlocks.TinOre.get(), AdditionsItems.RawTin.get()));
        this.add(AdditionsBlocks.DeepslateTinOre.get(),
                (block) -> createOreDrop(AdditionsBlocks.DeepslateTinOre.get(), AdditionsItems.RawTin.get()));
        this.add(AdditionsBlocks.RubyOre.get(),
                (block) -> createOreDrop(AdditionsBlocks.RubyOre.get(), AdditionsItems.Ruby.get()));

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return AdditionsBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}