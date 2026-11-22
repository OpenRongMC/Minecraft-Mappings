package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.phys.HitResult.Type;
@ParametersAreNonnullByDefault
public enum HitResultType {
   MISS(Type.MISS),
   BLOCK(Type.BLOCK),
   ENTITY(Type.ENTITY);

   public final Type data;

   private HitResultType(Type data) {
      this.data = data;
   }

   public static HitResultType convert(@Nullable Type data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
