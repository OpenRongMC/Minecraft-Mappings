package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.sounds.SoundSource;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum SoundCategory {
   MASTER(SoundSource.MASTER),
   MUSIC(SoundSource.MUSIC),
   RECORDS(SoundSource.RECORDS),
   WEATHER(SoundSource.WEATHER),
   BLOCKS(SoundSource.BLOCKS),
   HOSTILE(SoundSource.HOSTILE),
   NEUTRAL(SoundSource.NEUTRAL),
   PLAYERS(SoundSource.PLAYERS),
   AMBIENT(SoundSource.AMBIENT),
   VOICE(SoundSource.VOICE),
   UI(SoundSource.UI);

   public final SoundSource data;

   private SoundCategory(SoundSource data) {
      this.data = data;
   }

   public static SoundCategory convert(@Nullable SoundSource data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @Nonnull
   @MappedMethod
   public String getName() {
      return this.data.getName();
   }

   @Nonnull
   @MappedMethod
   public static SoundCategory getAmbientMapped() {
      return convert(SoundSource.AMBIENT);
   }

   @Nonnull
   @MappedMethod
   public static SoundCategory getBlocksMapped() {
      return convert(SoundSource.BLOCKS);
   }

   
}
