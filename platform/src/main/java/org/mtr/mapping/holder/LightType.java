package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.LightLayer;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum LightType {
   SKY(LightLayer.SKY),
   BLOCK(LightLayer.BLOCK);

   public final LightLayer data;

   private LightType(LightLayer data) {
      this.data = data;
   }

   public static LightType convert(@Nullable LightLayer data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @Nonnull
   @MappedMethod
   public static LightType getBlockMapped() {
      return convert(LightLayer.BLOCK);
   }

   @Nonnull
   @MappedMethod
   public static LightType getSkyMapped() {
      return convert(LightLayer.SKY);
   }
}
