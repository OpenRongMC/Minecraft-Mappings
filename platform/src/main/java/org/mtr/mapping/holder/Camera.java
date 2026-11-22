package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class Camera extends HolderBase<net.minecraft.client.Camera> {
   public Camera(net.minecraft.client.Camera data) {
      super(data);
   }

   @MappedMethod
   public boolean isDetached() {
      return ((net.minecraft.client.Camera)this.data).isDetached();
   }

   @MappedMethod
   public float xRot() {
      return ((net.minecraft.client.Camera)this.data).xRot();
   }

   @Nonnull
   @MappedMethod
   public BlockPos blockPosition() {
      return new BlockPos(((net.minecraft.client.Camera)this.data).blockPosition());
   }

   @MappedMethod
   public float yaw() {
      return ((net.minecraft.client.Camera)this.data).yaw();
   }

   @Nonnull
   @MappedMethod
   public Vector3d position() {
      return new Vector3d(((net.minecraft.client.Camera)this.data).position());
   }

   @Nonnull
   @MappedMethod
   public Vector3d getPos() {
      return this.position();
   }

   @MappedMethod
   public boolean isThirdPerson() {
      return this.isDetached();
   }

   @MappedMethod
   public float getYaw() {
      return this.yaw();
   }

   @MappedMethod
   public float getPitch() {
      return this.xRot();
   }

   @Nonnull
   @MappedMethod
   public BlockPos getBlockPos() {
      return this.blockPosition();
   }


}
