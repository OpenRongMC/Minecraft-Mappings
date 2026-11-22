package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.level.material.FlowingFluid;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class FlowableFluid extends HolderBase<FlowingFluid> {
   public FlowableFluid(FlowingFluid data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public FluidState getSource(boolean bl) {
      return new FluidState(((FlowingFluid)this.data).getSource(bl));
   }

   @Nonnull
   @MappedMethod
   public Fluid getSource() {
      return new Fluid(((FlowingFluid)this.data).getSource());
   }

   @Nonnull
   @MappedMethod
   public FluidState getStill(boolean falling) {
      return this.getSource(falling);
   }
}
