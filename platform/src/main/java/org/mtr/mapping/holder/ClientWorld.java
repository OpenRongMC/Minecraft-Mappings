package org.mtr.mapping.holder;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.multiplayer.ClientLevel;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ClientWorld extends HolderBase<ClientLevel> {
   public ClientWorld(ClientLevel data) {
      super(data);
   }

   @MappedMethod
   public boolean equals(@Nullable Object data) {
      return data instanceof HolderBase && ((ClientLevel)this.data).equals(((HolderBase)data).data);
   }

   @MappedMethod
   public boolean isThundering() {
      return ((ClientLevel)this.data).isThundering();
   }

   @MappedMethod
   public boolean isRaining() {
      return ((ClientLevel)this.data).isRaining();
   }

   @MappedMethod
   public void playLocalSound(BlockPos blockPos, SoundEvent soundEvent, SoundCategory soundSource, float f, float g, boolean bl) {
      ((ClientLevel)this.data)
         .playLocalSound((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, f, g, bl);
   }

   @Nonnull
   @MappedMethod
   public PlayerEntity getPlayerByUUID(UUID uUID) {
      return new PlayerEntity(((ClientLevel)this.data).getPlayerByUUID(uUID));
   }

   @Nonnull
   @MappedMethod
   public ClientChunkManager getChunkSource() {
      return new ClientChunkManager(((ClientLevel)this.data).getChunkSource());
   }

   @MappedMethod
   public int getBrightness(LightType lightLayer, BlockPos blockPos) {
      return ((ClientLevel)this.data).getBrightness(lightLayer.data, (net.minecraft.core.BlockPos)blockPos.data);
   }

   @Nonnull
   @MappedMethod
   public BlockState getBlockState(BlockPos blockPos) {
      return new BlockState(((ClientLevel)this.data).getBlockState((net.minecraft.core.BlockPos)blockPos.data));
   }

   @MappedMethod
   public void playLocalSound(double d, double e, double f, SoundEvent soundEvent, SoundCategory soundSource, float g, float h, boolean bl) {
      ((ClientLevel)this.data).playLocalSound(d, e, f, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, g, h, bl);
   }

   @MappedMethod
   public void playLocalSound(Entity entity, SoundEvent soundEvent, SoundCategory soundSource, float f, float g) {
      ((ClientLevel)this.data)
         .playLocalSound((net.minecraft.world.entity.Entity)entity.data, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, f, g);
   }

   @Nullable
   @MappedMethod
   public BlockEntity getBlockEntity(BlockPos blockPos) {
      net.minecraft.world.level.block.entity.BlockEntity be = ((ClientLevel)this.data).getBlockEntity((net.minecraft.core.BlockPos)blockPos.data);
      return be == null ? null : new BlockEntity(be);
   }

   @Nonnull
   @MappedMethod
   public <T extends BlockEntityAbstractMapping> Optional<T> getBlockEntity(BlockPos blockPos, BlockEntityType<T> blockEntityType) {
      return ((ClientLevel)this.data)
         .getBlockEntity((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.entity.BlockEntityType)blockEntityType.data);
   }

   @MappedMethod
   public int getLightLevel(LightType type, BlockPos pos) {
      return this.getBrightness(type, pos);
   }

   @Nullable
   @MappedMethod
   public PlayerEntity getPlayerByUuid(UUID uuid) {
      return this.getPlayerByUUID(uuid);
   }

   @MappedMethod
   public void playSoundAtBlockCenter(BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean useDistance) {
      this.playLocalSound(pos, sound, category, volume, pitch, useDistance);
   }


}
