package org.mtr.mapping.render.object;

import com.mojang.blaze3d.systems.RenderSystem;
import java.io.Closeable;
import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;
import org.mtr.mapping.render.tool.GlStateTracker;

public class VertexBuffer implements Closeable {
   private int id = GL33.glGenBuffers();
   public static final int USAGE_STATIC_DRAW = 35044;
   public static final int USAGE_DYNAMIC_DRAW = 35048;
   public static final int USAGE_STREAM_DRAW = 35040;

   public void bind(int target) {
      GlStateTracker.assertProtected();
      GL33.glBindBuffer(target, this.id);
   }

   public void upload(ByteBuffer buffer, int usage) {
      int vboPrevious = GL33.glGetInteger(34964);
      GL33.glBindBuffer(34962, this.id);
      buffer.clear();
      GL33.glBufferData(34962, buffer, usage);
      GL33.glBindBuffer(34962, vboPrevious);
   }

   public void upload(ByteBuffer buffer, int size, int usage) {
      int vboPrevious = GL33.glGetInteger(34964);
      GL33.glBindBuffer(34962, this.id);
      buffer.clear();
      GL33.nglBufferData(34962, size, MemoryUtil.memAddress0(buffer), usage);
      GL33.glBindBuffer(34962, vboPrevious);
   }

   @Override
   public void close() {
      if (RenderSystem.isOnRenderThread()) {
         GL33.glDeleteBuffers(this.id);
         this.id = 0;
      } else {
         RenderSystem.queueFencedTask(this::close);
      }
   }
}
