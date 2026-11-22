package org.mtr.mapping.render.object;

import com.mojang.blaze3d.systems.RenderSystem;
import java.io.Closeable;
import org.lwjgl.opengl.GL33;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.render.model.Mesh;
import org.mtr.mapping.render.tool.GlStateTracker;
import org.mtr.mapping.render.vertex.VertexAttributeMapping;

public final class VertexArray implements Closeable {
   private int id;
   public final MaterialProperties materialProperties;
   public final IndexBuffer indexBuffer;
   public final VertexAttributeMapping mapping;

   public VertexArray(Mesh mesh, VertexAttributeMapping mapping) {
      this.id = GL33.glGenVertexArrays();
      this.materialProperties = mesh.materialProperties;
      this.indexBuffer = mesh.indexBuffer;
      this.mapping = mapping;
      GL33.glBindVertexArray(this.id);
      mapping.setupAttributesToVao(mesh.vertexBuffer);
      mesh.indexBuffer.bind(34963);
      unbind();
   }

   public VertexArray(VertexArray other, MaterialProperties materialProperties) {
      this.id = other.id;
      this.materialProperties = materialProperties;
      this.indexBuffer = other.indexBuffer;
      this.mapping = other.mapping;
   }

   public void bind() {
      GlStateTracker.assertProtected();
      GL33.glBindVertexArray(this.id);
   }

   public static void unbind() {
      GlStateTracker.assertProtected();
      GL33.glBindVertexArray(0);
   }

   public void draw() {
      GL33.glDrawElements(4, this.indexBuffer.getVertexCount(), this.indexBuffer.indexType, 0L);
   }

   @Override
   public void close() {
      if (RenderSystem.isOnRenderThread()) {
         GL33.glDeleteVertexArrays(this.id);
         this.id = 0;
      } else {
         RenderSystem.queueFencedTask(this::close);
      }
   }
}
