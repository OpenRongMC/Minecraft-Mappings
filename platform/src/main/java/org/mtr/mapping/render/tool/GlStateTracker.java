package org.mtr.mapping.render.tool;

import java.util.Locale;
import org.lwjgl.opengl.GL33;
import org.mtr.mapping.tool.DummyClass;

public final class GlStateTracker {
   private static boolean supportVertexAttributeDivisor;
   private static boolean isGl4ES = false;
   private static boolean isStateProtected;
   private static int vertArrayBinding;
   private static int arrayBufBinding;
   private static int elementBufBinding;
   private static int activeTexture;
   private static int textureBinding;
   private static int currentProgram;
   private static boolean blendEnabled;
   private static boolean depthTestEnabled;
   private static boolean cullEnabled;
   private static boolean depthMask;
   private static int depthFunc;
   private static int blendSrcRgb;
   private static int blendDstRgb;
   private static int blendSrcAlpha;
   private static int blendDstAlpha;

   public static void capture() {
      int contextVersion = GL33.glGetInteger(33307) * 10 + GL33.glGetInteger(33308);
      supportVertexAttributeDivisor = contextVersion >= 33;
      String glVersion = GL33.glGetString(7938);
      isGl4ES = glVersion != null && glVersion.toLowerCase(Locale.ENGLISH).contains("gl4es");
      if (!isStateProtected) {
         vertArrayBinding = GL33.glGetInteger(34229);
         arrayBufBinding = GL33.glGetInteger(34964);
         elementBufBinding = GL33.glGetInteger(34965);
         activeTexture = GL33.glGetInteger(34016);
         textureBinding = GL33.glGetInteger(32873);
         currentProgram = GL33.glGetInteger(35725);
         blendEnabled = GL33.glIsEnabled(3042);
         depthTestEnabled = GL33.glIsEnabled(2929);
         cullEnabled = GL33.glIsEnabled(2884);
         depthMask = GL33.glGetBoolean(2930);
         depthFunc = GL33.glGetInteger(2932);
         blendSrcRgb = GL33.glGetInteger(32969);
         blendDstRgb = GL33.glGetInteger(32968);
         blendSrcAlpha = GL33.glGetInteger(32971);
         blendDstAlpha = GL33.glGetInteger(32970);
         isStateProtected = true;
      }
   }

   public static void restore() {
      if (!isStateProtected) {
         IllegalStateException e = new IllegalStateException("GlStateTracker: Not captured");
         DummyClass.logException(e);
         throw e;
      } else {
         GL33.glBindVertexArray(vertArrayBinding);
         GL33.glBindBuffer(34962, arrayBufBinding);
         GL33.glBindBuffer(34963, elementBufBinding);
         GL33.glUseProgram(currentProgram);
         GL33.glActiveTexture(activeTexture);
         GL33.glBindTexture(3553, textureBinding);
         setEnabled(3042, blendEnabled);
         setEnabled(2929, depthTestEnabled);
         setEnabled(2884, cullEnabled);
         GL33.glDepthMask(depthMask);
         GL33.glDepthFunc(depthFunc);
         GL33.glBlendFuncSeparate(blendSrcRgb, blendDstRgb, blendSrcAlpha, blendDstAlpha);
         isStateProtected = false;
      }
   }

   private static void setEnabled(int cap, boolean enabled) {
      if (enabled) {
         GL33.glEnable(cap);
      } else {
         GL33.glDisable(cap);
      }
   }

   public static void assertProtected() {
      if (!isStateProtected) {
         IllegalStateException e = new IllegalStateException("GlStateTracker: Not protected");
         DummyClass.logException(e);
         throw e;
      }
   }

   public static boolean supportVertexAttributeDivisor() {
      return supportVertexAttributeDivisor;
   }

   public static boolean isGl4ES() {
      return isGl4ES;
   }
}
