package net.gobies.additions.item;

import net.gobies.additions.Additions;
import net.gobies.additions.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Additions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {

    @SubscribeEvent
    public static void BuildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.RAW_COPPER.getDefaultInstance(), ModItems.RawTin.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ModItems.RawTin.get().getDefaultInstance(), ModItems.RawBronzeAlloy.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ModItems.RawBronzeAlloy.get().getDefaultInstance(), ModItems.RawSteelAlloy.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.getEntries().putAfter(Items.COPPER_INGOT.getDefaultInstance(), ModItems.TinIngot.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ModItems.TinIngot.get().getDefaultInstance(), ModItems.BronzeIngot.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ModItems.BronzeIngot.get().getDefaultInstance(), ModItems.SteelIngot.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.DIAMOND.getDefaultInstance(), ModItems.Ruby.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


            event.getEntries().putAfter(Items.GOLD_NUGGET.getDefaultInstance(), ModItems.TinNugget.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ModItems.TinNugget.get().getDefaultInstance(), ModItems.BronzeNugget.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(ModItems.BronzeNugget.get().getDefaultInstance(), ModItems.SteelNugget.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.getEntries().putAfter(Items.COPPER_ORE.getDefaultInstance(), ModBlocks.TinOre.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.getEntries().putAfter(Items.NETHER_QUARTZ_ORE.getDefaultInstance(), ModBlocks.RubyOre.get().asItem().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);


        }
    }
}