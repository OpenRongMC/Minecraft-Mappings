package org.mtr.mapping.mapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResult.Fail;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.holder.ActionResult;
import org.mtr.mapping.holder.BlockAbstractMapping;
import org.mtr.mapping.holder.BlockHitResult;
import org.mtr.mapping.holder.BlockPos;
import org.mtr.mapping.holder.BlockRenderType;
import org.mtr.mapping.holder.BlockSettings;
import org.mtr.mapping.holder.BlockView;
import org.mtr.mapping.holder.Direction;
import org.mtr.mapping.holder.Entity;
import org.mtr.mapping.holder.Fluid;
import org.mtr.mapping.holder.FluidState;
import org.mtr.mapping.holder.Hand;
import org.mtr.mapping.holder.ItemPlacementContext;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.holder.LivingEntity;
import org.mtr.mapping.holder.PlayerEntity;
import org.mtr.mapping.holder.Random;
import org.mtr.mapping.holder.ServerWorld;
import org.mtr.mapping.holder.ShapeContext;
import org.mtr.mapping.holder.VoxelShape;
import org.mtr.mapping.holder.World;
import org.mtr.mapping.holder.WorldAccess;

public class BlockExtension extends BlockAbstractMapping implements BlockHelper {
   @MappedMethod
   public BlockExtension(BlockSettings blockSettings) {
      super(blockSettings);
   }

   @MappedMethod
   public void onBreak2(World world, BlockPos pos, org.mtr.mapping.holder.BlockState state, PlayerEntity player) {
      super.playerWillDestroy2(world, pos, state, player);
   }

   @Deprecated
   @Override
   public org.mtr.mapping.holder.BlockState playerWillDestroy2(World world, BlockPos pos, org.mtr.mapping.holder.BlockState state, PlayerEntity player) {
      this.onBreak2(world, pos, state, player);
      return super.playerWillDestroy2(world, pos, state, player);
   }

   @Nonnull
   @MappedMethod
   public ItemStack getPickStack2(BlockView world, BlockPos pos, org.mtr.mapping.holder.BlockState state) {
      return new ItemStack(((Item)this.asItem2().data).getDefaultInstance());
   }

   @Deprecated
   @Override
   protected void createBlockStateDefinition2(Builder<Block, BlockState> builder) {
      this.appendPropertiesHelper(builder);
   }

   @MappedMethod
   public static void scheduleBlockTick(World world, BlockPos pos, org.mtr.mapping.holder.Block block, int ticks) {
      ((Level)world.data).scheduleTick((net.minecraft.core.BlockPos)pos.data, (Block)block.data, ticks);
   }

   @MappedMethod
   public static boolean hasScheduledBlockTick(World world, BlockPos pos, org.mtr.mapping.holder.Block block) {
      return ((Level)world.data).getBlockTicks().hasScheduledTick((net.minecraft.core.BlockPos)pos.data, (Block)block.data);
   }

   @MappedMethod
   public static void scheduleFluidTick(World world, BlockPos pos, Fluid fluid, int ticks) {
      ((Level)world.data).scheduleTick((net.minecraft.core.BlockPos)pos.data, (net.minecraft.world.level.material.Fluid)fluid.data, ticks);
   }

   @MappedMethod
   public static boolean hasScheduledFluidTick(World world, BlockPos pos, Fluid fluid) {
      return ((Level)world.data).getFluidTicks().hasScheduledTick((net.minecraft.core.BlockPos)pos.data, (net.minecraft.world.level.material.Fluid)fluid.data);
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
      return new org.mtr.mapping.holder.BlockState(
         super.updateShape(
            (BlockState)state.data,
            (LevelReader)world.data,
            (ScheduledTickAccess)world.data,
            (net.minecraft.core.BlockPos)pos.data,
            direction.data,
            (net.minecraft.core.BlockPos)neighborPos.data,
            (BlockState)neighborState.data,
            ((LevelAccessor)world.data).getRandom()
         )
      );
   }

   @Deprecated
   protected BlockState updateShape(
      BlockState state,
      LevelReader level,
      ScheduledTickAccess scheduledTickAccess,
      net.minecraft.core.BlockPos pos,
      net.minecraft.core.Direction direction,
      net.minecraft.core.BlockPos neighborPos,
      BlockState neighborState,
      RandomSource random
   ) {
      return level instanceof LevelAccessor levelAccessor
         ? (BlockState)this.getStateForNeighborUpdate2(
               new org.mtr.mapping.holder.BlockState(state),
               Direction.convert(direction),
               new org.mtr.mapping.holder.BlockState(neighborState),
               new WorldAccess(levelAccessor),
               new BlockPos(pos),
               new BlockPos(neighborPos)
            )
            .data
         : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
   }

   @Nonnull
   @MappedMethod
   public VoxelShape getCollisionShape2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return new VoxelShape(
         super.getCollisionShape((BlockState)state.data, (BlockGetter)world.data, (net.minecraft.core.BlockPos)pos.data, (CollisionContext)context.data)
      );
   }

   @Deprecated
   protected net.minecraft.world.phys.shapes.VoxelShape getCollisionShape(
      BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos, CollisionContext context
   ) {
      return (net.minecraft.world.phys.shapes.VoxelShape)this.getCollisionShape2(
            new org.mtr.mapping.holder.BlockState(state), new BlockView(level), new BlockPos(pos), new ShapeContext(context)
         )
         .data;
   }

   @Nonnull
   @MappedMethod
   public VoxelShape getOutlineShape2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return new VoxelShape(
         super.getShape((BlockState)state.data, (BlockGetter)world.data, (net.minecraft.core.BlockPos)pos.data, (CollisionContext)context.data)
      );
   }

   @Deprecated
   protected net.minecraft.world.phys.shapes.VoxelShape getShape(BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos, CollisionContext context) {
      return (net.minecraft.world.phys.shapes.VoxelShape)this.getOutlineShape2(
            new org.mtr.mapping.holder.BlockState(state), new BlockView(level), new BlockPos(pos), new ShapeContext(context)
         )
         .data;
   }

   @Nonnull
   @MappedMethod
   public ActionResult onUse2(org.mtr.mapping.holder.BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      return new ActionResult(InteractionResult.PASS);
   }

   @Deprecated
   protected InteractionResult useWithoutItem(
      BlockState state, Level level, net.minecraft.core.BlockPos pos, Player player, net.minecraft.world.phys.BlockHitResult hit
   ) {
      return (InteractionResult)this.onUse2(
            new org.mtr.mapping.holder.BlockState(state),
            new World(level),
            new BlockPos(pos),
            new PlayerEntity(player),
            Hand.MAIN_HAND,
            new BlockHitResult(hit)
         )
         .data;
   }

   @Deprecated
   protected InteractionResult useItemOn(
      net.minecraft.world.item.ItemStack stack,
      BlockState state,
      Level level,
      net.minecraft.core.BlockPos pos,
      Player player,
      InteractionHand hand,
      net.minecraft.world.phys.BlockHitResult hit
   ) {
      InteractionResult result = (InteractionResult)this.onUse2(
            new org.mtr.mapping.holder.BlockState(state),
            new World(level),
            new BlockPos(pos),
            new PlayerEntity(player),
            Hand.convert(hand),
            new BlockHitResult(hit)
         )
         .data;
      return (InteractionResult)(result instanceof Fail ? InteractionResult.PASS : result);
   }

   @MappedMethod
   public void onEntityCollision2(org.mtr.mapping.holder.BlockState state, World world, BlockPos pos, Entity entity) {
   }

   @Deprecated
   protected void entityInside(
      BlockState state,
      Level level,
      net.minecraft.core.BlockPos pos,
      net.minecraft.world.entity.Entity entity,
      InsideBlockEffectApplier effectApplier,
      boolean bl
   ) {
      this.onEntityCollision2(new org.mtr.mapping.holder.BlockState(state), new World(level), new BlockPos(pos), new Entity(entity));
      super.entityInside(state, level, pos, entity, effectApplier, bl);
   }

   @MappedMethod
   public void onPlaced2(World world, BlockPos pos, org.mtr.mapping.holder.BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
   }

   @Deprecated
   @Override
   public void setPlacedBy2(World world, BlockPos pos, org.mtr.mapping.holder.BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
      this.onPlaced2(world, pos, state, placer, itemStack);
      super.setPlacedBy2(world, pos, state, placer, itemStack);
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

   @MappedMethod
   public void neighborUpdate2(
      org.mtr.mapping.holder.BlockState state, World world, BlockPos pos, org.mtr.mapping.holder.Block block, BlockPos fromPos, boolean notify
   ) {
   }

   @Deprecated
   protected void neighborChanged(
      BlockState state, Level level, net.minecraft.core.BlockPos pos, Block neighborBlock, Orientation orientation, boolean movedByPiston
   ) {
      this.neighborUpdate2(
         new org.mtr.mapping.holder.BlockState(state),
         new World(level),
         new BlockPos(pos),
         new org.mtr.mapping.holder.Block(neighborBlock),
         new BlockPos(pos),
         movedByPiston
      );
      super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);
   }

   @Nonnull
   @MappedMethod
   public VoxelShape getCameraCollisionShape2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return new VoxelShape(
         super.getVisualShape((BlockState)state.data, (BlockGetter)world.data, (net.minecraft.core.BlockPos)pos.data, (CollisionContext)context.data)
      );
   }

   @Deprecated
   protected net.minecraft.world.phys.shapes.VoxelShape getVisualShape(
      BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos, CollisionContext context
   ) {
      return (net.minecraft.world.phys.shapes.VoxelShape)this.getCameraCollisionShape2(
            new org.mtr.mapping.holder.BlockState(state), new BlockView(level), new BlockPos(pos), new ShapeContext(context)
         )
         .data;
   }

   @Nonnull
   @MappedMethod
   public BlockRenderType getRenderType2(org.mtr.mapping.holder.BlockState state) {
      return BlockRenderType.convert(super.getRenderShape((BlockState)state.data));
   }

   @Deprecated
   protected RenderShape getRenderShape(BlockState state) {
      return this.getRenderType2(new org.mtr.mapping.holder.BlockState(state)).data;
   }

   @MappedMethod
   public void scheduledTick2(org.mtr.mapping.holder.BlockState state, ServerWorld world, BlockPos pos, Random random) {
   }

   @Deprecated
   protected void tick(BlockState state, ServerLevel level, net.minecraft.core.BlockPos pos, RandomSource random) {
      this.scheduledTick2(new org.mtr.mapping.holder.BlockState(state), new ServerWorld(level), new BlockPos(pos), new Random(random));
   }

   @Nonnull
   @MappedMethod
   public String getTranslationKey2() {
      return this.getDescriptionId2();
   }

   @MappedMethod
   public boolean emitsRedstonePower2(org.mtr.mapping.holder.BlockState state) {
      return super.isSignalSource((BlockState)state.data);
   }

   @Deprecated
   protected boolean isSignalSource(BlockState state) {
      return this.emitsRedstonePower2(new org.mtr.mapping.holder.BlockState(state));
   }

   @MappedMethod
   public int getWeakRedstonePower2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos, Direction direction) {
      return super.getSignal((BlockState)state.data, (BlockGetter)world.data, (net.minecraft.core.BlockPos)pos.data, direction.data);
   }

   @Deprecated
   protected int getSignal(BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos, net.minecraft.core.Direction direction) {
      return this.getWeakRedstonePower2(new org.mtr.mapping.holder.BlockState(state), new BlockView(level), new BlockPos(pos), Direction.convert(direction));
   }

   @MappedMethod
   public int getStrongRedstonePower2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos, Direction direction) {
      return super.getDirectSignal((BlockState)state.data, (BlockGetter)world.data, (net.minecraft.core.BlockPos)pos.data, direction.data);
   }

   @Deprecated
   protected int getDirectSignal(BlockState state, BlockGetter level, net.minecraft.core.BlockPos pos, net.minecraft.core.Direction direction) {
      return this.getStrongRedstonePower2(new org.mtr.mapping.holder.BlockState(state), new BlockView(level), new BlockPos(pos), Direction.convert(direction));
   }

   @Nonnull
   @MappedMethod
   public FluidState getFluidState2(org.mtr.mapping.holder.BlockState state) {
      return new FluidState(super.getFluidState((BlockState)state.data));
   }

   @Deprecated
   protected net.minecraft.world.level.material.FluidState getFluidState(BlockState state) {
      return (net.minecraft.world.level.material.FluidState)this.getFluidState2(new org.mtr.mapping.holder.BlockState(state)).data;
   }

   @MappedMethod
   public void setDefaultState2(org.mtr.mapping.holder.BlockState state) {
      this.registerDefaultState2(state);
   }

   @Nonnull
   @MappedMethod
   public VoxelShape getCullingShape2(org.mtr.mapping.holder.BlockState state, BlockView world, BlockPos pos) {
      return new VoxelShape(super.getOcclusionShape((BlockState)state.data));
   }

   @Deprecated
   protected net.minecraft.world.phys.shapes.VoxelShape getOcclusionShape(BlockState state) {
      return (net.minecraft.world.phys.shapes.VoxelShape)this.getCullingShape2(
            new org.mtr.mapping.holder.BlockState(state), new BlockView(EmptyBlockGetter.INSTANCE), BlockPos.of(0L)
         )
         .data;
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

   @MappedMethod
   public void randomDisplayTick2(org.mtr.mapping.holder.BlockState state, World world, BlockPos pos, Random random) {
      super.animateTick2(state, world, pos, random);
   }

   @MappedMethod
   @Override
   public void animateTick2(org.mtr.mapping.holder.BlockState state, World world, BlockPos pos, Random random) {
      this.randomDisplayTick2(state, world, pos, random);
   }

   @MappedMethod
   public void randomTick2(org.mtr.mapping.holder.BlockState state, ServerWorld world, BlockPos pos, Random random) {
      super.randomTick((BlockState)state.data, (ServerLevel)world.data, (net.minecraft.core.BlockPos)pos.data, (RandomSource)random.data);
   }

   @Deprecated
   protected void randomTick(BlockState state, ServerLevel level, net.minecraft.core.BlockPos pos, RandomSource random) {
      this.randomTick2(new org.mtr.mapping.holder.BlockState(state), new ServerWorld(level), new BlockPos(pos), new Random(random));
   }

   @MappedMethod
   public boolean hasRandomTicks2(org.mtr.mapping.holder.BlockState state) {
      return super.isRandomlyTicking((BlockState)state.data);
   }

   @Deprecated
   protected boolean isRandomlyTicking(BlockState state) {
      return this.hasRandomTicks2(new org.mtr.mapping.holder.BlockState(state));
   }
}
