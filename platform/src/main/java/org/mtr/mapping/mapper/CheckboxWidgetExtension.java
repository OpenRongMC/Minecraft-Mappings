package org.mtr.mapping.mapper;

import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.components.Checkbox.OnValueChange;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.CheckboxWidgetAbstractMapping;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.Text;
import org.mtr.mapping.holder.TextRenderer;

public class CheckboxWidgetExtension extends CheckboxWidgetAbstractMapping {
   private final Consumer<Boolean> onPress;

   @MappedMethod
   public CheckboxWidgetExtension(int x, int y, int width, int height, boolean showMessage, Consumer<Boolean> onPress) {
      this(x, y, width, height, "", showMessage, onPress);
   }

   @MappedMethod
   public CheckboxWidgetExtension(int x, int y, int width, int height, String message, boolean showMessage, Consumer<Boolean> onPress) {
      this(x, y, width, height, TextHelper.literal(message), showMessage, onPress);
   }

   @MappedMethod
   public CheckboxWidgetExtension(int x, int y, int width, int height, MutableText message, boolean showMessage, Consumer<Boolean> onPress) {
      super(
         x,
         y,
         width,
         new Text((Component)(showMessage ? (Component)message.data : Component.empty())),
         new TextRenderer(((Minecraft)MinecraftClient.getInstance().data).font),
         false,
         OnValueChange.NOP
      );
      this.height = height;
      this.onPress = onPress;
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
      if (graphicsHolder.drawContext != null) {
         super.extractContents(graphicsHolder.drawContext, mouseX, mouseY, delta);
      }
   }

   @Override
   public void renderContents2(net.minecraft.client.gui.GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
      final TransitUi.Palette palette = TransitUi.current();
      final int x = getX();
      final int y = getY();
      final var font = Minecraft.getInstance().font;
      final int size = getBoxSize2(new TextRenderer(font));
      final boolean custom = TransitUi.customControl(graphics, this, size, size);
      if (palette == null && !custom) {
         super.renderContents2(graphics, mouseX, mouseY, delta);
         return;
      }
      final int color = isChecked2() || isHoveredOrFocused()
            ? palette == null ? TransitUi.MTR_ACCENT : palette.accent : TransitUi.BORDER;
      if (!custom) {
         graphics.fill(x, y, x + size, y + size, color);
         graphics.fill(x + 1, y + 1, x + size - 1, y + size - 1, TransitUi.INSET);
      }
      if (isChecked2()) {
         graphics.fill(x + 3, y + 3, x + size - 3, y + size - 3, color);
      }
      if (textWidget != null) {
         textWidget.setPosition(x + size + 4, y + Math.max(0, (size - textWidget.getHeight()) / 2));
         textWidget.extractRenderState(graphics, mouseX, mouseY, delta);
      }
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

   @Deprecated
   @Override
   public final void onPress2(InputWithModifiers inputWithModifiers) {
      super.onPress2(inputWithModifiers);
      this.onPress.accept(this.isChecked2());
   }

   @MappedMethod
   public final boolean isChecked2() {
      return this.selected2();
   }

   @MappedMethod
   public final void setChecked(boolean checked) {
      if (checked != this.isChecked2()) {
         super.onPress2(new InputWithModifiers() {
            public int input() {
               return 0;
            }

            public int modifiers() {
               return 0;
            }
         });
      }
   }

   @MappedMethod
   @Override
   public void setMessage2(Text text) {
      super.setMessage2(text);
      this.syncTextWidget(this.getWidth2());
   }

   @MappedMethod
   @Override
   public void setWidth2(int width) {
      super.setWidth2(width);
      this.syncTextWidget(width);
   }

   private void syncTextWidget(int width) {
      MultiLineTextWidget textWidget = this.textWidget;
      if (textWidget != null) {
         textWidget.setMessage((Component)this.getMessage2().data);
         int boxSize = getBoxSize2(new TextRenderer(Minecraft.getInstance().font));
         textWidget.setMaxWidth(Math.max(boxSize, width - boxSize - 4));
      }
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
}
