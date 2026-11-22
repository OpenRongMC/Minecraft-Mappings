package org.mtr.mapping.holder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class FluidState extends HolderBase<net.minecraft.world.level.material.FluidState> {
   public FluidState(net.minecraft.world.level.material.FluidState data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Fluid getType() {
      return new Fluid(((net.minecraft.world.level.material.FluidState)this.data).getType());
   }

   @Nonnull
   @MappedMethod
   public Fluid getFluid() {
      return this.getType();
   }


}
