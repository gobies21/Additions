package net.gobies.additions.datagen;

import net.gobies.additions.Additions;
import net.gobies.additions.worldgen.AdditionsCF;
import net.gobies.additions.worldgen.AdditionsPF;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, AdditionsCF::bootstrap)
            .add(Registries.PLACED_FEATURE, AdditionsPF::bootstrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(Additions.MOD_ID));
    }
}