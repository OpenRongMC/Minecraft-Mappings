package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.block.state.properties.StairsShape;
@ParametersAreNonnullByDefault
public enum StairShape {
   STRAIGHT(StairsShape.STRAIGHT),
   INNER_LEFT(StairsShape.INNER_LEFT),
   INNER_RIGHT(StairsShape.INNER_RIGHT),
   OUTER_LEFT(StairsShape.OUTER_LEFT),
   OUTER_RIGHT(StairsShape.OUTER_RIGHT);

   public final StairsShape data;

   private StairShape(StairsShape data) {
      this.data = data;
   }

   public static StairShape convert(@Nullable StairsShape data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
