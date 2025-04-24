package net.gobies.additions.item;

import net.gobies.additions.Additions;
import net.gobies.additions.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Additions.MOD_ID);
    public static final RegistryObject<CreativeModeTab> ADDITIONS_TAB = CREATIVE_MODE_TABS.register("moreartifacts_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SteelIngot.get()))
                    .title(Component.translatable("creativetab.additions_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.TinOre.get());
                        pOutput.accept(ModBlocks.RubyOre.get());
                        pOutput.accept(ModItems.RawTin.get());
                        pOutput.accept(ModItems.TinIngot.get());
                        pOutput.accept(ModItems.TinNugget.get());
                        pOutput.accept(ModItems.RawBronzeAlloy.get());
                        pOutput.accept(ModItems.BronzeIngot.get());
                        pOutput.accept(ModItems.BronzeNugget.get());
                        pOutput.accept(ModItems.RawSteelAlloy.get());
                        pOutput.accept(ModItems.SteelIngot.get());
                        pOutput.accept(ModItems.SteelNugget.get());
                        pOutput.accept(ModItems.Ruby.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}