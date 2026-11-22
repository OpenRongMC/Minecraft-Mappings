package org.mtr.mapping.mapper;

import java.util.function.Supplier;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.GuiGraphicsExtractor.HoveredTextEffects;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ButtonWidgetAbstractMapping;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.PressAction;
import org.mtr.mapping.holder.Text;

public class ButtonWidgetExtension extends ButtonWidgetAbstractMapping {
   @MappedMethod
   public ButtonWidgetExtension(int x, int y, int width, int height, PressAction onPress) {
      this(x, y, width, height, "", onPress);
   }

   @MappedMethod
   public ButtonWidgetExtension(int x, int y, int width, int height, String message, PressAction onPress) {
      this(x, y, width, height, TextHelper.literal(message), onPress);
   }

   @MappedMethod
   public ButtonWidgetExtension(int x, int y, int width, int height, MutableText message, PressAction onPress) {
      super(x, y, width, height, new Text((Component)message.data), onPress, Supplier::get);
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
      if (graphicsHolder.drawContext != null) {
         final TransitUi.Palette palette = TransitUi.current();
         if (!TransitUi.customControl(graphicsHolder, this, getWidth(), getHeight())) {
            if (palette == null) {
               this.extractDefaultSprite(graphicsHolder.drawContext);
            } else {
               TransitUi.control(graphicsHolder.drawContext, this, palette);
            }
         }
         this.extractDefaultLabel(graphicsHolder.drawContext.textRendererForWidget(this, HoveredTextEffects.NONE));
      }
   }

   @Override
   protected void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float delta) {
      GraphicsHolder.createInstanceSafe(guiGraphics, graphicsHolder -> this.render(graphicsHolder, mouseX, mouseY, delta));
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
   public final void onPress2(InputWithModifiers inputWithModifiers) {
      this.onPress2();
   }

   @MappedMethod
   public void onPress2() {
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
