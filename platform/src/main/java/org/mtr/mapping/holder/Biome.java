package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Biome extends HolderBase<net.minecraft.world.level.biome.Biome> {
   public Biome(net.minecraft.world.level.biome.Biome data) {
      super(data);
   }

}
