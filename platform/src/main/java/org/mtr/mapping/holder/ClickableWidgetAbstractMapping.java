package org.mtr.mapping.holder;



import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class ClickableWidgetAbstractMapping extends AbstractWidget {

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
   public boolean mouseReleased2(MouseButtonEvent mouseButtonEvent) {
      return super.mouseReleased(mouseButtonEvent);
   }

   @Deprecated
   public final boolean mouseReleased(MouseButtonEvent mouseButtonEvent) {
      return this.mouseReleased2(mouseButtonEvent);
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
   public void setFocused2(boolean bl) {
      super.setFocused(bl);
   }

   @Deprecated
   public final void setFocused(boolean bl) {
      this.setFocused2(bl);
   }

   @MappedMethod
   protected abstract void updateWidgetNarration2(NarrationElementOutput var1);

   @Deprecated
   protected final void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
      this.updateWidgetNarration2(narrationElementOutput);
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
   public boolean keyPressed2(KeyEvent keyEvent) {
      return super.keyPressed(keyEvent);
   }

   @Deprecated
   public final boolean keyPressed(KeyEvent keyEvent) {
      return this.keyPressed2(keyEvent);
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
   public boolean isMouseOver2(double d, double e) {
      return super.isMouseOver(d, e);
   }

   @Deprecated
   public final boolean isMouseOver(double d, double e) {
      return this.isMouseOver2(d, e);
   }

   @MappedMethod
   protected abstract void renderWidget2(GuiGraphicsExtractor var1, int var2, int var3, float var4);

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
   public boolean mouseDragged2(MouseButtonEvent mouseButtonEvent, double d, double e) {
      return super.mouseDragged(mouseButtonEvent, d, e);
   }

   @Deprecated
   public final boolean mouseDragged(MouseButtonEvent mouseButtonEvent, double d, double e) {
      return this.mouseDragged2(mouseButtonEvent, d, e);
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
   public void setHeightMapped(int newData) {
      this.height = newData;
   }

   @MappedMethod
   public void setVisibleMapped(boolean newData) {
      this.visible = newData;
   }

@MappedMethod
   public ClickableWidgetAbstractMapping(int i, int j, int k, int l, Text component) {
      super(i, j, k, l, (Component)component.data);
   }

@Deprecated
   protected final void extractWidgetRenderState(GuiGraphicsExtractor guiGraphics, int i, int j, float f) {
      this.renderWidget2(guiGraphics, i, j, f);
   }
}
