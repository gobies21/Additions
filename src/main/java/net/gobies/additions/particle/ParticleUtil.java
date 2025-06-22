package net.gobies.additions.particle;

import net.gobies.additions.init.AdditionsParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleUtil {
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(AdditionsParticles.Shiny.get(), ShinyParticle::provider);
    }
}
