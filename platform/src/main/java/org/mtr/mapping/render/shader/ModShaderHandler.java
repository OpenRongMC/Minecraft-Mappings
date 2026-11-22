package org.mtr.mapping.render.shader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.BooleanSupplier;

public final class ModShaderHandler {
   private static ModShaderHandler.InternalHandler internalHandler;
   private static final String IRIS_PREFIX = "net.irisshaders";
   private static final String IRIS_CLASS = "net.irisshaders.iris.api.v0.IrisApi";
   private static final String OPTIFINE_PREFIX = "net.optifine";
   private static final String OPTIFINE_CLASS = "net.optifine.shaders.Shaders";

   public static ModShaderHandler.InternalHandler getInternalHandler() {
      if (internalHandler == null) {
         internalHandler = new ModShaderHandler.InternalHandler();

         try {
            Class<?> ignored = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            internalHandler = new ModShaderHandler.Iris();
         } catch (Exception var2) {
         }

         try {
            Class<?> ignored = Class.forName("net.optifine.shaders.Shaders");
            internalHandler = new ModShaderHandler.Optifine();
         } catch (Exception var1) {
         }
      }

      return internalHandler;
   }

   public static boolean renderingShadows() {
      return getInternalHandler().renderingShadows();
   }

   public static class InternalHandler {
      public boolean noShaderPackInUse() {
         return true;
      }

      public boolean renderingShadows() {
         return false;
      }
   }

   private static class Iris extends ModShaderHandler.InternalHandler {
      private final BooleanSupplier shadersEnabledSupplier = createShadersEnabledSupplier();
      private final BooleanSupplier shadowPassSupplier = createShadowPassSupplier();

      @Override
      public boolean noShaderPackInUse() {
         return !this.shadersEnabledSupplier.getAsBoolean();
      }

      @Override
      public boolean renderingShadows() {
         return this.shadowPassSupplier.getAsBoolean();
      }

      private static BooleanSupplier createShadersEnabledSupplier() {
         try {
            Class<?> irisApiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            Object irisApiInstance = irisApiClass.getMethod("getInstance").invoke(null);
            Method fnIsShaderPackInUse = irisApiClass.getMethod("isShaderPackInUse");
            return () -> {
               try {
                  return (Boolean)fnIsShaderPackInUse.invoke(irisApiInstance);
               } catch (Exception var3x) {
                  return false;
               }
            };
         } catch (Exception var3) {
            return () -> false;
         }
      }

      private static BooleanSupplier createShadowPassSupplier() {
         try {
            Class<?> irisApiClass = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            Object irisApiInstance = irisApiClass.getMethod("getInstance").invoke(null);
            Method fnIsRenderingShadowPass = irisApiClass.getMethod("isRenderingShadowPass");
            return () -> {
               try {
                  return (Boolean)fnIsRenderingShadowPass.invoke(irisApiInstance);
               } catch (Exception var3x) {
                  return false;
               }
            };
         } catch (Exception var3) {
            return () -> false;
         }
      }
   }

   private static class Optifine extends ModShaderHandler.InternalHandler {
      private final BooleanSupplier shadersEnabledSupplier = createShadersEnabledSupplier();

      @Override
      public boolean noShaderPackInUse() {
         return !this.shadersEnabledSupplier.getAsBoolean();
      }

      @Override
      public boolean renderingShadows() {
         for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className.startsWith("net.optifine") && className.toLowerCase().contains("shadow")) {
               return true;
            }
         }

         return false;
      }

      private static BooleanSupplier createShadersEnabledSupplier() {
         try {
            Class<?> ofShaders = Class.forName("net.optifine.shaders.Shaders");
            Field field = ofShaders.getDeclaredField("activeProgramID");
            return () -> {
               try {
                  return (Integer)field.get(null) != 0;
               } catch (IllegalAccessException var2x) {
                  return false;
               }
            };
         } catch (Exception var2) {
            return () -> false;
         }
      }
   }
}
