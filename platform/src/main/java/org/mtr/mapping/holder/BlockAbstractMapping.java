package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.storage.loot.LootParams.Builder;
import org.mtr.mapping.annotation.MappedMethod;

@ParametersAreNonnullByDefault
public abstract class BlockAbstractMapping extends net.minecraft.world.level.block.Block {

   @MappedMethod
   protected final void registerDefaultState2(BlockState blockState) {
      super.registerDefaultState((net.minecraft.world.level.block.state.BlockState)blockState.data);
   }

   @MappedMethod
   public void setPlacedBy2(World level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
      super.setPlacedBy(
         (Level)level.data,
         (net.minecraft.core.BlockPos)blockPos.data,
         (net.minecraft.world.level.block.state.BlockState)blockState.data,
         (net.minecraft.world.entity.LivingEntity)livingEntity.data,
         (net.minecraft.world.item.ItemStack)itemStack.data
      );
   }

   @Deprecated
   public final void setPlacedBy(
      Level level,
      net.minecraft.core.BlockPos blockPos,
      net.minecraft.world.level.block.state.BlockState blockState,
      net.minecraft.world.entity.LivingEntity livingEntity,
      net.minecraft.world.item.ItemStack itemStack
   ) {
      this.setPlacedBy2(new World(level), new BlockPos(blockPos), new BlockState(blockState), new LivingEntity(livingEntity), new ItemStack(itemStack));
   }

   @Nonnull
   @MappedMethod
   public Item asItem2() {
      return new Item(super.asItem());
   }

   @Deprecated
   public final net.minecraft.world.item.Item asItem() {
      Item tempData = this.asItem2();
      return tempData == null ? null : (net.minecraft.world.item.Item)tempData.data;
   }

   @Nonnull
   @MappedMethod
   public final BlockState defaultBlockState2() {
      return new BlockState(super.defaultBlockState());
   }

   @Nonnull
   @MappedMethod
   public final String getDescriptionId2() {
      return super.getDescriptionId();
   }

   @MappedMethod
   protected void createBlockStateDefinition2(
      net.minecraft.world.level.block.state.StateDefinition.Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder
   ) {
      super.createBlockStateDefinition(builder);
   }

   @Deprecated
   protected final void createBlockStateDefinition(
      net.minecraft.world.level.block.state.StateDefinition.Builder<net.minecraft.world.level.block.Block, net.minecraft.world.level.block.state.BlockState> builder
   ) {
      this.createBlockStateDefinition2(builder);
   }

   @Nonnull
   @MappedMethod
   public BlockState playerWillDestroy2(World level, BlockPos blockPos, BlockState blockState, PlayerEntity player) {
      return new BlockState(
         super.playerWillDestroy(
            (Level)level.data,
            (net.minecraft.core.BlockPos)blockPos.data,
            (net.minecraft.world.level.block.state.BlockState)blockState.data,
            (Player)player.data
         )
      );
   }

   @Deprecated
   public final net.minecraft.world.level.block.state.BlockState playerWillDestroy(
      Level level, net.minecraft.core.BlockPos blockPos, net.minecraft.world.level.block.state.BlockState blockState, Player player
   ) {
      BlockState tempData = this.playerWillDestroy2(new World(level), new BlockPos(blockPos), new BlockState(blockState), new PlayerEntity(player));
      return tempData == null ? null : (net.minecraft.world.level.block.state.BlockState)tempData.data;
   }

   @Nonnull
   @MappedMethod
   protected Block asBlock2() {
      return new Block(super.asBlock());
   }

   @Deprecated
   protected final net.minecraft.world.level.block.Block asBlock() {
      Block tempData = this.asBlock2();
      return tempData == null ? null : (net.minecraft.world.level.block.Block)tempData.data;
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

   @MappedMethod
   public void animateTick2(BlockState blockState, World level, BlockPos blockPos, Random randomSource) {
      super.animateTick(
         (net.minecraft.world.level.block.state.BlockState)blockState.data,
         (Level)level.data,
         (net.minecraft.core.BlockPos)blockPos.data,
         (RandomSource)randomSource.data
      );
   }

   @Deprecated
   public final void animateTick(
      net.minecraft.world.level.block.state.BlockState blockState, Level level, net.minecraft.core.BlockPos blockPos, RandomSource randomSource
   ) {
      this.animateTick2(new BlockState(blockState), new World(level), new BlockPos(blockPos), new Random(randomSource));
   }

   @Nonnull
   @MappedMethod
   public final BlockState getDefaultState2() {
      return this.defaultBlockState2();
   }

@MappedMethod
   public BlockAbstractMapping(BlockSettings properties) {
      super((Properties)properties.data);
   }
}
