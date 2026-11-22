package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public interface TickableSoundInstance extends net.minecraft.client.resources.sounds.TickableSoundInstance {

   @MappedMethod
   boolean isStopped2();

   @Deprecated
   default boolean isStopped() {
      return this.isStopped2();
   }

   @MappedMethod
   boolean isRelative2();

   @Deprecated
   default boolean isRelative() {
      return this.isRelative2();
   }

   @MappedMethod
   void tick2();

   @Deprecated
   default void tick() {
      this.tick2();
   }

   @MappedMethod
   boolean isLooping2();

   @Deprecated
   default boolean isLooping() {
      return this.isLooping2();
   }
}
