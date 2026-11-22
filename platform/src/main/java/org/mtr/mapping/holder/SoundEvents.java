package org.mtr.mapping.holder;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.mtr.mapping.annotation.MappedMethod;
import org.mtr.mapping.tool.HolderBase;

@ParametersAreNonnullByDefault
public final class SoundEvents extends HolderBase<net.minecraft.sounds.SoundEvents> {
   public SoundEvents(net.minecraft.sounds.SoundEvents data) {
      super(data);
   }

   @Nonnull
   @MappedMethod
   public static SoundEvent getExperienceOrbPickupMapped() {
      return new SoundEvent(net.minecraft.sounds.SoundEvents.EXPERIENCE_ORB_PICKUP);
   }

   @Nonnull
   @MappedMethod
   public static SoundEvent getBucketFillMapped() {
      return new SoundEvent(net.minecraft.sounds.SoundEvents.BUCKET_FILL);
   }

   @Nonnull
   @MappedMethod
   public static SoundEvent getEntityExperienceOrbPickupMapped() {
      return getExperienceOrbPickupMapped();
   }

   @Nonnull
   @MappedMethod
   public static SoundEvent getItemBucketFillMapped() {
      return getBucketFillMapped();
   }


}
