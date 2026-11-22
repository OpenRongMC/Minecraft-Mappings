package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class BlockEntityAbstractMapping extends net.minecraft.world.level.block.entity.BlockEntity {

   @Nonnull
   @MappedMethod
   public BlockState getBlockState2() {
      return new BlockState(super.getBlockState());
   }

   @Deprecated
   public final net.minecraft.world.level.block.state.BlockState getBlockState() {
      BlockState tempData = this.getBlockState2();
      return tempData == null ? null : (net.minecraft.world.level.block.state.BlockState)tempData.data;
   }

   @MappedMethod
   public void setChanged2() {
      super.setChanged();
   }

   @Deprecated
   public final void setChanged() {
      this.setChanged2();
   }

   @MappedMethod
   protected static void setChanged2(World level, BlockPos blockPos, BlockState blockState) {
      net.minecraft.world.level.block.entity.BlockEntity.setChanged(
         (Level)level.data, (net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.state.BlockState)blockState.data
      );
   }

   @MappedMethod
   protected void loadAdditional2(ValueInput valueInput) {
      super.loadAdditional(valueInput);
   }

   @Deprecated
   protected final void loadAdditional(ValueInput valueInput) {
      this.loadAdditional2(valueInput);
   }

   @Nonnull
   @MappedMethod
   public Packet<ClientGamePacketListener> getUpdatePacket2() {
      return super.getUpdatePacket();
   }

   @Deprecated
   public final Packet<ClientGamePacketListener> getUpdatePacket() {
      return this.getUpdatePacket2();
   }

   @MappedMethod
   protected void saveAdditional2(ValueOutput valueOutput) {
      super.saveAdditional(valueOutput);
   }

   @Deprecated
   protected final void saveAdditional(ValueOutput valueOutput) {
      this.saveAdditional2(valueOutput);
   }

   @Nonnull
   @MappedMethod
   public CompoundTag getUpdateTag2(Provider provider) {
      return new CompoundTag(super.getUpdateTag(provider));
   }

   @Deprecated
   public final net.minecraft.nbt.CompoundTag getUpdateTag(Provider provider) {
      CompoundTag tempData = this.getUpdateTag2(provider);
      return tempData == null ? null : (net.minecraft.nbt.CompoundTag)tempData.data;
   }

   @Nonnull
   @MappedMethod
   public World getLevel2() {
      return new World(super.getLevel());
   }

   @Deprecated
   public final Level getLevel() {
      World tempData = this.getLevel2();
      return tempData == null ? null : (Level)tempData.data;
   }

   @MappedMethod
   public void setRemoved2() {
      super.setRemoved();
   }

   @Deprecated
   public final void setRemoved() {
      this.setRemoved2();
   }

   @Nonnull
   @MappedMethod
   public BlockPos getBlockPos2() {
      return new BlockPos(super.getBlockPos());
   }

   @Deprecated
   public final net.minecraft.core.BlockPos getBlockPos() {
      BlockPos tempData = this.getBlockPos2();
      return tempData == null ? null : (net.minecraft.core.BlockPos)tempData.data;
   }

   @MappedMethod
   public boolean isRemoved2() {
      return super.isRemoved();
   }

   @Deprecated
   public final boolean isRemoved() {
      return this.isRemoved2();
   }

   @Nullable
   @MappedMethod
   public World getWorld2() {
      return this.getLevel2();
   }

   @Nonnull
   @MappedMethod
   public BlockPos getPos2() {
      return this.getBlockPos2();
   }

   @Nonnull
   @MappedMethod
   public BlockState getCachedState2() {
      return this.getBlockState2();
   }

@MappedMethod
   public BlockEntityAbstractMapping(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
      super(
         (net.minecraft.world.level.block.entity.BlockEntityType)blockEntityType.data,
         (net.minecraft.core.BlockPos)blockPos.data,
         (net.minecraft.world.level.block.state.BlockState)blockState.data
      );
   }
}
