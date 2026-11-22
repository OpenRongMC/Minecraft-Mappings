package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class SlabBlockAbstractMapping extends net.minecraft.world.level.block.SlabBlock {

   @MappedMethod
   protected void createBlockStateDefinition2(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      super.createBlockStateDefinition(builder);
   }

   @Deprecated
   protected final void createBlockStateDefinition(Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder) {
      this.createBlockStateDefinition2(builder);
   }

   @Nonnull
   @MappedMethod
   public final String getDescriptionId2() {
      return super.getDescriptionId();
   }

   @Nonnull
   @MappedMethod
   protected BlockState updateShape2(
      BlockState blockState,
      LevelReader levelReader,
      ScheduledTickAccess scheduledTickAccess,
      BlockPos blockPos,
      Direction direction,
      BlockPos blockPos2,
      BlockState blockState2,
      Random randomSource
   ) {
      return new BlockState(
         super.updateShape(
            (net.minecraft.world.level.block.state.BlockState)blockState.data,
            levelReader,
            scheduledTickAccess,
            (net.minecraft.core.BlockPos)blockPos.data,
            direction.data,
            (net.minecraft.core.BlockPos)blockPos2.data,
            (net.minecraft.world.level.block.state.BlockState)blockState2.data,
            (RandomSource)randomSource.data
         )
      );
   }

   @Deprecated
   protected final net.minecraft.world.level.block.state.BlockState updateShape(
      net.minecraft.world.level.block.state.BlockState blockState,
      LevelReader levelReader,
      ScheduledTickAccess scheduledTickAccess,
      net.minecraft.core.BlockPos blockPos,
      net.minecraft.core.Direction direction,
      net.minecraft.core.BlockPos blockPos2,
      net.minecraft.world.level.block.state.BlockState blockState2,
      RandomSource randomSource
   ) {
      BlockState tempData = this.updateShape2(
         new BlockState(blockState),
         levelReader,
         scheduledTickAccess,
         new BlockPos(blockPos),
         Direction.convert(direction),
         new BlockPos(blockPos2),
         new BlockState(blockState2),
         new Random(randomSource)
      );
      return tempData == null ? null : (net.minecraft.world.level.block.state.BlockState)tempData.data;
   }

   @Nonnull
   @MappedMethod
   public BlockState getStateForPlacement2(ItemPlacementContext blockPlaceContext) {
      return new BlockState(super.getStateForPlacement((BlockPlaceContext)blockPlaceContext.data));
   }

   @Deprecated
   public final net.minecraft.world.level.block.state.BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
      BlockState tempData = this.getStateForPlacement2(new ItemPlacementContext(blockPlaceContext));
      return tempData == null ? null : (net.minecraft.world.level.block.state.BlockState)tempData.data;
   }

   @Nonnull
   @MappedMethod
   public final BlockState defaultBlockState2() {
      return new BlockState(super.defaultBlockState());
   }

@MappedMethod
   public SlabBlockAbstractMapping(BlockSettings properties) {
      super((Properties)properties.data);
   }
}
