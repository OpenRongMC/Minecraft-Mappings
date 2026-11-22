package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public enum Axis {
   X(net.minecraft.core.Direction.Axis.X),
   Y(net.minecraft.core.Direction.Axis.Y),
   Z(net.minecraft.core.Direction.Axis.Z);

   public final net.minecraft.core.Direction.Axis data;

   private Axis(net.minecraft.core.Direction.Axis data) {
      this.data = data;
   }

   public static Axis convert(@Nullable net.minecraft.core.Direction.Axis data) {
      return data == null ? null : values()[data.ordinal()];
   }

   @MappedMethod
   public boolean isHorizontal() {
      return this.data.isHorizontal();
   }

}
