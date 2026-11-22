package org.mtr.mapping.mapper;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.SliderWidgetAbstractMapping;
import org.mtr.mapping.holder.Text;

public abstract class SliderWidgetExtension extends SliderWidgetAbstractMapping {
   @MappedMethod
   public SliderWidgetExtension(int x, int y, int width, int height) {
      this(x, y, width, height, "");
   }

   @MappedMethod
   public SliderWidgetExtension(int x, int y, int width, int height, String message) {
      this(x, y, width, height, TextHelper.literal(message));
   }

   @MappedMethod
   public SliderWidgetExtension(int x, int y, int width, int height, MutableText message) {
      super(x, y, width, height, new Text((Component)message.data), 0.0);
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
      if (graphicsHolder.drawContext != null) {
         final TransitUi.Palette palette = TransitUi.current();
         final boolean custom = TransitUi.customControl(graphicsHolder, this, getWidth(), getHeight());
         if (palette == null && !custom) {
            super.renderWidget2(graphicsHolder.drawContext, mouseX, mouseY, delta);
         } else {
            if (!custom) TransitUi.control(graphicsHolder.drawContext, this, palette);
            final int travel = Math.max(0, getWidth() - 10);
            final int handle = getX() + 5 + (int) Math.round(travel * value);
            TransitUi.rectangle(graphicsHolder, getX() + 4, getY() + getHeight() - 5, travel + 2, 1, TransitUi.BORDER);
            TransitUi.rectangle(graphicsHolder, getX() + 4, getY() + getHeight() - 5, handle - getX() - 3, 1, palette == null ? TransitUi.MTR_ACCENT : palette.accent);
            TransitUi.rectangle(graphicsHolder, handle, getY() + getHeight() - 6, 2, 3, palette == null ? TransitUi.MTR_ACCENT : palette.accent);
            graphicsHolder.drawContext.centeredText(net.minecraft.client.Minecraft.getInstance().font, getMessage(),
                  getX() + getWidth() / 2, getY() + Math.max(0, (getHeight() - 10) / 2), active ? TransitUi.TEXT : TransitUi.MUTED_TEXT);
         }
      }
   }

   @Deprecated
   @Override
   public final void renderWidget2(GuiGraphicsExtractor drawContext, int mouseX, int mouseY, float delta) {
      GraphicsHolder.createInstanceSafe(drawContext, graphicsHolder -> this.render(graphicsHolder, mouseX, mouseY, delta));
   }

   @Deprecated
   @Override
   public final boolean mouseScrolled2(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
      return this.mouseScrolled3(mouseX, mouseY, verticalAmount);
   }

   @MappedMethod
   public boolean mouseScrolled3(double mouseX, double mouseY, double amount) {
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
   public void onClick2(MouseButtonEvent event, boolean bl) {
      this.onClick2(event.x(), event.y());
   }

   @MappedMethod
   protected void onClick2(double mouseX, double mouseY) {
      super.onClick2(new MouseButtonEvent(mouseX, mouseY, new MouseButtonInfo(InputConstants.MOUSE_BUTTON_LEFT, 0)), false);
   }

   @Deprecated
   @Override
   public void onDrag2(MouseButtonEvent event, double deltaX, double deltaY) {
      this.onDrag2(event.x(), event.y(), deltaX, deltaY);
   }

   @MappedMethod
   protected void onDrag2(double mouseX, double mouseY, double deltaX, double deltaY) {
      super.onDrag2(new MouseButtonEvent(mouseX, mouseY, new MouseButtonInfo(InputConstants.MOUSE_BUTTON_LEFT, 0)), deltaX, deltaY);
   }
}
