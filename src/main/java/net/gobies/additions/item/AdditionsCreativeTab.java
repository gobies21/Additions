package net.gobies.additions.item;

import net.gobies.additions.Additions;
import net.gobies.additions.Config;
import net.gobies.additions.init.AdditionsItems;
import net.gobies.additions.init.AdditionsBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Additions.MOD_ID);
    public static final RegistryObject<CreativeModeTab> ADDITIONS_TAB = CREATIVE_MODE_TABS.register("moreartifacts_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AdditionsItems.SteelIngot.get()))
                    .title(Component.translatable("creativetab.additions_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(AdditionsBlocks.TinOre.get());
                        pOutput.accept(AdditionsBlocks.DeepslateTinOre.get());
                        pOutput.accept(AdditionsBlocks.RubyOre.get());
                        pOutput.accept(AdditionsBlocks.RawTinBlock.get());
                        pOutput.accept(AdditionsBlocks.RawBronzeBlock.get());
                        pOutput.accept(AdditionsBlocks.RawSteelBlock.get());
                        pOutput.accept(AdditionsBlocks.TinBlock.get());
                        pOutput.accept(AdditionsBlocks.BronzeBlock.get());
                        pOutput.accept(AdditionsBlocks.SteelBlock.get());
                        pOutput.accept(AdditionsBlocks.RubyBlock.get());
                        pOutput.accept(AdditionsItems.RawTin.get());
                        pOutput.accept(AdditionsItems.TinIngot.get());
                        pOutput.accept(AdditionsItems.TinNugget.get());
                        pOutput.accept(AdditionsItems.RawBronzeAlloy.get());
                        pOutput.accept(AdditionsItems.BronzeIngot.get());
                        pOutput.accept(AdditionsItems.BronzeNugget.get());
                        pOutput.accept(AdditionsItems.RawSteelAlloy.get());
                        pOutput.accept(AdditionsItems.SteelIngot.get());
                        pOutput.accept(AdditionsItems.SteelNugget.get());
                        pOutput.accept(AdditionsItems.Ruby.get());

                        if (Config.ENABLE_GEMS.get()) {
                            pOutput.accept(AdditionsItems.RubyGem.get());
                            pOutput.accept(AdditionsItems.DiamondGem.get());
                            pOutput.accept(AdditionsItems.EmeraldGem.get());
                            if (ModList.get().isLoaded("iceandfire")) {
                                pOutput.accept(AdditionsItems.SapphireGem.get());
                            }
                        }

                        if (Config.ENABLE_FLINT_TOOLS.get()) {
                            pOutput.accept(AdditionsItems.FlintSword.get());
                            pOutput.accept(AdditionsItems.FlintPickaxe.get());
                            pOutput.accept(AdditionsItems.FlintAxe.get());
                            pOutput.accept(AdditionsItems.FlintShovel.get());
                            pOutput.accept(AdditionsItems.FlintHoe.get());
                        }

                        pOutput.accept(AdditionsItems.TinSword.get());
                        pOutput.accept(AdditionsItems.TinPickaxe.get());
                        pOutput.accept(AdditionsItems.TinAxe.get());
                        pOutput.accept(AdditionsItems.TinShovel.get());
                        pOutput.accept(AdditionsItems.TinHoe.get());
                        pOutput.accept(AdditionsItems.TinHelmet.get());
                        pOutput.accept(AdditionsItems.TinChestplate.get());
                        pOutput.accept(AdditionsItems.TinLeggings.get());
                        pOutput.accept(AdditionsItems.TinBoots.get());
                        pOutput.accept(AdditionsItems.BronzeSword.get());
                        pOutput.accept(AdditionsItems.BronzePickaxe.get());
                        pOutput.accept(AdditionsItems.BronzeAxe.get());
                        pOutput.accept(AdditionsItems.BronzeShovel.get());
                        pOutput.accept(AdditionsItems.BronzeHoe.get());
                        pOutput.accept(AdditionsItems.BronzeHelmet.get());
                        pOutput.accept(AdditionsItems.BronzeChestplate.get());
                        pOutput.accept(AdditionsItems.BronzeLeggings.get());
                        pOutput.accept(AdditionsItems.BronzeBoots.get());
                        pOutput.accept(AdditionsItems.SteelSword.get());
                        pOutput.accept(AdditionsItems.SteelPickaxe.get());
                        pOutput.accept(AdditionsItems.SteelAxe.get());
                        pOutput.accept(AdditionsItems.SteelShovel.get());
                        pOutput.accept(AdditionsItems.SteelHoe.get());
                        pOutput.accept(AdditionsItems.SteelHelmet.get());
                        pOutput.accept(AdditionsItems.SteelChestplate.get());
                        pOutput.accept(AdditionsItems.SteelLeggings.get());
                        pOutput.accept(AdditionsItems.SteelBoots.get());
                        pOutput.accept(AdditionsItems.MatchBox.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}