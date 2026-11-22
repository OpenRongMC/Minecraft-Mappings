package org.mtr.mapping.holder;

import java.io.File;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.storage.LevelResource;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class MinecraftServer extends HolderBase<net.minecraft.server.MinecraftServer> {
   public MinecraftServer(net.minecraft.server.MinecraftServer data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Path getServerDirectory() {
      return ((net.minecraft.server.MinecraftServer)this.data).getServerDirectory();
   }

   @Nonnull
   @MappedMethod
   public PlayerManager getPlayerList() {
      return new PlayerManager(((net.minecraft.server.MinecraftServer)this.data).getPlayerList());
   }

   @MappedMethod
   public void halt(boolean bl) {
      ((net.minecraft.server.MinecraftServer)this.data).halt(bl);
   }

   @Nonnull
   @MappedMethod
   public Path getWorldPath(WorldSavePath levelResource) {
      return ((net.minecraft.server.MinecraftServer)this.data).getWorldPath((LevelResource)levelResource.data);
   }

   @MappedMethod
   public void executeIfPossible(Runnable runnable) {
      ((net.minecraft.server.MinecraftServer)this.data).executeIfPossible(runnable);
   }

   @Nonnull
   @MappedMethod
   public final ServerWorld overworld() {
      return new ServerWorld(((net.minecraft.server.MinecraftServer)this.data).overworld());
   }

   @MappedMethod
   public boolean isRunning() {
      return ((net.minecraft.server.MinecraftServer)this.data).isRunning();
   }

   @Nonnull
   @MappedMethod
   public File getRunDirectory() {
      return this.getServerDirectory().toFile();
   }

   @Nonnull
   @MappedMethod
   public PlayerManager getPlayerManager() {
      return this.getPlayerList();
   }

   @Nonnull
   @MappedMethod
   public ServerWorld getOverworld() {
      return this.overworld();
   }

   @Nonnull
   @MappedMethod
   public Path getSavePath(WorldSavePath path) {
      return this.getWorldPath(path);
   }

   @MappedMethod
   public void stop(boolean waitForShutdown) {
      this.halt(waitForShutdown);
   }

   @MappedMethod
   public void execute(Runnable runnable) {
      this.executeIfPossible(runnable);
   }
}
