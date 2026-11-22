package org.mtr.mapping.holder;


import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class OverlayTexture extends HolderBase<net.minecraft.client.renderer.texture.OverlayTexture> {
   public OverlayTexture(net.minecraft.client.renderer.texture.OverlayTexture data) {
      super(data);
   }

   @MappedMethod
   public static int getNoOverlayMapped() {
      return net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;
   }

   @MappedMethod
   public static int getDefaultUvMapped() {
      return getNoOverlayMapped();
   }


}
