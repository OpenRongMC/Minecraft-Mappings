package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.RandomSource;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Random extends HolderBase<RandomSource> {
   public Random(RandomSource data) {
      super(data);
   }

}
