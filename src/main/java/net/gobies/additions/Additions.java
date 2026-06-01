package net.gobies.additions;

import com.mojang.logging.LogUtils;
import net.gobies.additions.events.FoodEvents;
import net.gobies.additions.init.*;
import net.gobies.additions.item.AdditionsCreativeTab;
import net.gobies.additions.network.MobHPSyncPacket;
import net.gobies.additions.network.PacketHandler;
import net.gobies.additions.init.AdditionsCommands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import org.slf4j.Logger;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Mod(Additions.MOD_ID)
public class Additions {
    public static final String MOD_ID = "additions";
    public static final Logger LOGGER = LogUtils.getLogger();
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public Additions() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        AdditionsItems.register(modBus);

        AdditionsRarities.register();

        AdditionsBlocks.register(modBus);

        AdditionsParticles.register(modBus);

        AdditionsCreativeTab.register(modBus);

        AdditionsSounds.register(modBus);

        AdditionsEffects.register(modBus);

        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(FoodEvents.class);

        PacketHandler.registerMessages();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> MobHPSyncPacket.registerPackets(PacketHandler.INSTANCE));
    }


    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        AdditionsCommands.register(event.getDispatcher());
    }

    public static void queueServerWork(int tick, Runnable action) {
        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
            workQueue.add(new AbstractMap.SimpleEntry<>(action, tick));
        }
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
            workQueue.forEach((work) -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0) {
                    actions.add(work);
                }

            });
            actions.forEach((e) -> ((Runnable) e.getKey()).run());
            workQueue.removeAll(actions);
        }
    }

    public static boolean isChampionsLoaded() {
        return ModList.get().isLoaded("champions");
    }

    public static boolean isIceandFireLoaded() {
        return ModList.get().isLoaded("iceandfire");
    }
}
