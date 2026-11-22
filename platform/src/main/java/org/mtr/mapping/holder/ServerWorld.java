package org.mtr.mapping.holder;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ServerWorld extends HolderBase<ServerLevel> {
   public ServerWorld(ServerLevel data) {
      super(data);
   }

   @MappedMethod
   public static ServerWorld cast(HolderBase<?> data) {
      return new ServerWorld((ServerLevel)data.data);
   }

   @Nonnull
   @MappedMethod
   public BlockState getBlockState(BlockPos blockPos) {
      return new BlockState(((ServerLevel)this.data).getBlockState((net.minecraft.core.BlockPos)blockPos.data));
   }

   @MappedMethod
   public void playSound(
      Entity entity, double d, double e, double f, Holder<net.minecraft.sounds.SoundEvent> holder, SoundCategory soundSource, float g, float h
   ) {
      ((ServerLevel)this.data).playSound(entity == null ? null : (net.minecraft.world.entity.Entity)entity.data, d, e, f, holder, soundSource.data, g, h);
   }

   @MappedMethod
   public void playSound(Entity entity, Entity entity2, SoundEvent soundEvent, SoundCategory soundSource, float f, float g) {
      ((ServerLevel)this.data)
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
      ((ServerLevel)this.data)
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
      ((ServerLevel)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data, d, e, f, (net.minecraft.sounds.SoundEvent)soundEvent.data, soundSource.data
         );
   }

   @MappedMethod
   public void playSound(Entity entity, BlockPos blockPos, SoundEvent soundEvent, SoundCategory soundSource, float f, float g) {
      ((ServerLevel)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data,
            (net.minecraft.core.BlockPos)blockPos.data,
            (net.minecraft.sounds.SoundEvent)soundEvent.data,
            soundSource.data,
            f,
            g
         );
   }

   @Nonnull
   @MappedMethod
   public MinecraftServer getServer() {
      return new MinecraftServer(((ServerLevel)this.data).getServer());
   }

   @Nonnull
   @MappedMethod
   public PlayerEntity getPlayerByUUID(UUID uUID) {
      return new PlayerEntity(((ServerLevel)this.data).getPlayerByUUID(uUID));
   }

   @MappedMethod
   public boolean setBlock(BlockPos blockPos, BlockState blockState, int i, int j) {
      return ((ServerLevel)this.data)
         .setBlock((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.state.BlockState)blockState.data, i, j);
   }

   @MappedMethod
   public boolean setBlock(BlockPos blockPos, BlockState blockState, int i) {
      return ((ServerLevel)this.data)
         .setBlock((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.state.BlockState)blockState.data, i);
   }

   @Nullable
   @MappedMethod
   public BlockEntity getBlockEntity(BlockPos blockPos) {
      net.minecraft.world.level.block.entity.BlockEntity be = ((ServerLevel)this.data).getBlockEntity((net.minecraft.core.BlockPos)blockPos.data);
      return be == null ? null : new BlockEntity(be);
   }

   @Nonnull
   @MappedMethod
   public <T extends BlockEntityAbstractMapping> Optional<T> getBlockEntity(BlockPos blockPos, BlockEntityType<T> blockEntityType) {
      return ((ServerLevel)this.data)
         .getBlockEntity((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.entity.BlockEntityType)blockEntityType.data);
   }

   @MappedMethod
   public void playSound(Entity entity, BlockPos blockPos, SoundEvent soundEvent, SoundCategory soundSource) {
      ((ServerLevel)this.data)
         .playSound(
            entity == null ? null : (net.minecraft.world.entity.Entity)entity.data,
            (net.minecraft.core.BlockPos)blockPos.data,
            (net.minecraft.sounds.SoundEvent)soundEvent.data,
            soundSource.data
         );
   }

   @MappedMethod
   public boolean setBlockState(BlockPos pos, BlockState state) {
      return this.setBlock(pos, state, 3);
   }

   @MappedMethod
   public boolean setBlockState(BlockPos pos, BlockState state, int flags) {
      return this.setBlock(pos, state, flags);
   }

   @Nullable
   @MappedMethod
   public PlayerEntity getPlayerByUuid(UUID uuid) {
      return this.getPlayerByUUID(uuid);
   }


}
