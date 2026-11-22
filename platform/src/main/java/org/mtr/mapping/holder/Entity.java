package org.mtr.mapping.holder;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.world.phys.Vec3;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Entity extends HolderBase<net.minecraft.world.entity.Entity> {
   public Entity(net.minecraft.world.entity.Entity data) {
      super(data);
   }

   @MappedMethod
   public final double getX() {
      return ((net.minecraft.world.entity.Entity)this.data).getX();
   }

   @MappedMethod
   public double getX(double d) {
      return ((net.minecraft.world.entity.Entity)this.data).getX(d);
   }

   @MappedMethod
   public double getZ(double d) {
      return ((net.minecraft.world.entity.Entity)this.data).getZ(d);
   }

   @MappedMethod
   public final double getZ() {
      return ((net.minecraft.world.entity.Entity)this.data).getZ();
   }

   @Nonnull
   @MappedMethod
   public UUID getUUID() {
      return ((net.minecraft.world.entity.Entity)this.data).getUUID();
   }

   @MappedMethod
   public void push(double d, double e, double f) {
      ((net.minecraft.world.entity.Entity)this.data).push(d, e, f);
   }

   @MappedMethod
   public void push(Vector3d vec3) {
      ((net.minecraft.world.entity.Entity)this.data).push((Vec3)vec3.data);
   }

   @MappedMethod
   public void push(Entity entity) {
      ((net.minecraft.world.entity.Entity)this.data).push((net.minecraft.world.entity.Entity)entity.data);
   }

   @Nonnull
   @MappedMethod
   public Vector3d position() {
      return new Vector3d(((net.minecraft.world.entity.Entity)this.data).position());
   }

   @Nonnull
   @MappedMethod
   public UUID getUuid() {
      return this.getUUID();
   }

   @Nonnull
   @MappedMethod
   public Vector3d getPos() {
      return this.position();
   }

   @MappedMethod
   public void addVelocity(double x, double y, double z) {
      this.push(x, y, z);
   }

}
