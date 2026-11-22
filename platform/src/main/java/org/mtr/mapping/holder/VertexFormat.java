package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class VertexFormat extends HolderBase<com.mojang.renderpearl.api.vertex.VertexFormat> {
   public VertexFormat(com.mojang.renderpearl.api.vertex.VertexFormat data) {
      super(data);
   }

}
