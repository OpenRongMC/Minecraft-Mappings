package org.mtr.mapping.holder;
import net.minecraft.util.RandomSource;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class MovingSoundInstanceAbstractMapping extends AbstractTickableSoundInstance {
   

   @MappedMethod
   public abstract void tick();

@MappedMethod
   public MovingSoundInstanceAbstractMapping(SoundEvent soundEvent, SoundCategory soundSource, Random randomSource) {
      super((net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, (RandomSource)randomSource.data);
   }
}
