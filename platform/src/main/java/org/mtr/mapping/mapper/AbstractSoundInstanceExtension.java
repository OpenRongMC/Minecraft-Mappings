package org.mtr.mapping.mapper;

import java.util.function.Consumer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.util.RandomSource;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.AbstractSoundInstanceAbstractMapping;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Random;
import org.mtr.mapping.holder.SoundCategory;
import org.mtr.mapping.holder.SoundEvent;

public abstract class AbstractSoundInstanceExtension extends AbstractSoundInstanceAbstractMapping {
   @MappedMethod
   protected void setIsRelativeMapped(boolean isRelative) {
      this.relative = isRelative;
   }

   @MappedMethod
   protected void setIsRepeatableMapped(boolean isRepeatable) {
      this.looping = isRepeatable;
   }

   @MappedMethod
   @Override
   public boolean isRelative2() {
      return super.isRelative2();
   }

   @MappedMethod
   public boolean isRepeatable() {
      return this.getLoopingMapped();
   }

   @MappedMethod
   protected AbstractSoundInstanceExtension(SoundEvent sound, SoundCategory category) {
      super(sound, category, new Random(RandomSource.create()));
   }

   @MappedMethod
   protected AbstractSoundInstanceExtension(Identifier soundId, SoundCategory category) {
      super(soundId, category, new Random(RandomSource.create()));
   }

   @MappedMethod
   public static void iterateSoundIds(Consumer<Identifier> consumer) {
      ((SoundManager)MinecraftClient.getInstance().getSoundManager().data)
         .getAvailableSounds()
         .forEach(identifier -> consumer.accept(new Identifier(identifier)));
   }
}
