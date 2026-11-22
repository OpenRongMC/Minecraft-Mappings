package org.mtr.mapping.holder;

import net.minecraft.client.gui.Font;


import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class TextFieldWidgetAbstractMapping extends EditBox {

   @MappedMethod
   public void setMaxLength2(int i) {
      super.setMaxLength(i);
   }

   @Deprecated
   public final void setMaxLength(int i) {
      this.setMaxLength2(i);
   }

   @MappedMethod
   public void setFocused2(boolean bl) {
      super.setFocused(bl);
   }

   @Deprecated
   public final void setFocused(boolean bl) {
      this.setFocused2(bl);
   }

   @MappedMethod
   public boolean keyPressed2(KeyEvent keyEvent) {
      return super.keyPressed(keyEvent);
   }

   @Deprecated
   public final boolean keyPressed(KeyEvent keyEvent) {
      return this.keyPressed2(keyEvent);
   }

   @MappedMethod
   public void setVisible2(boolean bl) {
      super.setVisible(bl);
   }

   @Deprecated
   public final void setVisible(boolean bl) {
      this.setVisible2(bl);
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
   public boolean isVisible2() {
      return super.isVisible();
   }

   @Deprecated
   public final boolean isVisible() {
      return this.isVisible2();
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
   public int getWidth2() {
      return super.getWidth();
   }

   @Deprecated
   public final int getWidth() {
      return this.getWidth2();
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
   public void setValue2(String string) {
      super.setValue(string);
   }

   @Deprecated
   public final void setValue(String string) {
      this.setValue2(string);
   }

   @MappedMethod
   public void setEditable2(boolean bl) {
      super.setEditable(bl);
   }

   @Deprecated
   public final void setEditable(boolean bl) {
      this.setEditable2(bl);
   }

   @Nonnull
   @MappedMethod
   public String getValue2() {
      return super.getValue();
   }

   @Deprecated
   public final String getValue() {
      return this.getValue2();
   }

   @MappedMethod
   public void setSuggestion2(String string) {
      super.setSuggestion(string);
   }

   @Deprecated
   public final void setSuggestion(String string) {
      this.setSuggestion2(string);
   }

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
   public boolean charTyped2(CharacterEvent characterEvent) {
      return super.charTyped(characterEvent);
   }

   @Deprecated
   public final boolean charTyped(CharacterEvent characterEvent) {
      return this.charTyped2(characterEvent);
   }

   @MappedMethod
   public void setResponder2(Consumer<String> consumer) {
      super.setResponder(consumer);
   }

   @Deprecated
   public final void setResponder(Consumer<String> consumer) {
      this.setResponder2(consumer);
   }

   @MappedMethod
   public void renderWidget2(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      super.extractWidgetRenderState(guiGraphics, i, j, f);
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
   public void setX2(int i) {
      super.setX(i);
   }

   @Deprecated
   public final void setX(int i) {
      this.setX2(i);
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
   public void setY2(int i) {
      super.setY(i);
   }

   @Deprecated
   public final void setY(int i) {
      this.setY2(i);
   }

   @MappedMethod
   public boolean mouseClicked2(MouseButtonEvent mouseButtonEvent, boolean bl) {
      return super.mouseClicked(mouseButtonEvent, bl);
   }

   @Deprecated
   public final boolean mouseClicked(MouseButtonEvent mouseButtonEvent, boolean bl) {
      return this.mouseClicked2(mouseButtonEvent, bl);
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
   public boolean isFocused2() {
      return super.isFocused();
   }

   @Deprecated
   public final boolean isFocused() {
      return this.isFocused2();
   }

   @MappedMethod
   public int getCursorPosition2() {
      return super.getCursorPosition();
   }

   @Deprecated
   public final int getCursorPosition() {
      return this.getCursorPosition2();
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
   public void setText2(String text) {
      this.setValue2(text);
   }

   @Nonnull
   @MappedMethod
   public String getText2() {
      return this.getValue2();
   }



@MappedMethod
   public TextFieldWidgetAbstractMapping(TextRenderer font, int i, int j, int k, int l, Text component) {
      super((Font)font.data, i, j, k, l, (Component)component.data);
   }



@Deprecated
   public final void extractWidgetRenderState(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      this.renderWidget2(guiGraphics, i, j, f);
   }
}
