package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.phys.shapes.BooleanOp;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class BooleanBiFunction extends HolderBase<BooleanOp> {
   public BooleanBiFunction(BooleanOp data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static BooleanBiFunction getAndMapped() {
      return new BooleanBiFunction(BooleanOp.AND);
   }

   
}
