package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class VertexFormatElement extends HolderBase<com.mojang.renderpearl.api.vertex.VertexFormatElement> {
   public VertexFormatElement(com.mojang.renderpearl.api.vertex.VertexFormatElement data) {
      super(data);
   }


}
