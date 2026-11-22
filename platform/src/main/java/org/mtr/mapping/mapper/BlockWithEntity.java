package org.mtr.mapping.mapper;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.mtr.mapping.annotation.MappedMethod;

public interface BlockWithEntity extends EntityBlock {
   @Deprecated
   default BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
      return this.createBlockEntity(new org.mtr.mapping.holder.BlockPos(pos), new org.mtr.mapping.holder.BlockState(state));
   }

   @MappedMethod
   BlockEntityExtension createBlockEntity(org.mtr.mapping.holder.BlockPos var1, org.mtr.mapping.holder.BlockState var2);

   @Deprecated
   default <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
      return (world1, pos, state1, blockEntity) -> {
         if (blockEntity.getType() == type && blockEntity instanceof BlockEntityExtension) {
            ((BlockEntityExtension)blockEntity).blockEntityTick();
         }
      };
   }
}
