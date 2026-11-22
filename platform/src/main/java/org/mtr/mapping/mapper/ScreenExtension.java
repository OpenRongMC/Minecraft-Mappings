package org.mtr.mapping.mapper;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.ScreenAbstractMapping;
import org.mtr.mapping.holder.Text;

public class ScreenExtension extends ScreenAbstractMapping {
   @MappedMethod
   protected ScreenExtension() {
      this("");
   }

   @MappedMethod
   protected ScreenExtension(String title) {
      this(TextHelper.literal(title));
   }

   @MappedMethod
   protected ScreenExtension(MutableText title) {
      super(new Text((Component)title.data));
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
      if (graphicsHolder.drawContext != null) {
         super.render2(graphicsHolder.drawContext, mouseX, mouseY, delta);
      }
   }

   @Deprecated
   @Override
   public void render2(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
      GraphicsHolder.createInstanceSafe(context, graphicsHolder -> this.render(graphicsHolder, mouseX, mouseY, delta));
   }

   @MappedMethod
   public final void renderBackground(GraphicsHolder graphicsHolder) {
      if (graphicsHolder.drawContext != null && !TransitUi.backdrop(this, graphicsHolder.drawContext, width, height)) {
         final TransitUi.Palette palette = TransitUi.palette(this);
         if (palette == null) {
            super.renderBackground2(graphicsHolder.drawContext, 0, 0, 0.0F);
         } else {
            TransitUi.background(graphicsHolder.drawContext, width, height, palette);
         }
      }
   }

   @Deprecated
   @Override
   public void renderBackground2(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
   }

   @Override
   public void removed2() {
      TransitUi.close(this);
      super.removed2();
   }

   @MappedMethod
   public final void addChild(ClickableWidget child) {
      this.addRenderableWidget2((AbstractWidget)child.data);
   }

   @MappedMethod
   public final void addSelectableChild(ClickableWidget child) {
      this.addWidget2((AbstractWidget)child.data);
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

   @MappedMethod
   public void resize2(MinecraftClient client, int width, int height) {
      super.resize2(width, height);
   }

   @Deprecated
   @Override
   public final void resize2(int width, int height) {
      this.resize2(MinecraftClient.getInstance(), width, height);
   }
}
