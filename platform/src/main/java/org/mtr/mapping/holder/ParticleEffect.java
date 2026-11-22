package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.particles.ParticleOptions;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ParticleEffect extends HolderBase<ParticleOptions> {
   public ParticleEffect(ParticleOptions data) {
      super(data);
   }

}
