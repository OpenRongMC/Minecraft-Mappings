package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack.Pose;
import com.mojang.math.Axis;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.renderer.SubmitNodeCollector;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Entity;
import org.mtr.mapping.holder.Matrix4f;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.MutableText;
import org.mtr.mapping.holder.OrderedText;
import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.holder.Vector3i;
import org.mtr.mapping.tool.ColorHelper;
import org.mtr.mapping.tool.DummyClass;

public final class GraphicsHolder extends DummyClass {
   VertexConsumer vertexConsumer;
   private int matrixPushes;
   private final Map<RenderType, DeferredVertexBatch> batches = new LinkedHashMap<>();
   final PoseStack matrixStack;
   final SubmitNodeCollector vertexConsumerProvider;
   final GuiGraphicsExtractor drawContext;

   @MappedMethod
   public static int getDefaultLight() {
      return 15728880;
   }

   @Deprecated
   public static void createInstanceSafe(@Nullable PoseStack matrixStack, @Nullable SubmitNodeCollector vertexConsumerProvider, Consumer<GraphicsHolder> consumer) {
      createInstanceSafe(new GraphicsHolder(matrixStack, vertexConsumerProvider), consumer);
   }

   @Deprecated
   public static void createInstanceSafe(GuiGraphicsExtractor drawContext, Consumer<GraphicsHolder> consumer) {
      createInstanceSafe(new GraphicsHolder(drawContext), consumer);
   }

   private static void createInstanceSafe(GraphicsHolder graphicsHolder, Consumer<GraphicsHolder> consumer) {
      try {
         consumer.accept(graphicsHolder);
      } catch (Exception var3) {
         logException(var3);
      }

      graphicsHolder.flush();
      while (graphicsHolder.matrixPushes > 0) {
         graphicsHolder.pop();
      }
   }

   private GraphicsHolder(@Nullable PoseStack matrixStack, @Nullable SubmitNodeCollector vertexConsumerProvider) {
      this.matrixStack = matrixStack;
      this.vertexConsumerProvider = vertexConsumerProvider;
      this.drawContext = null;
      this.push();
   }

   private GraphicsHolder(GuiGraphicsExtractor drawContext) {
      this.matrixStack = null;
      this.vertexConsumerProvider = null;
      this.drawContext = drawContext;
   }

   @MappedMethod
   public void push() {
      if (this.matrixStack != null) {
         this.matrixStack.pushPose();
         this.matrixPushes++;
      } else if (this.drawContext != null) {
         this.drawContext.pose().pushMatrix();
         this.matrixPushes++;
      }
   }

   @MappedMethod
   public void pop() {
      if (this.matrixPushes > 0) {
         if (this.matrixStack != null) {
            this.matrixStack.popPose();
            this.matrixPushes--;
         } else if (this.drawContext != null) {
            this.drawContext.pose().popMatrix();
            this.matrixPushes--;
         }
      }
   }

   @MappedMethod
   public void translate(double x, double y, double z) {
      if (this.matrixStack != null) {
         this.matrixStack.translate(x, y, z);
      } else if (this.drawContext != null) {
         this.drawContext.pose().translate((float)x, (float)y);
      }
   }

   @MappedMethod
   public void scale(float x, float y, float z) {
      if (this.matrixStack != null) {
         this.matrixStack.scale(x, y, z);
      } else if (this.drawContext != null) {
         this.drawContext.pose().scale(x, y);
      }
   }

   @MappedMethod
   public void rotateXRadians(float angle) {
      if (this.matrixStack != null) {
         this.matrixStack.rotate(Axis.XP.rotation(angle));
      }
   }

   @MappedMethod
   public void rotateYRadians(float angle) {
      if (this.matrixStack != null) {
         this.matrixStack.rotate(Axis.YP.rotation(angle));
      }
   }

   @MappedMethod
   public void rotateZRadians(float angle) {
      if (this.matrixStack != null) {
         this.matrixStack.rotate(Axis.ZP.rotation(angle));
      } else if (this.drawContext != null) {
         this.drawContext.pose().rotate(angle);
      }
   }

   @MappedMethod
   public void rotateXDegrees(float angle) {
      if (this.matrixStack != null) {
         this.matrixStack.rotate(Axis.XP.rotationDegrees(angle));
      }
   }

   @MappedMethod
   public void rotateYDegrees(float angle) {
      if (this.matrixStack != null) {
         this.matrixStack.rotate(Axis.YP.rotationDegrees(angle));
      }
   }

   @MappedMethod
   public void rotateZDegrees(float angle) {
      if (this.matrixStack != null) {
         this.matrixStack.rotate(Axis.ZP.rotationDegrees(angle));
      } else if (this.drawContext != null) {
         this.drawContext.pose().rotate((float)Math.toRadians(angle));
      }
   }

   @MappedMethod
   public Matrix4f copyPositionMatrix() {
      return this.matrixStack == null ? new Matrix4f() : new Matrix4f(new org.joml.Matrix4f(this.matrixStack.last().pose()));
   }

   @MappedMethod
   public void drawText(MutableText mutableText, int x, int y, int color, boolean shadow, int light) {
      if (this.drawContext != null) {
         this.drawContext.text(font(), (Component)mutableText.data, x, y, color, shadow);
      } else if (this.matrixStack != null) {
         this.drawWorldText((Component)mutableText.data, x, y, color, shadow, light);
      }
   }

   @MappedMethod
   public void drawText(OrderedText orderedText, int x, int y, int color, boolean shadow, int light) {
      if (this.drawContext != null) {
         this.drawContext.text(font(), (FormattedCharSequence)orderedText.data, x, y, color, shadow);
      } else if (this.matrixStack != null) {
         this.drawWorldText((FormattedCharSequence)orderedText.data, x, y, color, shadow, light);
      }
   }

   @MappedMethod
   public void drawText(String text, int x, int y, int color, boolean shadow, int light) {
      if (this.drawContext != null) {
         this.drawContext.text(font(), text, x, y, color, shadow);
      } else if (this.matrixStack != null) {
         this.drawWorldText(text, x, y, color, shadow, light);
      }
   }

   private void drawWorldText(Component text, int x, int y, int color, boolean shadow, int light) {
      drawWorldText(text.getVisualOrderText(), x, y, color, shadow, light);
   }

   private void drawWorldText(FormattedCharSequence text, int x, int y, int color, boolean shadow, int light) {
      if (vertexConsumerProvider != null) {
         vertexConsumerProvider.submitText(matrixStack, x, y, text, shadow, DisplayMode.NORMAL, light, color, 0, 0);
      }
   }

   private void drawWorldText(String text, int x, int y, int color, boolean shadow, int light) {
      drawWorldText(Component.literal(text), x, y, color, shadow, light);
   }

   private void flush() {
      if (vertexConsumerProvider != null) batches.forEach((layer, batch) -> batch.submit(vertexConsumerProvider, layer));
      batches.clear();
   }

   @MappedMethod
   public void drawCenteredText(String text, int centerX, int y, int color) {
      if (this.drawContext != null) {
         this.drawContext.centeredText(font(), text, centerX, y, color);
      }
   }

   @MappedMethod
   public void drawCenteredText(MutableText text, int centerX, int y, int color) {
      if (this.drawContext != null) {
         this.drawContext.centeredText(font(), (Component)text.data, centerX, y, color);
      }
   }

   @MappedMethod
   public static int getTextWidth(MutableText mutableText) {
      return font().width((FormattedText)mutableText.data);
   }

   @MappedMethod
   public static int getTextWidth(OrderedText orderedText) {
      return font().width((FormattedCharSequence)orderedText.data);
   }

   @MappedMethod
   public static int getTextWidth(String text) {
      return font().width(text);
   }

   @MappedMethod
   public static List<OrderedText> wrapLines(MutableText mutableText, int width) {
      return font().split((FormattedText)mutableText.data, width).stream().map(OrderedText::new).collect(Collectors.toList());
   }

   private static Font font() {
      return ((Minecraft)MinecraftClient.getInstance().data).font;
   }

   private static MinecraftClient getInstance() {
      return MinecraftClient.getInstance();
   }

   @MappedMethod
   public void createVertexConsumer(RenderLayer renderLayer) {
      if (this.vertexConsumerProvider != null) {
         this.vertexConsumer = batches.computeIfAbsent((RenderType)renderLayer.data, layer -> new DeferredVertexBatch());
      }
   }

   @MappedMethod
   public void drawLineInWorld(float x1, float y1, float z1, float x2, float y2, float z2, int color) {
      if (this.matrixStack != null && this.vertexConsumer != null) {
         ColorHelper.unpackColor(color, (a, r, g, b) -> {
            Pose entry = this.matrixStack.last();
            org.joml.Matrix4f matrix4f = entry.pose();
            float lineWidth = Math.max(1.0F, (float)((Minecraft)MinecraftClient.getInstance().data).getWindow().getGuiScale());
            this.vertexConsumer.addVertex(matrix4f, x1, y1, z1).setColor(r, g, b, a).setNormal(entry, 0.0F, 1.0F, 0.0F).setLineWidth(lineWidth);
            this.vertexConsumer.addVertex(matrix4f, x2, y2, z2).setColor(r, g, b, a).setNormal(entry, 0.0F, 1.0F, 0.0F).setLineWidth(lineWidth);
         });
      }
   }

   @MappedMethod
   public void drawTextureInWorld(
      float x1,
      float y1,
      float z1,
      float x2,
      float y2,
      float z2,
      float x3,
      float y3,
      float z3,
      float x4,
      float y4,
      float z4,
      float u1,
      float v1,
      float u2,
      float v2,
      Direction facing,
      int color,
      int light
   ) {
      if (this.matrixStack != null && this.vertexConsumer != null) {
         ColorHelper.unpackColor(
            color,
            (a, r, g, b) -> {
               Vector3i vector3i = facing.getUnitVec3i();
               int x = vector3i.getX();
               int y = vector3i.getY();
               int z = vector3i.getZ();
               Pose entry = this.matrixStack.last();
               org.joml.Matrix4f matrix4f = entry.pose();
               this.vertexConsumer
                  .addVertex(matrix4f, x1, y1, z1)
                  .setColor(r, g, b, a)
                  .setUv(u1, v2)
                  .setOverlay(OverlayTexture.NO_OVERLAY)
                  .setLight(light)
                  .setNormal(entry, x, y, z);
               this.vertexConsumer
                  .addVertex(matrix4f, x2, y2, z2)
                  .setColor(r, g, b, a)
                  .setUv(u2, v2)
                  .setOverlay(OverlayTexture.NO_OVERLAY)
                  .setLight(light)
                  .setNormal(entry, x, y, z);
               this.vertexConsumer
                  .addVertex(matrix4f, x3, y3, z3)
                  .setColor(r, g, b, a)
                  .setUv(u2, v1)
                  .setOverlay(OverlayTexture.NO_OVERLAY)
                  .setLight(light)
                  .setNormal(entry, x, y, z);
               this.vertexConsumer
                  .addVertex(matrix4f, x4, y4, z4)
                  .setColor(r, g, b, a)
                  .setUv(u1, v1)
                  .setOverlay(OverlayTexture.NO_OVERLAY)
                  .setLight(light)
                  .setNormal(entry, x, y, z);
            }
         );
      }
   }

   @MappedMethod
   public void renderEntity(Entity entity, double x, double y, double z, float yaw, float tickDelta, int light) {
   }
}
