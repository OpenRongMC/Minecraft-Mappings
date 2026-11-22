package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BlockHitResult extends HolderBase<net.minecraft.world.phys.BlockHitResult> {
   public BlockHitResult(net.minecraft.world.phys.BlockHitResult data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Direction getDirection() {
      return Direction.convert(((net.minecraft.world.phys.BlockHitResult)this.data).getDirection());
   }

   @Nonnull
   @MappedMethod
   public Vector3d getLocation() {
      return new Vector3d(((net.minecraft.world.phys.BlockHitResult)this.data).getLocation());
   }

   @Nonnull
   @MappedMethod
   public Direction getSide() {
      return this.getDirection();
   }

   @Nonnull
   @MappedMethod
   public Vector3d getPos() {
      return this.getLocation();
   }




}
