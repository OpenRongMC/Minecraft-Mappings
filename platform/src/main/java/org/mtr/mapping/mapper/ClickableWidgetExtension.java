package org.mtr.mapping.mapper;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ClickableWidgetAbstractMapping;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.Text;

public class ClickableWidgetExtension extends ClickableWidgetAbstractMapping {
   @MappedMethod
   public ClickableWidgetExtension(int x, int y, int width, int height) {
      this(x, y, width, height, "");
   }

   @MappedMethod
   public ClickableWidgetExtension(int x, int y, int width, int height, String message) {
      this(x, y, width, height, TextHelper.literal(message));
   }

   @MappedMethod
   public ClickableWidgetExtension(int x, int y, int width, int height, MutableText message) {
      super(x, y, width, height, new Text((Component)message.data));
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
   }

   @Deprecated
   @Override
   public final void renderWidget2(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
      GraphicsHolder.createInstanceSafe(context, graphicsHolder -> this.render(graphicsHolder, mouseX, mouseY, delta));
   }

   @Deprecated
   @Override
   public final boolean mouseScrolled2(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      return this.mouseScrolled2(mouseX, mouseY, verticalAmount);
   }

   @MappedMethod
   public boolean mouseScrolled2(double mouseX, double mouseY, double amount) {
      return super.mouseScrolled2(mouseX, mouseY, 0.0, amount);
   }

   @MappedMethod
   @Override
   public final int getX2() {
      return super.getX2();
   }

   @MappedMethod
   @Override
   public final int getY2() {
      return super.getY2();
   }

   @MappedMethod
   @Override
   public final void setX2(int x) {
      super.setX2(x);
   }

   @MappedMethod
   @Override
   public final void setY2(int y) {
      super.setY2(y);
   }

   @MappedMethod
   @Override
   public final boolean isHovered2() {
      return super.isHovered2();
   }

   @Deprecated
   @Override
   protected void updateWidgetNarration2(NarrationElementOutput builder) {
   }

   @Deprecated
   @Override
   public boolean mouseClicked2(MouseButtonEvent event, boolean bl) {
      return this.mouseClicked2(event.x(), event.y(), LegacyInput.mouseToLegacy(event.button()));
   }

   @MappedMethod
   public boolean mouseClicked2(double mouseX, double mouseY, int button) {
      return super.mouseClicked2(new MouseButtonEvent(mouseX, mouseY, new MouseButtonInfo(LegacyInput.mouseToNative(button), 0)), false);
   }

   @Deprecated
   @Override
   public boolean mouseReleased2(MouseButtonEvent event) {
      return this.mouseReleased2(event.x(), event.y(), LegacyInput.mouseToLegacy(event.button()));
   }

   @MappedMethod
   public boolean mouseReleased2(double mouseX, double mouseY, int button) {
      return super.mouseReleased2(new MouseButtonEvent(mouseX, mouseY, new MouseButtonInfo(LegacyInput.mouseToNative(button), 0)));
   }

   @Deprecated
   @Override
   public boolean mouseDragged2(MouseButtonEvent event, double deltaX, double deltaY) {
      return this.mouseDragged2(event.x(), event.y(), LegacyInput.mouseToLegacy(event.button()), deltaX, deltaY);
   }

   @MappedMethod
   public boolean mouseDragged2(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      return super.mouseDragged2(new MouseButtonEvent(mouseX, mouseY, new MouseButtonInfo(LegacyInput.mouseToNative(button), 0)), deltaX, deltaY);
   }

   @Deprecated
   @Override
   public boolean keyPressed2(KeyEvent event) {
      return this.keyPressed2(LegacyInput.keyToLegacy(event.key()), event.key(), LegacyInput.modifiersToLegacy(event.modifiers()));
   }

   @MappedMethod
   public boolean keyPressed2(int keyCode, int scanCode, int modifiers) {
      return super.keyPressed2(new KeyEvent(LegacyInput.keyToNative(keyCode), keyCode, LegacyInput.modifiersToNative(modifiers)));
   }

   @Deprecated
   @Override
   public boolean charTyped2(CharacterEvent event) {
      return this.charTyped2((char)event.codepoint(), 0);
   }

   @MappedMethod
   public boolean charTyped2(char chr, int modifiers) {
      return super.charTyped2(new CharacterEvent(chr));
   }
}
