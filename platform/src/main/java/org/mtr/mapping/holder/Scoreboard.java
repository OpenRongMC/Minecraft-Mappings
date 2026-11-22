package org.mtr.mapping.holder;


import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Scoreboard extends HolderBase<net.minecraft.world.scores.Scoreboard> {
   public Scoreboard(net.minecraft.world.scores.Scoreboard data) {
      super(data);
   }


}
