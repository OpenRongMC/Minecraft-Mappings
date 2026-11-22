package org.mtr.mapping.holder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class HitResult extends HolderBase<net.minecraft.world.phys.HitResult> {
   public HitResult(net.minecraft.world.phys.HitResult data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Vector3d getLocation() {
      return new Vector3d(((net.minecraft.world.phys.HitResult)this.data).getLocation());
   }

   @Nonnull
   @MappedMethod
   public Vector3d getPos() {
      return this.getLocation();
   }
}
