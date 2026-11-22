package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.DummyInterface;

public interface ModelHelper extends DummyInterface {
   @MappedMethod
   void render(GraphicsHolder var1, int var2, int var3, float var4, float var5, float var6, float var7);

   @Deprecated
   default void render3(PoseStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
      GraphicsHolder.createInstanceSafe(matrixStack, null, graphicsHolder -> {
         graphicsHolder.vertexConsumer = vertexConsumer;
         this.render(graphicsHolder, light, overlay, red, green, blue, alpha);
      });
   }
}
