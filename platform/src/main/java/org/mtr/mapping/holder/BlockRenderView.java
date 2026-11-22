package org.mtr.mapping.holder;

import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockRenderView extends HolderBase<BlockAndTintGetter> {
   public BlockRenderView(BlockAndTintGetter data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public BlockEntity getBlockEntity(BlockPos blockPos) {
      return new BlockEntity(((BlockAndTintGetter)this.data).getBlockEntity((net.minecraft.core.BlockPos)blockPos.data));
   }

   @Nonnull
   @MappedMethod
   public <T extends BlockEntityAbstractMapping> Optional<T> getBlockEntity(BlockPos blockPos, BlockEntityType<T> blockEntityType) {
      return ((BlockAndTintGetter)this.data)
         .getBlockEntity((net.minecraft.core.BlockPos)blockPos.data, (net.minecraft.world.level.block.entity.BlockEntityType)blockEntityType.data);
   }

}
