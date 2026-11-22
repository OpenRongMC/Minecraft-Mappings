package org.mtr.mapping.registry;

import java.util.function.Consumer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.DefaultParticleType;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.tool.RegistryObject;

public final class ParticleTypeRegistryObject extends RegistryObject<DefaultParticleType> {
   final Identifier identifier;
   private final DefaultParticleType defaultParticleType;

   ParticleTypeRegistryObject(DefaultParticleType defaultParticleType, Identifier identifier) {
      this.identifier = identifier;
      this.defaultParticleType = defaultParticleType;
   }

   @MappedMethod
   public DefaultParticleType get() {
      return this.defaultParticleType;
   }

   @MappedMethod
   public boolean isPresent() {
      return true;
   }

   @MappedMethod
   public void ifPresent(Consumer<DefaultParticleType> consumer) {
      consumer.accept(this.defaultParticleType);
   }
}
