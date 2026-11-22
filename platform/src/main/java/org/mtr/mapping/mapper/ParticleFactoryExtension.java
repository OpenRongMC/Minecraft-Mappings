package org.mtr.mapping.mapper;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ClientWorld;
import org.mtr.mapping.holder.SpriteBillboardParticle;
import org.mtr.mapping.holder.SpriteProvider;
import org.mtr.mapping.tool.DummyClass;

public abstract class ParticleFactoryExtension implements ParticleProvider<SimpleParticleType> {
   private final ParticleFactoryExtension.CreateParticle createParticle;
   private final ParticleFactoryExtension.CreateSpriteBillboardParticle createSpriteBillboardParticle;
   private final SpriteProvider spriteProvider;

   @MappedMethod
   public ParticleFactoryExtension(ParticleFactoryExtension.CreateParticle createParticle, SpriteProvider spriteProvider) {
      this.createParticle = createParticle;
      this.createSpriteBillboardParticle = null;
      this.spriteProvider = spriteProvider;
   }

   @MappedMethod
   public ParticleFactoryExtension(ParticleFactoryExtension.CreateSpriteBillboardParticle createSpriteBillboardParticle, SpriteProvider spriteProvider) {
      this.createParticle = null;
      this.createSpriteBillboardParticle = createSpriteBillboardParticle;
      this.spriteProvider = spriteProvider;
   }

   @Deprecated
   public final Particle createParticle(
      SimpleParticleType defaultParticleType, ClientLevel clientWorld, double x, double y, double z, double velocityX, double velocityY, double velocityZ
   ) {
      if (this.createParticle != null) {
         return (Particle)this.createParticle.create(new ClientWorld(clientWorld), x, y, z, velocityX, velocityY, velocityZ).data;
      } else if (this.createSpriteBillboardParticle != null) {
         SpriteBillboardParticle spriteBillboardParticle = this.createSpriteBillboardParticle
            .create(new ClientWorld(clientWorld), x, y, z, velocityX, velocityY, velocityZ);
         return (Particle)spriteBillboardParticle.data;
      } else {
         NullPointerException nullPointerException = new NullPointerException("Both createParticle and createSpriteBillboardParticle are null!");
         DummyClass.logException(nullPointerException);
         throw nullPointerException;
      }
   }

   @FunctionalInterface
   public interface CreateParticle {
      @MappedMethod
      org.mtr.mapping.holder.Particle create(ClientWorld var1, double var2, double var4, double var6, double var8, double var10, double var12);
   }

   @FunctionalInterface
   public interface CreateSpriteBillboardParticle {
      @MappedMethod
      SpriteBillboardParticle create(ClientWorld var1, double var2, double var4, double var6, double var8, double var10, double var12);
   }
}
