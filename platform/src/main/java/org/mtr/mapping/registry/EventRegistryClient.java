package org.mtr.mapping.registry;

import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents.Load;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents.Unload;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndLevelTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.StartTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.StartLevelTick;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.Disconnect;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.Join;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents.CollectSubmits;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents.BeforeTranslucentTerrain;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ClientWorld;
import org.mtr.mapping.holder.Vector3d;
import org.mtr.mapping.holder.WorldChunk;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.tool.DummyClass;

public class EventRegistryClient extends DummyClass {
   @MappedMethod
   public void registerStartClientTick(Runnable runnable) {
      ClientTickEvents.START_CLIENT_TICK.register((StartTick)minecraftServer -> runnable.run());
   }

   @MappedMethod
   public void registerEndClientTick(Runnable runnable) {
      ClientTickEvents.END_CLIENT_TICK.register((EndTick)minecraftServer -> runnable.run());
   }

   @MappedMethod
   public void registerStartWorldTick(Consumer<ClientWorld> consumer) {
      ClientTickEvents.START_LEVEL_TICK.register((StartLevelTick)clientWorld -> consumer.accept(new ClientWorld(clientWorld)));
   }

   @MappedMethod
   public void registerEndWorldTick(Consumer<ClientWorld> consumer) {
      ClientTickEvents.END_LEVEL_TICK.register((EndLevelTick)clientWorld -> consumer.accept(new ClientWorld(clientWorld)));
   }

   @MappedMethod
   public void registerClientJoin(Runnable runnable) {
      ClientPlayConnectionEvents.JOIN.register((Join)(handler, sender, client) -> runnable.run());
   }

   @MappedMethod
   public void registerClientDisconnect(Runnable runnable) {
      ClientPlayConnectionEvents.DISCONNECT.register((Disconnect)(handler, client) -> runnable.run());
   }

   @MappedMethod
   public void registerChunkLoad(BiConsumer<ClientWorld, WorldChunk> consumer) {
      ClientChunkEvents.CHUNK_LOAD.register((Load)(clientWorld, worldChunk) -> consumer.accept(new ClientWorld(clientWorld), new WorldChunk(worldChunk)));
   }

   @MappedMethod
   public void registerChunkUnload(BiConsumer<ClientWorld, WorldChunk> consumer) {
      ClientChunkEvents.CHUNK_UNLOAD.register((Unload)(clientWorld, worldChunk) -> consumer.accept(new ClientWorld(clientWorld), new WorldChunk(worldChunk)));
   }

   @MappedMethod
   public void registerGuiRendering(Consumer<GraphicsHolder> consumer) {
      HudElementRegistry.addLast(Identifier.fromNamespaceAndPath("mtr", "hud_" + Integer.toUnsignedString(new Random().nextInt())),(drawContext, tickDelta) -> GraphicsHolder.createInstanceSafe(drawContext, consumer));
   }

   @MappedMethod
   public void registerWorldRenderAfterEntities(BiConsumer<GraphicsHolder, Vector3d> consumer) {
      LevelRenderEvents.COLLECT_SUBMITS.register((CollectSubmits)context -> {
         var cameraState = context.levelState().cameraRenderState;
         org.mtr.mapping.render.CapturedRenderMatrices.capture(cameraState.viewRotationMatrix, cameraState.projectionMatrix);
         org.mtr.mapping.render.CapturedProjection.capture(cameraState.projectionMatrix);
         PoseStack matrices = context.poseStack();
         SubmitNodeCollector consumers = context.submitNodeCollector();
         if (matrices != null && consumers != null) {
            Vector3d camera = new Vector3d(context.levelState().cameraRenderState.pos);
            GraphicsHolder.createInstanceSafe(matrices, consumers, graphicsHolder -> consumer.accept(graphicsHolder, camera));
         }
      });
   }

   @MappedMethod
   public void registerWorldRenderBeforeTranslucent(Runnable runnable) {
      LevelRenderEvents.BEFORE_TRANSLUCENT_TERRAIN.register((BeforeTranslucentTerrain)context -> runnable.run());
   }

   @MappedMethod
   public void registerResourceReloadEvent(final Runnable runnable) {
      final Identifier identifier = Identifier.fromNamespaceAndPath("mtr", Integer.toHexString(new Random().nextInt()));
      ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
         @Deprecated
         public final Identifier getFabricId() {
            return identifier;
         }

         @Deprecated
         public final void onResourceManagerReload(ResourceManager manager) {
            runnable.run();
         }
      });
   }
}
