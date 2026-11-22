package org.mtr.mapping.mapper;

import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.TickableSoundInstance;

public interface TickableSoundInstanceExtension extends TickableSoundInstance {
   @MappedMethod
   @Override
   boolean isRelative2();

   @MappedMethod
   @Override
   boolean isLooping2();

   @MappedMethod
   default boolean isDone() {
      return false;
   }

   @MappedMethod
   @Override
   default boolean isStopped2() {
      return this.isDone();
   }
}
