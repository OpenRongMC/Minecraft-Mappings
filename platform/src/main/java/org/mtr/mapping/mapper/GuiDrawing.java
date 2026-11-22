package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix4f;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.tool.ColorHelper;
import org.mtr.mapping.tool.DummyClass;

public final class GuiDrawing extends DummyClass {
   private VertexConsumer vertexConsumer;
   private GuiGraphicsExtractor drawContext;
   private BufferBuilder bufferBuilder;
   private Matrix4f matrix;
   private Identifier currentTexture;
   private final GraphicsHolder graphicsHolder;

   @MappedMethod
   public GuiDrawing(GraphicsHolder graphicsHolder) {
      this.graphicsHolder = graphicsHolder;
   }

   @MappedMethod
   public void beginDrawingRectangle() {
      this.matrix = this.graphicsHolder.matrixStack == null ? null : this.graphicsHolder.matrixStack.last().pose();
      this.vertexConsumer = null;
      this.drawContext = this.graphicsHolder.drawContext;
      this.currentTexture = null;
   }

   @MappedMethod
   public void drawRectangle(double x1, double y1, double x2, double y2, int color) {
      if (this.matrix != null && this.vertexConsumer != null) {
         ColorHelper.unpackColor(color, (a, r, g, b) -> {
            this.vertexConsumer.addVertex(this.matrix, (float)x1, (float)y1, 0.0F).setColor(r, g, b, a);
            this.vertexConsumer.addVertex(this.matrix, (float)x1, (float)y2, 0.0F).setColor(r, g, b, a);
            this.vertexConsumer.addVertex(this.matrix, (float)x2, (float)y2, 0.0F).setColor(r, g, b, a);
            this.vertexConsumer.addVertex(this.matrix, (float)x2, (float)y1, 0.0F).setColor(r, g, b, a);
         });
      } else if (this.drawContext != null) {
         blitWithPose(this.drawContext, x1, y1, x2, y2, (poseX, poseY, poseW, poseH) -> this.drawContext.fill(0, 0, 1, 1, color));
      }
   }

   @MappedMethod
   public void drawRectangle(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, int color) {
      if (this.matrix != null && this.vertexConsumer != null) {
         ColorHelper.unpackColor(color, (a, r, g, b) -> {
            this.vertexConsumer.addVertex(this.matrix, (float)x1, (float)y1, 0.0F).setColor(r, g, b, a);
            this.vertexConsumer.addVertex(this.matrix, (float)x2, (float)y2, 0.0F).setColor(r, g, b, a);
            this.vertexConsumer.addVertex(this.matrix, (float)x3, (float)y3, 0.0F).setColor(r, g, b, a);
            this.vertexConsumer.addVertex(this.matrix, (float)x4, (float)y4, 0.0F).setColor(r, g, b, a);
         });
      } else if (this.drawContext != null) {
         int minX = (int)Math.min(Math.min(x1, x2), Math.min(x3, x4));
         int minY = (int)Math.min(Math.min(y1, y2), Math.min(y3, y4));
         int maxX = (int)Math.max(Math.max(x1, x2), Math.max(x3, x4));
         int maxY = (int)Math.max(Math.max(y1, y2), Math.max(y3, y4));
         this.drawContext.fill(minX, minY, maxX, maxY, color);
      }
   }

   @MappedMethod
   public void finishDrawingRectangle() {
   }

   @MappedMethod
   public void beginDrawingTexture(Identifier identifier) {
      this.matrix = this.graphicsHolder.matrixStack == null ? null : this.graphicsHolder.matrixStack.last().pose();
      this.drawContext = this.graphicsHolder.drawContext;
      this.currentTexture = identifier;
      this.bufferBuilder = null;
   }

   @MappedMethod
   public void drawTexture(double x1, double y1, double x2, double y2, float u1, float v1, float u2, float v2) {
      if (this.drawContext != null && this.currentTexture != null) {
         blitWithPose(
            this.drawContext,
            x1,
            y1,
            x2,
            y2,
            (poseX, poseY, poseW, poseH) -> this.drawContext.blit((net.minecraft.resources.Identifier)this.currentTexture.data, 0, 0, 1, 1, u1, u2, v1, v2)
         );
      } else if (this.matrix != null && this.bufferBuilder != null) {
         this.bufferBuilder.addVertex(this.matrix, (float)x1, (float)y1, 0.0F).setUv(u1, v1);
         this.bufferBuilder.addVertex(this.matrix, (float)x1, (float)y2, 0.0F).setUv(u1, v2);
         this.bufferBuilder.addVertex(this.matrix, (float)x2, (float)y2, 0.0F).setUv(u2, v2);
         this.bufferBuilder.addVertex(this.matrix, (float)x2, (float)y1, 0.0F).setUv(u2, v1);
      }
   }

   private static void blitWithPose(GuiGraphicsExtractor drawContext, double x1, double y1, double x2, double y2, GuiDrawing.QuadDrawer drawer) {
      float w = (float)(x2 - x1);
      float h = (float)(y2 - y1);
      if (w != 0.0F && h != 0.0F) {
         Matrix3x2fStack pose = drawContext.pose();
         pose.pushMatrix();
         pose.translate((float)x1, (float)y1);
         pose.scale(w, h);
         drawer.draw(0, 0, w, h);
         pose.popMatrix();
      }
   }

   @MappedMethod
   public void finishDrawingTexture() {
      this.currentTexture = null;
      this.bufferBuilder = null;
   }

   @FunctionalInterface
   private interface QuadDrawer {
      void draw(int var1, int var2, float var3, float var4);
   }
}
