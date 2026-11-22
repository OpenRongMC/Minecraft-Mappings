package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;

import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Identifier;

public abstract class EntityRenderer<T extends EntityExtension> extends net.minecraft.client.renderer.entity.EntityRenderer<T, EntityRenderState> {

   @MappedMethod
   public EntityRenderer(EntityRenderer.Argument argument) {
      super(argument.data);
   }

   @Deprecated
   public final EntityRenderState createRenderState() {
      return new StoredEntityRenderState();
   }

   @Deprecated
   public void extractRenderState(T entity, EntityRenderState state, float tickDelta) {
      super.extractRenderState(entity, state, tickDelta);
      StoredEntityRenderState stored = (StoredEntityRenderState) state;
      stored.entity = entity; stored.tickDelta = tickDelta; stored.yaw = entity.getYRot();
   }

   @Deprecated
   public void submit(EntityRenderState state, PoseStack matrices, SubmitNodeCollector collector, CameraRenderState camera) {
      StoredEntityRenderState stored = (StoredEntityRenderState) state;
      if (stored.entity != null) {
         GraphicsHolder.createInstanceSafe(
            matrices, collector, graphicsHolder -> this.render((T) stored.entity, stored.yaw, stored.tickDelta, graphicsHolder, state.lightCoords)
         );
      }
   }

   @MappedMethod
   public abstract void render(T var1, float var2, float var3, GraphicsHolder var4, int var5);

   @MappedMethod
   public abstract Identifier getTexture2(T var1);

   @Deprecated
   public final boolean shouldRender(T entity, Frustum frustum, double x, double y, double z, float partialTick) {
      return this.shouldRender2(entity, new org.mtr.mapping.holder.Frustum(frustum), x, y, z);
   }

   @MappedMethod
   public boolean shouldRender2(T entity, org.mtr.mapping.holder.Frustum frustum, double x, double y, double z) {
      return super.shouldRender(entity, (Frustum)frustum.data, x, y, z, 0);
   }

   private static final class StoredEntityRenderState extends EntityRenderState { EntityExtension entity; float tickDelta, yaw; }

   @Deprecated
   public static final class Argument {
      private final Context data;

      public Argument(Context data) {
         this.data = data;
      }
   }
}
