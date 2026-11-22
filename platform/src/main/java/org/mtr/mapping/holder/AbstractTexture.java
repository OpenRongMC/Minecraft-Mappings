package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class AbstractTexture extends HolderBase<net.minecraft.client.renderer.texture.AbstractTexture> {
   public AbstractTexture(net.minecraft.client.renderer.texture.AbstractTexture data) {
      super(data);
   }

}
