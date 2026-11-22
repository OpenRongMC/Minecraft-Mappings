package org.mtr.mapping.holder;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.BlockGetter;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockView extends HolderBase<BlockGetter> {
   public BlockView(BlockGetter data) {
      super(data);
   }

   @MappedMethod
   public static BlockView cast(HolderBase<?> data) {
      return new BlockView((BlockGetter)data.data);
   }

   @Nullable
   @MappedMethod
   public BlockEntity getBlockEntity(BlockPos blockPos) {
      net.minecraft.world.level.block.entity.BlockEntity be = ((BlockGetter)this.data).getBlockEntity((net.minecraft.core.BlockPos)blockPos.data);
      return be == null ? null : new BlockEntity(be);
   }

   @Nonnull
   @MappedMethod
   public <T extends BlockEntityAbstractMapping> Optional<T> getBlockEntity(BlockPos blockPos, BlockEntityType<T> blockEntityType) {
      return ((BlockGetter)this.data)
         .getBlockEntity((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.entity.BlockEntityType)blockEntityType.data);
   }

   @Nonnull
   @MappedMethod
   public BlockState getBlockState(BlockPos blockPos) {
      return new BlockState(((BlockGetter)this.data).getBlockState((net.minecraft.core.BlockPos)blockPos.data));
   }

}
