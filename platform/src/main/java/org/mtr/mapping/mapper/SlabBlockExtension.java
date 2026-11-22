package org.mtr.mapping.mapper;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.ItemPlacementContext;
import org.mtr.mapping.holder.Property;
import org.mtr.mapping.holder.Random;
import org.mtr.mapping.holder.SlabBlockAbstractMapping;
import org.mtr.mapping.holder.SlabType;
import org.mtr.mapping.holder.WorldAccess;
import org.mtr.mapping.tool.HolderBase;

public class SlabBlockExtension extends SlabBlockAbstractMapping implements BlockHelper {
   @MappedMethod
   public SlabBlockExtension(BlockSettings blockSettings) {
      super(blockSettings);
   }

   @Deprecated
   @Override
   protected void createBlockStateDefinition2(Builder<Block, BlockState> builder) {
      this.appendPropertiesHelper(builder);
   }

   @MappedMethod
   @Override
   public void addBlockProperties(List<HolderBase<?>> properties) {
      properties.add(new Property(TYPE));
      properties.add(new Property(WATERLOGGED));
   }

   @MappedMethod
   public static SlabType getType(org.mtr.mapping.holder.BlockState state) {
      return SlabType.convert((net.minecraft.world.level.block.state.properties.SlabType)((BlockState)state.data).getValue(TYPE));
   }

   @Nonnull
   @MappedMethod
   public org.mtr.mapping.holder.BlockState getDefaultState2() {
      return this.defaultBlockState2();
   }

   @Nullable
   @MappedMethod
   public org.mtr.mapping.holder.BlockState getPlacementState2(ItemPlacementContext ctx) {
      return super.getStateForPlacement2(ctx);
   }

   @Nullable
   @Deprecated
   @Override
   public org.mtr.mapping.holder.BlockState getStateForPlacement2(ItemPlacementContext ctx) {
      return this.getPlacementState2(ctx);
   }

   @Nonnull
   @MappedMethod
   public org.mtr.mapping.holder.BlockState getStateForNeighborUpdate2(
      org.mtr.mapping.holder.BlockState state,
      Direction direction,
      org.mtr.mapping.holder.BlockState neighborState,
      WorldAccess world,
      BlockPos pos,
      BlockPos neighborPos
   ) {
      return super.updateShape2(
         state,
         (LevelReader)world.data,
         (ScheduledTickAccess)world.data,
         pos,
         direction,
         neighborPos,
         neighborState,
         new Random(((LevelAccessor)world.data).getRandom())
      );
   }

   @Nonnull
   @Deprecated
   @Override
   protected org.mtr.mapping.holder.BlockState updateShape2(
      org.mtr.mapping.holder.BlockState state,
      LevelReader levelReader,
      ScheduledTickAccess scheduledTickAccess,
      BlockPos pos,
      Direction direction,
      BlockPos neighborPos,
      org.mtr.mapping.holder.BlockState neighborState,
      Random random
   ) {
      return levelReader instanceof LevelAccessor levelAccessor
         ? this.getStateForNeighborUpdate2(state, direction, neighborState, new WorldAccess(levelAccessor), pos, neighborPos)
         : super.updateShape2(state, levelReader, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
   }

   @MappedMethod
   public boolean isSideInvisible2(org.mtr.mapping.holder.BlockState state, org.mtr.mapping.holder.BlockState neighborState, Direction direction) {
      return super.skipRendering((BlockState)state.data, (BlockState)neighborState.data, direction.data);
   }

   @Deprecated
   protected boolean skipRendering(BlockState state, BlockState neighborState, net.minecraft.core.Direction direction) {
      return this.isSideInvisible2(
         new org.mtr.mapping.holder.BlockState(state), new org.mtr.mapping.holder.BlockState(neighborState), Direction.convert(direction)
      );
   }

   @MappedMethod
   public float getAmbientOcclusionLightLevel2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos) {
      return super.getShadeBrightness((BlockState)state.data, (BlockGetter)world.data, (net.minecraft.core.BlockPos)pos.data);
   }

   @Deprecated
   protected float getShadeBrightness(BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos) {
      return this.getAmbientOcclusionLightLevel2(new org.mtr.mapping.holder.BlockState(state), new BlockView(level), new BlockPos(pos));
   }

   @MappedMethod
   public boolean isTranslucent2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos) {
      return super.propagatesSkylightDown((BlockState)state.data);
   }

   @Deprecated
   protected boolean propagatesSkylightDown(BlockState state) {
      return this.isTranslucent2(new org.mtr.mapping.holder.BlockState(state), new BlockView(EmptyBlockGetter.INSTANCE), BlockPos.of(0L));
   }

   @Nonnull
   @MappedMethod
   public String getTranslationKey2() {
      return this.getDescriptionId2();
   }
}
