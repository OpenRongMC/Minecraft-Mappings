package org.mtr.mapping.holder;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Window extends HolderBase<com.mojang.blaze3d.platform.Window> {
   public Window(com.mojang.blaze3d.platform.Window data) {
      super(data);
   }

   @MappedMethod
   public int getGuiScaledHeight() {
      return ((com.mojang.blaze3d.platform.Window)this.data).getGuiScaledHeight();
   }

   @MappedMethod
   public int getGuiScaledWidth() {
      return ((com.mojang.blaze3d.platform.Window)this.data).getGuiScaledWidth();
   }

   @MappedMethod
   public int getHeight() {
      return ((com.mojang.blaze3d.platform.Window)this.data).getHeight();
   }

   @MappedMethod
   public int getGuiScale() {
      return ((com.mojang.blaze3d.platform.Window)this.data).getGuiScale();
   }

   @MappedMethod
   public int getScaledWidth() {
      return this.getGuiScaledWidth();
   }

   @MappedMethod
   public int getScaledHeight() {
      return this.getGuiScaledHeight();
   }

   @MappedMethod
   public double getScaleFactor() {
      return this.getGuiScale();
   }

   @MappedMethod
   public int getFramebufferHeight() {
      return this.getHeight();
   }


}
