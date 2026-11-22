package org.mtr.mapping.render.vertex;

import org.lwjgl.opengl.GL33;
import org.mtr.mapping.render.tool.GlStateTracker;

public enum VertexAttributeType {
   POSITION(0, 5126, 3, 1, false, false),
   COLOR(1, 5121, 4, 1, true, false),
   UV_TEXTURE(2, 5126, 2, 1, false, false),
   UV_OVERLAY(3, 5122, 2, 1, false, true),
   UV_LIGHTMAP(4, 5122, 2, 1, false, true),
   NORMAL(5, 5120, 3, 1, true, false),
   MATRIX_MODEL(6, 5126, 4, 4, false, false);

   public final int location;
   public final int type;
   public final int size;
   public final int span;
   public final int byteSize;
   public final boolean normalized;
   public final boolean iPointer;

   private VertexAttributeType(int location, int type, int size, int span, boolean normalized, boolean iPointer) {
      this.location = location;
      this.type = type;
      this.size = size;
      this.span = span;
      this.normalized = normalized;
      this.iPointer = iPointer;

      this.byteSize = switch (type) {
         case 5120, 5121 -> 1;
         case 5122 -> 2;
         default -> 0;
         case 5126 -> 4;
      } * size * span;
   }

   public void toggleAttributeArray(boolean enable) {
      for (int i = 0; i < this.span; i++) {
         if (enable) {
            GL33.glEnableVertexAttribArray(this.location + i);
         } else {
            GL33.glDisableVertexAttribArray(this.location + i);
         }
      }
   }

   public void setupAttributePointer(int stride, int pointer) {
      for (int i = 0; i < this.span; i++) {
         int attributePointer = pointer + i * this.byteSize / this.span;
         if (this.iPointer) {
            if (!GlStateTracker.isGl4ES()) {
               GL33.glVertexAttribIPointer(this.location + i, this.size, this.type, stride, attributePointer);
            } else {
               GL33.glVertexAttribPointer(this.location + i, this.size, this.type, false, stride, attributePointer);
            }
         } else {
            GL33.glVertexAttribPointer(this.location + i, this.size, this.type, this.normalized, stride, attributePointer);
         }
      }
   }

   public void setAttributeDivisor(int divisor) {
      if (GlStateTracker.supportVertexAttributeDivisor()) {
         for (int i = 0; i < this.span; i++) {
            GL33.glVertexAttribDivisor(this.location + i, divisor);
         }
      }
   }
}
