package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.block.Rotation;
@ParametersAreNonnullByDefault
public enum BlockRotation {
   NONE(Rotation.NONE),
   CLOCKWISE_90(Rotation.CLOCKWISE_90),
   CLOCKWISE_180(Rotation.CLOCKWISE_180),
   COUNTERCLOCKWISE_90(Rotation.COUNTERCLOCKWISE_90);

   public final Rotation data;

   private BlockRotation(Rotation data) {
      this.data = data;
   }

   public static BlockRotation convert(@Nullable Rotation data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
