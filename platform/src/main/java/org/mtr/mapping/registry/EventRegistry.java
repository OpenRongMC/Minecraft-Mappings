package org.mtr.mapping.registry;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents.Load;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents.Unload;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarting;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStopped;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStopping;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndTick;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndLevelTick;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.StartTick;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.StartLevelTick;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.Disconnect;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents.Join;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.MinecraftServer;
import org.mtr.mapping.holder.ServerPlayerEntity;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.holder.WorldChunk;
import org.mtr.mapping.tool.DummyClass;

public class EventRegistry extends DummyClass {
   @MappedMethod
   public void registerServerStarting(Consumer<MinecraftServer> consumer) {
      ServerLifecycleEvents.SERVER_STARTING.register((ServerStarting)minecraftServer -> consumer.accept(new MinecraftServer(minecraftServer)));
   }

   @MappedMethod
   public void registerServerStarted(Consumer<MinecraftServer> consumer) {
      ServerLifecycleEvents.SERVER_STARTED.register((ServerStarted)minecraftServer -> consumer.accept(new MinecraftServer(minecraftServer)));
   }

   @MappedMethod
   public void registerServerStopping(Consumer<MinecraftServer> consumer) {
      ServerLifecycleEvents.SERVER_STOPPING.register((ServerStopping)minecraftServer -> consumer.accept(new MinecraftServer(minecraftServer)));
   }

   @MappedMethod
   public void registerServerStopped(Consumer<MinecraftServer> consumer) {
      ServerLifecycleEvents.SERVER_STOPPED.register((ServerStopped)minecraftServer -> consumer.accept(new MinecraftServer(minecraftServer)));
   }

   @MappedMethod
   public void registerStartServerTick(Runnable runnable) {
      ServerTickEvents.START_SERVER_TICK.register((StartTick)minecraftServer -> runnable.run());
   }

   @MappedMethod
   public void registerEndServerTick(Runnable runnable) {
      ServerTickEvents.END_SERVER_TICK.register((EndTick)minecraftServer -> runnable.run());
   }

   @MappedMethod
   public void registerStartWorldTick(Consumer<ServerWorld> consumer) {
      ServerTickEvents.START_LEVEL_TICK.register((StartLevelTick)serverWorld -> consumer.accept(new ServerWorld(serverWorld)));
   }

   @MappedMethod
   public void registerEndWorldTick(Consumer<ServerWorld> consumer) {
      ServerTickEvents.END_LEVEL_TICK.register((EndLevelTick)serverWorld -> consumer.accept(new ServerWorld(serverWorld)));
   }

   @MappedMethod
   public void registerPlayerJoin(BiConsumer<MinecraftServer, ServerPlayerEntity> consumer) {
      ServerPlayConnectionEvents.JOIN
         .register((Join)(handler, sender, server) -> consumer.accept(new MinecraftServer(server), new ServerPlayerEntity(handler.player)));
   }

   @MappedMethod
   public void registerPlayerDisconnect(BiConsumer<MinecraftServer, ServerPlayerEntity> consumer) {
      ServerPlayConnectionEvents.DISCONNECT
         .register((Disconnect)(handler, server) -> consumer.accept(new MinecraftServer(server), new ServerPlayerEntity(handler.player)));
   }

   @MappedMethod
   public void registerChunkLoad(BiConsumer<ServerWorld, WorldChunk> consumer) {
      ServerChunkEvents.CHUNK_LOAD.register((Load)(serverWorld, worldChunk, generated) -> consumer.accept(new ServerWorld(serverWorld), new WorldChunk(worldChunk)));
   }

   @MappedMethod
   public void registerChunkUnload(BiConsumer<ServerWorld, WorldChunk> consumer) {
      ServerChunkEvents.CHUNK_UNLOAD.register((Unload)(serverWorld, worldChunk) -> consumer.accept(new ServerWorld(serverWorld), new WorldChunk(worldChunk)));
   }
}
