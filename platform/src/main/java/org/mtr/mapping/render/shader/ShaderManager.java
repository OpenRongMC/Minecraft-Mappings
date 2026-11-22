package org.mtr.mapping.render.shader;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.ClientAvatarState;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Matrix4f;
import org.mtr.mapping.render.CapturedProjection;
import org.mtr.mapping.render.CapturedRenderMatrices;
import org.mtr.mapping.render.RenderDiagnosticsFlag;
import org.mtr.mapping.render.batch.MaterialProperties;
import org.mtr.mapping.tool.DummyClass;

public final class ShaderManager {
   private static final Logger LOGGER = LogManager.getLogger("MTR-RenderDiag");
   private ShaderProgram shader;

   public boolean isReady() {
      return this.shader != null;
   }

   public void reloadShaders() {
      if (this.shader != null) {
         this.shader.close();
         this.shader = null;
      }

      try {
         this.shader = new ShaderProgram();
      } catch (Exception var2) {
         DummyClass.logException(var2);
      }
   }

   public void setupShaderBatchState(MaterialProperties materialProperties) {
      if (this.shader != null) {
         logFovDiag();
         materialProperties.setupCompositeState();
         this.shader.bind(copyModelView(), copyProjection(), !materialProperties.translucent);
      }
   }

   public void setModelMat(Matrix4f modelMat) {
      if (this.shader != null) {
         this.shader.setModelMat(modelMat);
      }
   }

   public void cleanupShaderBatchState() {
      ShaderProgram.unbind();
   }

   private static Matrix4f copyModelView() {
      Matrix4f captured = CapturedRenderMatrices.copyViewOrNull();
      return captured != null ? captured : new Matrix4f(RenderSystem.getModelViewMatrix());
   }

   private static Matrix4f copyProjection() {
      Matrix4f fromLevel = CapturedRenderMatrices.copyProjectionOrNull();
      return fromLevel != null ? fromLevel : buildFallbackProjection();
   }

   public static Matrix4f buildFallbackProjection() {
      Matrix4f captured = CapturedProjection.copyOrNull();
      if (captured != null) {
         return captured;
      } else {
         Minecraft minecraft = Minecraft.getInstance();
         float partialTick = minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(true);
         float fov = minecraft.gameRenderer.getFov(minecraft.gameRenderer.getMainCamera(), partialTick, true);
         Matrix4f projection = new Matrix4f(minecraft.gameRenderer.getProjectionMatrix(fov));
         PoseStack bobStack = new PoseStack();
         float bobPartialTick = minecraft.gameRenderer.getMainCamera().getPartialTickTime();
         bobHurt(bobStack, bobPartialTick);
         if (Boolean.TRUE.equals(minecraft.options.bobView().get())) {
            bobView(bobStack, bobPartialTick);
         }

         projection.mul(bobStack.last().pose());
         return projection;
      }
   }

   private static void logFovDiag() {
      Minecraft minecraft = Minecraft.getInstance();
      float partialTick = minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(true);
      float optionsFov = ((Integer)minecraft.options.fov().get()).intValue();
      float fov = minecraft.gameRenderer.getFov(minecraft.gameRenderer.getMainCamera(), partialTick, true);
      RenderDiagnosticsFlag.lastOptionsFov = optionsFov;
      RenderDiagnosticsFlag.lastDynamicFov = fov;
      if (RenderDiagnosticsFlag.isEnabled()) {
         long now = System.currentTimeMillis();
         if (now - RenderDiagnosticsFlag.lastShaderLogMillis >= 250L) {
            RenderDiagnosticsFlag.lastShaderLogMillis = now;
            LOGGER.info(
               "[MTR-RenderDiag] shaderFOV options={}/dynamic={}/delta={} capturedView={} capturedProj={}",
               String.format("%.3f", optionsFov),
               String.format("%.3f", fov),
               String.format("%.3f", fov - optionsFov),
               CapturedRenderMatrices.isValid(),
               CapturedProjection.copyOrNull() != null
            );
         }
      }
   }

   private static void bobHurt(PoseStack poseStack, float tickProgress) {
      if (Minecraft.getInstance().getCameraEntity() instanceof LivingEntity livingEntity) {
         float hurtTick = livingEntity.hurtTime - tickProgress;
         if (livingEntity.isDeadOrDying()) {
            float death = Math.min(livingEntity.deathTime + tickProgress, 20.0F);
            poseStack.rotate(Axis.ZP.rotationDegrees(40.0F - 8000.0F / (death + 200.0F)));
         }

         if (!(hurtTick < 0.0F)) {
            hurtTick /= livingEntity.hurtDuration;
            hurtTick = Mth.sin(hurtTick * hurtTick * hurtTick * hurtTick * (float) Math.PI);
            float hurtDir = livingEntity.getHurtDir();
            poseStack.rotate(Axis.YP.rotationDegrees(-hurtDir));
            float tilt = (float)(-hurtTick * 14.0 * (Double)Minecraft.getInstance().options.damageTiltStrength().get());
            poseStack.rotate(Axis.ZP.rotationDegrees(tilt));
            poseStack.rotate(Axis.YP.rotationDegrees(hurtDir));
         }
      }
   }

   private static void bobView(PoseStack poseStack, float tickProgress) {
      if (Minecraft.getInstance().getCameraEntity() instanceof AbstractClientPlayer player) {
         ClientAvatarState avatarState = player.avatarState();
         float walkDist = avatarState.getBackwardsInterpolatedWalkDistance(tickProgress);
         float bob = avatarState.getInterpolatedBob(tickProgress);
         poseStack.translate(Mth.sin(walkDist * (float) Math.PI) * bob * 0.5F, -Math.abs(Mth.cos(walkDist * (float) Math.PI) * bob), 0.0F);
         poseStack.rotate(Axis.ZP.rotationDegrees(Mth.sin(walkDist * (float) Math.PI) * bob * 3.0F));
         poseStack.rotate(Axis.XP.rotationDegrees(Math.abs(Mth.cos(walkDist * (float) Math.PI - 0.2F) * bob) * 5.0F));
      }
   }
}
