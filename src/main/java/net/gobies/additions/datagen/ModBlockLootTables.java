package net.gobies.additions.datagen;

import net.gobies.additions.block.ModBlocks;
import net.gobies.additions.item.ModItems;
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
      //  this.dropSelf(ModBlocks.ALUMINUM_BLOCK.get());

        this.add(ModBlocks.TinOre.get(),
                (block) -> createOreDrop(ModBlocks.TinOre.get(), ModItems.RawTin.get()));
        this.add(ModBlocks.RubyOre.get(),
                (block) -> createOreDrop(ModBlocks.RubyOre.get(), ModItems.RawTin.get()));

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}