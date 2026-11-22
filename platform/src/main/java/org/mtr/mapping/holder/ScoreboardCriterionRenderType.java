package org.mtr.mapping.holder;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.scores.criteria.ObjectiveCriteria.RenderType;
@ParametersAreNonnullByDefault
public enum ScoreboardCriterionRenderType {
   INTEGER(RenderType.INTEGER),
   HEARTS(RenderType.HEARTS);

   public final RenderType data;

   private ScoreboardCriterionRenderType(RenderType data) {
      this.data = data;
   }

   public static ScoreboardCriterionRenderType convert(@Nullable RenderType data) {
      return data == null ? null : values()[data.ordinal()];
   }

}
