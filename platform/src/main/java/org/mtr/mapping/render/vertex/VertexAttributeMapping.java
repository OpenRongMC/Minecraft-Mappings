package org.mtr.mapping.render.vertex;

import java.util.HashMap;
import java.util.Map;
import org.mtr.mapping.render.object.VertexBuffer;

public final class VertexAttributeMapping {
   public final Map<VertexAttributeType, VertexAttributeSource> sources;
   public final Map<VertexAttributeType, Integer> pointers = new HashMap<>();
   public final int strideVertex;
   public final int strideInstance;
   public final int paddingVertex;
   public final int paddingInstance;

   private VertexAttributeMapping(Map<VertexAttributeType, VertexAttributeSource> sources) {
      this.sources = sources;
      int strideVertex = 0;
      int strideInstance = 0;

      for (VertexAttributeType vertexAttributeType : VertexAttributeType.values()) {
         switch ((VertexAttributeSource)sources.get(vertexAttributeType)) {
            case VERTEX_BUFFER:
               this.pointers.put(vertexAttributeType, strideVertex);
               strideVertex += vertexAttributeType.byteSize;
               break;
            case INSTANCE_BUFFER:
               this.pointers.put(vertexAttributeType, strideInstance);
               strideInstance += vertexAttributeType.byteSize;
         }
      }

      if (strideVertex % 2 != 0) {
         strideVertex++;
         this.paddingVertex = 1;
      } else {
         this.paddingVertex = 0;
      }

      if (strideInstance % 2 != 0) {
         strideInstance++;
         this.paddingInstance = 1;
      } else {
         this.paddingInstance = 0;
      }

      this.strideVertex = strideVertex;
      this.strideInstance = strideInstance;
   }

   public void setupAttributesToVao(VertexBuffer vertexBuf) {
      for (VertexAttributeType vertexAttributeType : VertexAttributeType.values()) {
         switch ((VertexAttributeSource)this.sources.get(vertexAttributeType)) {
            case VERTEX_BUFFER:
               vertexAttributeType.toggleAttributeArray(true);
               vertexBuf.bind(34962);
               vertexAttributeType.setupAttributePointer(this.strideVertex, this.pointers.get(vertexAttributeType));
               vertexAttributeType.setAttributeDivisor(0);
               break;
            case GLOBAL:
               vertexAttributeType.toggleAttributeArray(false);
         }
      }
   }

   public static class Builder {
      private final HashMap<VertexAttributeType, VertexAttributeSource> sources = new HashMap<>(VertexAttributeType.values().length);

      public Builder() {
         for (VertexAttributeType vertexAttributeType : VertexAttributeType.values()) {
            this.sources.put(vertexAttributeType, VertexAttributeSource.VERTEX_BUFFER);
         }
      }

      public VertexAttributeMapping.Builder set(VertexAttributeType vertexAttributeType, VertexAttributeSource vertexAttributeSource) {
         this.sources.put(vertexAttributeType, vertexAttributeSource);
         return this;
      }

      public VertexAttributeMapping build() {
         return new VertexAttributeMapping(this.sources);
      }
   }
}
