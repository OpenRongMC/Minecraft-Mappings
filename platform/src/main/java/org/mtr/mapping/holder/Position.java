package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Position extends HolderBase<net.minecraft.core.Position> {
   public Position(net.minecraft.core.Position data) {
      super(data);
   }

}
