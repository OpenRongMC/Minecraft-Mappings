package org.mtr.mapping.holder;


import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Frustum extends HolderBase<net.minecraft.client.renderer.culling.Frustum> {
   public Frustum(net.minecraft.client.renderer.culling.Frustum data) {
      super(data);
   }



@MappedMethod
   public Frustum(Frustum frustum) {
      super(new net.minecraft.client.renderer.culling.Frustum((net.minecraft.client.renderer.culling.Frustum)frustum.data));
   }
}
