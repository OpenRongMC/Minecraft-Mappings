package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ScoreboardCriterion extends HolderBase<ObjectiveCriteria> {
   public ScoreboardCriterion(ObjectiveCriteria data) {
      super(data);
   }

}
