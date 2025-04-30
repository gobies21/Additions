package net.gobies.additions.item;

import net.gobies.additions.Additions;
import net.gobies.additions.Config;
import net.gobies.additions.item.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
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
                        pOutput.accept(ModBlocks.DeepslateTinOre.get());
                        pOutput.accept(ModBlocks.RubyOre.get());
                        pOutput.accept(ModBlocks.RawTinBlock.get());
                        pOutput.accept(ModBlocks.RawBronzeBlock.get());
                        pOutput.accept(ModBlocks.RawSteelBlock.get());
                        pOutput.accept(ModBlocks.TinBlock.get());
                        pOutput.accept(ModBlocks.BronzeBlock.get());
                        pOutput.accept(ModBlocks.SteelBlock.get());
                        pOutput.accept(ModBlocks.RubyBlock.get());
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
                        if (Config.ENABLE_GEMS.get()) {
                            pOutput.accept(ModItems.RubyGem.get());
                            pOutput.accept(ModItems.DiamondGem.get());
                            pOutput.accept(ModItems.EmeraldGem.get());
                            if (ModList.get().isLoaded("iceandfire")) {
                                pOutput.accept(ModItems.SapphireGem.get());
                            }
                        }
                        if (Config.ENABLE_FLINT_TOOLS.get()) {
                            pOutput.accept(ModItems.FlintSword.get());
                            pOutput.accept(ModItems.FlintPickaxe.get());
                            pOutput.accept(ModItems.FlintAxe.get());
                            pOutput.accept(ModItems.FlintShovel.get());
                            pOutput.accept(ModItems.FlintHoe.get());
                        }

                        pOutput.accept(ModItems.TinSword.get());
                        pOutput.accept(ModItems.TinPickaxe.get());
                        pOutput.accept(ModItems.TinAxe.get());
                        pOutput.accept(ModItems.TinShovel.get());
                        pOutput.accept(ModItems.TinHoe.get());
                        pOutput.accept(ModItems.TinHelmet.get());
                        pOutput.accept(ModItems.TinChestplate.get());
                        pOutput.accept(ModItems.TinLeggings.get());
                        pOutput.accept(ModItems.TinBoots.get());
                        pOutput.accept(ModItems.BronzeSword.get());
                        pOutput.accept(ModItems.BronzePickaxe.get());
                        pOutput.accept(ModItems.BronzeAxe.get());
                        pOutput.accept(ModItems.BronzeShovel.get());
                        pOutput.accept(ModItems.BronzeHoe.get());
                        pOutput.accept(ModItems.BronzeHelmet.get());
                        pOutput.accept(ModItems.BronzeChestplate.get());
                        pOutput.accept(ModItems.BronzeLeggings.get());
                        pOutput.accept(ModItems.BronzeBoots.get());
                        pOutput.accept(ModItems.SteelSword.get());
                        pOutput.accept(ModItems.SteelPickaxe.get());
                        pOutput.accept(ModItems.SteelAxe.get());
                        pOutput.accept(ModItems.SteelShovel.get());
                        pOutput.accept(ModItems.SteelHoe.get());
                        pOutput.accept(ModItems.SteelHelmet.get());
                        pOutput.accept(ModItems.SteelChestplate.get());
                        pOutput.accept(ModItems.SteelLeggings.get());
                        pOutput.accept(ModItems.SteelBoots.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}