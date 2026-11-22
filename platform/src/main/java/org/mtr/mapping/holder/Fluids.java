package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Fluids extends HolderBase<net.minecraft.world.level.material.Fluids> {
   public Fluids(net.minecraft.world.level.material.Fluids data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static FlowableFluid getWaterMapped() {
      return new FlowableFluid(net.minecraft.world.level.material.Fluids.WATER);
   }


}
