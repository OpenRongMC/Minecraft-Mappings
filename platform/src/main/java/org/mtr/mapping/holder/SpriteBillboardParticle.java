package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.particle.SingleQuadParticle;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class SpriteBillboardParticle extends HolderBase<SingleQuadParticle> {
   public SpriteBillboardParticle(SingleQuadParticle data) {
      super(data);
   }

}
