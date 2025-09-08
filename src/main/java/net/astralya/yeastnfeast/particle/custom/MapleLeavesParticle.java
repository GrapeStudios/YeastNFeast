package net.astralya.yeastnfeast.particle.custom;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class MapleLeavesParticle extends SpriteBillboardParticle {

    private final float fallingDriftAngle;

    protected MapleLeavesParticle(ClientWorld world, double x, double y, double z,
                                  double velocityX, double velocityY, double velocityZ,
                                  SpriteProvider spriteProvider) {
        super(world, x, y, z);
        setSprite(spriteProvider.getSprite(random.nextInt(12), 12));
        maxAge = 100;
        gravityStrength = 0.0003F;
        velocityMultiplier = 0.98F;
        scale = random.nextBoolean() ? 0.05F : 0.075F;
        setBoundingBoxSpacing(scale, scale);
        fallingDriftAngle = random.nextFloat() * ((float) Math.PI * 2F);

        this.velocityX = velocityX;
        this.velocityY = -0.01D;
        this.velocityZ = velocityZ;

        prevAngle = angle = 0;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        prevPosX = x;
        prevPosY = y;
        prevPosZ = z;

        if (maxAge-- <= 0) {
            markDead();
            return;
        }

        float progress = 1.0F - ((float) maxAge / 300.0F);
        double driftStrength = Math.sin(progress * Math.PI) * 0.0025F;
        velocityX += Math.cos(fallingDriftAngle) * driftStrength;
        velocityZ += Math.sin(fallingDriftAngle) * driftStrength;

        velocityY -= gravityStrength;

        move(velocityX, velocityY, velocityZ);

        if (onGround || (maxAge < 299 && velocityX == 0.0 && velocityZ == 0.0)) {
            markDead();
        } else {
            velocityX *= velocityMultiplier;
            velocityY *= velocityMultiplier;
            velocityZ *= velocityMultiplier;
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
            return new MapleLeavesParticle(world, x, y, z, vx, vy, vz, spriteProvider);
        }
    }
}