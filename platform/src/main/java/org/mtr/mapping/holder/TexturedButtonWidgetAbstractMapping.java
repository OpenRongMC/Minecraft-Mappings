package org.mtr.mapping.holder;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.Button.OnPress;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class TexturedButtonWidgetAbstractMapping extends ImageButton {

   @MappedMethod
   public int getX2() {
      return super.getX();
   }

   @Deprecated
   public final int getX() {
      return this.getX2();
   }

   @MappedMethod
   public int getY2() {
      return super.getY();
   }

   @Deprecated
   public final int getY() {
      return this.getY2();
   }

   @MappedMethod
   public void setWidth2(int i) {
      super.setWidth(i);
   }

   @Deprecated
   public final void setWidth(int i) {
      this.setWidth2(i);
   }

   @MappedMethod
   public int getHeight2() {
      return super.getHeight();
   }

   @Deprecated
   public final int getHeight() {
      return this.getHeight2();
   }

   @MappedMethod
   public void setSize2(int i, int j) {
      super.setSize(i, j);
   }

   @Deprecated
   public final void setSize(int i, int j) {
      this.setSize2(i, j);
   }

   @MappedMethod
   public void playDownSound2(SoundManager soundManager) {
      super.playDownSound((net.minecraft.client.sounds.SoundManager)soundManager.data);
   }

   @Deprecated
   public final void playDownSound(net.minecraft.client.sounds.SoundManager soundManager) {
      this.playDownSound2(new SoundManager(soundManager));
   }

   @MappedMethod
   public boolean mouseScrolled2(double d, double e, double f, double g) {
      return super.mouseScrolled(d, e, f, g);
   }

   @Deprecated
   public final boolean mouseScrolled(double d, double e, double f, double g) {
      return this.mouseScrolled2(d, e, f, g);
   }

   @MappedMethod
   public boolean isHovered2() {
      return super.isHovered();
   }

   @Deprecated
   public final boolean isHovered() {
      return this.isHovered2();
   }

   @MappedMethod
   public void setY2(int i) {
      super.setY(i);
   }

   @Deprecated
   public final void setY(int i) {
      this.setY2(i);
   }

   @MappedMethod
   public void setX2(int i) {
      super.setX(i);
   }

   @Deprecated
   public final void setX(int i) {
      this.setX2(i);
   }

@MappedMethod
   public TexturedButtonWidgetAbstractMapping(int i, int j, int k, int l, WidgetSprites widgetSprites, OnPress onPress, Text component) {
      super(i, j, k, l, widgetSprites, onPress, (Component)component.data);
   }




}
