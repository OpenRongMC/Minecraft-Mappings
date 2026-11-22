package org.mtr.mapping.render.object;

import java.nio.ByteBuffer;
import org.lwjgl.opengl.ARBInstancedArrays;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;
import org.lwjgl.opengl.GLCapabilities;
import org.mtr.mapping.render.vertex.VertexAttributeMapping;
import org.mtr.mapping.render.vertex.VertexAttributeType;

public final class InstancedDrawHelper {
   public static boolean setupInstanceAttributes(
      VertexArray vertexArray, VertexBuffer instanceBuffer, VertexAttributeMapping mapping, VertexAttributeType... vertexAttributeTypes
   ) {
      vertexArray.bind();
      instanceBuffer.bind(34962);
      boolean supported = true;

      for (VertexAttributeType vertexAttributeType : vertexAttributeTypes) {
         supported &= setupInstanceAttribute(mapping, vertexAttributeType);
      }

      VertexArray.unbind();
      return supported;
   }

   public static void setupInstanceAttributePointers(
      VertexBuffer instanceBuffer, VertexAttributeMapping mapping, int instanceOffsetBytes, VertexAttributeType... vertexAttributeTypes
   ) {
      instanceBuffer.bind(34962);

      for (VertexAttributeType vertexAttributeType : vertexAttributeTypes) {
         vertexAttributeType.setupAttributePointer(mapping.strideInstance, instanceOffsetBytes + mapping.pointers.get(vertexAttributeType));
      }
   }

   public static void uploadInstances(VertexBuffer instanceBuffer, ByteBuffer byteBuffer, int size) {
      instanceBuffer.bind(34962);
      instanceBuffer.upload(byteBuffer, size, 35040);
   }

   public static void drawElementsInstanced(VertexArray vertexArray, int instanceCount) {
      GL33.glDrawElementsInstanced(4, vertexArray.indexBuffer.getVertexCount(), vertexArray.indexBuffer.indexType, 0L, instanceCount);
   }

   private static boolean setupInstanceAttribute(VertexAttributeMapping mapping, VertexAttributeType vertexAttributeType) {
      vertexAttributeType.toggleAttributeArray(true);
      vertexAttributeType.setupAttributePointer(mapping.strideInstance, mapping.pointers.get(vertexAttributeType));
      return applyInstanceAttributeDivisor(vertexAttributeType);
   }

   private static boolean applyInstanceAttributeDivisor(VertexAttributeType vertexAttributeType) {
      boolean supported = true;

      for (int i = 0; i < vertexAttributeType.span; i++) {
         supported &= applyInstanceAttributeDivisor(vertexAttributeType.location + i);
      }

      return supported;
   }

   private static boolean applyInstanceAttributeDivisor(int location) {
      try {
         GLCapabilities capabilities = GL.getCapabilities();
         if (capabilities.OpenGL33) {
            GL33.glVertexAttribDivisor(location, 1);
            return true;
         }

         if (capabilities.GL_ARB_instanced_arrays) {
            ARBInstancedArrays.glVertexAttribDivisorARB(location, 1);
            return true;
         }
      } catch (IllegalStateException var2) {
      }

      return false;
   }

   private InstancedDrawHelper() {
   }
}
