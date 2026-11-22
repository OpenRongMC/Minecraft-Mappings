package org.mtr.mapping.holder;

import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.redstone.Orientation;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class World extends HolderBase<Level> {
   public World(Level data) {
      super(data);
   }

   @MappedMethod
   public static World cast(HolderBase<?> data) {
      return new World((Level)data.data);
   }

   @MappedMethod
   public boolean destroyBlock(BlockPos blockPos, boolean bl, Entity entity, int i) {
      return ((Level)this.data).destroyBlock((net.minecraft.core.BlockPos)blockPos.data, bl, (net.minecraft.world.entity.Entity)entity.data, i);
   }

   @MappedMethod
   public void addParticle(ParticleEffect particleOptions, double d, double e, double f, double g, double h, double i) {
      ((Level)this.data).addParticle((ParticleOptions)particleOptions.data, d, e, f, g, h, i);
   }

   @MappedMethod
   public void addParticle(ParticleEffect particleOptions, boolean bl, boolean bl2, double d, double e, double f, double g, double h, double i) {
      ((Level)this.data).addParticle((ParticleOptions)particleOptions.data, bl, bl2, d, e, f, g, h, i);
   }

   @Nonnull
   @MappedMethod
   public FluidState getFluidState(BlockPos blockPos) {
      return new FluidState(((Level)this.data).getFluidState((net.minecraft.core.BlockPos)blockPos.data));
   }

   @MappedMethod
   public boolean isThundering() {
      return ((Level)this.data).isThundering();
   }

   @MappedMethod
   public boolean isRaining() {
      return ((Level)this.data).isRaining();
   }

   @MappedMethod
   public void playLocalSound(BlockPos blockPos, SoundEvent soundEvent, SoundCategory soundSource, float f, float g, boolean bl) {
      ((Level)this.data)
         .playLocalSound((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, f, g, bl);
   }

   @MappedMethod
   public void playLocalSound(double d, double e, double f, SoundEvent soundEvent, SoundCategory soundSource, float g, float h, boolean bl) {
      ((Level)this.data).playLocalSound(d, e, f, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, g, h, bl);
   }

   @MappedMethod
   public void playLocalSound(Entity entity, SoundEvent soundEvent, SoundCategory soundSource, float f, float g) {
      ((Level)this.data)
         .playLocalSound((net.minecraft.world.entity.Entity)entity.data, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data, f, g);
   }

   @MappedMethod
   public boolean destroyBlock(BlockPos blockPos, boolean bl) {
      return ((Level)this.data).destroyBlock((net.minecraft.core.BlockPos)blockPos.data, bl);
   }

   @MappedMethod
   public boolean destroyBlock(BlockPos blockPos, boolean bl, Entity entity) {
      return ((Level)this.data).destroyBlock((net.minecraft.core.BlockPos)blockPos.data, bl, (net.minecraft.world.entity.Entity)entity.data);
   }

   @MappedMethod
   public boolean addFreshEntity(Entity entity) {
      return ((Level)this.data).addFreshEntity((net.minecraft.world.entity.Entity)entity.data);
   }

   @MappedMethod
   public int getHeight(HeightMapType types, int i, int j) {
      return ((Level)this.data).getHeight(types.data, i, j);
   }

   @MappedMethod
   public int getBrightness(LightType lightLayer, BlockPos blockPos) {
      return ((Level)this.data).getBrightness(lightLayer.data, (net.minecraft.core.BlockPos)blockPos.data);
   }

   @MappedMethod
   public long getDayTime() {
      return ((Level)this.data).getOverworldClockTime();
   }

   @MappedMethod
   public int getHeight() {
      return ((Level)this.data).getHeight();
   }

   @MappedMethod
   public int getHeight(HeightMapType types, BlockPos blockPos) {
      return ((Level)this.data).getHeight(types.data, (net.minecraft.core.BlockPos)blockPos.data);
   }

   @Nonnull
   @MappedMethod
   public BlockState getBlockState(BlockPos blockPos) {
      return new BlockState(((Level)this.data).getBlockState((net.minecraft.core.BlockPos)blockPos.data));
   }

   @MappedMethod
   public boolean isClientSide() {
      return ((Level)this.data).isClientSide();
   }

   @MappedMethod
   public void updateNeighborsAtExceptFromFacing(BlockPos blockPos, Block block, Direction direction, Orientation orientation) {
      ((Level)this.data)
         .updateNeighborsAtExceptFromFacing(
            (net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.Block)block.data, direction.data, orientation
         );
   }

   @MappedMethod
   public void levelEvent(int i, BlockPos blockPos, int j) {
      ((Level)this.data).levelEvent(i, (net.minecraft.core.BlockPos)blockPos.data, j);
   }

   @MappedMethod
   public void levelEvent(Entity entity, int i, BlockPos blockPos, int j) {
      ((Level)this.data).levelEvent((net.minecraft.world.entity.Entity)entity.data, i, (net.minecraft.core.BlockPos)blockPos.data, j);
   }

   @MappedMethod
   public boolean setBlock(BlockPos blockPos, BlockState blockState, int i, int j) {
      return ((Level)this.data).setBlock((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.state.BlockState)blockState.data, i, j);
   }

   @MappedMethod
   public boolean setBlock(BlockPos blockPos, BlockState blockState, int i) {
      return ((Level)this.data).setBlock((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.state.BlockState)blockState.data, i);
   }

   @MappedMethod
   public boolean hasChunksAt(BlockPos blockPos, BlockPos blockPos2) {
      return ((Level)this.data).hasChunksAt((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.core.BlockPos)blockPos2.data);
   }

   @MappedMethod
   public boolean hasChunksAt(int i, int j, int k, int l) {
      return ((Level)this.data).hasChunksAt(i, j, k, l);
   }

   @MappedMethod
   public boolean hasChunksAt(int i, int j, int k, int l, int m, int n) {
      return ((Level)this.data).hasChunksAt(i, j, k, l, m, n);
   }

   @Nonnull
   @MappedMethod
   public ChunkManager getChunkSource() {
      return new ChunkManager(((Level)this.data).getChunkSource());
   }

   @Nullable
   @MappedMethod
   public BlockEntity getBlockEntity(BlockPos blockPos) {
      net.minecraft.world.level.block.entity.BlockEntity be = ((Level)this.data).getBlockEntity((net.minecraft.core.BlockPos)blockPos.data);
      return be == null ? null : new BlockEntity(be);
   }

   @Nonnull
   @MappedMethod
   public MinecraftServer getServer() {
      return new MinecraftServer(((Level)this.data).getServer());
   }

   @MappedMethod
   public boolean hasChunk(int i, int j) {
      return ((Level)this.data).hasChunk(i, j);
   }

   @Nonnull
   @MappedMethod
   public <T extends BlockEntityAbstractMapping> Optional<T> getBlockEntity(BlockPos blockPos, BlockEntityType<T> blockEntityType) {
      return ((Level)this.data)
         .getBlockEntity((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.entity.BlockEntityType)blockEntityType.data);
   }

   @Nonnull
   @MappedMethod
   public PlayerEntity getNearestPlayer(double d, double e, double f, double g, Predicate<net.minecraft.world.entity.Entity> predicate) {
      return new PlayerEntity(((Level)this.data).getNearestPlayer(d, e, f, g, predicate));
   }

   @Nonnull
   @MappedMethod
   public PlayerEntity getNearestPlayer(double d, double e, double f, double g, boolean bl) {
      return new PlayerEntity(((Level)this.data).getNearestPlayer(d, e, f, g, bl));
   }

   @Nonnull
   @MappedMethod
   public PlayerEntity getNearestPlayer(Entity entity, double d) {
      return new PlayerEntity(((Level)this.data).getNearestPlayer((net.minecraft.world.entity.Entity)entity.data, d));
   }

   @Nonnull
   @MappedMethod
   public Scoreboard getScoreboard() {
      return new Scoreboard(((Level)this.data).getScoreboard());
   }

   @MappedMethod
   public void playSound(
      Entity entity, double d, double e, double f, Holder<net.minecraft.sounds.SoundEvent> holder, SoundCategory soundSource, float g, float h
   ) {
      ((Level)this.data).playSound(entity == null ? null : (net.minecraft.world.entity.Entity)entity.data, d, e, f, holder, soundSource.data, g, h);
   }

   @MappedMethod
   public void playSound(Entity entity, Entity entity2, SoundEvent soundEvent, SoundCategory soundSource, float f, float g) {
      ((Level)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data,
            (net.minecraft.world.entity.Entity)entity2.data,
            (net.minecraft.sounds.SoundEvent)soundEvent.data,
            soundSource.data,
            f,
            g
         );
   }

   @MappedMethod
   public void playSound(Entity entity, double d, double e, double f, SoundEvent soundEvent, SoundCategory soundSource, float g, float h) {
      ((Level)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data,
            d,
            e,
            f,
            (net.minecraft.sounds.SoundEvent)soundEvent.data,
            soundSource.data,
            g,
            h
         );
   }

   @MappedMethod
   public void playSound(Entity entity, double d, double e, double f, SoundEvent soundEvent, SoundCategory soundSource) {
      ((Level)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data, d, e, f, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data
         );
   }

   @MappedMethod
   public void playSound(Entity entity, BlockPos blockPos, SoundEvent soundEvent, SoundCategory soundSource, float f, float g) {
      ((Level)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data,
            (net.minecraft.core.BlockPos)blockPos.data,
            (net.minecraft.sounds.SoundEvent)soundEvent.data,
            soundSource.data,
            f,
            g
         );
   }

   @MappedMethod
   public void playSound(Entity entity, BlockPos blockPos, SoundEvent soundEvent, SoundCategory soundSource) {
      ((Level)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data,
            (net.minecraft.core.BlockPos)blockPos.data,
            (net.minecraft.sounds.SoundEvent)soundEvent.data,
            soundSource.data
         );
   }

   @MappedMethod
   public boolean hasSignal(BlockPos blockPos, Direction direction) {
      return ((Level)this.data).hasSignal((net.minecraft.core.BlockPos)blockPos.data, direction.data);
   }

   @MappedMethod
   public void updateNeighborsAt(BlockPos blockPos, Block block, Orientation orientation) {
      ((Level)this.data).updateNeighborsAt((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.Block)block.data, orientation);
   }

   @MappedMethod
   public void updateNeighborsAt(BlockPos blockPos, Block block) {
      ((Level)this.data).updateNeighborsAt((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.Block)block.data);
   }

   @MappedMethod
   public boolean isClient() {
      return this.isClientSide();
   }

   @MappedMethod
   public boolean setBlockState(BlockPos pos, BlockState state) {
      return this.setBlock(pos, state, 3);
   }

   @MappedMethod
   public boolean setBlockState(BlockPos pos, BlockState state, int flags) {
      return this.setBlock(pos, state, flags);
   }

   @MappedMethod
   public boolean setBlockState(BlockPos pos, BlockState state, int flags, int maxUpdateDepth) {
      return this.setBlock(pos, state, flags, maxUpdateDepth);
   }

   @MappedMethod
   public boolean isEmittingRedstonePower(BlockPos pos, Direction direction) {
      return this.hasSignal(pos, direction);
   }

   @MappedMethod
   public void syncWorldEvent(@Nullable PlayerEntity player, int eventId, BlockPos pos, int data) {
      this.levelEvent(player == null ? null : new Entity((net.minecraft.world.entity.Entity)player.data), eventId, pos, data);
   }

   @MappedMethod
   public void syncWorldEvent(int eventId, BlockPos pos, int data) {
      this.levelEvent(eventId, pos, data);
   }

   @MappedMethod
   public void updateNeighbors(BlockPos pos, Block block) {
      this.updateNeighborsAt(pos, block);
   }

   @MappedMethod
   public boolean getIsClientMapped() {
      return this.isClientSide();
   }

   @MappedMethod
   public int getLightLevel(LightType type, BlockPos pos) {
      return this.getBrightness(type, pos);
   }

   @MappedMethod
   public boolean isRegionLoaded(BlockPos a, BlockPos b) {
      return this.hasChunksAt(a, b);
   }

   @MappedMethod
   public boolean isRegionLoaded(int x1, int y1, int z1, int x2, int y2, int z2) {
      return this.hasChunksAt(x1, y1, z1, x2, y2, z2);
   }

   @Nonnull
   @MappedMethod
   public ChunkManager getChunkManager() {
      return this.getChunkSource();
   }

   @MappedMethod
   public boolean isChunkLoaded(int chunkX, int chunkZ) {
      return this.hasChunk(chunkX, chunkZ);
   }

   @MappedMethod
   public int getTopY(HeightMapType type, int x, int z) {
      return this.getHeight(type, x, z);
   }

   @MappedMethod
   public boolean breakBlock(BlockPos pos, boolean drop) {
      return this.destroyBlock(pos, drop);
   }

   @MappedMethod
   public boolean breakBlock(BlockPos pos, boolean drop, @Nullable Entity entity) {
      return this.destroyBlock(pos, drop, entity);
   }

   @MappedMethod
   public void playSound(double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean useDistance) {
      this.playLocalSound(x, y, z, sound, category, volume, pitch, useDistance);
   }
}
