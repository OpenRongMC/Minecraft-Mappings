package org.mtr.mapping.holder;
import net.minecraft.client.gui.components.Button.CreateNarration;
import net.minecraft.client.gui.components.Button.OnPress;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class ButtonWidgetAbstractMapping extends Button {

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
   public int getWidth2() {
      return super.getWidth();
   }

   @Deprecated
   public final int getWidth() {
      return this.getWidth2();
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
   public void setMessage2(Text component) {
      super.setMessage((Component)component.data);
   }

   @Deprecated
   public final void setMessage(Component component) {
      this.setMessage2(new Text(component));
   }

   @MappedMethod
   public void setAlpha2(float f) {
      super.setAlpha(f);
   }

   @Deprecated
   public final void setAlpha(float f) {
      this.setAlpha2(f);
   }

   @MappedMethod
   public boolean isMouseOver2(double d, double e) {
      return super.isMouseOver(d, e);
   }

   @Deprecated
   public final boolean isMouseOver(double d, double e) {
      return this.isMouseOver2(d, e);
   }

   @MappedMethod
   public void onPress2(InputWithModifiers inputWithModifiers) {
      super.onPress(inputWithModifiers);
   }

   @Deprecated
   public final void onPress(InputWithModifiers inputWithModifiers) {
      this.onPress2(inputWithModifiers);
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
   public boolean getActiveMapped() {
      return this.active;
   }

   @MappedMethod
   public void setActiveMapped(boolean newData) {
      this.active = newData;
   }

   @MappedMethod
   public boolean getVisibleMapped() {
      return this.visible;
   }

   @MappedMethod
   public void setVisibleMapped(boolean newData) {
      this.visible = newData;
   }

@MappedMethod
   public ButtonWidgetAbstractMapping(int i, int j, int k, int l, Text component, OnPress onPress, CreateNarration createNarration) {
      super(i, j, k, l, (Component)component.data, onPress, createNarration);
   }
}
