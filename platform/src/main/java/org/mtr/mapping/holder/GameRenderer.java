package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class GameRenderer extends HolderBase<net.minecraft.client.renderer.GameRenderer> {
   public GameRenderer(net.minecraft.client.renderer.GameRenderer data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Camera getMainCamera() {
      return new Camera(((net.minecraft.client.renderer.GameRenderer)this.data).mainCamera());
   }

   @Nonnull
   @MappedMethod
   public Camera getCamera() {
      return this.getMainCamera();
   }


}
