package org.mtr.mapping.mapper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.Vector3d;

public abstract class BlockEntityRenderer<T extends BlockEntityExtension>
   implements net.minecraft.client.renderer.blockentity.BlockEntityRenderer<T, BlockEntityRenderState> {
   private T currentEntity;

   @MappedMethod
   public BlockEntityRenderer(BlockEntityRenderer.Argument argument) {
   }

   @Deprecated
   public BlockEntityRenderState createRenderState() {
      return new BlockEntityRenderer.StoredEntityRenderState();
   }

   @Deprecated
   public void extractRenderState(T entity, BlockEntityRenderState state, float tickDelta, Vec3 cameraPos, CrumblingOverlay overlay) {
      net.minecraft.client.renderer.blockentity.BlockEntityRenderer.super.extractRenderState(entity, state, tickDelta, cameraPos, overlay);
      this.currentEntity = entity;
      if (state instanceof BlockEntityRenderer.StoredEntityRenderState stored) {
         stored.entity = entity;
         stored.tickDelta = tickDelta;
      }
   }

   @Deprecated
   public void submit(BlockEntityRenderState state, PoseStack matrices, SubmitNodeCollector collector, CameraRenderState camera) {
      T entity;
      float tickDelta;
      if (state instanceof BlockEntityRenderer.StoredEntityRenderState stored && stored.entity != null) {
         entity = (T)stored.entity;
         tickDelta = stored.tickDelta;
      } else {
         entity = this.currentEntity;
         tickDelta = 0.0F;
      }

      if (entity != null) {
         GraphicsHolder.createInstanceSafe(
            matrices, collector, graphicsHolder -> this.render(entity, tickDelta, graphicsHolder, state.lightCoords, OverlayTexture.NO_OVERLAY)
         );
      }
   }

   @MappedMethod
   public abstract void render(T var1, float var2, GraphicsHolder var3, int var4, int var5);

   @MappedMethod
   public boolean rendersOutsideBoundingBox2(T blockEntity) {
      return false;
   }

   @Deprecated
   public boolean shouldRenderOffScreen() {
      return this.rendersOutsideBoundingBox2(this.currentEntity);
   }

   @MappedMethod
   public int getRenderDistance2() {
      return 64;
   }

   @Deprecated
   public int getViewDistance() {
      return this.getRenderDistance2();
   }

   @MappedMethod
   public boolean isInRenderDistance(T blockEntity, Vector3d position) {
      return net.minecraft.client.renderer.blockentity.BlockEntityRenderer.super.shouldRender(blockEntity, (Vec3)position.data);
   }

   @Deprecated
   public boolean shouldRender(T blockEntity, Vec3 position) {
      return this.isInRenderDistance(blockEntity, new Vector3d(position));
   }

   @Deprecated
   public static final class Argument {
      private final Context data;

      public Argument(Context data) {
         this.data = data;
      }
   }

   private static final class StoredEntityRenderState extends BlockEntityRenderState {
      private BlockEntityExtension entity;
      private float tickDelta;
   }
}
