package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.particle.SpriteSet;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class SpriteProvider extends HolderBase<SpriteSet> {
   public SpriteProvider(SpriteSet data) {
      super(data);
   }

}
