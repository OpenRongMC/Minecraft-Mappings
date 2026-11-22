package org.mtr.mapping.render.vertex;

import com.mojang.blaze3d.vertex.VertexConsumer;
import org.mtr.mapping.holder.Vector3f;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.render.model.RawMesh;
import org.mtr.mapping.render.model.RawModel;

public final class CapturingVertexConsumer implements VertexConsumer {
   private RawMesh buildingMesh;
   private Vertex buildingVertex = new Vertex();
   public final RawModel rawModel = new RawModel();

   public void beginStage(MaterialProperties materialProperties) {
      this.buildingMesh = this.rawModel.getRawMesh(materialProperties);
   }

   public void finish() {
      if (this.buildingVertex.position != null) {
         this.buildingMesh.addVertex(this.buildingVertex);
         this.buildingVertex = new Vertex();
      }
   }

   public VertexConsumer addVertex(float x, float y, float z) {
      this.finish();
      this.buildingVertex.position = new Vector3f(x, y, z);
      return this;
   }

   public VertexConsumer setColor(int red, int green, int blue, int alpha) {
      return this;
   }

   public VertexConsumer setColor(int color) {
      return this;
   }

   public VertexConsumer setUv(float u, float v) {
      this.buildingVertex.u = u;
      this.buildingVertex.v = v;
      return this;
   }

   public VertexConsumer setUv1(int u, int v) {
      return this;
   }

   public VertexConsumer setUv2(int u, int v) {
      return this;
   }

   public VertexConsumer setUv3(float u, float v) { return this; }

   public VertexConsumer setNormal(float x, float y, float z) {
      this.buildingVertex.normal = new Vector3f(x, y, z);
      return this;
   }

   public VertexConsumer setLineWidth(float lineWidth) {
      return this;
   }
}
