package org.mtr.mapping.holder;
import net.minecraft.client.gui.components.Checkbox.OnValueChange;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class CheckboxWidgetAbstractMapping extends Checkbox {

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
   public static int getBoxSize2(TextRenderer font) {
      return Checkbox.getBoxSize((Font)font.data);
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
   public boolean selected2() {
      return super.selected();
   }

   @Deprecated
   public final boolean selected() {
      return this.selected2();
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
   public void onPress2(InputWithModifiers inputWithModifiers) {
      super.onPress(inputWithModifiers);
   }

   @Deprecated
   public final void onPress(InputWithModifiers inputWithModifiers) {
      this.onPress2(inputWithModifiers);
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

   @Nonnull
   @MappedMethod
   public Text getMessage2() {
      return new Text(super.getMessage());
   }

   @Deprecated
   public final Component getMessage() {
      Text tempData = this.getMessage2();
      return tempData == null ? null : (Component)tempData.data;
   }

   @MappedMethod
   public void renderContents2(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      super.extractContents(guiGraphics, i, j, f);
   }

   @Deprecated
   public final void extractContents(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      this.renderContents2(guiGraphics, i, j, f);
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
   public void setVisibleMapped(boolean newData) {
      this.visible = newData;
   }

@MappedMethod
   public CheckboxWidgetAbstractMapping(int i, int j, int k, Text component, TextRenderer font, boolean bl, OnValueChange onValueChange) {
      super(i, j, k, (Component)component.data, (Font)font.data, bl, onValueChange);
   }
}
