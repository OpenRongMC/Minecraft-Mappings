package org.mtr.mapping.holder;
import net.minecraft.client.Options;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.sounds.SoundEngine.PlayResult;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class SoundManager extends HolderBase<net.minecraft.client.sounds.SoundManager> {
   public SoundManager(net.minecraft.client.sounds.SoundManager data) {
      super(data);
   }

   @MappedMethod
   public void stop() {
      ((net.minecraft.client.sounds.SoundManager)this.data).stop();
   }

   @MappedMethod
   public void stop(SoundInstance soundInstance) {
      ((net.minecraft.client.sounds.SoundManager)this.data).stop((net.minecraft.client.resources.sounds.SoundInstance)soundInstance.data);
   }

   @MappedMethod
   public void stop(Identifier identifier, SoundCategory soundSource) {
      ((net.minecraft.client.sounds.SoundManager)this.data).stop((net.minecraft.resources.Identifier)identifier.data, soundSource.data);
   }

   @Nonnull
   @MappedMethod
   public PlayResult play(SoundInstance soundInstance) {
      return ((net.minecraft.client.sounds.SoundManager)this.data).play((net.minecraft.client.resources.sounds.SoundInstance)soundInstance.data);
   }

   @MappedMethod
   public boolean isActive(SoundInstance soundInstance) {
      return ((net.minecraft.client.sounds.SoundManager)this.data).isActive((net.minecraft.client.resources.sounds.SoundInstance)soundInstance.data);
   }

   @MappedMethod
   public boolean isPlaying(SoundInstance sound) {
      return this.isActive(sound);
   }

@MappedMethod
   public SoundManager(GameOptions options) {
      super(new net.minecraft.client.sounds.SoundManager((Options)options.data));
   }
}
