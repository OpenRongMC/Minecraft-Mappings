package org.mtr.mapping.holder;
import net.minecraft.world.item.context.UseOnContext;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.item.context.BlockPlaceContext;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ItemPlacementContext extends HolderBase<BlockPlaceContext> {
   public ItemPlacementContext(BlockPlaceContext data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Direction getHorizontalDirection() {
      return Direction.convert(((BlockPlaceContext)this.data).getHorizontalDirection());
   }

   @Nonnull
   @MappedMethod
   public ItemStack getItemInHand() {
      return new ItemStack(((BlockPlaceContext)this.data).getItemInHand());
   }

   @Nonnull
   @MappedMethod
   public Vector3d getClickLocation() {
      return new Vector3d(((BlockPlaceContext)this.data).getClickLocation());
   }

   @Nonnull
   @MappedMethod
   public PlayerEntity getPlayer() {
      return new PlayerEntity(((BlockPlaceContext)this.data).getPlayer());
   }

   @Nonnull
   @MappedMethod
   public BlockPos getClickedPos() {
      return new BlockPos(((BlockPlaceContext)this.data).getClickedPos());
   }

   @Nonnull
   @MappedMethod
   public World getLevel() {
      return new World(((BlockPlaceContext)this.data).getLevel());
   }

   @Nonnull
   @MappedMethod
   public Direction getClickedFace() {
      return Direction.convert(((BlockPlaceContext)this.data).getClickedFace());
   }

   @Nonnull
   @MappedMethod
   public Hand getHand() {
      return Hand.convert(((BlockPlaceContext)this.data).getHand());
   }

   @Nonnull
   @MappedMethod
   public BlockPos getBlockPos() {
      return this.getClickedPos();
   }

   @Nonnull
   @MappedMethod
   public Direction getPlayerFacing() {
      return this.getHorizontalDirection();
   }

   @Nonnull
   @MappedMethod
   public World getWorld() {
      return this.getLevel();
   }

   @Nonnull
   @MappedMethod
   public Direction getSide() {
      return this.getClickedFace();
   }

   @MappedMethod
   public float getPlayerYaw() {
      return this.getPlayer() == null ? 0.0F : this.getPlayer().getYRot();
   }

   @Nonnull
   @MappedMethod
   public Vector3d getHitPos() {
      return this.getClickLocation();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getStack() {
      return this.getItemInHand();
   }

@MappedMethod
   public ItemPlacementContext(ItemUsageContext useOnContext) {
      super(new BlockPlaceContext((UseOnContext)useOnContext.data));
   }


}
