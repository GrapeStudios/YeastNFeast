package net.astralya.yeastnfeast.particle.custom;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;

public class DrippingSyrupParticle extends SpriteBillboardParticle {

    private final float wobbleSpeed;
    private final float wobbleAmount;

    protected DrippingSyrupParticle(ClientWorld world, double x, double y, double z,
                                    double vx, double vy, double vz, SpriteProvider spriteProvider) {
        super(world, x, y, z, vx, vy, vz);
        setSprite(spriteProvider.getSprite(world.random));
        scale *= 0.4F;
        maxAge = 40 + world.random.nextInt(10);
        gravityStrength = 0.0025F;
        velocityY = -0.005;
        velocityX = 0;
        velocityZ = 0;
        wobbleSpeed = 0.1F + world.random.nextFloat() * 0.1F;
        wobbleAmount = 0.0015F + world.random.nextFloat() * 0.0015F;
        setColor(0.48F, 0.3F, 0.12F);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;

        if (age++ >= maxAge) {
            markDead();
            return;
        }

        velocityY -= gravityStrength;

        double wobbleX = MathHelper.sin(age * wobbleSpeed) * wobbleAmount;
        double wobbleZ = MathHelper.cos(age * wobbleSpeed) * wobbleAmount;

        move(wobbleX, velocityY, wobbleZ);

        if (onGround) {
            setAlpha(0.6F);
            markDead();
        }
    }

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new DrippingSyrupParticle(world, x, y, z, vx, vy, vz, spriteProvider);
        }
    }
}