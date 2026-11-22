package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.GameType;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum GameMode {
   SURVIVAL(GameType.SURVIVAL),
   CREATIVE(GameType.CREATIVE),
   ADVENTURE(GameType.ADVENTURE),
   SPECTATOR(GameType.SPECTATOR);

   public final GameType data;

   private GameMode(GameType data) {
      this.data = data;
   }

   public static GameMode convert(@Nullable GameType data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @Nonnull
   @MappedMethod
   public static GameMode getCreativeMapped() {
      return convert(GameType.CREATIVE);
   }

   @Nonnull
   @MappedMethod
   public static GameMode getSurvivalMapped() {
      return convert(GameType.SURVIVAL);
   }

}
