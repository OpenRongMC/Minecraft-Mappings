package org.mtr.mapping.render.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joml.Matrix4f;
import org.mtr.mapping.render.object.VertexArray;
import org.mtr.mapping.render.shader.ShaderManager;
import org.mtr.mapping.render.vertex.VertexAttributeState;

public final class BatchManager {
   private final Map<MaterialProperties, List<BatchManager.RenderCall>> opaqueBatches = new HashMap<>();
   private final Map<MaterialProperties, List<BatchManager.RenderCall>> cutoutBatches = new HashMap<>();
   private final Map<MaterialProperties, List<BatchManager.RenderCall>> translucentBatches = new HashMap<>();

   public void queue(List<VertexArray> vertexArrays, VertexAttributeState vertexAttributeState) {
      vertexArrays.forEach(vertexArray -> this.queue(vertexArray, vertexAttributeState));
   }

   public void queue(VertexArray vertexArray, VertexAttributeState vertexAttributeState) {
      MaterialProperties materialProperties = vertexArray.materialProperties;
      (materialProperties.translucent ? this.translucentBatches : (materialProperties.cutoutHack ? this.cutoutBatches : this.opaqueBatches))
         .computeIfAbsent(materialProperties, key -> new ArrayList<>())
         .add(new BatchManager.RenderCall(vertexArray, vertexAttributeState));
   }

   public void drawAll(ShaderManager shaderManager, boolean renderTranslucent) {
      drawBatch(this.opaqueBatches, shaderManager);
      drawBatch(this.cutoutBatches, shaderManager);
      if (renderTranslucent) {
         drawBatch(this.translucentBatches, shaderManager);
      }
   }

   private static void drawBatch(Map<MaterialProperties, List<BatchManager.RenderCall>> batches, ShaderManager shaderManager) {
      batches.forEach((materialProperties, renderCalls) -> {
         if (renderCalls != null) {
            shaderManager.setupShaderBatchState(materialProperties);
            renderCalls.forEach(renderCall -> renderCall.draw(shaderManager));
            shaderManager.cleanupShaderBatchState();
         }
      });
      batches.clear();
   }

   private static class RenderCall {
      public final VertexArray vertexArray;
      public final VertexAttributeState vertexAttributeState;

      public RenderCall(VertexArray vertexArray, VertexAttributeState vertexAttributeState) {
         this.vertexArray = vertexArray;
         this.vertexAttributeState = vertexAttributeState;
      }

      public void draw(ShaderManager shaderManager) {
         this.vertexArray.bind();
         if (this.vertexAttributeState.matrix4f != null) {
            shaderManager.setModelMat((Matrix4f)this.vertexAttributeState.matrix4f.data);
         } else {
            shaderManager.setModelMat(new Matrix4f());
         }

         this.vertexAttributeState.apply();
         this.vertexArray.materialProperties.vertexAttributeState.apply();
         this.vertexArray.draw();
      }
   }
}
