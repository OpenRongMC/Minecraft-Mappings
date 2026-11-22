package org.mtr.mapping.holder;



import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class SliderWidgetAbstractMapping extends AbstractSliderButton {

   @MappedMethod
   protected abstract void applyValue2();

   @Deprecated
   protected final void applyValue() {
      this.applyValue2();
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
   protected void onDrag2(MouseButtonEvent mouseButtonEvent, double d, double e) {
      super.onDrag(mouseButtonEvent, d, e);
   }

   @Deprecated
   protected final void onDrag(MouseButtonEvent mouseButtonEvent, double d, double e) {
      this.onDrag2(mouseButtonEvent, d, e);
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
   protected abstract void updateMessage2();

   @Deprecated
   protected final void updateMessage() {
      this.updateMessage2();
   }

   @MappedMethod
   public void setHeight2(int i) {
      super.setHeight(i);
   }

   @Deprecated
   public final void setHeight(int i) {
      this.setHeight2(i);
   }

   @MappedMethod
   public void onClick2(MouseButtonEvent mouseButtonEvent, boolean bl) {
      super.onClick(mouseButtonEvent, bl);
   }

   @Deprecated
   public final void onClick(MouseButtonEvent mouseButtonEvent, boolean bl) {
      this.onClick2(mouseButtonEvent, bl);
   }

   @MappedMethod
   public void renderWidget2(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      super.extractWidgetRenderState(guiGraphics, i, j, f);
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
   public SliderWidgetAbstractMapping(int i, int j, int k, int l, Text component, double d) {
      super(i, j, k, l, (Component)component.data, d);
   }

@Deprecated
   public final void extractWidgetRenderState(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      this.renderWidget2(guiGraphics, i, j, f);
   }
}
