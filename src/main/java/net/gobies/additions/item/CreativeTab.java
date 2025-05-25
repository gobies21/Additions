package net.gobies.additions.item;

import net.gobies.additions.Additions;
import net.gobies.additions.Config;
import net.gobies.additions.item.blocks.AdditionsBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {

    @SubscribeEvent
    public static void BuildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.RAW_COPPER.getDefaultInstance(), AdditionsItems.RawTin.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsItems.RawTin.get().getDefaultInstance(), AdditionsItems.RawBronzeAlloy.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsItems.RawBronzeAlloy.get().getDefaultInstance(), AdditionsItems.RawSteelAlloy.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.COPPER_INGOT.getDefaultInstance(), AdditionsItems.TinIngot.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsItems.TinIngot.get().getDefaultInstance(), AdditionsItems.BronzeIngot.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsItems.BronzeIngot.get().getDefaultInstance(), AdditionsItems.SteelIngot.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.DIAMOND.getDefaultInstance(), AdditionsItems.Ruby.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.GOLD_NUGGET.getDefaultInstance(), AdditionsItems.TinNugget.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsItems.TinNugget.get().getDefaultInstance(), AdditionsItems.BronzeNugget.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsItems.BronzeNugget.get().getDefaultInstance(), AdditionsItems.SteelNugget.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            if (Config.ENABLE_GEMS.get()) {
                event.getEntries().putAfter(Items.AMETHYST_SHARD.getDefaultInstance(), AdditionsItems.DiamondGem.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(AdditionsItems.DiamondGem.get().getDefaultInstance(), AdditionsItems.EmeraldGem.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                event.getEntries().putAfter(AdditionsItems.EmeraldGem.get().getDefaultInstance(), AdditionsItems.RubyGem.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                if (ModList.get().isLoaded("iceandfire")) {
                    event.getEntries().putAfter(AdditionsItems.RubyGem.get().getDefaultInstance(), AdditionsItems.SapphireGem.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.getEntries().putAfter(Items.DEEPSLATE_COPPER_ORE.getDefaultInstance(), AdditionsBlocks.TinOre.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsBlocks.TinOre.get().asItem().getDefaultInstance(), AdditionsBlocks.DeepslateTinOre.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.NETHER_GOLD_ORE.getDefaultInstance(), AdditionsBlocks.RubyOre.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.RAW_COPPER_BLOCK.getDefaultInstance(), AdditionsBlocks.RawTinBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsBlocks.RawTinBlock.get().asItem().getDefaultInstance(), AdditionsBlocks.RawBronzeBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.RAW_IRON_BLOCK.getDefaultInstance(), AdditionsBlocks.RawSteelBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.getEntries().putAfter(Items.COPPER_BLOCK.getDefaultInstance(), AdditionsBlocks.TinBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(AdditionsBlocks.TinBlock.get().asItem().getDefaultInstance(), AdditionsBlocks.BronzeBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.IRON_BLOCK.getDefaultInstance(), AdditionsBlocks.SteelBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.REDSTONE_BLOCK.getDefaultInstance(), AdditionsBlocks.RubyBlock.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);



        }
    }
}