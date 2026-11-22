package org.mtr.mapping.mapper;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockEntityAbstractMapping;
import org.mtr.mapping.holder.BlockEntityType;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockState;

public abstract class BlockEntityExtension extends BlockEntityAbstractMapping {
   @MappedMethod
   public BlockEntityExtension(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState) {
      super(type, blockPos, blockState);
   }

   @Deprecated
   @Override
   protected void saveAdditional2(ValueOutput output) {
      super.saveAdditional2(output);
      CompoundTag extra = new CompoundTag();
      this.writeCompoundTag(new org.mtr.mapping.holder.CompoundTag(extra));
      output.store("mtrData", CompoundTag.CODEC, extra);
   }

   @Deprecated
   @Override
   protected void loadAdditional2(ValueInput input) {
      super.loadAdditional2(input);
      input.read("mtrData", CompoundTag.CODEC).ifPresent(tag -> this.readCompoundTag(new org.mtr.mapping.holder.CompoundTag(tag)));
   }

   @MappedMethod
   public void writeCompoundTag(org.mtr.mapping.holder.CompoundTag compoundTag) {
   }

   @MappedMethod
   public void readCompoundTag(org.mtr.mapping.holder.CompoundTag compoundTag) {
   }

   @Deprecated
   @Override
   public Packet<ClientGamePacketListener> getUpdatePacket2() {
      return ClientboundBlockEntityDataPacket.create(this);
   }

   @Deprecated
   @Override
   public org.mtr.mapping.holder.CompoundTag getUpdateTag2(Provider provider) {
      org.mtr.mapping.holder.CompoundTag tag = super.getUpdateTag2(provider);
      CompoundTag extra = new CompoundTag();
      this.writeCompoundTag(new org.mtr.mapping.holder.CompoundTag(extra));
      if (!extra.isEmpty()) {
         ((CompoundTag)tag.data).put("mtrData", extra);
      }

      return tag;
   }

   @MappedMethod
   public void blockEntityTick() {
   }

   @MappedMethod
   @Override
   public void setChanged2() {
      super.setChanged2();
      Level world = this.getLevel();
      net.minecraft.world.level.block.state.BlockState blockState = this.getBlockState();
      if (world != null && !world.isClientSide() && blockState != null) {
         world.sendBlockUpdated(this.getBlockPos(), blockState, blockState, 2);
      }
   }

   @MappedMethod
   public void markDirty2() {
      this.setChanged2();
   }

   @MappedMethod
   public void markRemoved2() {
      this.setRemoved2();
   }

   @MappedMethod
   public double getRenderDistance2() {
      return 0.0;
   }
}
