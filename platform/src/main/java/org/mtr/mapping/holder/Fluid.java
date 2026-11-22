package org.mtr.mapping.holder;

import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Fluid extends HolderBase<net.minecraft.world.level.material.Fluid> {
   public Fluid(net.minecraft.world.level.material.Fluid data) {
      super(data);
   }

   @MappedMethod
   public static Fluid cast(HolderBase<?> data) {
      return new Fluid((net.minecraft.world.level.material.Fluid)data.data);
   }

}
