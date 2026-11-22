package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.renderer.Lightmap;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class LightmapTextureManager extends HolderBase<Lightmap> {
   public LightmapTextureManager(Lightmap data) {
      super(data);
   }

   @MappedMethod
   public static int block(int i) {
      return net.minecraft.util.LightCoordsUtil.block(i);
   }

   @MappedMethod
   public static int pack(int i, int j) {
      return net.minecraft.util.LightCoordsUtil.pack(i, j);
   }

   @MappedMethod
   public static int getBlockLightCoordinates(int light) {
      return block(light);
   }


}
