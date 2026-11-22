package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
@ParametersAreNonnullByDefault
public enum SlabType {
   TOP(net.minecraft.world.level.block.state.properties.SlabType.TOP),
   BOTTOM(net.minecraft.world.level.block.state.properties.SlabType.BOTTOM),
   DOUBLE(net.minecraft.world.level.block.state.properties.SlabType.DOUBLE);

   public final net.minecraft.world.level.block.state.properties.SlabType data;

   private SlabType(net.minecraft.world.level.block.state.properties.SlabType data) {
      this.data = data;
   }

   public static SlabType convert(@Nullable net.minecraft.world.level.block.state.properties.SlabType data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
