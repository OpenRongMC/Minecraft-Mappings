package org.mtr.mapping.mapper;

import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.VertexFormat;

public final class RenderLayerHelper {
   @MappedMethod
   public static RenderLayer createTriangles(
      String name, VertexFormat vertexFormat, int expectedBufferSize, boolean hasCrumbling, boolean translucent, RenderLayer renderLayerForPhase
   ) {
      return renderLayerForPhase;
   }

   @MappedMethod
   public static RenderLayer createQuads(
      String name, VertexFormat vertexFormat, int expectedBufferSize, boolean hasCrumbling, boolean translucent, RenderLayer renderLayerForPhase
   ) {
      return renderLayerForPhase;
   }
}
