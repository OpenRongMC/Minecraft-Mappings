package org.mtr.mapping.holder;
import net.minecraft.util.RandomSource;


import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class AbstractSoundInstanceAbstractMapping extends net.minecraft.client.resources.sounds.AbstractSoundInstance {

   @MappedMethod
   public boolean isRelative2() {
      return super.isRelative();
   }

   @Deprecated
   public final boolean isRelative() {
      return this.isRelative2();
   }

   @MappedMethod
   public double getZMapped() {
      return this.z;
   }

   @MappedMethod
   public void setZMapped(double newData) {
      this.z = newData;
   }

   @MappedMethod
   public double getYMapped() {
      return this.y;
   }

   @MappedMethod
   public void setYMapped(double newData) {
      this.y = newData;
   }

   @MappedMethod
   public double getXMapped() {
      return this.x;
   }

   @MappedMethod
   public void setXMapped(double newData) {
      this.x = newData;
   }

   @MappedMethod
   public boolean getLoopingMapped() {
      return this.looping;
   }

@MappedMethod
   public AbstractSoundInstanceAbstractMapping(SoundEvent soundEvent, SoundCategory soundSource, Random randomSource) {
      super((net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, (RandomSource)randomSource.data);
   }

@MappedMethod
   public AbstractSoundInstanceAbstractMapping(Identifier identifier, SoundCategory soundSource, Random randomSource) {
      super((net.minecraft.resources.Identifier)identifier.data, soundSource.data, (RandomSource)randomSource.data);
   }
   public boolean isLooping2() { return super.isLooping(); }
   @Override public final boolean isLooping() { return isLooping2(); }
}
