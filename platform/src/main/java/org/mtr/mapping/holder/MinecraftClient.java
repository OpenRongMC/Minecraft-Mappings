package org.mtr.mapping.holder;
import net.minecraft.client.main.GameConfig;


import java.io.File;
import java.nio.file.Path;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class MinecraftClient extends HolderBase<Minecraft> {
   public MinecraftClient(Minecraft data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Path getResourcePackDirectory() {
      return ((Minecraft)this.data).getResourcePackDirectory();
   }

   @Nonnull
   @MappedMethod
   public TextureManager getTextureManager() {
      return new TextureManager(((Minecraft)this.data).getTextureManager());
   }

   @MappedMethod
   public void schedule(Runnable runnable) {
      ((Minecraft)this.data).schedule(runnable);
   }

   @Nonnull
   @MappedMethod
   public ResourceManager getResourceManager() {
      return new ResourceManager(((Minecraft)this.data).getResourceManager());
   }

   @MappedMethod
   public void setScreenAndShow(@Nullable Screen screen) {
      ((Minecraft)this.data).setScreenAndShow(screen == null ? null : (net.minecraft.client.gui.screens.Screen)screen.data);
   }

   @MappedMethod
   public boolean isRunning() {
      return ((Minecraft)this.data).isRunning();
   }

   @Nonnull
   @MappedMethod
   public ClientPlayNetworkHandler getConnection() {
      return ((Minecraft)this.data).getConnection() == null ? null : new ClientPlayNetworkHandler(((Minecraft)this.data).getConnection());
   }

   @Nonnull
   @MappedMethod
   public Window getWindow() {
      return new Window(((Minecraft)this.data).getWindow());
   }

   @Nonnull
   @MappedMethod
   public SoundManager getSoundManager() {
      return new SoundManager(((Minecraft)this.data).getSoundManager());
   }

   @MappedMethod
   public boolean isSameThread() {
      return ((Minecraft)this.data).isSameThread();
   }

   @Nonnull
   @MappedMethod
   public static MinecraftClient getInstance() {
      return new MinecraftClient(Minecraft.getInstance());
   }

   @Nonnull
   @MappedMethod
   public DeltaTracker getDeltaTracker() {
      return ((Minecraft)this.data).getDeltaTracker();
   }

   @MappedMethod
   public boolean isPaused() {
      return ((Minecraft)this.data).isPaused();
   }

   @Nonnull
   @MappedMethod
   public ClientPlayerEntity getPlayerMapped() {
      return ((Minecraft)this.data).player == null ? null : new ClientPlayerEntity(((Minecraft)this.data).player);
   }

   @Nonnull
   @MappedMethod
   public File getGameDirectoryMapped() {
      return ((Minecraft)this.data).gameDirectory;
   }

   @Nullable
   @MappedMethod
   public Screen getScreenMapped() {
      return ((Minecraft)this.data).gui.screen() == null ? null : new Screen(((Minecraft)this.data).gui.screen());
   }

   @Nullable
   @MappedMethod
   public HitResult getHitResultMapped() {
      return ((Minecraft)this.data).hitResult == null ? null : new HitResult(((Minecraft)this.data).hitResult);
   }

   @Nullable
   @MappedMethod
   public ClientWorld getLevelMapped() {
      return ((Minecraft)this.data).level == null ? null : new ClientWorld(((Minecraft)this.data).level);
   }

   @Nonnull
   @MappedMethod
   public TextRenderer getFontMapped() {
      return new TextRenderer(((Minecraft)this.data).font);
   }

   @Nonnull
   @MappedMethod
   public GameRenderer getGameRendererMapped() {
      return new GameRenderer(((Minecraft)this.data).gameRenderer);
   }

   @Nonnull
   @MappedMethod
   public GameOptions getOptionsMapped() {
      return new GameOptions(((Minecraft)this.data).options);
   }

   @Nullable
   @MappedMethod
   public ClientWorld getWorldMapped() {
      return this.getLevelMapped();
   }

   @Nonnull
   @MappedMethod
   public File getRunDirectoryMapped() {
      return this.getGameDirectoryMapped();
   }

   @MappedMethod
   public void openScreen(@Nullable Screen screen) {
      this.setScreenAndShow(screen);
   }

   @Nullable
   @MappedMethod
   public Screen getCurrentScreenMapped() {
      return this.getScreenMapped();
   }

   @Nullable
   @MappedMethod
   public HitResult getCrosshairTargetMapped() {
      return this.getHitResultMapped();
   }

   @MappedMethod
   public float getLastFrameDuration() {
      return this.getDeltaTracker().getGameTimeDeltaTicks();
   }

   @Nullable
   @MappedMethod
   public ClientPlayNetworkHandler getNetworkHandler() {
      return this.getConnection();
   }

   @MappedMethod
   public void execute(Runnable runnable) {
      this.schedule(runnable);
   }

@MappedMethod
   public MinecraftClient(GameConfig gameConfig) {
      super(new Minecraft(gameConfig));
   }
}
