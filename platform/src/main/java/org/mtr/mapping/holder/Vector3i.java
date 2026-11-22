package org.mtr.mapping.holder;


import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.Vec3i;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Vector3i extends HolderBase<Vec3i> {
   public Vector3i(Vec3i data) {
      super(data);
   }

   @MappedMethod
   public static Vector3i cast(HolderBase<?> data) {
      return new Vector3i((Vec3i)data.data);
   }

   @MappedMethod
   public int getZ() {
      return ((Vec3i)this.data).getZ();
   }

   @MappedMethod
   public int getX() {
      return ((Vec3i)this.data).getX();
   }

   @MappedMethod
   public int getY() {
      return ((Vec3i)this.data).getY();
   }

@MappedMethod
   public Vector3i(int i, int j, int k) {
      super(new Vec3i(i, j, k));
   }
}
