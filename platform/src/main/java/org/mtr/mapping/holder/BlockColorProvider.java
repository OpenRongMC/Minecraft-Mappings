package org.mtr.mapping.holder;
import net.minecraft.client.renderer.block.BlockAndTintGetter;


/** Public MTR tint callback; 26.3 registration uses a BlockTintsFactory adapter. */
@FunctionalInterface
public interface BlockColorProvider {
    int getColor2(BlockState state, BlockRenderView world, BlockPos pos, int tintIndex);

@Deprecated
   default int getColor(
      net.minecraft.world.level.block.state.BlockState blockState, BlockAndTintGetter blockAndTintGetter, net.minecraft.core.BlockPos blockPos, int i
   ) {
      return this.getColor2(new BlockState(blockState), new BlockRenderView(blockAndTintGetter), new BlockPos(blockPos), i);
   }
}
