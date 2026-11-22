package org.mtr.mapping.mapper;

import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.RegEx;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.Text;
import org.mtr.mapping.holder.TextFieldWidgetAbstractMapping;
import org.mtr.mapping.tool.TextCase;

public class TextFieldWidgetExtension extends TextFieldWidgetAbstractMapping {
   private final int maxLength;
   private final TextCase textCase;
   private final String filter;
   private final String suggestion;

   @MappedMethod
   public TextFieldWidgetExtension(
      int x, int y, int width, int height, int maxLength, TextCase textCase, @RegEx @Nullable String filter, @Nullable String suggestion
   ) {
      this(x, y, width, height, "", maxLength, textCase, filter, suggestion);
   }

   @MappedMethod
   public TextFieldWidgetExtension(
      int x, int y, int width, int height, String message, int maxLength, TextCase textCase, @RegEx @Nullable String filter, @Nullable String suggestion
   ) {
      this(x, y, width, height, TextHelper.literal(message), maxLength, textCase, filter, suggestion);
   }

   @MappedMethod
   public TextFieldWidgetExtension(
      int x, int y, int width, int height, MutableText text, int maxLength, TextCase textCase, @RegEx @Nullable String filter, @Nullable String suggestion
   ) {
      super(MinecraftClient.getInstance().getFontMapped(), x, y, width, height, new Text((Component)text.data));
      this.maxLength = maxLength;
      this.textCase = textCase;
      this.filter = filter;
      this.suggestion = suggestion;
      this.setResponder2(value -> {});
      this.setMaxLength2(0);
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
      if (graphicsHolder.drawContext != null) {
         super.renderWidget2(graphicsHolder.drawContext, mouseX, mouseY, delta);
         final TransitUi.Palette palette = TransitUi.current();
         if (palette != null) {
            TransitUi.rectangle(graphicsHolder, getX(), getY() + getHeight() - 1, getWidth(), 1,
                  isFocused() ? palette.accent : TransitUi.BORDER);
         }
      }
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
   public final void setChangedListener2(Consumer<String> changedListener) {
      super.setResponder2(text -> {
         String newText;
         if (this.filter != null && !this.filter.isEmpty()) {
            newText = this.trySetLength(((String)this.textCase.convert.apply(text)).replaceAll(this.filter, ""));
            if (!newText.equals(text)) {
               this.setValue2(newText);
            }
         } else {
            newText = this.trySetLength((String)this.textCase.convert.apply(text));
         }

         this.setSuggestion2(newText.isEmpty() && this.suggestion != null ? this.suggestion : "");
         changedListener.accept(newText);
      });
   }

   @Deprecated
   @Override
   public boolean mouseClicked2(MouseButtonEvent event, boolean bl) {
      return this.mouseClicked2(event.x(), event.y(), LegacyInput.mouseToLegacy(event.button()));
   }

   @MappedMethod
   public boolean mouseClicked2(double mouseX, double mouseY, int button) {
      if (this.isVisible2() && isBetween(mouseX, this.getX2(), this.getX2() + this.width) && isBetween(mouseY, this.getY2(), this.getY2() + this.height)) {
         if (button == 1) {
            this.setValue2("");
         } else if (button != 0) {
            return false;
         }

         return super.mouseClicked2(new MouseButtonEvent(mouseX, mouseY, new MouseButtonInfo(InputConstants.MOUSE_BUTTON_LEFT, 0)), false);
      } else {
         this.setFocused2(false);
         return false;
      }
   }

   @MappedMethod
   public void tick2() {
   }

   @Deprecated
   @Override
   public final void setMaxLength2(int maxLength) {
      super.setMaxLength2(Integer.MAX_VALUE);
   }

   private String trySetLength(String text) {
      return text.isEmpty() ? "" : text.substring(0, Math.min(this.maxLength, text.length()));
   }

   private static boolean isBetween(double value, double min, double max) {
      return value >= min && value <= max;
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

   @MappedMethod
   @Override
   public void setText2(String text) {
      this.setValue2(text);
   }

   @MappedMethod
   @Override
   public String getText2() {
      return this.getValue2();
   }

   @MappedMethod
   public int getCursor2() {
      return this.getCursorPosition2();
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
