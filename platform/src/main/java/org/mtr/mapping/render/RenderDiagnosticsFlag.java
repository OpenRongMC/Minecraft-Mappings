package org.mtr.mapping.render;

public final class RenderDiagnosticsFlag {
   public static volatile boolean enabled = Boolean.getBoolean("mtr.renderDiagnostics");
   public static volatile float lastOptionsFov = Float.NaN;
   public static volatile float lastDynamicFov = Float.NaN;
   public static volatile long lastShaderLogMillis;

   private RenderDiagnosticsFlag() {
   }

   public static void setEnabled(boolean value) {
      enabled = value;
   }

   public static boolean isEnabled() {
      return enabled;
   }
}
