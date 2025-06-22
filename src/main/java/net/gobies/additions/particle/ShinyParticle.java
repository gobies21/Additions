package net.gobies.additions.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ShinyParticle extends TextureSheetParticle {
    public static ShinyParticleProvider provider(SpriteSet spriteSet) {
        return new ShinyParticleProvider(spriteSet);
    }

    public static class ShinyParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public ShinyParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new ShinyParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }

    private final SpriteSet spriteSet;

    protected ShinyParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.3f, 0.3f);
        this.lifetime = 7;
        this.gravity = 0.5f;
        this.hasPhysics = true;
        this.xd = vx * 0;
        this.yd = vy * 0;
        this.zd = vz * 0;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSprite(this.spriteSet.get(1, 40));
        }
    }
}
