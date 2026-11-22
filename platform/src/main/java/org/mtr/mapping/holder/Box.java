package org.mtr.mapping.holder;


import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Box extends HolderBase<AABB> {
   public Box(AABB data) {
      super(data);
   }

   @MappedMethod
   public boolean equals(@Nullable Object data) {
      return data instanceof HolderBase && ((AABB)this.data).equals(((HolderBase)data).data);
   }

   @MappedMethod
   public boolean contains(Vector3d vec3) {
      return ((AABB)this.data).contains((Vec3)vec3.data);
   }

   @MappedMethod
   public boolean contains(double d, double e, double f) {
      return ((AABB)this.data).contains(d, e, f);
   }

   @MappedMethod
   public boolean intersects(Box aABB) {
      return ((AABB)this.data).intersects((AABB)aABB.data);
   }

   @MappedMethod
   public boolean intersects(BlockPos blockPos) {
      return ((AABB)this.data).intersects((net.minecraft.core.BlockPos)blockPos.data);
   }

   @MappedMethod
   public boolean intersects(Vector3d vec3, Vector3d vec32) {
      return ((AABB)this.data).intersects((Vec3)vec3.data, (Vec3)vec32.data);
   }

   @MappedMethod
   public boolean intersects(double d, double e, double f, double g, double h, double i) {
      return ((AABB)this.data).intersects(d, e, f, g, h, i);
   }

   @MappedMethod
   public double getMaxXMapped() {
      return ((AABB)this.data).maxX;
   }

   @MappedMethod
   public double getMaxYMapped() {
      return ((AABB)this.data).maxY;
   }

   @MappedMethod
   public double getMinXMapped() {
      return ((AABB)this.data).minX;
   }

   @MappedMethod
   public double getMaxZMapped() {
      return ((AABB)this.data).maxZ;
   }

   @MappedMethod
   public double getMinYMapped() {
      return ((AABB)this.data).minY;
   }

   @MappedMethod
   public double getMinZMapped() {
      return ((AABB)this.data).minZ;
   }



@MappedMethod
   public Box(BlockPos blockPos) {
      super(new AABB((net.minecraft.core.BlockPos)blockPos.data));
   }

@MappedMethod
   public Box(double d, double e, double f, double g, double h, double i) {
      super(new AABB(d, e, f, g, h, i));
   }
}
