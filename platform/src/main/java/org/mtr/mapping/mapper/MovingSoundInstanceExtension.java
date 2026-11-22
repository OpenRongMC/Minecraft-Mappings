package org.mtr.mapping.mapper;

import net.minecraft.util.RandomSource;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.MovingSoundInstanceAbstractMapping;
import org.mtr.mapping.holder.Random;
import org.mtr.mapping.holder.SoundCategory;
import org.mtr.mapping.holder.SoundEvent;

public abstract class MovingSoundInstanceExtension extends MovingSoundInstanceAbstractMapping {
   @MappedMethod
   protected void setIsRelativeMapped(boolean isRelative) {
      this.relative = isRelative;
   }

   @MappedMethod
   protected void setIsRepeatableMapped(boolean isRepeatable) {
      this.looping = isRepeatable;
   }

   @MappedMethod
   public boolean isRelative() {
      return super.isRelative();
   }

   @MappedMethod
   public boolean isRepeatable() {
      return this.looping;
   }

   @MappedMethod
   protected MovingSoundInstanceExtension(SoundEvent sound, SoundCategory category) {
      super(sound, category, new Random(RandomSource.create()));
   }

   @MappedMethod
   protected void setVolume(float volume) {
      this.volume = volume;
   }

   @MappedMethod
   protected void setPitch(float pitch) {
      this.pitch = pitch;
   }

   @MappedMethod
   protected void setRepeatDelay(int repeatDelay) {
      this.delay = repeatDelay;
   }

   @MappedMethod
   protected void setX(double x) {
      this.x = x;
   }

   @MappedMethod
   protected void setY(double y) {
      this.y = y;
   }

   @MappedMethod
   protected void setZ(double z) {
      this.z = z;
   }

   @Deprecated
   @Override
   public final void tick() {
      this.tick2();
   }

   @MappedMethod
   public void tick2() {
   }

   @MappedMethod
   public void setDone2() {
      this.stop();
   }

   @MappedMethod
   public boolean shouldAlwaysPlay2() {
      return super.canStartSilent();
   }

   @Deprecated
   public boolean canStartSilent() {
      return this.shouldAlwaysPlay2();
   }

   @MappedMethod
   public boolean canPlay2() {
      return super.canPlaySound();
   }

   @Deprecated
   public boolean canPlaySound() {
      return this.canPlay2();
   }
}
