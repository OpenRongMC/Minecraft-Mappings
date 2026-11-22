package org.mtr.mapping.mapper;

import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.PressAction;
import org.mtr.mapping.holder.Text;
import org.mtr.mapping.holder.TexturedButtonWidgetAbstractMapping;

public class TexturedButtonWidgetExtension extends TexturedButtonWidgetAbstractMapping {
   @MappedMethod
   public TexturedButtonWidgetExtension(
      int x, int y, int width, int height, Identifier normalTexture, Identifier highlightedTexture, Identifier disabledTexture, PressAction onPress
   ) {
      this(x, y, width, height, normalTexture, highlightedTexture, disabledTexture, onPress, "");
   }

   @MappedMethod
   public TexturedButtonWidgetExtension(
      int x,
      int y,
      int width,
      int height,
      Identifier normalTexture,
      Identifier highlightedTexture,
      Identifier disabledTexture,
      PressAction onPress,
      String message
   ) {
      this(x, y, width, height, normalTexture, highlightedTexture, disabledTexture, onPress, TextHelper.literal(message));
   }

   @MappedMethod
   public TexturedButtonWidgetExtension(
      int x,
      int y,
      int width,
      int height,
      Identifier normalTexture,
      Identifier highlightedTexture,
      Identifier disabledTexture,
      PressAction onPress,
      MutableText message
   ) {
      super(
         x,
         y,
         width,
         height,
         new WidgetSprites(formatIdentifier(normalTexture), formatIdentifier(disabledTexture), formatIdentifier(highlightedTexture)),
         onPress,
         new Text((Component)message.data)
      );
   }

   @MappedMethod
   public void render(GraphicsHolder graphicsHolder, int mouseX, int mouseY, float delta) {
      if (graphicsHolder.drawContext != null) {
         super.extractWidgetRenderState(graphicsHolder.drawContext, mouseX, mouseY, delta);
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

   private static net.minecraft.resources.Identifier formatIdentifier(Identifier identifier) {
      String beginning = "textures/gui/sprites/";
      String namespace = identifier.getNamespace();
      String path = identifier.getPath();
      return net.minecraft.resources.Identifier.fromNamespaceAndPath(
         namespace, (path.startsWith("textures/gui/sprites/") ? path.substring("textures/gui/sprites/".length()) : path).replace(".png", "")
      );
   }
}
