package org.mtr.mapping.render;

import javax.annotation.Nullable;
import org.joml.Matrix4f;

public final class CapturedRenderMatrices {
   private static final Matrix4f VIEW = new Matrix4f();
   private static final Matrix4f PROJECTION = new Matrix4f();
   private static boolean valid;

   private CapturedRenderMatrices() {
   }

   public static void capture(Matrix4f viewMatrix, Matrix4f projectionMatrix) {
      VIEW.set(viewMatrix);
      PROJECTION.set(projectionMatrix);
      valid = true;
      CapturedProjection.capture(projectionMatrix);
   }

   public static boolean isValid() {
      return valid;
   }

   @Nullable
   public static Matrix4f copyViewOrNull() {
      return valid ? new Matrix4f(VIEW) : null;
   }

   @Nullable
   public static Matrix4f copyProjectionOrNull() {
      return valid ? new Matrix4f(PROJECTION) : null;
   }
}
