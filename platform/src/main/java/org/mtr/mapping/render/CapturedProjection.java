package org.mtr.mapping.render;

import javax.annotation.Nullable;
import org.joml.Matrix4f;

public final class CapturedProjection {
   private static final Matrix4f LAST = new Matrix4f();
   private static boolean valid;

   private CapturedProjection() {
   }

   public static void capture(Matrix4f matrix) {
      LAST.set(matrix);
      valid = true;
   }

   @Nullable
   public static Matrix4f copyOrNull() {
      return valid ? new Matrix4f(LAST) : null;
   }
}
