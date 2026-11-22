package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Particle extends HolderBase<net.minecraft.client.particle.Particle> {
   public Particle(net.minecraft.client.particle.Particle data) {
      super(data);
   }

}
