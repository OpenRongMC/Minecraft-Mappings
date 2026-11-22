package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.InteractionHand;
@ParametersAreNonnullByDefault
public enum Hand {
   MAIN_HAND(InteractionHand.MAIN_HAND),
   OFF_HAND(InteractionHand.OFF_HAND);

   public final InteractionHand data;

   private Hand(InteractionHand data) {
      this.data = data;
   }

   public static Hand convert(@Nullable InteractionHand data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
