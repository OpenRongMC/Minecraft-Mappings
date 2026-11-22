package org.mtr.mapping.render.vertex;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Objects;
import javax.annotation.Nullable;
import org.lwjgl.opengl.GL33;
import org.mtr.mapping.holder.Matrix4f;
import org.mtr.mapping.holder.OverlayTexture;
import org.mtr.mapping.holder.Vector3f;
import org.mtr.mapping.render.tool.GlStateTracker;
import org.mtr.mapping.render.tool.Utilities;

public final class VertexAttributeState {
   public final Integer color;
   public final Integer lightmapUV;
   public final Vector3f position;
   public final Float textureU;
   public final Float textureV;
   public final Integer overlayUV;
   public final Vector3f normal;
   public final Matrix4f matrix4f;

   public VertexAttributeState(int color, int lightmapUV, Matrix4f matrix4f) {
      this.color = color;
      this.lightmapUV = Utilities.exchangeLightmapUVBits(lightmapUV);
      this.position = null;
      this.textureU = null;
      this.textureV = null;
      this.overlayUV = Utilities.exchangeLightmapUVBits(OverlayTexture.getNoOverlayMapped());
      this.normal = null;
      this.matrix4f = matrix4f;
   }

   public VertexAttributeState(@Nullable Integer color, @Nullable Integer lightmapUV) {
      this.color = color;
      this.lightmapUV = lightmapUV;
      this.position = null;
      this.textureU = null;
      this.textureV = null;
      this.overlayUV = null;
      this.normal = null;
      this.matrix4f = null;
   }

   public void apply() {
      for (VertexAttributeType vertexAttributeType : VertexAttributeType.values()) {
         switch (vertexAttributeType) {
            case POSITION:
               if (this.position != null) {
                  GL33.glVertexAttrib3f(vertexAttributeType.location, this.position.getX(), this.position.getY(), this.position.getZ());
               }
               break;
            case COLOR:
               if (this.color != null) {
                  GL33.glVertexAttrib4f(
                     vertexAttributeType.location,
                     (this.color >>> 24 & 0xFF) / 255.0F,
                     (this.color >>> 16 & 0xFF) / 255.0F,
                     (this.color >>> 8 & 0xFF) / 255.0F,
                     (this.color & 0xFF) / 255.0F
                  );
               }
               break;
            case UV_TEXTURE:
               if (this.textureU != null && this.textureV != null) {
                  GL33.glVertexAttrib2f(vertexAttributeType.location, this.textureU, this.textureV);
               }
               break;
            case UV_OVERLAY:
               if (this.overlayUV != null) {
                  if (!GlStateTracker.isGl4ES()) {
                     GL33.glVertexAttribI2i(vertexAttributeType.location, (short)(this.overlayUV >>> 16), (short)this.overlayUV.intValue());
                  } else {
                     GL33.glVertexAttrib2f(vertexAttributeType.location, (short)(this.overlayUV >>> 16), (short)this.overlayUV.intValue());
                  }
               }
               break;
            case UV_LIGHTMAP:
               if (this.lightmapUV != null) {
                  if (!GlStateTracker.isGl4ES()) {
                     GL33.glVertexAttribI2i(vertexAttributeType.location, (short)(this.lightmapUV >>> 16), (short)this.lightmapUV.intValue());
                  } else {
                     GL33.glVertexAttrib2f(vertexAttributeType.location, (short)(this.lightmapUV >>> 16), (short)this.lightmapUV.intValue());
                  }
               }
               break;
            case NORMAL:
               if (this.normal != null) {
                  GL33.glVertexAttrib3f(vertexAttributeType.location, this.normal.getX(), this.normal.getY(), this.normal.getZ());
               }
               break;
            case MATRIX_MODEL:
               if (this.matrix4f != null && Utilities.canUseCustomShader()) {
                  FloatBuffer floatBuffer = ByteBuffer.allocate(64).asFloatBuffer();
                  Utilities.store(this.matrix4f, floatBuffer);
                  GL33.glVertexAttrib4f(vertexAttributeType.location, floatBuffer.get(0), floatBuffer.get(1), floatBuffer.get(2), floatBuffer.get(3));
                  GL33.glVertexAttrib4f(vertexAttributeType.location + 1, floatBuffer.get(4), floatBuffer.get(5), floatBuffer.get(6), floatBuffer.get(7));
                  GL33.glVertexAttrib4f(vertexAttributeType.location + 2, floatBuffer.get(8), floatBuffer.get(9), floatBuffer.get(10), floatBuffer.get(11));
                  GL33.glVertexAttrib4f(vertexAttributeType.location + 3, floatBuffer.get(12), floatBuffer.get(13), floatBuffer.get(14), floatBuffer.get(15));
               }
         }
      }
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      } else {
         return !(object instanceof VertexAttributeState vertexAttributeState)
            ? false
            : Objects.equals(this.color, vertexAttributeState.color)
               && Objects.equals(this.lightmapUV, vertexAttributeState.lightmapUV)
               && Objects.equals(this.position, vertexAttributeState.position)
               && Objects.equals(this.textureU, vertexAttributeState.textureU)
               && Objects.equals(this.textureV, vertexAttributeState.textureV)
               && Objects.equals(this.overlayUV, vertexAttributeState.overlayUV)
               && Objects.equals(this.normal, vertexAttributeState.normal)
               && Objects.equals(this.matrix4f, vertexAttributeState.matrix4f);
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.position, this.color, this.textureU, this.textureV, this.lightmapUV, this.normal, this.overlayUV, this.matrix4f);
   }
}
