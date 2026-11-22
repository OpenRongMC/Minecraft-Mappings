package org.mtr.mapping.registry;

import java.util.function.Consumer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.SoundEvent;
import org.mtr.mapping.tool.RegistryObject;

public final class SoundEventRegistryObject extends RegistryObject<SoundEvent> {
   private final SoundEvent soundEvent;

   SoundEventRegistryObject(SoundEvent soundEvent) {
      this.soundEvent = soundEvent;
   }

   @MappedMethod
   public SoundEvent get() {
      return this.soundEvent;
   }

   @MappedMethod
   public boolean isPresent() {
      return true;
   }

   @MappedMethod
   public void ifPresent(Consumer<SoundEvent> consumer) {
      consumer.accept(this.soundEvent);
   }
}
