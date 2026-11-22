package org.mtr.mapping.holder;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockBehaviour.BlockStateBase;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockState extends HolderBase<net.minecraft.world.level.block.state.BlockState> {
   public BlockState(net.minecraft.world.level.block.state.BlockState data) {
      super(data);
   }

   @MappedMethod
   public boolean hasProperty(Property<?> property) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data)
         .hasProperty((net.minecraft.world.level.block.state.properties.Property)property.data);
   }

   @MappedMethod
   public boolean is(TagKey<net.minecraft.world.level.block.Block> tagKey, Predicate<BlockStateBase> predicate) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).is(tagKey, predicate);
   }

   @MappedMethod
   public boolean is(HolderSet<net.minecraft.world.level.block.Block> holderSet) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).is(holderSet);
   }

   @MappedMethod
   public boolean is(Holder<net.minecraft.world.level.block.Block> holder) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).is(holder);
   }

   @MappedMethod
   public boolean is(Block block) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).is((net.minecraft.world.level.block.Block)block.data);
   }

   @MappedMethod
   public boolean is(ResourceKey<net.minecraft.world.level.block.Block> resourceKey) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).is(resourceKey);
   }

   @MappedMethod
   public boolean is(TagKey<net.minecraft.world.level.block.Block> tagKey) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).is(tagKey);
   }

   @MappedMethod
   public final void updateNeighbourShapes(WorldAccess levelAccessor, BlockPos blockPos, int i) {
      ((net.minecraft.world.level.block.state.BlockState)this.data)
         .updateNeighbourShapes((LevelAccessor)levelAccessor.data, (net.minecraft.core.BlockPos)blockPos.data, i);
   }

   @MappedMethod
   public final void updateNeighbourShapes(WorldAccess levelAccessor, BlockPos blockPos, int i, int j) {
      ((net.minecraft.world.level.block.state.BlockState)this.data)
         .updateNeighbourShapes((LevelAccessor)levelAccessor.data, (net.minecraft.core.BlockPos)blockPos.data, i, j);
   }

   @MappedMethod
   public boolean isAir() {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).isAir();
   }

   @Nonnull
   @MappedMethod
   public <T extends Comparable<T>> T getValue(Property<T> property) {
      return (T)((net.minecraft.world.level.block.state.BlockState)this.data)
         .getValue((net.minecraft.world.level.block.state.properties.Property)property.data);
   }

   @Nonnull
   @MappedMethod
   public <T extends Comparable<T>, V extends T> BlockState setValue(Property<T> property, V comparable) {
      return new BlockState(
         (net.minecraft.world.level.block.state.BlockState)((net.minecraft.world.level.block.state.BlockState)this.data)
            .setValue((net.minecraft.world.level.block.state.properties.Property)property.data, comparable)
      );
   }

   @Nonnull
   @MappedMethod
   public Block getBlock() {
      return new Block(((net.minecraft.world.level.block.state.BlockState)this.data).getBlock());
   }

   @MappedMethod
   public boolean canBeReplaced() {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).canBeReplaced();
   }

   @MappedMethod
   public boolean canBeReplaced(Fluid fluid) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).canBeReplaced((net.minecraft.world.level.material.Fluid)fluid.data);
   }

   @MappedMethod
   public boolean canBeReplaced(ItemPlacementContext blockPlaceContext) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).canBeReplaced((BlockPlaceContext)blockPlaceContext.data);
   }

   @MappedMethod
   public boolean canOcclude() {
      return ((net.minecraft.world.level.block.state.BlockState)this.data).canOcclude();
   }

   @MappedMethod
   public int getDirectSignal(BlockView blockGetter, BlockPos blockPos, Direction direction) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data)
         .getDirectSignal((BlockGetter)blockGetter.data, (net.minecraft.core.BlockPos)blockPos.data, direction.data);
   }

   @Nonnull
   @MappedMethod
   public <T extends Comparable<T>> BlockState cycle(Property<T> property) {
      return new BlockState(
         (net.minecraft.world.level.block.state.BlockState)((net.minecraft.world.level.block.state.BlockState)this.data)
            .cycle((net.minecraft.world.level.block.state.properties.Property)property.data)
      );
   }

   @MappedMethod
   public boolean isFaceSturdy(BlockView blockGetter, BlockPos blockPos, Direction direction, SupportType supportType) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data)
         .isFaceSturdy((BlockGetter)blockGetter.data, (net.minecraft.core.BlockPos)blockPos.data, direction.data, supportType);
   }

   @MappedMethod
   public boolean isFaceSturdy(BlockView blockGetter, BlockPos blockPos, Direction direction) {
      return ((net.minecraft.world.level.block.state.BlockState)this.data)
         .isFaceSturdy((BlockGetter)blockGetter.data, (net.minecraft.core.BlockPos)blockPos.data, direction.data);
   }

   @MappedMethod
   public boolean isOf(Block block) {
      return this.is(block);
   }

   @Nonnull
   @MappedMethod
   public <T extends Comparable<T>, V extends T> BlockState with(Property<T> property, V value) {
      return this.setValue(property, value);
   }

   @Nonnull
   @MappedMethod
   public <T extends Comparable<T>> T get(Property<T> property) {
      return this.getValue(property);
   }

   @MappedMethod
   public <T extends Comparable<T>> boolean contains(Property<T> property) {
      return this.hasProperty(property);
   }

   @MappedMethod
   public boolean canReplace(ItemPlacementContext ctx) {
      return this.canBeReplaced(ctx);
   }

   @MappedMethod
   public final void updateNeighbors(WorldAccess world, BlockPos pos, int flags) {
      this.updateNeighbourShapes(world, pos, flags);
   }

   @MappedMethod
   public boolean isOpaqueFullCube(BlockView world, BlockPos pos) {
      return this.canOcclude();
   }

   @MappedMethod
   public boolean isSideSolidFullSquare(BlockView world, BlockPos pos, Direction direction) {
      return this.isFaceSturdy(world, pos, direction);
   }


}
