package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
@ParametersAreNonnullByDefault
public enum Mirror {
   NONE(net.minecraft.world.level.block.Mirror.NONE),
   LEFT_RIGHT(net.minecraft.world.level.block.Mirror.LEFT_RIGHT),
   FRONT_BACK(net.minecraft.world.level.block.Mirror.FRONT_BACK);

   public final net.minecraft.world.level.block.Mirror data;

   private Mirror(net.minecraft.world.level.block.Mirror data) {
      this.data = data;
   }

   public static Mirror convert(@Nullable net.minecraft.world.level.block.Mirror data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
