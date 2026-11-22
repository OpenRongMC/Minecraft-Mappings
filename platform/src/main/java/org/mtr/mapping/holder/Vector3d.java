package org.mtr.mapping.holder;
import net.minecraft.core.Vec3i;
import org.joml.Vector3fc;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.phys.Vec3;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Vector3d extends HolderBase<Vec3> {
   public Vector3d(Vec3 data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public Vector3d zRot(float f) {
      return new Vector3d(((Vec3)this.data).zRot(f));
   }

   @Nonnull
   @MappedMethod
   public Vector3d subtract(double d) {
      return new Vector3d(((Vec3)this.data).subtract(d));
   }

   @Nonnull
   @MappedMethod
   public Vector3d subtract(Vector3d vec3) {
      return new Vector3d(((Vec3)this.data).subtract((Vec3)vec3.data));
   }

   @Nonnull
   @MappedMethod
   public Vector3d subtract(double d, double e, double f) {
      return new Vector3d(((Vec3)this.data).subtract(d, e, f));
   }

   @Nonnull
   @MappedMethod
   public Vector3d yRot(float f) {
      return new Vector3d(((Vec3)this.data).yRot(f));
   }

   @Nonnull
   @MappedMethod
   public Vector3d add(double d, double e, double f) {
      return new Vector3d(((Vec3)this.data).add(d, e, f));
   }

   @Nonnull
   @MappedMethod
   public Vector3d add(double d) {
      return new Vector3d(((Vec3)this.data).add(d));
   }

   @Nonnull
   @MappedMethod
   public Vector3d add(Vector3d vec3) {
      return new Vector3d(((Vec3)this.data).add((Vec3)vec3.data));
   }

   @MappedMethod
   public double distanceTo(Vector3d vec3) {
      return ((Vec3)this.data).distanceTo((Vec3)vec3.data);
   }

   @MappedMethod
   public double distanceToSqr(Vector3d vec3) {
      return ((Vec3)this.data).distanceToSqr((Vec3)vec3.data);
   }

   @MappedMethod
   public double distanceToSqr(double d, double e, double f) {
      return ((Vec3)this.data).distanceToSqr(d, e, f);
   }

   @Nonnull
   @MappedMethod
   public Vector3d xRot(float f) {
      return new Vector3d(((Vec3)this.data).xRot(f));
   }

   @Nonnull
   @MappedMethod
   public static Vector3d getZeroMapped() {
      return new Vector3d(Vec3.ZERO);
   }

   @MappedMethod
   public double getYMapped() {
      return ((Vec3)this.data).y;
   }

   @MappedMethod
   public double getXMapped() {
      return ((Vec3)this.data).x;
   }

   @MappedMethod
   public double getZMapped() {
      return ((Vec3)this.data).z;
   }

   @Nonnull
   @MappedMethod
   public Vector3d rotateX(float pitch) {
      return this.xRot(pitch);
   }

   @Nonnull
   @MappedMethod
   public Vector3d rotateY(float yaw) {
      return this.yRot(yaw);
   }

   @Nonnull
   @MappedMethod
   public Vector3d rotateZ(float roll) {
      return this.zRot(roll);
   }

   @MappedMethod
   public double squaredDistanceTo(Vector3d other) {
      return this.distanceToSqr(other);
   }

   @MappedMethod
   public double squaredDistanceTo(double x, double y, double z) {
      return this.distanceToSqr(x, y, z);
   }

@MappedMethod
   public Vector3d(Vector3i vec3i) {
      super(new Vec3((Vec3i)vec3i.data));
   }

@MappedMethod
   public Vector3d(double d, double e, double f) {
      super(new Vec3(d, e, f));
   }

@MappedMethod
   public Vector3d(Vector3fc vector3fc) {
      super(new Vec3(vector3fc));
   }
}
