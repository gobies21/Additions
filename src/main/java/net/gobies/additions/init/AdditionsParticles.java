package net.gobies.additions.init;

import net.gobies.additions.Additions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AdditionsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES;
    public static final RegistryObject<SimpleParticleType> Shiny;


    public AdditionsParticles() {
    }

    public static void register(IEventBus eventbus) {
        PARTICLES.register(eventbus);
    }

    static {
        PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Additions.MOD_ID);
        Shiny = PARTICLES.register("shiny", () -> new SimpleParticleType(true));
    }
}
