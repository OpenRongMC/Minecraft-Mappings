package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.scores.Objective;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ScoreboardObjective extends HolderBase<Objective> {
   public ScoreboardObjective(Objective data) {
      super(data);
   }


}
