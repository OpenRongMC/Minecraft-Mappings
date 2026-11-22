package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.item.context.UseOnContext;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class ItemUsageContext extends HolderBase<UseOnContext> {
   public ItemUsageContext(UseOnContext data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Direction getHorizontalDirection() {
      return Direction.convert(((UseOnContext)this.data).getHorizontalDirection());
   }

   @Nonnull
   @MappedMethod
   public ItemStack getItemInHand() {
      return new ItemStack(((UseOnContext)this.data).getItemInHand());
   }

   @Nonnull
   @MappedMethod
   public BlockPos getClickedPos() {
      return new BlockPos(((UseOnContext)this.data).getClickedPos());
   }

   @Nonnull
   @MappedMethod
   public Vector3d getClickLocation() {
      return new Vector3d(((UseOnContext)this.data).getClickLocation());
   }

   @Nonnull
   @MappedMethod
   public World getLevel() {
      return new World(((UseOnContext)this.data).getLevel());
   }

   @Nonnull
   @MappedMethod
   public Direction getClickedFace() {
      return Direction.convert(((UseOnContext)this.data).getClickedFace());
   }

   @Nonnull
   @MappedMethod
   public PlayerEntity getPlayer() {
      return new PlayerEntity(((UseOnContext)this.data).getPlayer());
   }

   @Nonnull
   @MappedMethod
   public BlockPos getBlockPos() {
      return this.getClickedPos();
   }

   @Nonnull
   @MappedMethod
   public World getWorld() {
      return this.getLevel();
   }

   @Nonnull
   @MappedMethod
   public ItemStack getStack() {
      return this.getItemInHand();
   }

   @Nonnull
   @MappedMethod
   public Direction getPlayerFacing() {
      return this.getHorizontalDirection();
   }

   @Nonnull
   @MappedMethod
   public Direction getSide() {
      return this.getClickedFace();
   }

   @Nonnull
   @MappedMethod
   public Vector3d getHitPos() {
      return this.getClickLocation();
   }


}
